package com.vueshop.manager.config;

import com.vueshop.manager.dao.dto.AuthInfoDto;
import com.vueshop.manager.dao.model.MenuInfo;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/2
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {


	@Autowired
	protected Map<String, AuthInfoDto> redisCache;

	private static final List<String> menuWhiteList = Arrays.asList("home", "menus", "login");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
		if (authInfoDto.isNotAuth()) {
			return false;
		}
		return true;
	}


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
