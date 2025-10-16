//--------------------------------------------
// 分類：社員管理システム・サービス層
//
// 更新履歴：2025/08/10 新規作成 
// Copyright(c) 2025 IVIS All rights reserved.
//--------------------------------------------

package empsys.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import empsys.repository.EmpEmployeeEntity;
import empsys.repository.EmpEmployeePayrollInfo;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

/**
 * EmpSysCreateServiceのテストプログラム.
 */
public class EmpSysCreateServiceTest extends EmpSysServiceTestCommon {
    @InjectMocks
    private EmpSysCreateService targetService;

    @Test
    public void testConstructor() {     // EMUT_06001
        assertNotNull(targetService, "インスタンス生成失敗");
    }

    @Test
    public void testCreateEmployee01() {
        // モックにスタブ（振る舞い）を設定（成功パターン）
        when(mockRepository.create(any(EmpEmployeeEntity.class))).thenReturn(1);

        // テスト対象のメソッドを呼び出す
        // テストデータは、スーパークラスEmpSysServiceTestCommon中のstaff
        EmpServiceResult result = targetService.createEmployee(staff.id, staff.name, staff.gender,
                staff.birthday, staff.hireDate, staff.deptIndex, staff.postIndex,
                staff.baseSalary, staff.allowance, staff.hasHomeAllowance, staff.deduction);

        // 戻り値の検証
        assertTrue(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(1001)の情報登録、正常終了", result.message,
                "戻り値のメッセージが正しくない");
        
        // スタブに渡した引数の検証
        ArgumentCaptor<EmpEmployeeEntity> captor = ArgumentCaptor.forClass(EmpEmployeeEntity.class);
        verify(mockRepository).create(captor.capture());
        // 引数はnullでないことを確認
        assertNotNull(captor.getValue(), "create()に渡した引数がnull");
        // 引数のデータ型確認
        // assertTrue(captor.getValue() instanceof EmpEmployeeEntity,
        //     "create()に渡した引数のデータ型が正しくない");
        // 正しいEmpEmployeeEntityのオブジェクトが作成されたことを確認
        EmpEmployeeEntity target = captor.getValue();               
        verifyFileds(target, staff);
    }

    @Test
    public void testCreateEmployee02() {
        // モックにスタブ（振る舞い）を設定（失敗パターン）
        when(mockRepository.create(any(EmpEmployeeEntity.class))).thenReturn(0);

        // テスト対象のメソッドを呼び出す
        // テストデータは、スーパークラスEmpSysServiceTestCommon中のstaff
        EmpServiceResult result = targetService.createEmployee(staff.id, staff.name, staff.gender,
                staff.birthday, staff.hireDate, staff.deptIndex, staff.postIndex,
                staff.baseSalary, staff.allowance, staff.hasHomeAllowance, staff.deduction);

        // 戻り値の検証
        assertFalse(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(1001)の情報登録失敗: データは既に存在する", result.message,
                "戻り値のメッセージが正しくない");
    }

    /**
     * 社員情報のフィールド値を確認する.
     *
     * @param target テスト対象が生成したデータ（出力）
     * @param stub スタブ側が作成した（テスト入力データ）
     */
    private void verifyFileds(EmpEmployeeEntity target, EmpEmployeePayrollInfo stub) {
        String prefix = "create()に渡したオブジェクトのフィールド: ";
        assertEquals(target.deptIndex, stub.deptIndex, prefix + "deptIndexの値が正しくない");
        assertEquals(target.baseSalary, stub.baseSalary, prefix + "baseSalaryの値が正しくない");
        assertEquals(target.allowance, stub.allowance, prefix + "allowanceの値が正しくない");
        assertEquals(target.hasHomeAllowance, stub.hasHomeAllowance, prefix + "hasHomeAllowanceの値が正しくない");
        assertEquals(target.deduction, stub.deduction, prefix + "deductionの値が正しくない");

        assertEquals(target.id, stub.id, prefix + "idの値が正しくない");
        assertEquals(target.name, stub.name, prefix + "nameの値が正しくない");
        assertEquals(target.gender, stub.gender, prefix + "genderの値が正しくない");
        assertEquals(target.birthday, stub.birthday, prefix + "birthdayの値が正しくない");
        assertEquals(target.hireDate, stub.hireDate, prefix + "hireDateの値が正しくない");
    }
}