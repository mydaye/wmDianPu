package com.heycolor.wmdianpudemo.constant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;

/**
 * @author LunKeee
 * 2023/7/26 19:58
 * 页面响应entity（工具类）,前后端分离
 */

public class ReturnInfo extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    //存元素 和setData的区别是 这个方法可以指定key的值
    public ReturnInfo put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    //远程调用服务时，另一个服务也会返回R类型的数据，这个方法可以获取key为data的value值
    //利用fastjson进行反序列化
    public <T> T getData(TypeReference<T> typeReference) {
        Object data = get("data");	//默认是map
        String jsonString = JSON.toJSONString(data);//转为json字符串
        T t = JSON.parseObject(jsonString, typeReference);//转为对象
        return t;
    }
    //利用fastjson进行反序列化
    public <T> T getData(String key,TypeReference<T> typeReference) {
        Object data = get(key);	//默认是map
        //转为json字符串
        String jsonString = JSON.toJSONString(data);//转为json字符串
        //转成需要的对象
        T t = JSON.parseObject(jsonString, typeReference);//转为对象
        return t;
    }

    //获取code（状态码）的值
    public Integer getCode() {
        return (Integer) this.get("code");
    }

    //无参构造
    public ReturnInfo() {
        put("code", -1);
        put("message", "error");
        put("data","NULL");
    }

    //一般情况使用，携带code、错误信息、返回数据
    public static ReturnInfo res(StateCode stateCode, String message, Object data){
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.put("code", stateCode.getCode());
        returnInfo.put("message", stateCode.getMessage() + message);
        returnInfo.put("data", data);
        return returnInfo;
    }

    //一般情况使用，携带code、错误信息,无需data
    public static ReturnInfo res(StateCode stateCode, String message){
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.put("code", stateCode.getCode());
        returnInfo.put("message", stateCode.getMessage() + message);
        return returnInfo;
    }
}
