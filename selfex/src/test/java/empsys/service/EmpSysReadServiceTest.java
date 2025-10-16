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

import empsys.repository.EmpEmployeePayrollInfo;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;


/**
 * EmpSysReadServiceのテストプログラム.
 */
public class EmpSysReadServiceTest extends EmpSysServiceTestCommon {
    @InjectMocks
    private EmpSysReadService targetService;

    @Test
    public void testConstructor() {
        assertNotNull(targetService, "インスタンス生成失敗");
    }

    @Test
    public void testGetAllEmployee01() {
        // モックにスタブ（振る舞い）を設定（成功パターン）
        when(mockRepository.findAll()).thenReturn(allEntities);

        // テスト対象のメソッドを呼び出す
        EmpReadServiceResult result = targetService.getAllEmployee();

        // 戻り値の検証
        assertTrue(result.success, "戻り値(success)の値が正しくない");
        assertEquals("全社員情報(3件)取得: 正常終了", result.message,
                "戻り値のメッセージが正しくない");

        // 取得した社員情報のリストがnullでないことを確認
        ArrayList<EmpEmployee> allemp = result.employeeList;
        assertNotNull(allemp, "取得した社員情報のリストがnull");
        // 3つの社員情報が取得できたことを確認
        assertEquals(3, allemp.size(), "取得した社員情報の件数が正しくない");

        // 各社員情報がnullでないことを確認
        assertNotNull(allemp.get(0), "取得した社員情報がnull");
        assertNotNull(allemp.get(1), "取得した社員情報がnull");
        assertNotNull(allemp.get(2), "取得した社員情報がnull");

        // 各役職のクラスのオブジェクトが作成されたことを確認
        assertEquals(EmpStaff.class, allemp.get(0).getClass(),
                "取得した社員情報のデータ型がEmpStaffではない");
        assertEquals(EmpSectionChief.class, allemp.get(1).getClass(),
                "取得した社員情報のデータ型がEmpSectionChiefではない");
        assertEquals(EmpGeneralManager.class, allemp.get(2).getClass(),
                "取得した社員情報のデータ型がEmpGeneralManagerではない");

        // オブジェクトのフィールド値の確認(private メソッドを使用)
        verifyFileds("EmpStaffのオブジェクト: ", allemp.get(0), staff);
        verifyFileds("EmpSectionChiefのオブジェクト: ", allemp.get(1), secChief);
        verifyFileds("EmpGeneralManagerのオブジェクト: ", allemp.get(2), manager);
    }

    @Test
    public void testGetAllEmployee02() {
        // モックにスタブ（振る舞い）を設定（データなしパターン）
        when(mockRepository.findAll()).thenReturn(new ArrayList<>());

        // テスト対象のメソッドを呼び出す
        EmpReadServiceResult result = targetService.getAllEmployee();

        // 戻り値の検証
        assertFalse(result.success, "戻り値(success)の値が正しくない");
        assertEquals("全社員情報取得: 社員情報が存在しない", result.message,
                "戻り値のメッセージが正しくない");
    }

    @Test
    public void testQueryEmployee01() {
        // モックにスタブ（振る舞い）を設定（成功パターン）
        when(mockRepository.findById(anyString())).thenReturn(staff);

        // テスト対象のメソッドを呼び出す
        EmpReadServiceResult result = targetService.queryEmployee(staff.id);
        
        // 戻り値確認
        assertTrue(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(1001)の情報取得、正常終了", result.message,
                "戻り値のメッセージが正しくない");

        // スタブに渡した引数の検証
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockRepository).findById(captor.capture());
        assertEquals(staff.id, captor.getValue(),
            "findById()に渡した社員番号が正しくない");

        // 社員情報が取得できたことを確認
        ArrayList<EmpEmployee> empList = result.employeeList;
        assertNotNull(empList, "取得した社員情報のリストがnull");
        assertEquals(1, empList.size(), "取得した社員情報の件数が正しくない");

        // 正しい役職クラスのオブジェクトが作成されたことを確認
        EmpEmployee emp = empList.get(0);
        assertEquals(EmpStaff.class, emp.getClass(),
                "取得した社員情報のデータ型がEmpStaffではない");

