package empsys.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * [cite: 122] 社員情報の共通部分を提供する抽象クラス
 */
public abstract class EmpEmployee {

    //  詳細設計書 4.2 に基づくフィールド定義 (protected)
    protected String id;                // 社員番号
    protected String name;              // 名前
    protected int genderIndex;          // 性別
    protected LocalDate birthday;       // 生年月日
    protected LocalDate hireDate;       // 入社日
    protected int deptIndex;            // 所属部署
    protected int baseSalary;           // 基本給
    protected int allowance;            // 各手当
    protected boolean hasHomeAllowance; // 住宅手当の有無
    protected int deduction;            // 控除額
    protected int attendance;           // 当月出勤日数
    protected int expectedWorkdays;     // 当月標準勤務日数
    protected int homeAllowance;        // 住宅手当額
    protected int payRaise;             // 昇給額

    /**
     * [cite: 153] コンストラクタ
     */
    public EmpEmployee(String id, String name, int gender, LocalDate birthday, LocalDate hireDate, int dept, 
                       int baseSalary, int allowance, boolean hasHomeAllowance, int deduction, int attendance, 
                       int expectedWorkdays, int homeAllowance, int payRaise) {
        this.id = id;
        this.name = name;
        this.genderIndex = gender;
        this.birthday = birthday;
        this.hireDate = hireDate;
        this.deptIndex = dept;
        this.baseSalary = baseSalary;
        this.allowance = allowance;
        this.hasHomeAllowance = hasHomeAllowance;
        this.deduction = deduction;
        this.attendance = attendance;
        this.expectedWorkdays = expectedWorkdays;
        this.homeAllowance = homeAllowance;
        this.payRaise = payRaise;
    }

    // [cite: 170] 社員番号を返す
    public String getId() {
        return id;
    }

    // [cite: 173] 名前を返す
    public String getName() {
        return name;
    }

    // [cite: 176] 性別を返す
    public int getGenderIndex() {
        return genderIndex;
    }

    // [cite: 179] 生年月日を返す
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * [cite: 182] 年齢計算
     * 生年月日と今日の間の年数を求める
     */
    public int calcAge() {
        if (birthday == null) return 0;
        return (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }

    // [cite: 186] 入社日を返す
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * [cite: 189] 勤続年数計算
     * 入社日と今日の間の年数を求める（1年未満切り捨て）
     */
    public int calcServiceYears() {
        if (hireDate == null) return 0;
        return (int) ChronoUnit.YEARS.between(hireDate, LocalDate.now());
    }

    // [cite: 194] 所属部署を返す
    public int getDeptIndex() {
        return deptIndex;
    }

    // [cite: 197] 基本給を返す
    public int getBaseSalary() {
        return baseSalary;
    }

    // [cite: 200] 各手当を返す
    public int getAllowance() {
        return allowance;
    }

    // [cite: 203] 住宅手当の有無を返す
    public boolean getHasHomeAllowance() {
        return hasHomeAllowance;
    }

    // [cite: 206] 控除額を返す
    public int getDeduction() {
        return deduction;
    }

    // [cite: 209] 出勤日数を返す
    public int getAttendance() {
        return attendance;
    }

    /**
     * [cite: 212] 当月の月給を計算して返す（抽象メソッド）
     * 実装はサブクラスで行われる
     */
    public abstract int sumSalary();
}