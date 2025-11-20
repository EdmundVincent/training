package empsys.service;

import empsys.repository.EmpEmployeePayrollInfo;
import empsys.repository.EmpEmployeeEditablePart;
import empsys.repository.EmpRepository;

public class EmpSysUpdateService {

    private EmpRepository repository;

    public EmpSysUpdateService(EmpRepository repository) {
        this.repository = repository;
    }

    public EmpServiceResult updateEmployee(String id, int dept, int post, int baseSalary, int allowance, boolean hasHomeAllowance, int deduction) {
        
        EmpEmployeePayrollInfo entity = repository.findById(id);

        if (entity == null) {
            return new EmpServiceResult(false, "更新対象の社員が見つかりません。");
        }

        EmpEmployeeEditablePart updateEntity = new EmpEmployeeEditablePart();
        updateEntity.setDeptIndex(dept);
        updateEntity.setPostIndex(post);
        updateEntity.setBaseSalary(baseSalary);
        updateEntity.setAllowance(allowance);
        updateEntity.setHasHomeAllowance(hasHomeAllowance);
        updateEntity.setDeduction(deduction);

        int result = repository.update(id, updateEntity);

        if (result == 0) {
            return new EmpServiceResult(false, "社員情報の更新に失敗しました。");
        }

        return new EmpServiceResult(true, "社員情報を更新しました。");
    }
}
