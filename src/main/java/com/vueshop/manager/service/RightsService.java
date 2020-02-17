package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import java.util.List;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/17
 */
public interface RightsService {

	List<MenuInfoResponse> queryMenuInfoAll();

	List<MenuInfoResponse> getAllRightsInfoNormal();

	List<MenuInfoResponse> getAllRightsInfoTree();

	List<MenuInfoResponse> getAllRightsInfoTreeByRoleId(Long roleId);
}
