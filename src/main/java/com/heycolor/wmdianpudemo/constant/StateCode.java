package com.heycolor.wmdianpudemo.constant;

/**
 * @author LunKeee
 * 2024/11/26 4:43
 * 全局状态码
 */

public enum StateCode {

    SUCCESS(200,"success"),
    FAILED(400,"请求失败 - "),  //请求失败
    REQUEST_METHOD_ERROR(401,"请求失败 - "),  //请求方法不支持
    MISSING_PARAMETER(402,"请求失败 - "), //缺少参数
    Sql_Null_error(403,"请求失败 - "), //权限不足，提交参数不存在
    NOT_LOGGED_IN(404,"请求失败 - "),

    UNKNOWN_EXCEPTION(500,"请求失败 - "),
    SERVICE_UNRESPONSIVE(501,"请求失败 - "),
    DATABASE_UNRESPONSIVE(502,"请求失败 - ");

    //状态码
    int code;
    //状态说明
    String message;

    StateCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
