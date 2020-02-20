package com.vueshop.manager.dao.mapper;

import com.vueshop.manager.dao.model.MenuInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
public interface MenuInfoDao {

    List<MenuInfo> queryMenuInfoAll();

    List<MenuInfo> queryMenuInfo();

    List<MenuInfo> queryMenuInfoByRoleId(Long roleId);

	void insertRoleBatch(@Param("roleId") long roleId, @Param("rids") List<Long> rids);

	void deleteRights(@Param("roleId") long roleId, @Param("rightId")long rightId);
}
