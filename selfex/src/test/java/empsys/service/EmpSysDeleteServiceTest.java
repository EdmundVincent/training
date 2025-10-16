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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

/**
 * EmpSysDeleteServiceのテストプログラム.
 */
public class EmpSysDeleteServiceTest extends EmpSysServiceTestCommon {
    @InjectMocks
    private EmpSysDeleteService targetService;

    @Test
    public void testConstructor() {
        assertNotNull(targetService, "インスタンス生成失敗");
    }

    @Test
    public void testDeleteAllEmployee01() {
        // モックにスタブ（振る舞い）を設定（成功パターン）
        when(mockRepository.deleteAll()).thenReturn(100);

        // テスト対象のメソッドを呼び出す
        EmpServiceResult result = targetService.deleteAllEmployee();

        // 戻り値の検証
        assertTrue(result.success, "戻り値(success)の値が正しくない");
        assertEquals("全社員情報(100件)削除、正常終了", result.message,
                "戻り値のメッセージが正しくない");
    }

    @Test
    public void testDeleteEmployee01() {
        // モックにスタブ（振る舞い）を設定（成功パターン）
        when(mockRepository.delete(anyString())).thenReturn(1);

        // テスト対象のメソッドを呼び出す
        EmpServiceResult result = targetService.deleteEmployee("10011");

        // スタブに渡した引数の検証
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockRepository).delete(captor.capture());
        assertEquals("10011", captor.getValue(), "delete()に渡した引数が正しくない");

        // 戻り値の検証
        assertTrue(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(10011)の情報削除、正常終了", result.message,
                "戻り値のメッセージが正しくない");
    }

    @Test
    public void testDeleteEmployee02() {
        // モックにスタブ（振る舞い）を設定（失敗パターン）
        when(mockRepository.delete(anyString())).thenReturn(0);

        // テスト対象のメソッドを呼び出す
        EmpServiceResult result = targetService.deleteEmployee("10012");

        // 戻り値の検証
        assertFalse(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(10012)の情報削除失敗: データが存在しない", result.message,
                "戻り値のメッセージが正しくない");
    }
}