package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.request.UserInfoRequest;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import com.vueshop.manager.dao.mapper.UserInfoDao;
import com.vueshop.manager.dao.model.UserInfo;
import com.vueshop.manager.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private Map<String, UserInfo> redisCache;


    @Override
    public UserInfoResponse queryUserInfo(UserInfoRequest userInfoRequest) {
        UserInfoResponse userInfoResponse = null;
        UserInfo userInfo = userInfoDao.queryUserInfo(userInfoRequest.getUsername());
        if (userInfo == null) {
            return userInfoResponse;
        }
        userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userInfo, userInfoResponse);

        String token = UUID.randomUUID().toString().replace("-", "");
        userInfoResponse.setToken(token);
        redisCache.put(token, userInfo);
        return userInfoResponse;
    }
}
