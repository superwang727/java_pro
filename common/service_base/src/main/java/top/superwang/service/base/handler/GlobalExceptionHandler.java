package top.superwang.service.base.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.superwang.common.base.result.R;


// 统一异常处理
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 异常处理
    @ResponseBody // 返回json格式
    public R error(Exception e){

//        e.printStackTrace(); // 异常跟踪栈,打印到控制台

        log.error(e.getMessage());
        return R.error();


    }
}
