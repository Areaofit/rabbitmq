package com.areaofit.rabbitmq.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ResponseData", description = "请求返回结果")
public class ResponseData {

    @ApiModelProperty(value = "状态码")
    private int status = 200;

    @ApiModelProperty(value = "描述")
    private String desc = "success";

    @ApiModelProperty(value = "返回结果")
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
