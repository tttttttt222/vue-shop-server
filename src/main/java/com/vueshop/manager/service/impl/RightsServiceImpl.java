package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import com.vueshop.manager.dao.mapper.MenuInfoDao;
import com.vueshop.manager.dao.mapper.RoleInfoDao;
import com.vueshop.manager.dao.model.MenuInfo;
import com.vueshop.manager.service.RightsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Service
@Slf4j
public class RightsServiceImpl implements RightsService {


    @Autowired
    private MenuInfoDao menuInfoDao;

    @Override
    public List<MenuInfoResponse> queryMenuInfoAll() {
        HashMap<Long, MenuInfoResponse> menuInfoMap = new HashMap<>();
        List<MenuInfo> menuInfos = menuInfoDao.queryMenuInfoAll();
        List<MenuInfoResponse> menuInfoResponses = new ArrayList<>();
        for (MenuInfo menuInfo : menuInfos) {
            MenuInfoResponse menuInfoResponse = new MenuInfoResponse();
            BeanUtils.copyProperties(menuInfo, menuInfoResponse);
            menuInfoMap.put(menuInfo.getId(),menuInfoResponse);
            menuInfoResponses.add(menuInfoResponse);
        }
        Iterator<MenuInfoResponse> iterator = menuInfoResponses.iterator();
        while (iterator.hasNext()){
            MenuInfoResponse next = iterator.next();
            Long pid = next.getPid();
            if(pid != 0){
                MenuInfoResponse menuInfoResponse = menuInfoMap.get(pid);
                menuInfoResponse.getChildren().add(next);
                iterator.remove();
            }
        }
        return menuInfoResponses;
    }

    @Override
    public List<MenuInfoResponse> getAllRightsInfoNormal() {
        List<MenuInfo> menuInfos = menuInfoDao.queryMenuInfo();
        List<MenuInfoResponse> menuInfoResponses = new ArrayList<>();
        for (MenuInfo menuInfo : menuInfos) {
            MenuInfoResponse menuInfoResponse = new MenuInfoResponse();
            BeanUtils.copyProperties(menuInfo, menuInfoResponse);
            menuInfoResponses.add(menuInfoResponse);
        }
        return menuInfoResponses;
    }

    @Override
    public List<MenuInfoResponse> getAllRightsInfoTree() {
        HashMap<Long, MenuInfoResponse> menuInfoMap = new HashMap<>();
        List<MenuInfo> menuInfos = menuInfoDao.queryMenuInfo();
        List<MenuInfoResponse> menuInfoResponses = new ArrayList<>();
        for (MenuInfo menuInfo : menuInfos) {
            MenuInfoResponse menuInfoResponse = new MenuInfoResponse();
            BeanUtils.copyProperties(menuInfo, menuInfoResponse);
            menuInfoMap.put(menuInfo.getId(),menuInfoResponse);
            menuInfoResponses.add(menuInfoResponse);
        }
        Iterator<MenuInfoResponse> iterator = menuInfoResponses.iterator();
        while (iterator.hasNext()){
            MenuInfoResponse next = iterator.next();
            Long pid = next.getPid();
            if(pid != 0){
                MenuInfoResponse menuInfoResponse = menuInfoMap.get(pid);
                menuInfoResponse.getChildren().add(next);
                iterator.remove();
            }
        }
        return menuInfoResponses;
    }



}