        // オブジェクトのフィールド値の確認(private メソッドを使用)
        verifyFileds("EmpStaffのオブジェクト: ", emp, staff);
    }

    @Test
    public void testQueryEmployee02() {
        // モックにスタブ（振る舞い）を設定（成功パターン）
        when(mockRepository.findById(anyString())).thenReturn(secChief);

        // テスト対象のメソッドを呼び出す
        EmpReadServiceResult result = targetService.queryEmployee(secChief.id);

        // 戻り値の確認
        assertTrue(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(1002)の情報取得、正常終了", result.message,
                "戻り値のメッセージが正しくない");

        // 社員情報が取得できたことを確認
        ArrayList<EmpEmployee> empList = result.employeeList;
        assertNotNull(empList, "取得した社員情報のリストがnull");
        assertEquals(1, empList.size(), "取得した社員情報の件数が正しくない");

        // 正しい役職クラスのオブジェクトが作成されたことを確認
        EmpEmployee emp = empList.get(0);
        assertEquals(EmpSectionChief.class, emp.getClass(),
                "取得した社員情報のデータ型がEmpSectionChiefではない");

        // オブジェクトのフィールド値の確認(private メソッドを使用)
        verifyFileds("EmpSectionChiefのオブジェクト: ", emp, secChief);
    }

    @Test
    public void testQueryEmployee03() {
        // モックにスタブ（振る舞い）を設定（成功パターン）
        when(mockRepository.findById(anyString())).thenReturn(manager);

        // テスト対象のメソッドを呼び出す
        EmpReadServiceResult result = targetService.queryEmployee(manager.id);

        // 戻り値の確認
        assertTrue(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(1003)の情報取得、正常終了", result.message,
                "戻り値のメッセージが正しくない");

        // 社員情報が取得できたことを確認
        ArrayList<EmpEmployee> empList = result.employeeList;
        assertNotNull(empList, "取得した社員情報のリストがnull");
        assertEquals(1, empList.size(), "取得した社員情報の件数が正しくない");

        // 正しい役職クラスのオブジェクトが作成されたことを確認
        EmpEmployee emp = empList.get(0);
        assertEquals(EmpGeneralManager.class, emp.getClass(),
                "取得した社員情報のデータ型がEmpGeneralManagerではない");

        // オブジェクトのフィールド値の確認(private メソッドを使用)
        verifyFileds("EmpGeneralManagerのオブジェクト: ", emp, manager);
    }

    @Test
    public void testQueryEmployee04() {
        // モックにスタブ（振る舞い）を設定（失敗パターン）
        when(mockRepository.findById(anyString())).thenReturn(null);

        // テスト対象の呼び出し　存在しない社員番号でのクエリ
        EmpReadServiceResult result = targetService.queryEmployee("99991");

        // 戻り値の確認
        assertFalse(result.success, "戻り値(success)の値が正しくない");
        assertEquals("社員番号(99991)の情報取得失敗: データが存在しない", result.message,
                "戻り値のメッセージが正しくない");
    }

    /**
     * 社員情報のフィールド値を確認する.
     *
     * @param target テスト対象が生成したデータ（出力）
     * @param stub スタブ側が作成した（テスト入力データ）
     */
    private void verifyFileds(String type, EmpEmployee target, EmpEmployeePayrollInfo stub) {
        assertEquals(target.getDeptIndex(), stub.deptIndex, type + ": deptIndexの値が正しくない");
        assertEquals(target.getBaseSalary(), stub.baseSalary, type + ": baseSalaryの値が正しくない");
        assertEquals(target.getAllowance(), stub.allowance, type + ": allowanceの値が正しくない");
        assertEquals(target.getHasHomeAllowance(), stub.hasHomeAllowance, type + ": hasHomeAllowanceの値が正しくない");
        assertEquals(target.getDeduction(), stub.deduction, type + ": deductionの値が正しくない");

        assertEquals(target.getId(), stub.id, type + ": idの値が正しくない");
        assertEquals(target.getName(), stub.name, type + ": nameの値が正しくない");
        assertEquals(target.getGenderIndex(), stub.gender, type + ": genderの値が正しくない");
        assertEquals(target.getBirthday(), stub.birthday, type + ": birthdayの値が正しくない");
        assertEquals(target.getHireDate(), stub.hireDate, type + ": hireDateの値が正しくない");

        assertEquals(target.getAttendance(), stub.attendance, type + ": attendanceの値が正しくない");
    }
}