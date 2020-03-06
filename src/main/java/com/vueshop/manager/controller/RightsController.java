package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.request.MenuInfoRequest;
import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.service.RightsService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class RightsController {


	@Autowired
	private RightsService rightsService;

	@RequestMapping("menus")
	@ResponseBody
	public HttpResponse<List<MenuInfoResponse>> getMenus(HttpServletRequest request) {
		HttpResponse<List<MenuInfoResponse>> httpResponse = new HttpResponse<>(new Meta());

		List<MenuInfoResponse> menuInfoResponses = rightsService.queryMenuInfoAll();

		httpResponse.setData(menuInfoResponses);
		httpResponse.getMeta().setMsg("获取菜单列表成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


	@GetMapping("rights/{type}")
	@ResponseBody
	public HttpResponse<List<MenuInfoResponse>> getAllRightsInfo(HttpServletRequest request,
			@PathVariable String type) {
		HttpResponse<List<MenuInfoResponse>> httpResponse = new HttpResponse<>(new Meta());

		List<MenuInfoResponse> menuInfoResponses = null;
		if (type.equals("list")) {
			menuInfoResponses = rightsService.getAllRightsInfoNormal();
		} else if (type.equals("tree")) {
			menuInfoResponses = rightsService.getAllRightsInfoTree();
		}

		if (menuInfoResponses == null) {
			httpResponse.getMeta().setMsg("获取权限列表失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(menuInfoResponses);
		httpResponse.getMeta().setMsg("获取权限列表成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}

	@DeleteMapping("rights/{id}")
	@ResponseBody
	public HttpResponse<MenuInfoResponse> deleteMenuById(HttpServletRequest request, @PathVariable long id) {
		HttpResponse<MenuInfoResponse> httpResponse = new HttpResponse<>(new Meta());

		MenuInfoResponse menuInfoResponse = rightsService.deleteMenuById(id);

		if (menuInfoResponse == null) {
			httpResponse.getMeta().setMsg("菜单删除失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(menuInfoResponse);
		httpResponse.getMeta().setMsg("菜单删除成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}

	@PostMapping("rights")
	@ResponseBody
	public HttpResponse<MenuInfoResponse> addMenu(HttpServletRequest request,
			@RequestBody MenuInfoRequest menuInfoRequest) {
		HttpResponse<MenuInfoResponse> httpResponse = new HttpResponse<>(new Meta());

		MenuInfoResponse menuInfoResponse = rightsService.addMenuInfo(menuInfoRequest);

		if (menuInfoResponse == null) {
			httpResponse.getMeta().setMsg("菜单创建成功");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(menuInfoResponse);
		httpResponse.getMeta().setMsg("菜单创建失败");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


	@PutMapping("rights/{id}")
	@ResponseBody
	public HttpResponse<MenuInfoResponse> updateMenuInfoById(HttpServletRequest request, @PathVariable long id,
			@RequestBody MenuInfoRequest menuInfoRequest) {
		HttpResponse<MenuInfoResponse> httpResponse = new HttpResponse<>(new Meta());

		menuInfoRequest.setId(id);
		if (menuInfoRequest.getStatus() == null) {
			menuInfoRequest.setStatus(1);
		}
		MenuInfoResponse menuInfoResponse = rightsService.updateMenuInfoById(menuInfoRequest);

		if (menuInfoResponse == null) {
			httpResponse.getMeta().setMsg("菜单更新失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(menuInfoResponse);
		httpResponse.getMeta().setMsg("菜单更新成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


}
