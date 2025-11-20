package empsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import empsys.repository.EmpEmployeePayrollInfo;
import empsys.repository.EmpRepository;
import empsys.service.EmpEmployee;
import empsys.service.EmpReadServiceResult;
import empsys.service.EmpSysCreateService;
import empsys.service.EmpSysReadService;

/**
 * 社員管理システムのメインプログラム
 */
public class EmpSysMain {
    
    private static final String EMPLOYEE_DATA_FILE = "employeelist.txt";
    private static final String CHARSET = "UTF-8";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    // 役職インデックス定義
    private static final int POST_STAFF = 1;
    private static final int POST_SECTION_CHIEF = 2;
    private static final int POST_GENERAL_MANAGER = 3;
    
    // 部門インデックス定義
    private static final int DEPT_BUSINESS = 1;
    private static final int DEPT_MANAGEMENT = 2;
    private static final int DEPT_DEVELOPMENT = 3;
    private static final int DEPT_SALES = 4;

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("  社員管理システム");
        System.out.println("====================================");
        System.out.println();

        try {
            // リポジトリのダミー実装を作成
            EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl();
            
            // サービス層を初期化
            EmpSysCreateService createService = new EmpSysCreateService(repository);
            EmpSysReadService readService = new EmpSysReadService(repository);
            
            // ファイルから社員データを読み込んで登録
            List<EmployeeData> employeeDataList = readEmployeeDataFile(EMPLOYEE_DATA_FILE);
            
            System.out.println(employeeDataList.size() + "件の社員データをファイルから読み込みました。");
            System.out.println();
            
            // 各社員をシステムに登録
            System.out.println("【社員情報の登録】");
            for (EmployeeData data : employeeDataList) {
                int deptIndex = convertDeptName(data.dept);
                int postIndex = convertPostName(data.post);
                
                var result = createService.createEmployee(
                        data.id, data.name, data.gender,
                        data.birthday, data.hireDate,
                        deptIndex, postIndex,
                        data.baseSalary, data.allowance,
                        data.hasHomeAllowance, data.deduction
                );
                
                if (result.success) {
                    System.out.println("✓ " + result.message);
                } else {
                    System.out.println("✗ " + result.message);
                }
            }
            
            System.out.println();
            
            // 全社員情報を取得して表示
            System.out.println("【全社員情報の取得】");
            EmpReadServiceResult allResult = readService.getAllEmployee();
            if (allResult.success) {
                System.out.println(allResult.message);
                System.out.println();
                
                // 社員情報をテーブル形式で表示
                printEmployeeTable(allResult.employeeList);
            } else {
                System.out.println("エラー: " + allResult.message);
            }
            
        } catch (IOException e) {
            System.err.println("エラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ファイルから社員データを読み込む
     */
    private static List<EmployeeData> readEmployeeDataFile(String filename) throws IOException {
        List<EmployeeData> dataList = new ArrayList<>();
        File file = new File(filename);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file, java.nio.charset.StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = reader.readLine()) != null) {
                // ヘッダー行をスキップ
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                // 空行をスキップ
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                EmployeeData data = parseEmployeeData(line);
                if (data != null) {
                    dataList.add(data);
                }
            }
        }
        
