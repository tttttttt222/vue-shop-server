package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.request.RoleInfoRequest;
import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.RoleInfoResponse;
import com.vueshop.manager.controller.http.response.RoleInfoWithRightsResponse;
import com.vueshop.manager.controller.http.response.UserInfoResponse;
import com.vueshop.manager.dao.mapper.RoleInfoDao;
import com.vueshop.manager.dao.model.RoleInfo;
import com.vueshop.manager.service.RightsService;
import com.vueshop.manager.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/17
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RightsService rightsService;

	@Autowired
	private RoleInfoDao roleInfoDao;


	@Override
	public List<RoleInfoWithRightsResponse> queryAllRoles() {
		List<RoleInfo> roleInfos = roleInfoDao.queryRoleInfoAll();
		List<RoleInfoWithRightsResponse> roleInfoWithRightsResponses=new ArrayList<>();
		for (RoleInfo roleInfo : roleInfos) {
			RoleInfoWithRightsResponse roleInfoWithRightsResponse = new RoleInfoWithRightsResponse();
			BeanUtils.copyProperties(roleInfo,roleInfoWithRightsResponse);
			roleInfoWithRightsResponses.add(roleInfoWithRightsResponse);
			roleInfoWithRightsResponse.setChildren(rightsService.getAllRightsInfoTree());
		}
		return roleInfoWithRightsResponses;
	}

	@Override
	public RoleInfoResponse insertRole(RoleInfoRequest roleInfoRequest) {
		RoleInfoResponse roleInfoResponse = new RoleInfoResponse();
		try {
			roleInfoRequest.setStatus(1);
			roleInfoDao.insertRoleOne(roleInfoRequest);
		} catch (Exception e) {
			log.error("添加失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(roleInfoRequest, roleInfoResponse);
		return roleInfoResponse;
	}

	@Override
	public RoleInfoResponse queryRoleInfoById(long id) {
		RoleInfoResponse roleInfoResponse = new RoleInfoResponse();
		RoleInfo roleInfo=roleInfoDao.queryRoleInfoById(id);
		BeanUtils.copyProperties(roleInfo, roleInfoResponse);
		return roleInfoResponse;
	}

	@Override
	public RoleInfoResponse updateRoleInfoById(RoleInfoRequest roleInfoRequest) {
		RoleInfoResponse roleInfoResponse = new RoleInfoResponse();
		if (roleInfoRequest.getStatus() == null) {
			roleInfoRequest.setStatus(0);
		}
		try {
			roleInfoDao.updateRoleInfo(roleInfoRequest);
		} catch (Exception e) {
			log.error("更新失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(roleInfoRequest, roleInfoResponse);
		return roleInfoResponse;
	}

	@Override
	public RoleInfoResponse deleteRoleById(long id) {
		RoleInfoResponse roleInfoResponse = new RoleInfoResponse();
		try {
			roleInfoDao.deleteRoleById(id);
		} catch (Exception e) {
			log.error("删除失败", e.getMessage());
			return null;
		}
		return roleInfoResponse;
	}

	@Override
	public RoleInfoResponse roleAddRights(long roleId,List<Long> rids) {
		rightsService.addRightsByRoleId(roleId,rids);
		return new RoleInfoResponse();
	}

	@Override
	public List<MenuInfoResponse> roleDeleteRights(long roleId, long rightId) {
		rightsService.roleDeleteRights(roleId,rightId);
		List<MenuInfoResponse> allRightsInfoTreeByRoleId = rightsService.getAllRightsInfoTreeByRoleId(roleId);
		return  allRightsInfoTreeByRoleId;
	}


}
