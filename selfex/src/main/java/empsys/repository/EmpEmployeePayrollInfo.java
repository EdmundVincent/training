package empsys.repository;

import java.time.LocalDate;

/**
 * 社員の給与情報を含む勤務情報エンティティ
 */
public class EmpEmployeePayrollInfo extends EmpEmployeeEntity {
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
    public int attendance;
    public int sales;
    public int expectedWorkdays;
    public int homeAllowance;
    public int payRaise;

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

    public int getAttendance() {
        return attendance;
    }

    public int getSales() {
        return sales;
    }

    public int getExpectedWorkdays() {
        return expectedWorkdays;
    }

    public int getHomeAllowance() {
        return homeAllowance;
    }

    public int getPayRaise() {
        return payRaise;
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

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setExpectedWorkdays(int expectedWorkdays) {
        this.expectedWorkdays = expectedWorkdays;
    }

    public void setHomeAllowance(int homeAllowance) {
        this.homeAllowance = homeAllowance;
    }

    public void setPayRaise(int payRaise) {
        this.payRaise = payRaise;
    }
}
