package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.request.AddRidsRequest;
import com.vueshop.manager.controller.http.request.RoleInfoRequest;
import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.RoleInfoResponse;
import com.vueshop.manager.controller.http.response.RoleInfoWithRightsResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.dao.dto.AuthInfoDto;
import com.vueshop.manager.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/17
 */
@RestController
@RequestMapping("api/private/v1/")
@Slf4j
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;


    @GetMapping("roles")
    @ResponseBody
    public HttpResponse<List<RoleInfoWithRightsResponse>> queryAllRoles(HttpServletRequest request) {
        HttpResponse<List<RoleInfoWithRightsResponse>> httpResponse = new HttpResponse<>(new Meta());

        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        List<RoleInfoWithRightsResponse> roleInfoWithRightsResponse = roleService.queryAllRoles();

        httpResponse.setData(roleInfoWithRightsResponse);
        httpResponse.getMeta().setMsg("获取角色列表成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }

    @PostMapping("roles")
    @ResponseBody
    public HttpResponse<RoleInfoResponse> addRole(HttpServletRequest request,
                                                  @RequestBody RoleInfoRequest roleInfoRequest) {
        HttpResponse<RoleInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        RoleInfoResponse roleInfoResponse = roleService.insertRole(roleInfoRequest);

        if (roleInfoResponse == null) {
            httpResponse.getMeta().setMsg("角色创建成功");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(roleInfoResponse);
        httpResponse.getMeta().setMsg("角色创建失败");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }

    @GetMapping("roles/{id}")
    @ResponseBody
    public HttpResponse<RoleInfoResponse> queryUserById(HttpServletRequest request, @PathVariable long id) {
        HttpResponse<RoleInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        RoleInfoResponse roleInfoResponse = roleService.queryRoleInfoById(id);

        if (roleInfoResponse == null) {
            httpResponse.getMeta().setMsg("查询失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(roleInfoResponse);
        httpResponse.getMeta().setMsg("查询成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


    @PutMapping("roles/{id}")
    @ResponseBody
    public HttpResponse<RoleInfoResponse> updateRoleInfoById(HttpServletRequest request, @PathVariable long id,
                                                             @RequestBody RoleInfoRequest roleInfoRequest) {
        HttpResponse<RoleInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        roleInfoRequest.setId(id);
        RoleInfoResponse roleInfoResponse = roleService.updateRoleInfoById(roleInfoRequest);

        if (roleInfoResponse == null) {
            httpResponse.getMeta().setMsg("更新失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(roleInfoResponse);
        httpResponse.getMeta().setMsg("更新成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }

    @DeleteMapping("roles/{id}")
    @ResponseBody
    public HttpResponse<RoleInfoResponse> deleteRoleById(HttpServletRequest request, @PathVariable long id) {
        HttpResponse<RoleInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        RoleInfoResponse roleInfoResponse = roleService.deleteRoleById(id);

        if (roleInfoResponse == null) {
            httpResponse.getMeta().setMsg("删除失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(roleInfoResponse);
        httpResponse.getMeta().setMsg("删除成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }

    @PostMapping("roles/{roleId}/rights")
    @ResponseBody
    public HttpResponse<RoleInfoResponse> addRole(HttpServletRequest request, @PathVariable long roleId, @RequestBody AddRidsRequest rids) {
        HttpResponse<RoleInfoResponse> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }
        String[] split = rids.getRids().split(",");
        List<String> strings = Arrays.asList(split);
        ArrayList<Long> list = new ArrayList<>();
        for (String string : strings) {
            list.add(Long.valueOf(string));
        }
        RoleInfoResponse roleInfoResponse = roleService.roleAddRights(roleId, list);

        if (roleInfoResponse == null) {
            httpResponse.getMeta().setMsg("角色授权失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(roleInfoResponse);
        httpResponse.getMeta().setMsg("角色授权成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }

    @DeleteMapping("roles/{roleId}/rights/{rightId}")
    @ResponseBody
    public HttpResponse<List<MenuInfoResponse>> roleDeleteByRights(HttpServletRequest request, @PathVariable long roleId, @PathVariable long rightId) {
        HttpResponse<List<MenuInfoResponse>> httpResponse = new HttpResponse<>(new Meta());
        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto.isNotAuth()) {
            httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        List<MenuInfoResponse> menuInfoResponses = roleService.roleDeleteRights(roleId, rightId);

        if (menuInfoResponses == null) {
            httpResponse.getMeta().setMsg("角色删除权限失败");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        httpResponse.setData(menuInfoResponses);
        httpResponse.getMeta().setMsg("角色删除权限成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


}
