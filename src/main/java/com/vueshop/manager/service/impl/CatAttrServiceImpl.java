package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.request.CategoriesAttrRequest;
import com.vueshop.manager.controller.http.response.CategoriesAttrResponse;
import com.vueshop.manager.dao.mapper.CategoriesAttrDao;
import com.vueshop.manager.dao.model.CategoriesArrInfo;
import com.vueshop.manager.service.CatAttrService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/10
 */
@Service
@Slf4j
public class CatAttrServiceImpl implements CatAttrService {

	@Autowired
	private CategoriesAttrDao categoriesAttrDao;


	@Override
	public CategoriesAttrResponse queryCategoriesAttrById(CategoriesAttrRequest request) {
		CategoriesAttrResponse categoriesAttrResponse = new CategoriesAttrResponse();
		CategoriesArrInfo categoriesArrInfo = categoriesAttrDao.queryCategoriesAttrById(request.getId());
		BeanUtils.copyProperties(categoriesArrInfo, categoriesAttrResponse);
		return categoriesAttrResponse;
	}

	@Override
	public List<CategoriesArrInfo> queryCategoriesAttrByIdAndSel(CategoriesAttrRequest request) {
		List<CategoriesArrInfo> categoriesArrInfo = categoriesAttrDao
				.queryCategoriesAttrByIdAndSel(request.getCat_id(), request.getAttr_sel());
		return categoriesArrInfo;
	}


	@Override
	public CategoriesAttrResponse insertCategoriesAttr(CategoriesAttrRequest categoriesAttrRequest) {
		CategoriesAttrResponse categoriesAttrResponse = new CategoriesAttrResponse();
		try {
			categoriesAttrDao.insertCategoriesAttr(categoriesAttrRequest);
		} catch (Exception e) {
			log.error("添加分类失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(categoriesAttrRequest, categoriesAttrResponse);
		return categoriesAttrResponse;
	}

	@Override
	public CategoriesAttrResponse deleteCateAttrById(Long id) {
		CategoriesAttrResponse categoriesAttrResponse = new CategoriesAttrResponse();
		try {
			categoriesAttrDao.deleteCategoriesAttrById(id);
		} catch (Exception e) {
			log.error("删除失败", e.getMessage());
			return null;
		}
		return categoriesAttrResponse;
	}

	@Override
	public CategoriesAttrResponse updateCateAttrInfoById(CategoriesAttrRequest categoriesAttrRequest) {
		CategoriesAttrResponse categoriesAttrResponse = new CategoriesAttrResponse();
		try {
			categoriesAttrDao.updateCategoriesAttrInfo(categoriesAttrRequest);
		} catch (Exception e) {
			log.error("更新失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(categoriesAttrRequest, categoriesAttrResponse);
		return categoriesAttrResponse;
	}


}
