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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import empsys.repository.EmpEmployeeEditablePart;
import empsys.repository.EmpEmployeePayrollInfo;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

/**
 * EmpSysUpdateServiceのテストプログラム.
 */
public class EmpSysUpdateServiceTest extends EmpSysServiceTestCommon {

    @InjectMocks
    private EmpSysUpdateService targetService;

    @Test
    public void testConstructor() {
        assertNotNull(targetService, "インスタンス生成失敗");
    }

    @Test
    public void testUpdateEmployee01() {
        // モックにスタブ（振る舞い）を設定（成功パターン）
        when(mockRepository.findById(anyString())).thenReturn(staff); // スーパークラスのstaffを返す
        when(mockRepository.update(anyString(), any(EmpEmployeeEditablePart.class))).thenReturn(1);

        // テスト対象のメソッドを呼び出す（id以外のテストデータはスーパークラス中manager）
        EmpServiceResult result = targetService.updateEmployee(staff.id, manager.deptIndex,
                manager.postIndex, manager.baseSalary, manager.allowance, manager.hasHomeAllowance,
                manager.deduction);

        // 戻り値の確認
        assertTrue(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(1001)の情報更新、正常終了", result.message,
                "戻り値のメッセージが正しくない");

        // スタブのqueryEmployee()に渡した引数の検証
        ArgumentCaptor<String> selKeyCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockRepository).findById(selKeyCaptor.capture());
        assertEquals(staff.id, selKeyCaptor.getValue(),
                "findById()に渡した社員番号が正しくない");

        // スタブのupdateEmployee()に渡した引数の検証
        ArgumentCaptor<String> updKeyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<EmpEmployeeEditablePart> objCaptor = ArgumentCaptor.forClass(EmpEmployeeEditablePart.class);
        verify(mockRepository).update(updKeyCaptor.capture(), objCaptor.capture());
        assertEquals(staff.id, updKeyCaptor.getValue(),
                "update()に渡した社員番号が正しくない");
        assertNotNull(objCaptor.getValue(), "update()に渡したオブジェクトがnull");
        // assertTrue(objCaptor.getValue() instanceof EmpEmployeeEditablePart,
        //         "update()に渡した引数のデータ型が正しくない");
        // 正しいEmpEmployeeEditablePartのオブジェクトが作成されたことを確認
        EmpEmployeeEditablePart target = (EmpEmployeeEditablePart) objCaptor.getValue();
        checkFileds(target, manager);
    }

    @Test
    public void testUpdateEmployee02() {
        // モックにスタブ（振る舞い）を設定（失敗パターン）
        when(mockRepository.findById(anyString())).thenReturn(null); // nullを返す

        // テスト対象のメソッドを呼び出す
        EmpServiceResult result = targetService.updateEmployee("99999", manager.deptIndex,
                manager.postIndex, manager.baseSalary, manager.allowance, manager.hasHomeAllowance,
                manager.deduction);

        // 戻り値の確認
        assertFalse(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(99999)の情報更新失敗: データが存在しない", result.message,
                "戻り値のメッセージが正しくない");

        // updateが一度も呼ばれていないことを検証(失敗の場合、警告メッセージが表示される)
        verify(mockRepository, never()).update(anyString(), any(EmpEmployeeEditablePart.class));
    }

    @Test
    public void testUpdateEmployee03() {
        // モックにスタブ（振る舞い）を設定（検索が成功、更新が失敗のパターン）
        when(mockRepository.findById(anyString())).thenReturn(staff); // スーパークラスのstaffを返す
        when(mockRepository.update(anyString(), any(EmpEmployeeEditablePart.class))).thenReturn(0);

        // テスト対象のメソッドを呼び出す
        EmpServiceResult result = targetService.updateEmployee(staff.id, manager.deptIndex,
                manager.postIndex, manager.baseSalary, manager.allowance, manager.hasHomeAllowance,
                manager.deduction);

        // 戻り値の確認
        assertFalse(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(1001)の情報更新失敗: データが存在しない", result.message,
                "戻り値のメッセージが正しくない");
    }

    /**
     * 社員情報更新時のフィールド値を確認する.
     *
     * @param target テスト対象が生成したデータ（出力）
     * @param stub スタブ側が作成した（テスト入力データ）
     */
    private void checkFileds(EmpEmployeeEditablePart target, EmpEmployeePayrollInfo stub) {
        String prefix = "update()に渡したオブジェクトのフィールド: ";
        assertEquals(target.deptIndex, stub.deptIndex, prefix + "deptIndexの値が正しくない");
        assertEquals(target.baseSalary, stub.baseSalary, prefix + "baseSalaryの値が正しくない");
        assertEquals(target.allowance, stub.allowance, prefix + "allowanceの値が正しくない");
        assertEquals(target.hasHomeAllowance, stub.hasHomeAllowance, prefix + "hasHomeAllowanceの値が正しくない");
        assertEquals(target.deduction, stub.deduction, prefix + "deductionの値が正しくない");
    }        
}