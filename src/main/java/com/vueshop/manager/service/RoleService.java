package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.request.RoleInfoRequest;
import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.RoleInfoResponse;
import com.vueshop.manager.controller.http.response.RoleInfoWithRightsResponse;
import java.util.List;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/17
 */
public interface RoleService {

	List<RoleInfoWithRightsResponse> queryAllRoles();

	RoleInfoResponse insertRole(RoleInfoRequest roleInfoRequest);

	RoleInfoResponse queryRoleInfoById(long id);

	RoleInfoResponse updateRoleInfoById(RoleInfoRequest roleInfoRequest);

	RoleInfoResponse deleteRoleById(long id);

	RoleInfoResponse roleAddRights(long roleId,List<Long> rids);

	List<MenuInfoResponse> roleDeleteRights(long roleId, long rightId);
}
