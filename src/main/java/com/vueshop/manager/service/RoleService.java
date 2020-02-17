package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.request.RoleInfoRequest;
import com.vueshop.manager.controller.http.response.RoleInfoResponse;
import com.vueshop.manager.controller.http.response.RoleInfoWithRightsResponse;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import java.util.List;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/17
 */
public interface RoleService {

	List<RoleInfoWithRightsResponse> queryAllRoles();

	RoleInfoResponse insertRole(RoleInfoRequest roleInfoRequest);
}
