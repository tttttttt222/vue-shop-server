package com.vueshop.manager.controller;

import com.vueshop.manager.dao.dto.AuthInfoDto;
import com.vueshop.manager.dao.model.MenuInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/15
 */
@Slf4j
public class BaseController {

	@Autowired
	protected Map<String, AuthInfoDto> redisCache;

	private static final List<String> menuWhiteList = Arrays.asList("home", "menus", "login");

	public AuthInfoDto loginAuthorizationCheck(HttpServletRequest request) {
		AuthInfoDto authInfoDto = redisCache.get(request.getHeader("Authorization"));
		if (authInfoDto == null) {
			authInfoDto = new AuthInfoDto();
			authInfoDto.setErrMsg("未登录");
			authInfoDto.setNotAuth(true);
			return authInfoDto;
		}
		boolean isAccept = false;
		String requestURI = request.getRequestURI();
		for (MenuInfo menuInfo : authInfoDto.getMenuInfos()) {
			if (menuInfo.getPath() == null) {
				continue;
			}
			if (requestURI.contains(menuInfo.getPath())) {
				isAccept = true;
				break;
			}
		}
		for (String uri : menuWhiteList) {
			if (requestURI.contains(uri)) {
				isAccept = true;
				break;
			}
		}

		if (!isAccept) {
			authInfoDto.setErrMsg("没有权限访问该路径");
			authInfoDto.setNotAuth(true);
		}
		return authInfoDto;
	}


}
