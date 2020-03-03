package com.vueshop.manager.dao.mapper;

import com.vueshop.manager.controller.http.request.CategoriesInfoQueryRequest;
import com.vueshop.manager.controller.http.request.CategoriesInfoRequest;
import com.vueshop.manager.dao.model.CategoriesInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/3
 */
public interface CategoriesInfoDao {


	List<CategoriesInfo> queryCategoriesPage(@Param("query") CategoriesInfoQueryRequest categoriesInfoQueryRequest,
			@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

	Integer queryCategoriesPageCount(@Param("query") CategoriesInfoQueryRequest categoriesInfoQueryRequest);

	List<CategoriesInfo> queryCategoriesByPid(Long pid);

	void insertCategoriesInfo(@Param("categoriesInfo") CategoriesInfoRequest categoriesInfoRequest);

	CategoriesInfo queryCategoriesInfoById(Long id);

	void updateCategoriesInfo(@Param("categoriesInfo") CategoriesInfoRequest categoriesInfoRequest);

	void deleteCategoriesById(Long id);
}
