package empsys.service;

import java.time.LocalDate;

/**
 * 部長クラス
 * 詳細設計書 4.5 参照
 */
public class EmpGeneralManager extends EmpEmployee {

    // [cite: 858] 係数（基本給）
    protected static final double K_SALARY = 1.5;
    // [cite: 860] 特別手当
    protected static final int SPECIAL_ALLOWANCE = 20000;

    /**
     * コンストラクタ [cite: 863]
     */
    public EmpGeneralManager(String id, String name, int gender, LocalDate birthday, LocalDate hireDate, int dept,
            int baseSalary, int allowance, boolean hasHomeAllowance, int deduction, int attendance,
            int expectedWorkdays, int homeAllowance, int payRaise) {
        // [cite: 880] 基底クラスの初期化
        super(id, name, gender, birthday, hireDate, dept, baseSalary, allowance, hasHomeAllowance, deduction, attendance,
                expectedWorkdays, homeAllowance, payRaise);
    }

    /**
     * 当月の月給を計算する [cite: 883]
     */
    @Override
    public int sumSalary() {
        // [cite: 886] 月給 = 基本給 * 係数 + 各手当 + 特別手当 + 勤続年数 * 昇給額 - 控除額
        int salary = (int) ((this.getBaseSalary() * K_SALARY) 
                + this.getAllowance() 
                + SPECIAL_ALLOWANCE
                + (this.calcServiceYears() * this.payRaise) 
                - this.getDeduction());
        
        return salary;
    }
}