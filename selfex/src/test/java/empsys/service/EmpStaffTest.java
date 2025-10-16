//--------------------------------------------
// 分類：社員管理システム・サービス層
//
// 更新履歴：2025/08/10 新規作成
// Copyright(c) 2025 IVIS All rights reserved.
//--------------------------------------------

package empsys.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import empsys.repository.EmpDeptEntity;
import empsys.repository.EmpEmployeeEntity;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 * EmpStaffのテスト.
 */
public class EmpStaffTest extends EmpSysTestCommon {
    LocalDate today = LocalDate.now(); // 現在

    int bus = EmpDeptEntity.DEPT_BUS; // "事業部";
    LocalDate birthday = LocalDate.of(today.getYear() - 24, 1, 1); // 誕生日は24年前の1月1日

    // テストケース設計
    // ケース　試用期間　勤務期間　控除額
    // 1　　 期間中　１年未満　税金控除免除限度額未満
    // 2　　 期間中　１年未満　税金控除免除限度額
    // 3　　 期間中　１年未満　税金控除免除限度額超え
    // 4　　 最終日　　↑
    // 5　　 期間後　　↑　　　税金控除免除限度額未満
    // 6　　 期間後　　↑　　　税金控除免除限度額
    // 7　　 期間後　　↑　　　税金控除免除限度額超え
    // 8　　 期間後　１年超

    EmpStaff case1 = new EmpStaff("1", "任意 名前", EmpEmployeeEntity.MALE, birthday, today.minusMonths(2),
            bus, 50000, 2000, true,
            EmpStaff.TAX_FREE_LIMIT - 1000, 18, 10000, 20, 15000, 6000);
    EmpStaff case2 = new EmpStaff("2", "任意 名前", EmpEmployeeEntity.MALE, birthday, today.minusMonths(2),
            bus, 50000, 2000, true,
            EmpStaff.TAX_FREE_LIMIT, 19, 20000, 20, 15000, 6000);
    EmpStaff case3 = new EmpStaff("3", "任意 名前", EmpEmployeeEntity.MALE, birthday, today.minusMonths(2),
            bus, 50000, 2000, true,
            EmpStaff.TAX_FREE_LIMIT + 1000, 20, 30000, 20, 15000, 6000);

    EmpStaff case4 = new EmpStaff("4", "任意 名前", EmpEmployeeEntity.MALE, birthday, today.minusMonths(3).plusDays(1), 
            bus, 50000, 2000, true,
            EmpStaff.TAX_FREE_LIMIT + 1000, 18, 10000, 20, 15000, 6000);

    EmpStaff case5 = new EmpStaff("5", "任意 名前", EmpEmployeeEntity.MALE, birthday, today.minusMonths(4),
            bus, 50000, 2000, true,
            EmpStaff.TAX_FREE_LIMIT - 1000, 18, 10000, 20, 15000, 6000);
    EmpStaff case6 = new EmpStaff("6", "任意 名前", EmpEmployeeEntity.MALE, birthday, today.minusMonths(4),
            bus, 50000, 2000, true,
            EmpStaff.TAX_FREE_LIMIT, 18, 10000, 20, 15000, 6000);
    EmpStaff case7 = new EmpStaff("7", "任意 名前", EmpEmployeeEntity.MALE, birthday, today.minusMonths(4),
            bus, 50000, 2000, true,
            EmpStaff.TAX_FREE_LIMIT + 1000, 18, 10000, 20, 15000, 6000);

    EmpStaff case8 = new EmpStaff("8", "任意 名前", EmpEmployeeEntity.MALE, birthday, today.minusMonths(13),
            bus, 50000, 2000, true,
            EmpStaff.TAX_FREE_LIMIT + 1000, 18, 10000, 20, 15000, 6000);

    @Test
    void testIsProbation01() {  // EMUT_02001
        assertEquals(true, case1.isUnderProbation(), "試用期間中のはず");
    }

    @Test
    void testIsProbation02() {  // EMUT_02002
        assertEquals(true, case4.isUnderProbation(), "試用期間中のはず");
    }

    @Test
    void testIsProbation03() {  // EMUT_02003
        assertEquals(false, case5.isUnderProbation(), "試用期間中ではないはず");
    }

    @Test
    void testSumSalary1() {     // EMUT_02004
        assertEquals(13200, case1.sumSalary(), "給与計算が正しくない");
    }

    @Test
    void testSumSalary2() {     // EMUT_02005
        assertEquals(12400, case2.sumSalary(), "給与計算が正しくない");
    }

    @Test
    void testSumSalary3() {     // EMUT_02006
        assertEquals(21600, case3.sumSalary(), "給与計算が正しくない");
    }

    @Test
    void testSumSalary4() {     // EMUT_02007
        assertEquals(21200, case4.sumSalary(), "給与計算が正しくない");
    }

    @Test
    void testSumSalary5() {     // EMUT_02008
        assertEquals(43200, case5.sumSalary(), "給与計算が正しくない");
    }

    @Test
    void testSumSalary6() {    // EMUT_02009
        assertEquals(42200, case6.sumSalary(), "給与計算が正しくない");
    }

    @Test
    void testSumSalary7() {    // EMUT_02010
        assertEquals(51200, case7.sumSalary(), "給与計算が正しくない");
    }

    @Test
    void testSumSalary8() {     // EMUT_02011
        assertEquals(47200, case8.sumSalary(), "給与計算が正しくない");
    }
}
