package com.vueshop.manager.dao.mapper;

import com.vueshop.manager.controller.http.request.CategoriesAttrRequest;
import com.vueshop.manager.dao.model.CategoriesArrInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/4
 */
public interface CategoriesAttrDao {

	CategoriesArrInfo queryCategoriesAttrById(Long id);

	CategoriesArrInfo queryCategoriesAttrByIdAndSel(@Param("id")Long id,@Param("sel")String sel);

	void insertCategoriesAttr(@Param("categoriesAttr") CategoriesAttrRequest categoriesInfoRequest);

	void updateCategoriesAttrInfo(@Param("categoriesAttr") CategoriesAttrRequest categoriesInfoRequest);

	void deleteCategoriesAttrById(Long id);

}
