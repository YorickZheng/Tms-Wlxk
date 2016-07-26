package com.wlxk.support.util;

/**
 * Created by malin on 2016/7/24.
 */
public class CommonProperty {

    /**
     * 记录删除状态
     */
    public static class DeleteFlag {
        /** 未删除 */
        public final static Boolean DELETE_ON = Boolean.TRUE;
        /** 已删除 */
        public final static Boolean DELETE_OFF = Boolean.FALSE;
    }

    /**
     * 交易单审核操作命令
     */
    public static class TradeBillReviewCommand {
        /** 审核中 */
        public final static Integer PENDING_COMMAND = 0;
        /** 审核 */
        public final static Integer AUDITED_COMMAND = 1;
    }

    /**
     * 交易单审核状态
     */
    public static class ReviewStatus {
        /** 未审核 */
        public final static Integer UNAUDITED = 0;
        /** 审核中 */
        public final static Integer PENDING = 1;
        /** 已审核 */
        public final static Integer AUDITED = 2;
        /** 审核失败 */
        public final static Integer AUDIT_FAILURE = 3;
    }
}
