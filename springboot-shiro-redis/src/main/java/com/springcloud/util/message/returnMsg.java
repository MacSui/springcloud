package com.springcloud.util.message;

import com.springcloud.util.enums.returnCode;

/**
 * Created by Sui, Chengbin on 2017-11-07.
 */
public class returnMsg {

    private returnCode Code;
    private Object Data;

    public returnMsg(returnCode Code)
    {
        this.Code=Code;
    }

    public returnMsg(returnCode Code,Object Data)
    {
        this.Code=Code;
        this.Data=Data;
    }

    public returnCode getCode() {
        return Code;
    }

    public void setCode(returnCode code) {
        Code = code;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }


}
