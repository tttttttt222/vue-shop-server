package com.vueshop.manager.controller;

import com.alibaba.fastjson.JSON;
import com.vueshop.manager.controller.http.request.RoleInfoRequest;
import com.vueshop.manager.controller.http.request.UserInfoQueryRequest;
import com.vueshop.manager.controller.http.request.UserInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.RoleInfoResponse;
import com.vueshop.manager.controller.http.response.RoleInfoWithRightsResponse;
import com.vueshop.manager.controller.http.response.UserInfoQueryResponse;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.dao.dto.AuthInfoDto;
import com.vueshop.manager.service.RoleService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
		if (authInfoDto == null) {
			httpResponse.getMeta().setMsg("未登录");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		List<RoleInfoWithRightsResponse> roleInfoWithRightsResponse= roleService.queryAllRoles();

		httpResponse.setData(roleInfoWithRightsResponse);
		httpResponse.getMeta().setMsg("获取角色列表成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}

	@PostMapping("roles")
	@ResponseBody
	public HttpResponse<RoleInfoResponse> addRole(HttpServletRequest request,@RequestBody RoleInfoRequest roleInfoRequest) {
		HttpResponse<RoleInfoResponse> httpResponse = new HttpResponse<>(new Meta());
		AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
		if (authInfoDto == null) {
			httpResponse.getMeta().setMsg("未登录");
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




}
