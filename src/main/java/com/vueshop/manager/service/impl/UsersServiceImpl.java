package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.request.UserInfoQueryRequest;
import com.vueshop.manager.controller.http.request.UserInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.UserInfoQueryResponse;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import com.vueshop.manager.dao.mapper.UserInfoDao;
import com.vueshop.manager.dao.model.UserInfo;
import com.vueshop.manager.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfoQueryResponse queryUserPage(PageRequest<UserInfoQueryRequest> pageRequest) {
        UserInfoQueryResponse res = new UserInfoQueryResponse();
        List<UserInfo> userInfos = userInfoDao.queryUserPage(pageRequest.getQuery(), pageRequest.getPagestart(), pageRequest.getPagesize());
        Integer count = userInfoDao.queryUserPageCount(pageRequest.getQuery());
        for (UserInfo userInfo : userInfos) {
            userInfo.setMg_state(userInfo.getStatus() == 1 ? true : false);
        }
        res.setTotal(count);
        res.setPagenum(pageRequest.getPagenum());
        res.setUsers(userInfos);
        return res;
    }

    @Override
    public UserInfoResponse updateUserInfoById(UserInfoRequest userInfoRequest) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        try {
            userInfoDao.updateUserInfo(userInfoRequest);
        } catch (Exception e) {
            log.error("更新失败", e.getMessage());
            return null;
        }
        BeanUtils.copyProperties(userInfoRequest, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse queryUserInfoById(Long id) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        UserInfo userInfo = userInfoDao.queryUserInfoById(id);
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse deleteUserById(Long id) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        try {
            userInfoDao.deleteUserById(id);
        } catch (Exception e) {
            log.error("删除失败", e.getMessage());
            return null;
        }
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse insertUser(UserInfoRequest userInfoRequest) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        try {
            userInfoRequest.setStatus(1);
            userInfoDao.insertUserInfo(userInfoRequest);
        } catch (Exception e) {
            log.error("添加失败", e.getMessage());
            return null;
        }
        BeanUtils.copyProperties(userInfoRequest, userInfoResponse);
        return userInfoResponse;
    }


}
