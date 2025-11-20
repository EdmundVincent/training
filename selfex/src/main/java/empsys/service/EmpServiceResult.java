package empsys.service;

/**
 * サービス層の処理結果を表すクラス
 */
public class EmpServiceResult {

    // [cite: 924] 実行結果 (true=成功, false=失敗)
    public final boolean success;
    
    // [cite: 926] 実行メッセージ
    public final String message;

    // [cite: 929] コンストラクタ
    public EmpServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}