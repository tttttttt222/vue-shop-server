package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.request.UserInfoQueryRequest;
import com.vueshop.manager.controller.http.request.UserInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.UserInfoQueryResponse;
import com.vueshop.manager.controller.http.response.UserInfoResponse;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
public interface UsersService {

    UserInfoQueryResponse queryUserPage(PageRequest<UserInfoQueryRequest> pageRequest);

    UserInfoResponse updateUserInfoById(UserInfoRequest userInfoRequest);

    UserInfoResponse queryUserInfoById(Long id);

    UserInfoResponse deleteUserById(Long id);

    UserInfoResponse insertUser(UserInfoRequest userInfoRequest);
}
