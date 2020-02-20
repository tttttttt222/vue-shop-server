package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.dao.dto.AuthInfoDto;
import com.vueshop.manager.service.RightsService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/17
 */
@RestController
@RequestMapping("api/private/v1/")
@Slf4j
public class RightsController extends BaseController {


	@Autowired
	private RightsService rightsService;

	@RequestMapping("menus")
	@ResponseBody
	public HttpResponse<List<MenuInfoResponse>> getMenus(HttpServletRequest request) {
		HttpResponse<List<MenuInfoResponse>> httpResponse = new HttpResponse<>(new Meta());

		AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
		if (authInfoDto.isNotAuth()) {
			httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		List<MenuInfoResponse> menuInfoResponses = rightsService.queryMenuInfoAll();

		httpResponse.setData(menuInfoResponses);
		httpResponse.getMeta().setMsg("获取菜单列表成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


	@PostMapping("right/{type}")
	@ResponseBody
	public HttpResponse<List<MenuInfoResponse>> getAllRightsInfo(HttpServletRequest request, @PathVariable String type) {
		HttpResponse<List<MenuInfoResponse>> httpResponse = new HttpResponse<>(new Meta());
		AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
		if (authInfoDto.isNotAuth()) {
			httpResponse.getMeta().setMsg(authInfoDto.getErrMsg());
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}
		List<MenuInfoResponse> menuInfoResponses = null;
		if (type.equals("list")) {
			menuInfoResponses = rightsService.getAllRightsInfoNormal();
		} else if (type.equals("tree")) {
			menuInfoResponses = rightsService.getAllRightsInfoTree();
		}

		if (menuInfoResponses == null) {
			httpResponse.getMeta().setMsg("获取权限列表成功");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(menuInfoResponses);
		httpResponse.getMeta().setMsg("获取权限列表失败");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


}
