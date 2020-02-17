package com.vueshop.manager.dao.mapper;


import com.vueshop.manager.controller.http.request.UserInfoQueryRequest;
import com.vueshop.manager.controller.http.request.UserInfoRequest;
import com.vueshop.manager.dao.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名称:demo 描述: 创建人:ryw 创建时间:2018/12/25
 */
public interface UserInfoDao {

    UserInfo queryUserInfo(String username);

    List<UserInfo> queryUserPage(@Param("query") UserInfoQueryRequest userInfoRequest, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    Integer queryUserPageCount(@Param("query") UserInfoQueryRequest userInfoRequest);

    void updateUserInfo(@Param("userInfo")UserInfoRequest userInfoRequest);

	UserInfo queryUserInfoById(Long id);

	void deleteUserById(Long id);

	void insertUserInfo(@Param("userInfo")UserInfoRequest userInfoRequest);
}
