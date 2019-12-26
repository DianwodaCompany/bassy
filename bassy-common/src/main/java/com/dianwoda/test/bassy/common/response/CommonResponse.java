package com.dianwoda.test.bassy.common.response;

import com.dianwoda.test.bassy.common.enums.ErrorCode;
import lombok.Data;

/**
 * Date:2017/7/5
 * Comment:
 */
@Data
public class CommonResponse {

    private int errCode= ErrorCode.SUCCESS.getCode();
    private String msg=ErrorCode.SUCCESS.getMsg();
    private Object data= "";

    public CommonResponse(ErrorCode code, Object data){
        this.errCode=code.getCode();
        this.msg=code.getMsg();
        this.data=data;
    }

    public CommonResponse(ErrorCode code, String message, Object data){
        this.errCode=code.getCode();
        this.msg=message;
        this.data=data;
    }

    public static CommonResponse success(Object data){
        return new CommonResponse(ErrorCode.SUCCESS,data);
    }


    public static CommonResponse fail(String message){
        return new CommonResponse(ErrorCode.FAIL,message,null);
    }

    public static CommonResponse fail(){
        return new CommonResponse(ErrorCode.FAIL,null);
    }


}










