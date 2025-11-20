package empsys.repository;

import java.util.List;

/**
 * リポジトリ層のインターフェース
 */
public interface EmpRepository {
    /**
     * 全社員情報を取得する
     */
    List<EmpEmployeePayrollInfo> findAll();

    /**
     * 指定された社員番号の社員情報を取得する
     */
    EmpEmployeePayrollInfo findById(String id);

    /**
     * 社員情報を新規登録する
     */
    int create(EmpEmployeeEntity entity);

    /**
     * 社員情報を更新する
     */
    int update(String id, EmpEmployeeEditablePart entity);

    /**
     * 指定された社員番号の社員情報を削除する
     */
    int delete(String id);

    /**
     * 全社員情報を削除する
     */
    int deleteAll();
}
