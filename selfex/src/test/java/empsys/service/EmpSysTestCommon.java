//--------------------------------------------
// 分類：社員管理システム・サービス層
//
// 更新履歴：2025/08/10 新規作成 
// Copyright(c) 2025 IVIS All rights reserved.
//--------------------------------------------

package empsys.service;

import empsys.service.EmpSysTestCommon.StatsWatcher;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

/**
 * サービス層テストドライバーの共通機能.
 */
@ExtendWith(StatsWatcher.class)
public class EmpSysTestCommon {
    /**
     * テスト全体の初期化処理.
     */
    @BeforeAll
    public static void beforeAll() {
        System.out.println("Start test at: " + LocalDateTime.now());
    }

    /**
     * 各テストメソッドの前の初期化処理.
     * テストデータの初期化
     */
    @BeforeEach
    public void beforeEach() {
    }

    /**
     * 各テストメソッドの後の片付け処理.
     */
    @AfterEach
    public void afterEach() {
        StatsWatcher.setTargetName(this.getClass().getName());
    }

    /**
     * テスト全体が完了後の片付け処理.
     */
    @AfterAll
    public static void afterAll() {
        StatsWatcher.printStats();
    }

    // StatsWatcherをstatic inner classとして宣言
    static class StatsWatcher implements TestWatcher {
        private static int successCount = 0;
        private static int failureCount = 0;
        private static String targetName;

        @Override
        public void testSuccessful(ExtensionContext context) {
            successCount++;
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            failureCount++;
        }

        public static void setTargetName(String name) {
            targetName = name;
        }

        public static void printStats() {
            System.out.print("テスト対象: " + targetName + ";");
            System.out.println("成功: " + successCount + " 失敗: " + failureCount);
        }
        // 必要ならgetterで集計値取得
    }
}