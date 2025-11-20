package empsys.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 従業員クラス
 * 詳細設計書 4.3 参照
 */
public class EmpStaff extends EmpEmployee {

    // [cite: 780] 係数（売上高）
    protected static final double K_SALES = 0.02;
    // [cite: 782] 税金控除免除限度額
    protected static final int TAX_FREE_LIMIT = 10000;
    // [cite: 784] 試用期間の給料差し引き額
    protected static final int DEDUCTION_PROBATION = 30000;

    // [cite: 786] 当月売上高
    protected int sales = 0;

    /**
     * コンストラクタ [cite: 789]
     */
    public EmpStaff(String id, String name, int gender, LocalDate birthday, LocalDate hireDate, int dept,
            int baseSalary, int allowance, boolean hasHomeAllowance, int deduction, int attendance, int sales,
            int expectedWorkdays, int homeAllowance, int payRaise) {
        // [cite: 806] 基底クラス EmpEmployee の初期化
        super(id, name, gender, birthday, hireDate, dept, baseSalary, allowance, hasHomeAllowance, deduction, attendance,
                expectedWorkdays, homeAllowance, payRaise);
        // 売上高の初期化
        this.sales = sales;
    }

    /**
     * 試用期間か否かを返す [cite: 808]
     */
    public boolean isUnderProbation() {
        // [cite: 811] 入社日と今日の間の時間が３か月未満の場合は試用期間である
        if (this.getHireDate() == null) return false;
        long months = ChronoUnit.MONTHS.between(this.getHireDate(), LocalDate.now());
        return months < 3;
    }

    /**
     * 当月の月給を計算する [cite: 813]
     */
    @Override
    public int sumSalary() {
        // [cite: 816] (1) 基本計算: 基本給 + 各手当 + 売上高 * 係数 + 勤続年数 * 昇給額 - 控除額
        int salary = (int) (this.getBaseSalary() 
                + this.getAllowance() 
                + (this.sales * K_SALES) 
                + (this.calcServiceYears() * this.payRaise) 
                - this.getDeduction());

        // [cite: 817] (2) 勤続年数が1年未満の場合の控除額免除処理
        if (this.calcServiceYears() < 1) {
            if (this.getDeduction() > TAX_FREE_LIMIT) {
                // 控除額からTAX_FREE_LIMITの分を免除する (= 給与に加算して戻す)
                salary += TAX_FREE_LIMIT;
            }
        }

        // [cite: 818] (3) 試用期間の減額処理
        if (this.isUnderProbation()) {
            salary -= DEDUCTION_PROBATION;
        }

        return salary;
    }
}