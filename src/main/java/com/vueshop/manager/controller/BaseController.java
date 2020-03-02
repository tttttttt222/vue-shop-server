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


	public AuthInfoDto getAuthInfo(HttpServletRequest request) {
		AuthInfoDto authInfoDto = redisCache.get(request.getHeader("Authorization"));
		return authInfoDto;
	}


}
