package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.response.MenuInfoResponse;

import java.util.List;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
public interface HomeService {

    List<MenuInfoResponse> queryMenuInfoAll();
}
