package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.request.AssignRoleRequest;
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
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/15
 */
@RestController
@RequestMapping("api/private/v1/")
@Slf4j
public class UsersController extends BaseController {

    @Autowired
    private UsersService usersService;

    @GetMapping("users")
    @ResponseBody
    public HttpResponse<UserInfoQueryResponse> queryUserPage(HttpServletRequest request) {
        HttpResponse<UserInfoQueryResponse> httpResponse = new HttpResponse<>(new Meta());

        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }
        String pagenum = request.getParameter("pagenum");
        String pagesize = request.getParameter("pagesize");
        String query = request.getParameter("query");
        PageRequest<UserInfoQueryRequest> pageRequest = new PageRequest(Integer.parseInt(pagenum),
                Integer.parseInt(pagesize));
//		UserInfoQueryRequest userInfoQueryRequest = JSON.parseObject(query, UserInfoQueryRequest.class);
//		pageRequest.setQuery(userInfoQueryRequest == null ? new UserInfoQueryRequest() : userInfoQueryRequest);
        UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
        userInfoQueryRequest.setUsername(query);
        pageRequest.setQuery(userInfoQueryRequest);
        UserInfoQueryResponse userInfoPageResponse = usersService.queryUserPage(pageRequest);

        httpResponse.setData(userInfoPageResponse);
        httpResponse.getMeta().setMsg("获取菜单列表成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


    @PutMapping("users/{id}")
    @ResponseBody
    public HttpResponse<UserInfoResponse> updateUserInfoById(HttpServletRequest request, @PathVariable long id,
                                                             @RequestBody UserInfoRequest userInfoRequest) {
        HttpResponse<UserInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        userInfoRequest.setId(id);
		if (userInfoRequest.getStatus() == null) {
			userInfoRequest.setStatus(0);
		}
        UserInfoResponse userInfoResponse = usersService.updateUserInfoById(userInfoRequest);

        if (userInfoResponse == null) {
            httpResponse.getMeta().setMsg("更新失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(userInfoResponse);
        httpResponse.getMeta().setMsg("更新成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }

    //api/private/v1/users/1/state/false
    @PutMapping("users/{id}/state/{type}")
    @ResponseBody
    public HttpResponse<UserInfoResponse> updateUserStateById(HttpServletRequest request, @PathVariable long id,
                                                              @PathVariable boolean type) {
        HttpResponse<UserInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setId(id);
        userInfoRequest.setStatus(type ? 1 : 0);
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

    @GetMapping("users/{id}")
    @ResponseBody
    public HttpResponse<UserInfoResponse> queryUserById(HttpServletRequest request, @PathVariable long id) {
        HttpResponse<UserInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        UserInfoResponse userInfoResponse = usersService.queryUserInfoById(id);

        if (userInfoResponse == null) {
            httpResponse.getMeta().setMsg("查询失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(userInfoResponse);
        httpResponse.getMeta().setMsg("查询成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


    @DeleteMapping("users/{id}")
    @ResponseBody
    public HttpResponse<UserInfoResponse> deleteUserById(HttpServletRequest request, @PathVariable long id) {
        HttpResponse<UserInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        UserInfoResponse userInfoResponse = usersService.deleteUserById(id);

        if (userInfoResponse == null) {
            httpResponse.getMeta().setMsg("删除失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(userInfoResponse);
        httpResponse.getMeta().setMsg("删除成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


    @PostMapping("users")
    @ResponseBody
    public HttpResponse<UserInfoResponse> addUser(HttpServletRequest request,
                                                  @RequestBody UserInfoRequest userInfoRequest) {
        HttpResponse<UserInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        UserInfoResponse userInfoResponse = usersService.insertUser(userInfoRequest);

        if (userInfoResponse == null) {
            httpResponse.getMeta().setMsg("用户创建成功");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(userInfoResponse);
        httpResponse.getMeta().setMsg("用户创建失败");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


    @PutMapping("users/{id}/role")
    @ResponseBody
    public HttpResponse<UserInfoResponse> updateUserStateById(HttpServletRequest request, @PathVariable long id, @RequestBody AssignRoleRequest assignRoleRequest) {
        HttpResponse<UserInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }
        String rid = assignRoleRequest.getRid();
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setId(id);
        userInfoRequest.setRid(Integer.parseInt(rid));
        UserInfoResponse userInfoResponse = usersService.updateUserInfoById(userInfoRequest);

        if (userInfoResponse == null) {
            httpResponse.getMeta().setMsg("设置角色成功");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(userInfoResponse);
        httpResponse.getMeta().setMsg("设置角色失败");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }

}

