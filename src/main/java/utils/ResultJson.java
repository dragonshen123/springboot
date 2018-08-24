package utils;

import java.io.Serializable;

public class ResultJson implements Serializable {
    private Integer status;
    private Integer code;
    private Object data;
    private String message;

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
