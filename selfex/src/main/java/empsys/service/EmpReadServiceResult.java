package empsys.service;

import java.util.ArrayList;

/**
 * サービス層の検索処理結果を表すクラス
 */
public class EmpReadServiceResult extends EmpServiceResult {

    // [cite: 938] 検索結果の社員情報リスト
    public final ArrayList<EmpEmployee> employeeList;

    // [cite: 941] コンストラクタ
    public EmpReadServiceResult(boolean success, String message, ArrayList<EmpEmployee> employees) {
        // [cite: 947] スーパークラスのコンストラクタを呼び出す
        super(success, message);
        // [cite: 948] フィールド employeeList に employees の値を設定する
        this.employeeList = employees;
    }
}