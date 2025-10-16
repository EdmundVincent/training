//--------------------------------------------
// 分類：社員管理システム・サービス層
//
// 更新履歴：2025/08/10 新規作成 
// Copyright(c) 2025 IVIS All rights reserved.
//--------------------------------------------

package empsys.service;

import empsys.repository.EmpDeptEntity;
import empsys.repository.EmpEmployeeEntity;
import empsys.repository.EmpEmployeePayrollInfo;
import empsys.repository.EmpPostEntity;
import empsys.repository.EmpRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * サービス層 Serviceのテストドライバーの共通機能.
 */
@ExtendWith(MockitoExtension.class)  // Mockitoの機能を有効化
public class EmpSysServiceTestCommon extends EmpSysTestCommon {
    // EmpSys*Serviceに共通　3つの役職のテストデータ
    protected EmpEmployeePayrollInfo staff;     // ケース１
    protected EmpEmployeePayrollInfo secChief;  // ケース２
    protected EmpEmployeePayrollInfo manager;   // ケース３

    // 上のテストデータを格納するリスト
    protected ArrayList<EmpEmployeePayrollInfo> allEntities = new ArrayList<>();

    @Mock
    // protected EmpDatabase mockDatabase;
    protected EmpRepository mockRepository;

    /**
     * 各テストメソッドの前の初期化処理.
     * テストデータの初期化
     */
    @BeforeEach
    @Override
    public void beforeEach() {
        super.beforeEach(); // 必ず実行すること

        // テストデータの初期化
        staff = new EmpEmployeePayrollInfo();
        staff.deptIndex = EmpDeptEntity.DEPT_BUS;   // "事業部"
        staff.postIndex = EmpPostEntity.POST_STAFF;   // "従業員"
        staff.baseSalary = 3;
        staff.allowance = 4;
        staff.hasHomeAllowance = true;
        staff.deduction = 5;

        staff.id = "1001";
        staff.name = "任意 太郎";
        staff.gender = EmpEmployeeEntity.MALE;
        staff.birthday = LocalDate.of(1985, 5, 15);
        staff.hireDate = LocalDate.of(2020, 10, 1);

        staff.attendance = 6;
        staff.sales = 7;
        staff.expectedWorkdays = 8;
        staff.homeAllowance = 9;
        staff.payRaise = 10;

        secChief = new EmpEmployeePayrollInfo();
        secChief.deptIndex = EmpDeptEntity.DEPT_DEV;    // "開発部"
        secChief.postIndex = EmpPostEntity.POST_SECTIONCHIEF; // "課長"
        secChief.baseSalary = 23;
        secChief.allowance = 24;
        secChief.hasHomeAllowance = false;
        secChief.deduction = 25;

        secChief.id = "1002";
        secChief.name = "任意 女性";
        secChief.gender = EmpEmployeeEntity.FEMALE;
        secChief.birthday = LocalDate.of(1995, 5, 15);
        secChief.hireDate = LocalDate.of(2010, 10, 1);

        secChief.attendance = 26;
        secChief.sales = 27;
        secChief.expectedWorkdays = 28;
        secChief.homeAllowance = 29;
        secChief.payRaise = 210;

        manager = new EmpEmployeePayrollInfo();
        manager.deptIndex = EmpDeptEntity.DEPT_MGMT;    // "管理部"
        manager.postIndex = EmpPostEntity.POST_GENERALMANAGER;   // "部長"
        manager.baseSalary = 33;
        manager.allowance = 34;
        manager.hasHomeAllowance = true;
        manager.deduction = 35;

        manager.id = "1003";
        manager.name = "任意 部長";
        manager.gender = EmpEmployeeEntity.FEMALE;
        manager.birthday = LocalDate.of(1980, 5, 15);
        manager.hireDate = LocalDate.of(2005, 11, 3);

        manager.attendance = 36;
        manager.sales = 37;
        manager.expectedWorkdays = 38;
        manager.homeAllowance = 39;
        manager.payRaise = 310;

        // 全ての社員情報をリストに追加
        allEntities.clear();
        allEntities.add(staff);
        allEntities.add(secChief);
        allEntities.add(manager);
    }
} 
