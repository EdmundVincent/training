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
 * EmpSectionChiefのテストプログラム.
 */
public class EmpSectionChiefTest extends EmpSysTestCommon {
    LocalDate today = LocalDate.now(); // 現在
    int bus = EmpDeptEntity.DEPT_BUS; // "事業部";
    LocalDate birthday = LocalDate.of(today.getYear() - 24, 1, 1); // 誕生日は24年前の1月1日
    LocalDate hireDate1 = LocalDate.of(today.getYear() - 2, 4, 1); // ２年前から雇用

    EmpEmployee case1 = new EmpSectionChief("1", "任意 名前",
            EmpEmployeeEntity.MALE, birthday, hireDate1,
            bus, 50000, 2000, true, 5000, 20, 20, 15000, 6000);

    @Test
    void testSumSalary() {
        int salary = case1.sumSalary();
        assertEquals(71500, salary, "給与計算が正しくない");
    }
}
