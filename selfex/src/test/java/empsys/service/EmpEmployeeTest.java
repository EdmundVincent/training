//--------------------------------------------
// 分類：社員管理システム・サービス層
//
// 更新履歴：2024/05/16 新規作成 
// Copyright(c) 2024 IVIS All rights reserved.
//--------------------------------------------

package empsys.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import empsys.repository.EmpDeptEntity;
import empsys.repository.EmpEmployeeEntity;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 * EmpEmployeeのテスト.
 */
public class EmpEmployeeTest extends EmpSysTestCommon {

    /**
     * 抽象クラスEmpEmployeeのテスト用、具象クラス.
     */
    class TempEmpEmployee extends EmpEmployee {

        public TempEmpEmployee(String num, String name, int gender, LocalDate birthday,
                LocalDate hireDate, int dept, int baseSalary, int allowance,
                boolean hasHomeAllowance, int deduction, int attendance,
                int expectedWorkdays, int homeAllowance, int payRaise) {
            super(num, name, gender, birthday, hireDate, dept, baseSalary, allowance, hasHomeAllowance, deduction,
                    attendance, expectedWorkdays, homeAllowance, payRaise);
        }

        public int sumSalary() {
            return 0;
        }
    }

    // テストケース設計
    // ケース　誕生日　　　　　入社日　　　部署　　性別
    // 1　　 25年前の昨日　　1年前の昨日　事業部　　男
    // 2　　 25年前の今日　　1年前の今日　開発部　　女
    // 3　　 25年前の明日　　1年前の明日　営業部　　男

    LocalDate today = LocalDate.now();

    EmpEmployee case1 = new TempEmpEmployee("00001",
            "日本 太郎", EmpEmployeeEntity.MALE, today.minusYears(25).minusDays(1),
            today.minusYears(1).minusDays(1), EmpDeptEntity.DEPT_BUS, 50000,
            2000, true, 1000, 19, 20, 13000, 6000);

    EmpEmployee case2 = new TempEmpEmployee("00002",
            "日本 花子", EmpEmployeeEntity.FEMALE, today.minusYears(25),
            today.minusYears(1), EmpDeptEntity.DEPT_DEV, 55000,
            2500, false, 1200, 20, 20, 15000, 7000);

    EmpEmployee case3 = new TempEmpEmployee("00003",
            "日本 太郎", EmpEmployeeEntity.MALE, today.minusYears(25).plusDays(1),
            today.minusYears(1).plusDays(1), EmpDeptEntity.DEPT_SALES, 50000,
            2000, true, 1000, 19, 20, 15000, 6000);

    @Test
    void testConstructor() {    // EMUT_01001
        assertNotNull(case1, "コンストラクタ作成失敗");
        assertNotNull(case2, "コンストラクタ作成失敗");
        assertNotNull(case3, "コンストラクタ作成失敗");
    }

    @Test
    void testGetNum() {    // EMUT_01002
        assertEquals("00001", case1.getId(), "社員番号が正しくない");
    }

    @Test
    void testGetName() {    // EMUT_01003
        assertEquals("日本 太郎", case1.getName(), "社員名が正しくない");
    }

    @Test
    void testGetGender() {    // EMUT_01004
        assertEquals(EmpEmployeeEntity.MALE, case1.getGenderIndex(),
            "性別インデックス(男性)が正しくない");
        assertEquals(EmpEmployeeEntity.FEMALE, case2.getGenderIndex(),
            "性別インデックス(女性)が正しくない");
    }

    @Test
    void testGetBirthday() {    // EMUT_01005
        assertEquals(today.minusYears(25).minusDays(1), case1.getBirthday(),
            "誕生日が正しくない");
        assertEquals(today.minusYears(25), case2.getBirthday(),
            "誕生日が正しくない");
    }

    @Test
    void testCalcAge01() {    // EMUT_01006
        assertEquals(25, case1.calcAge(), "年齢が正しくない");
    }

    @Test
    void testCalcAge02() {    // EMUT_01007
        assertEquals(25, case2.calcAge(), "年齢が正しくない");
    }

    @Test
    void testCalcAge03() {    // EMUT_01008
        assertEquals(24, case3.calcAge());
    }

    @Test
    void testGetHireDate() {    // EMUT_01009
        assertEquals(today.minusYears(1).minusDays(1), case1.getHireDate(),
            "入社日が正しくない");
    }

    @Test
    void testGetServiceYears01() {    // EMUT_01010
        assertEquals(1, case1.calcServiceYears(), "勤続年数が正しくない");
    }

    @Test
    void testGetServiceYears02() {    // EMUT_01011
        assertEquals(1, case2.calcServiceYears(), "勤続年数が正しくない");
    }

    @Test
    void testGetServiceYears03() {    // EMUT_01012
        assertEquals(0, case3.calcServiceYears());
    }

    @Test
    void testGetDept() {        // EMUT_01013
        assertEquals(EmpDeptEntity.DEPT_BUS, case1.getDeptIndex(),
            "部署インデックスが正しくない");
    }

    @Test
    void testGetBaseSalary() {        // EMUT_01014
        assertEquals(50000, case1.getBaseSalary(),
            "基本給が正しくない");
    }

    @Test
    void testGetAllowance() {   // EMUT_01015
        assertEquals(2000, case1.getAllowance(),
            "手当が正しくない");
    }

    @Test
    void testGetHasHomeAllowance() {    // EMUT_01016
        assertEquals(true, case1.getHasHomeAllowance(),
            "自宅手当の有無が正しくない");
    }

    @Test
    void testGetDeduction() {   // EMUT_01017
        assertEquals(1000, case1.getDeduction(),
            "控除額が正しくない");
    }

    @Test
    void testGetAttendance() {  // EMUT_01018
        assertEquals(19, case1.getAttendance(),
            "出勤日数が正しくない");
    }

    // @Test
    // void testSumSalaryDummy() {
    //     assertEquals(0, case1.sumSalary());
    // }
}
