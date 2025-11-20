package empsys.service;

import java.util.ArrayList;
import java.util.List;

import empsys.repository.EmpEmployeePayrollInfo;
import empsys.repository.EmpRepository;

/**
 * サービス層の社員情報取得機能
 */
public class EmpSysReadService {

    private EmpRepository repository;

    // [cite: 469] 役職インデックス (基本設計書より)
    private static final int POST_STAFF = 1;
    private static final int POST_SECTION_CHIEF = 2;
    private static final int POST_GENERAL_MANAGER = 3;

    /**
     * コンストラクタ
     */
    public EmpSysReadService(EmpRepository repository) {
        this.repository = repository;
    }

    /**
     * [cite: 376] リポジトリ層の社員情報をサービス層の形式に変換する
     */
    protected EmpEmployee createEmployee(EmpEmployeePayrollInfo entity) {
        // [cite: 380] 引数 entity の postIndex を判定し、役職に応じたクラスのオブジェクトを生成する
        switch (entity.getPostIndex()) {
            case POST_STAFF:
                // [cite: 381] EmpStaff オブジェクトを生成
                return new EmpStaff(entity.getId(), entity.getName(), entity.getGender(), entity.getBirthday(),
                        entity.getHireDate(), entity.getDeptIndex(), entity.getBaseSalary(), entity.getAllowance(),
                        entity.isHasHomeAllowance(), entity.getDeduction(), entity.getAttendance(), entity.getSales(),
                        entity.getExpectedWorkdays(), entity.getHomeAllowance(), entity.getPayRaise());

            case POST_SECTION_CHIEF:
                // [cite: 382] EmpSectionChief オブジェクトを生成
                return new EmpSectionChief(entity.getId(), entity.getName(), entity.getGender(), entity.getBirthday(),
                        entity.getHireDate(), entity.getDeptIndex(), entity.getBaseSalary(), entity.getAllowance(),
                        entity.isHasHomeAllowance(), entity.getDeduction(), entity.getAttendance(),
                        entity.getExpectedWorkdays(), entity.getHomeAllowance(), entity.getPayRaise());

            case POST_GENERAL_MANAGER:
                // [cite: 383] EmpGeneralManager オブジェクトを生成
                return new EmpGeneralManager(entity.getId(), entity.getName(), entity.getGender(), entity.getBirthday(),
                        entity.getHireDate(), entity.getDeptIndex(), entity.getBaseSalary(), entity.getAllowance(),
                        entity.isHasHomeAllowance(), entity.getDeduction(), entity.getAttendance(),
                        entity.getExpectedWorkdays(), entity.getHomeAllowance(), entity.getPayRaise());
            
            default:
                return null;
        }
    }

    /**
     * [cite: 385] データベース上の全社員情報を取得する
     */
    public EmpReadServiceResult getAllEmployee() {
        // [cite: 389] リポジトリ層の全検索機能findAll()を実行
        List<EmpEmployeePayrollInfo> entities = repository.findAll();
        ArrayList<EmpEmployee> employees = new ArrayList<>();

        // [cite: 390] 取得結果が空の場合は処理結果フラグ（success）を false に設定
        if (entities == null || entities.isEmpty()) {
            return new EmpReadServiceResult(false, "全社員情報取得: 社員情報が存在しない", employees);
        }

        // [cite: 392] 取得した各レコードを createEmployee() を用いてサービス層形式に変換
        for (EmpEmployeePayrollInfo entity : entities) {
            EmpEmployee emp = createEmployee(entity);
            if (emp != null) {
                employees.add(emp);
            }
        }

        // [cite: 393] EmpReadServiceResult に格納して返却
        return new EmpReadServiceResult(true, "全社員情報(" + entities.size() + "件)取得: 正常終了", employees);
    }

    /**
     * [cite: 394] データベース上の社員番号idの社員情報を検索する
     */
    public EmpReadServiceResult queryEmployee(String id) {
        // [cite: 399] リポジトリ層のfindById(id)を実行
        EmpEmployeePayrollInfo entity = repository.findById(id);
        ArrayList<EmpEmployee> employees = new ArrayList<>();

        // [cite: 400] 取得結果が null の場合は処理結果フラグを false に設定
        if (entity == null) {
            return new EmpReadServiceResult(false, "社員番号(" + id + ")の情報取得失敗: データが存在しない", employees);
        }

        // 変換してリストに追加
        EmpEmployee emp = createEmployee(entity);
        if (emp != null) {
            employees.add(emp);
        }

        // [cite: 402] 処理結果フラグおよびメッセージを設定し返却
        return new EmpReadServiceResult(true, "社員番号(" + id + ")の情報取得、正常終了", employees);
    }
}