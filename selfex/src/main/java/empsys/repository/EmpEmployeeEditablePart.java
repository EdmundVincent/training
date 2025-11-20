package empsys.repository;

/**
 * 社員情報の編集可能部分エンティティ（更新用）
 */
public class EmpEmployeeEditablePart {
    public int deptIndex;
    public int postIndex;
    public int baseSalary;
    public int allowance;
    public boolean hasHomeAllowance;
    public int deduction;

    // Getters
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
