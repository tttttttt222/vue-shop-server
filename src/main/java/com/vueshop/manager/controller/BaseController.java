package com.vueshop.manager.controller;

import com.vueshop.manager.dao.dto.AuthInfoDto;
import com.vueshop.manager.dao.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
@Slf4j
public class BaseController {

    @Autowired
    protected Map<String, UserInfo> redisCache;

    public AuthInfoDto loginAuthorizationCheck(HttpServletRequest request) {
        AuthInfoDto authInfoDto = null;
        UserInfo userInfo = redisCache.get(request.getHeader("Authorization"));
        if (userInfo != null) {
            authInfoDto = new AuthInfoDto();
            authInfoDto.setUserInfo(userInfo);
        }
        return authInfoDto;
    }


}
