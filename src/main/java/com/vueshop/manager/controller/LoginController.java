package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.request.UserInfoRequest;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 项目名称:com.examples.demo.mapper
 * 描述:
 * 创建人:ryw
 * 创建时间:2018/5/23
 */
@RestController
@RequestMapping("api/private/v1/")
@Slf4j
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginService;


    @PostMapping("login")
    @ResponseBody
    public HttpResponse<UserInfoResponse> login(HttpServletRequest request, @RequestBody UserInfoRequest userInfoRequest) {
        loginAuthorizationCheck(request);
        HttpResponse<UserInfoResponse> httpResponse = new HttpResponse<>(new Meta());

        UserInfoResponse userInfoResponse = loginService.queryUserInfo(userInfoRequest);
        if (userInfoResponse == null || !userInfoResponse.getPassword().equals(userInfoRequest.getPassword())) {
            httpResponse.getMeta().setMsg("登录失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }
        httpResponse.setData(userInfoResponse);
        httpResponse.getMeta().setMsg("登录成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


}
