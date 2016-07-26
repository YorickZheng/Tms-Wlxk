package com.wlxk.db;

import com.wlxk.support.util.DateUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.UUID;

/**
 * Created by malin on 2016/7/25.
 */
public class MySqlBigDataTest {

    @Test
    public void test100W() throws Exception {
        System.out.println("开始时间:" + DateUtil.dateToString(new Date()));
        String url = "jdbc:mysql://127.0.0.1/qmy-tms?user=root&password=root&useUnicode=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url);
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement("");
        String sqlPrefix = "INSERT INTO tms_trade_bill (id, trade_bill_no) VALUES";
        StringBuilder sqlSuffix = new StringBuilder();
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 10000; j++) {
                sqlSuffix.append("('" + UUID.randomUUID().toString() + "', 'TMS_" + i * j + "'),");
            }
            String sql = sqlPrefix + sqlSuffix.substring(0, sqlSuffix.length() - 1);
            ps.addBatch(sql);
            ps.executeBatch();
            conn.commit();
            sqlSuffix = new StringBuilder();
            System.out.println("第" + i +"次结束时间:" + DateUtil.dateToString(new Date()));
        }
        ps.close();
        conn.close();
        System.out.println("结束时间:" + DateUtil.dateToString(new Date()));
    }
}
