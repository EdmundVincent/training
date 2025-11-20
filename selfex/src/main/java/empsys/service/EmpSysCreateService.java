package empsys.service;

import java.time.LocalDate;
import empsys.repository.EmpEmployeeEntity;
import empsys.repository.EmpRepository;

/**
 * サービス層の社員情報登録機能
 */
public class EmpSysCreateService {

    private EmpRepository repository;

    /**
     * コンストラクタ
     */
    public EmpSysCreateService(EmpRepository repository) {
        this.repository = repository;
    }

    /**
     * 社員情報を登録する
     */
    public EmpServiceResult createEmployee(String id, String name, int gender, LocalDate birthday, LocalDate hireDate,
            int dept, int post, int baseSalary, int allowance, boolean hasHomeAllowance, int deduction) {

        // パラメータを元にEmpEmployeeEntityオブジェクトを作成する
        EmpEmployeeEntity entity = new EmpEmployeeEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setGender(gender);
        entity.setBirthday(birthday);
        entity.setHireDate(hireDate);
        entity.setDeptIndex(dept);
        entity.setPostIndex(post);
        entity.setBaseSalary(baseSalary);
        entity.setAllowance(allowance);
        entity.setHasHomeAllowance(hasHomeAllowance);
        entity.setDeduction(deduction);

        // 作成したオブジェクトをリポジトリ層 create() 渡し
        int result = repository.create(entity);

        // create()の戻り値が0の場合は登録失敗
        if (result == 0) {
            return new EmpServiceResult(false, "社員番号(" + id + ")の情報登録失敗: データは既に存在する");
        }

        // それ以外の場合は登録成功
        return new EmpServiceResult(true, "社員番号(" + id + ")の情報登録、正常終了");
    }
}