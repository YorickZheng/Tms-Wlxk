package com.wlxk.support.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by malin on 2016/7/22.
 */
public class ResultsUtil {
    public static Map getSuccessResultMap(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("flag", true);
        result.put("data", data);
        result.put("code", CommonProperty.HttpCode.SUCCESS_200);
        return result;
    }

    public static Map getFailureResultMap(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("flag", false);
        result.put("data", data);
        result.put("code", CommonProperty.HttpCode.EXCEPTION);
        return result;
    }

    public static Map getFailureResultMap(Object data, String code) {
        Map<String, Object> result = new HashMap<>();
        result.put("flag", false);
        result.put("data", data);
        result.put("code", code);
        return result;
    }

}
