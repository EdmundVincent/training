package empsys.repository;

import java.time.LocalDate;

/**
 * 社員情報エンティティ（新規作成用）
 */
public class EmpEmployeeEntity extends EmpEmployeeEditablePart {
    public static final int MALE = 1;
    public static final int FEMALE = 2;

    public String id;
    public String name;
    public int gender;
    public LocalDate birthday;
    public LocalDate hireDate;
    public int deptIndex;
    public int postIndex;
    public int baseSalary;
    public int allowance;
    public boolean hasHomeAllowance;
    public int deduction;

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public int getDeptIndex() {
        return deptIndex;
    }

    public int getPostIndex() {
        return postIndex;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public int getAllowance() {
        return allowance;
    }

    public boolean isHasHomeAllowance() {
        return hasHomeAllowance;
    }

    public int getDeduction() {
        return deduction;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setDeptIndex(int deptIndex) {
        this.deptIndex = deptIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public void setHasHomeAllowance(boolean hasHomeAllowance) {
        this.hasHomeAllowance = hasHomeAllowance;
    }

    public void setDeduction(int deduction) {
        this.deduction = deduction;
    }
}