        return dataList;
    }

    /**
     * CSVデータを解析してEmployeeDataオブジェクトを作成
     */
    private static EmployeeData parseEmployeeData(String csvLine) {
        try {
            String[] fields = csvLine.split(",");
            if (fields.length < 11) {
                return null;
            }
            
            EmployeeData data = new EmployeeData();
            data.id = fields[0].trim();
            data.name = fields[1].trim();
            data.gender = parseGender(fields[2].trim());
            data.birthday = LocalDate.parse(fields[3].trim(), DATE_FORMATTER);
            data.hireDate = LocalDate.parse(fields[4].trim(), DATE_FORMATTER);
            data.dept = fields[5].trim();
            data.post = fields[6].trim();
            data.baseSalary = Integer.parseInt(fields[7].trim());
            data.allowance = Integer.parseInt(fields[8].trim());
            data.hasHomeAllowance = "あり".equals(fields[9].trim());
            data.deduction = Integer.parseInt(fields[10].trim());
            
            return data;
        } catch (Exception e) {
            System.err.println("データ解析エラー: " + csvLine);
            return null;
        }
    }

    /**
     * 性別文字列をインデックスに変換
     */
    private static int parseGender(String genderStr) {
        if ("男".equals(genderStr)) {
            return 1; // MALE
        } else if ("女".equals(genderStr)) {
            return 2; // FEMALE
        }
        return 1; // デフォルト: MALE
    }

    /**
     * 部門名をインデックスに変換
     */
    private static int convertDeptName(String deptName) {
        switch (deptName) {
            case "事業部":
                return DEPT_BUSINESS;
            case "管理部":
                return DEPT_MANAGEMENT;
            case "開発部":
                return DEPT_DEVELOPMENT;
            case "営業部":
                return DEPT_SALES;
            default:
                return DEPT_BUSINESS;
        }
    }

    /**
     * 役職名をインデックスに変換
     */
    private static int convertPostName(String postName) {
        switch (postName) {
            case "従業員":
                return POST_STAFF;
            case "課長":
                return POST_SECTION_CHIEF;
            case "部長":
                return POST_GENERAL_MANAGER;
            default:
                return POST_STAFF;
        }
    }

    /**
     * 社員情報をテーブル形式で表示
     */
    private static void printEmployeeTable(ArrayList<EmpEmployee> employees) {
        System.out.println("社員番号 | 名前          | 役職");
        System.out.println("---------|---------------|----------");
        
        for (EmpEmployee emp : employees) {
            String postName = getPostName(emp.getClass().getSimpleName());
            System.out.printf("%s | %-13s | %s%n", emp.getId(), emp.getName(), postName);
        }
    }

    /**
     * クラス名から役職名を取得
     */
    private static String getPostName(String className) {
        switch (className) {
            case "EmpStaff":
                return "従業員";
            case "EmpSectionChief":
                return "課長";
            case "EmpGeneralManager":
                return "部長";
            default:
                return "不明";
        }
    }

    /**
     * 社員データを保持する内部クラス
     */
    static class EmployeeData {
        String id;
        String name;
        int gender;
        LocalDate birthday;
        LocalDate hireDate;
        String dept;
        String post;
        int baseSalary;
        int allowance;
        boolean hasHomeAllowance;
        int deduction;
    }

    /**
     * リポジトリのダミー実装
     */
    static class EmployeeRepositoryImpl implements EmpRepository {
        private List<EmpEmployeePayrollInfo> employees = new ArrayList<>();

        @Override
        public List<EmpEmployeePayrollInfo> findAll() {
            return new ArrayList<>(employees);
        }

        @Override
        public EmpEmployeePayrollInfo findById(String id) {
            for (EmpEmployeePayrollInfo emp : employees) {
                if (emp.id.equals(id)) {
                    return emp;
                }
            }
            return null;
        }

        @Override
        public int create(empsys.repository.EmpEmployeeEntity entity) {
            EmpEmployeePayrollInfo emp = new EmpEmployeePayrollInfo();
            emp.id = entity.id;
            emp.name = entity.name;
            emp.gender = entity.gender;
            emp.birthday = entity.birthday;
            emp.hireDate = entity.hireDate;
            emp.deptIndex = entity.deptIndex;
            emp.postIndex = entity.postIndex;
            emp.baseSalary = entity.baseSalary;
            emp.allowance = entity.allowance;
            emp.hasHomeAllowance = entity.hasHomeAllowance;
            emp.deduction = entity.deduction;
            emp.attendance = 20; // デフォルト値
            emp.sales = 0; // デフォルト値
            emp.expectedWorkdays = 20; // デフォルト値
            emp.homeAllowance = entity.hasHomeAllowance ? 40000 : 0;
            emp.payRaise = 0; // デフォルト値
            
            employees.add(emp);
            return 1;
        }

        @Override
        public int update(String id, empsys.repository.EmpEmployeeEditablePart entity) {
            EmpEmployeePayrollInfo emp = findById(id);
            if (emp == null) {
                return 0;
            }
            
            emp.deptIndex = entity.deptIndex;
            emp.postIndex = entity.postIndex;
            emp.baseSalary = entity.baseSalary;
            emp.allowance = entity.allowance;
            emp.hasHomeAllowance = entity.hasHomeAllowance;
            emp.deduction = entity.deduction;
            
            return 1;
        }

        @Override
        public int delete(String id) {
            EmpEmployeePayrollInfo emp = findById(id);
            if (emp == null) {
                return 0;
            }
            
            employees.remove(emp);
            return 1;
        }

        @Override
        public int deleteAll() {
            int count = employees.size();
            employees.clear();
            return count;
        }
    }
}
