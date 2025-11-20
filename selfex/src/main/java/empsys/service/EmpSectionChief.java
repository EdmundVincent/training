package empsys.service;

import java.time.LocalDate;

/**
 * 課長クラス
 * 詳細設計書 4.4 参照
 */
public class EmpSectionChief extends EmpEmployee {

    // [cite: 824] 係数（基本給）
    protected static final double K_SALARY = 1.25;

    /**
     * コンストラクタ [cite: 827]
     */
    public EmpSectionChief(String id, String name, int gender, LocalDate birthday, LocalDate hireDate, int dept,
            int baseSalary, int allowance, boolean hasHomeAllowance, int deduction, int attendance,
            int expectedWorkdays, int homeAllowance, int payRaise) {
        // [cite: 844] 基底クラスの初期化
        super(id, name, gender, birthday, hireDate, dept, baseSalary, allowance, hasHomeAllowance, deduction, attendance,
                expectedWorkdays, homeAllowance, payRaise);
    }

    /**
     * 当月の月給を計算する [cite: 847]
     */
    @Override
    public int sumSalary() {
        // [cite: 850] 月給 = 基本給 * 係数 + 各手当 + 勤続年数 * 昇給額 - 控除額
        int salary = (int) ((this.getBaseSalary() * K_SALARY) 
                + this.getAllowance() 
                + (this.calcServiceYears() * this.payRaise) 
                - this.getDeduction());
        
        return salary;
    }
}