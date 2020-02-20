package com.vueshop.manager.dao.mapper;

import com.vueshop.manager.controller.http.request.RoleInfoRequest;
import com.vueshop.manager.dao.model.RoleInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/16
 */
public interface RoleInfoDao {

    List<RoleInfo> queryRoleInfoAll();


    void insertRoleOne(@Param("roleInfo") RoleInfoRequest roleInfoRequest);

	RoleInfo queryRoleInfoById(long id);

	void updateRoleInfo(@Param("roleInfo") RoleInfoRequest roleInfoRequest);

	void deleteRoleById(long id);
}
