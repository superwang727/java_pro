package top.superwang.service.edu.controller;


import org.springframework.web.bind.annotation.*;
import top.superwang.common.base.result.R;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class TeacherLogin {

//    登录
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

//    获取用户信息
    @GetMapping("info")
    public R userInfo(){

        return R.ok().data("name","admin")
                .data("roles","[admin,admin]")
                .data("avatar","https://passport.baidu.com/v6/ucenter");
    }


    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }

}
