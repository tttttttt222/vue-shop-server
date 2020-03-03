package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.request.CategoriesInfoQueryRequest;
import com.vueshop.manager.controller.http.request.CategoriesInfoRequest;
import com.vueshop.manager.controller.http.request.CategoriesInfoQueryRequest;
import com.vueshop.manager.controller.http.request.CategoriesInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.CategoriesInfoQueryResponse;
import com.vueshop.manager.controller.http.response.CategoriesInfoResponse;
import com.vueshop.manager.controller.http.response.CategoriesInfoQueryResponse;
import com.vueshop.manager.controller.http.response.CategoriesInfoResponse;
import com.vueshop.manager.dao.mapper.CategoriesInfoDao;
import com.vueshop.manager.dao.model.CategoriesInfo;
import com.vueshop.manager.dao.model.CategoriesInfo;
import com.vueshop.manager.service.CategoriesService;
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
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private CategoriesInfoDao categoriesInfoDao;

	@Override
	public CategoriesInfoQueryResponse queryCategoriesPage(PageRequest<CategoriesInfoQueryRequest> pageRequest) {
		CategoriesInfoQueryResponse res = new CategoriesInfoQueryResponse();
		List<CategoriesInfo> categoriesInfos = categoriesInfoDao
				.queryCategoriesPage(pageRequest.getQuery(), pageRequest.getPagestart(), pageRequest.getPagesize());
		Integer count = categoriesInfoDao.queryCategoriesPageCount(pageRequest.getQuery());

		if (pageRequest.getQuery().getType() != null && !pageRequest.getQuery().getType().equals("1")) {
			genCategpriesTree(categoriesInfos, Integer.parseInt(pageRequest.getQuery().getType()));
		}

		res.setTotal(count);
		res.setPagenum(pageRequest.getPagenum());
		res.setData(categoriesInfos);
		return res;
	}


	//设置分类树形结构
	private void genCategpriesTree(List<CategoriesInfo> categoriesInfos, Integer type) {

		for (CategoriesInfo categoriesInfo : categoriesInfos) {
			if (type > categoriesInfo.getCat_level() + 1) {
				List<CategoriesInfo> categoriesInfosChild = categoriesInfoDao
						.queryCategoriesByPid(categoriesInfo.getCat_id());
				if (categoriesInfosChild != null && categoriesInfosChild.size() != 0) {
					categoriesInfo.setChildren(categoriesInfosChild);
					genCategpriesTree(categoriesInfosChild, type);
				}
			}

		}

	}

	@Override
	public CategoriesInfoResponse insertCategories(CategoriesInfoRequest categoriesInfoRequest) {
		CategoriesInfoResponse categoriesInfoResponse = new CategoriesInfoResponse();
		try {
			categoriesInfoDao.insertCategoriesInfo(categoriesInfoRequest);
		} catch (Exception e) {
			log.error("添加分类失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(categoriesInfoRequest, categoriesInfoResponse);
		return categoriesInfoResponse;
	}

	@Override
	public CategoriesInfoResponse queryCategoriesInfoById(Long id) {
		CategoriesInfoResponse categoriesInfoResponse = new CategoriesInfoResponse();
		CategoriesInfo categoriesInfo = categoriesInfoDao.queryCategoriesInfoById(id);
		BeanUtils.copyProperties(categoriesInfo, categoriesInfoResponse);
		return categoriesInfoResponse;
	}

	@Override
	public CategoriesInfoResponse deleteCategoriesById(Long id) {
		CategoriesInfoResponse CategoriesInfoResponse = new CategoriesInfoResponse();
		try {
			categoriesInfoDao.deleteCategoriesById(id);
		} catch (Exception e) {
			log.error("删除失败", e.getMessage());
			return null;
		}
		return CategoriesInfoResponse;
	}

	@Override
	public CategoriesInfoResponse updateCategoriesInfoById(CategoriesInfoRequest CategoriesInfoRequest) {
		CategoriesInfoResponse CategoriesInfoResponse = new CategoriesInfoResponse();
		try {
			categoriesInfoDao.updateCategoriesInfo(CategoriesInfoRequest);
		} catch (Exception e) {
			log.error("更新失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(CategoriesInfoRequest, CategoriesInfoResponse);
		return CategoriesInfoResponse;
	}

}
