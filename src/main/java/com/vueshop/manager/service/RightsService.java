package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.request.MenuInfoRequest;
import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.dao.model.MenuInfo;
import java.util.List;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/17
 */
public interface RightsService {

	List<MenuInfoResponse> queryMenuInfoAll();

	List<MenuInfoResponse> getAllRightsInfoNormal();

	List<MenuInfoResponse> getAllRightsInfoTree();

	List<MenuInfoResponse> getAllRightsInfoTreeByRoleId(Long roleId);

	void addRightsByRoleId(Long roleId, List<Long> rids);

	void roleDeleteRights(Long roleId, Long rightId);

	void deleteRightsByRId(Long roleId);

	List<MenuInfo> queryMenuInfoByPid(Long roleId,Long rightId);

	List<MenuInfo> queryMenuInfoByRoleId(Long roleId);

	MenuInfoResponse addMenuInfo(MenuInfoRequest menuInfoRequest);

	MenuInfoResponse deleteMenuById(Long id);

	MenuInfoResponse updateMenuInfoById(MenuInfoRequest menuInfoRequest);
}
