package com.vueshop.manager.controller;

import com.alibaba.fastjson.JSON;
import com.vueshop.manager.controller.http.request.UserInfoQueryRequest;
import com.vueshop.manager.controller.http.request.UserInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.UserInfoQueryResponse;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.dao.dto.AuthInfoDto;
import com.vueshop.manager.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
@RestController
@RequestMapping("api/private/v1/")
@Slf4j
public class UsersController extends BaseController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("users")
    @ResponseBody
    public HttpResponse<UserInfoQueryResponse> queryUserPage(HttpServletRequest request) {
        HttpResponse<UserInfoQueryResponse> httpResponse = new HttpResponse<>(new Meta());

        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto == null) {
            httpResponse.getMeta().setMsg("未登录");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }
        String pagenum = request.getParameter("pagenum");
        String pagesize = request.getParameter("pagesize");
        String query = request.getParameter("query");
        PageRequest<UserInfoQueryRequest> pageRequest = new PageRequest(Integer.parseInt(pagenum), Integer.parseInt(pagesize));
        UserInfoQueryRequest userInfoQueryRequest = JSON.parseObject(query, UserInfoQueryRequest.class);
        pageRequest.setQuery(userInfoQueryRequest == null ? new UserInfoQueryRequest() : userInfoQueryRequest);
        UserInfoQueryResponse userInfoPageResponse = usersService.queryUserPage(pageRequest);

        httpResponse.setData(userInfoPageResponse);
        httpResponse.getMeta().setMsg("获取菜单列表成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


    @PostMapping("users/update")
    @ResponseBody
    public HttpResponse<UserInfoResponse> updateUserInfoById(HttpServletRequest request, @RequestBody UserInfoRequest userInfoRequest) {
        HttpResponse<UserInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto == null) {
            httpResponse.getMeta().setMsg("未登录");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        UserInfoResponse userInfoResponse = usersService.updateUserInfoById(userInfoRequest);

        if (userInfoResponse == null) {
            httpResponse.getMeta().setMsg("设置失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(userInfoResponse);
        httpResponse.getMeta().setMsg("设置成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }




}

