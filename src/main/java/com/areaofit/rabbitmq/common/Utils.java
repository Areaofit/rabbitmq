package com.areaofit.rabbitmq.common;

public class Utils {

    /**
     * 请求成功返回结果
     * @param data 返回 VO
     * @return
     */
    public static ResponseData successData(Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        return responseData;
    }
}
