package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.request.MenuInfoRequest;
import com.vueshop.manager.controller.http.request.UserInfoRequest;
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
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/14
 */
@Service
@Slf4j
public class RightsServiceImpl implements RightsService {


	@Autowired
	private MenuInfoDao menuInfoDao;

	@Override
	public List<MenuInfoResponse> queryMenuInfoAll() {
		List<MenuInfo> menuInfos = menuInfoDao.queryMenuInfoAll();
		List<MenuInfoResponse> menuInfoResponses = getTreeConstructMenuInfoResponses(menuInfos);
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
		List<MenuInfo> menuInfos = menuInfoDao.queryMenuInfo();
		List<MenuInfoResponse> menuInfoResponses = getTreeConstructMenuInfoResponses(menuInfos);
		return menuInfoResponses;
	}


	@Override
	public List<MenuInfoResponse> getAllRightsInfoTreeByRoleId(Long roleId) {
		List<MenuInfo> menuInfos = menuInfoDao.queryMenuInfoByRoleId(roleId);
		List<MenuInfoResponse> menuInfoResponses = getTreeConstructMenuInfoResponses(menuInfos);
		return menuInfoResponses;
	}

	//获取树形结构
	private List<MenuInfoResponse> getTreeConstructMenuInfoResponses(List<MenuInfo> menuInfos) {
		HashMap<Long, MenuInfoResponse> menuInfoMap = new HashMap<>();
		List<MenuInfoResponse> menuInfoResponses = new ArrayList<>();
		for (MenuInfo menuInfo : menuInfos) {
			MenuInfoResponse menuInfoResponse = new MenuInfoResponse();
			BeanUtils.copyProperties(menuInfo, menuInfoResponse);
			menuInfoMap.put(menuInfo.getId(), menuInfoResponse);
			menuInfoResponses.add(menuInfoResponse);
		}
		Iterator<MenuInfoResponse> iterator = menuInfoResponses.iterator();
		while (iterator.hasNext()) {
			MenuInfoResponse next = iterator.next();
			Long pid = next.getPid();
			if (pid != 0) {
				MenuInfoResponse menuInfoResponse = menuInfoMap.get(pid);
				if (menuInfoResponse.getChildren() == null) {
					menuInfoResponse.setChildren(new ArrayList<>());
				}
				menuInfoResponse.getChildren().add(next);
				iterator.remove();
			}
		}
		return menuInfoResponses;
	}

	@Override
	public void addRightsByRoleId(Long roleId, List<Long> rids) {
		menuInfoDao.insertRoleBatch(roleId, rids);
	}

	@Override
	public void roleDeleteRights(Long roleId, Long rightId) {
		menuInfoDao.deleteRights(roleId, rightId);
	}

	@Override
	public void deleteRightsByRId(Long roleId) {
		menuInfoDao.deleteRightsByRId(roleId);
	}

	//查询父id的信息
	@Override
	public List<MenuInfo> queryMenuInfoByPid(Long roleId, Long rightId) {
		return menuInfoDao.queryMenuInfoByPid(roleId, rightId);
	}


	@Override
	public List<MenuInfo> queryMenuInfoByRoleId(Long roleId) {
		return menuInfoDao.queryMenuInfoByRoleId(roleId);
	}

	@Override
	public MenuInfoResponse addMenuInfo(MenuInfoRequest menuInfoRequest) {
		MenuInfoResponse menuInfoResponse = new MenuInfoResponse();
		try {
			menuInfoRequest.setStatus(1);
			menuInfoDao.insertRightsInfo(menuInfoRequest);
		} catch (Exception e) {
			log.error("添加失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(menuInfoRequest, menuInfoResponse);
		return menuInfoResponse;
	}


	@Override
	public MenuInfoResponse deleteMenuById(Long id) {
		MenuInfoResponse menuInfoResponse = new MenuInfoResponse();
		try {
			menuInfoDao.deleteRightsById(id);
		} catch (Exception e) {
			log.error("删除失败", e.getMessage());
			return null;
		}
		return menuInfoResponse;
	}


	@Override
	public MenuInfoResponse updateMenuInfoById(MenuInfoRequest menuInfoRequest) {
		MenuInfoResponse menuInfoResponse = new MenuInfoResponse();
		try {
			menuInfoDao.updateRightsInfo(menuInfoRequest);
		} catch (Exception e) {
			log.error("更新失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(menuInfoRequest, menuInfoResponse);
		return menuInfoResponse;
	}
}
