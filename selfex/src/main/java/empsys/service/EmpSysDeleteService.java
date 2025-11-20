package empsys.service;

import empsys.repository.EmpRepository;

/**
 * サービス層の社員情報削除機能
 */
public class EmpSysDeleteService {

    private EmpRepository repository;

    public EmpSysDeleteService(EmpRepository repository) {
        this.repository = repository;
    }

    /**
     * [cite: 347] 指定された社員番号の社員情報を削除する
     */
    public EmpServiceResult deleteEmployee(String id) {
        // [cite: 352] リポジトリ層のdelete()を実行
        int result = repository.delete(id);

        // [cite: 353] 戻り値が0の場合は削除失敗
        if (result == 0) {
            return new EmpServiceResult(false, "社員番号(" + id + ")の情報削除失敗: データが存在しない");
        }
        return new EmpServiceResult(true, "社員番号(" + id + ")の情報削除、正常終了");
    }

    /**
     * [cite: 358] データベース上の社員情報を全部削除する
     */
    public EmpServiceResult deleteAllEmployee() {
        // [cite: 361] リポジトリ層のdeleteAll()を実行
        int count = repository.deleteAll();

        // [cite: 363] 削除件数に応じて成功メッセージを作成
        return new EmpServiceResult(true, "全社員情報(" + count + "件)削除、正常終了");
    }
}