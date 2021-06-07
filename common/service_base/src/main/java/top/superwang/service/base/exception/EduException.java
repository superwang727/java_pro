package top.superwang.service.base.exception;


import lombok.Data;
import top.superwang.common.base.result.ResultCode;

@Data
public class  EduException extends RuntimeException{
/*
*
* 自定义异常，两个构造方法：
* 一个是自定义异常消息和错误码
* 一个是传已经新建好的枚举类型
*
* */

    private Integer code;


    public EduException(String message, Integer code) {
        super(message);
        this.code = code;
    }


    public EduException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();

    }

//    @Override
//    public String toString() {
//        return "EduException{" + "code--->" + code +"," +"message--->" + this.getMessage()+"}";
//    }
}
