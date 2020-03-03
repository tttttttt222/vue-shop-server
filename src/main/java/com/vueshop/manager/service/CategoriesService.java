package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.request.CategoriesInfoQueryRequest;
import com.vueshop.manager.controller.http.request.CategoriesInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.CategoriesInfoQueryResponse;
import com.vueshop.manager.controller.http.response.CategoriesInfoResponse;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/3
 */
public interface CategoriesService {


	CategoriesInfoQueryResponse queryCategoriesPage(PageRequest<CategoriesInfoQueryRequest> pageRequest);

	CategoriesInfoResponse insertCategories(CategoriesInfoRequest categoriesInfoRequest);

	CategoriesInfoResponse queryCategoriesInfoById(Long id);

	CategoriesInfoResponse deleteCategoriesById(Long id);

	CategoriesInfoResponse updateCategoriesInfoById(CategoriesInfoRequest CategoriesInfoRequest);
}
