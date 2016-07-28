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
        public final static Boolean DELETE_ON = Boolean.FALSE;
        /** 已删除 */
        public final static Boolean DELETE_OFF = Boolean.TRUE;
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

    /**
     * 作废命令
     */
    public static class DisuseCommand {
        /** 作废 */
        public static final int DISUSE = 1;
        /** 取消作废 */
        public static final int DISUSE_CANCEL = 2;

        /** 命令集合 */
        public static final int[] COMMANDS = {DISUSE, DISUSE_CANCEL};
    }

    /**
     * 菜单查询命令
     */
    public static class MenuQueryCommand {
        /** 通过主键 */
        public static final int QUERY_BY_ID = 1;
        /** 通过父ID */
        public static final int QUERY_BY_PARENT_ID = 2;

        /** 命令集合 */
        public static final int[] COMMANDS = {QUERY_BY_ID, QUERY_BY_PARENT_ID};
    }

    /**
     * 自定义Http状态编码
     */
    public static class HttpCode {
        /** 成功 */
        public static final String SUCCESS_200 = "200000";

        /** 数据校验异常 */
        public static final String DATA_VALIDATION_EXCEPTION = "600000";
        /** 程序异常 */
        public static final String EXCEPTION = "601000";
    }
}
