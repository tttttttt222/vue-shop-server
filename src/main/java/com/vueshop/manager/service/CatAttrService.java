package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.request.CategoriesAttrRequest;
import com.vueshop.manager.controller.http.response.CategoriesAttrResponse;
import com.vueshop.manager.dao.model.CategoriesArrInfo;
import java.util.List;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/10
 */
public interface CatAttrService {

	CategoriesAttrResponse queryCategoriesAttrById(CategoriesAttrRequest request);

	List<CategoriesArrInfo> queryCategoriesAttrByIdAndSel(CategoriesAttrRequest categoriesAttrRequest);

	CategoriesAttrResponse insertCategoriesAttr(CategoriesAttrRequest categoriesAttrRequest);

	CategoriesAttrResponse deleteCateAttrById(Long id);

	CategoriesAttrResponse updateCateAttrInfoById(CategoriesAttrRequest categoriesAttrRequest);
}
