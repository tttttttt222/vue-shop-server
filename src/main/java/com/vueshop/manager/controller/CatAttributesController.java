package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.request.CategoriesAttrRequest;
import com.vueshop.manager.controller.http.request.CategoriesInfoRequest;
import com.vueshop.manager.controller.http.response.CategoriesAttrResponse;
import com.vueshop.manager.controller.http.response.CategoriesInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.dao.model.CategoriesArrInfo;
import com.vueshop.manager.service.CatAttrService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/2
 */
@RestController
@RequestMapping("api/private/v1/")
@Slf4j
public class CatAttributesController {

	@Autowired
	private CatAttrService catAttrService;

	@GetMapping("categories/{id}/attributes")
	@ResponseBody
	public HttpResponse<List<CategoriesArrInfo>> queryCategoriesById(HttpServletRequest request,
			@PathVariable long id) {
		HttpResponse<List<CategoriesArrInfo>> httpResponse = new HttpResponse<>(new Meta());

		String sel = request.getParameter("sel");
		CategoriesAttrRequest categoriesAttrRequest = new CategoriesAttrRequest();
		categoriesAttrRequest.setCat_id(id);
		categoriesAttrRequest.setAttr_sel(sel);

		List<CategoriesArrInfo> categoriesArrInfos = catAttrService
				.queryCategoriesAttrByIdAndSel(categoriesAttrRequest);

		if (categoriesArrInfos == null) {
			httpResponse.getMeta().setMsg("查询参数列表失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(categoriesArrInfos);
		httpResponse.getMeta().setMsg("查询参数列表成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


	@PostMapping("categories/{id}/attributes")
	@ResponseBody
	public HttpResponse<CategoriesAttrResponse> addCategories(HttpServletRequest request,
			@PathVariable long id,@RequestBody CategoriesAttrRequest categoriesAttrRequest) {
		HttpResponse<CategoriesAttrResponse> httpResponse = new HttpResponse<>(new Meta());

		categoriesAttrRequest.setCat_id(id);
		CategoriesAttrResponse categoriesAttrResponse = catAttrService.insertCategoriesAttr(categoriesAttrRequest);

		if (categoriesAttrResponse == null) {
			httpResponse.getMeta().setMsg("分类参数列表创建成功");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(categoriesAttrResponse);
		httpResponse.getMeta().setMsg("分类参数列表创建失败");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}

	@PutMapping("categories/{id}/attributes/{attrId}")
	@ResponseBody
	public HttpResponse<CategoriesAttrResponse> updateCategoriesInfoById(HttpServletRequest request,
			@PathVariable long id,@PathVariable long attrId,
			@RequestBody CategoriesAttrRequest categoriesAttrRequest) {
		HttpResponse<CategoriesAttrResponse> httpResponse = new HttpResponse<>(new Meta());

		categoriesAttrRequest.setId(attrId);
		categoriesAttrRequest.setCat_id(id);
		CategoriesAttrResponse categoriesAttrResponse = catAttrService.updateCateAttrInfoById(categoriesAttrRequest);

		if (categoriesAttrResponse == null) {
			httpResponse.getMeta().setMsg("更新失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(categoriesAttrResponse);
		httpResponse.getMeta().setMsg("更新成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


	@GetMapping("categories/{id}/attributes/{attrId}")
	@ResponseBody
	public HttpResponse<CategoriesAttrResponse> queryCategoriesById(HttpServletRequest request,
			@PathVariable long id,@PathVariable long attrId) {
		HttpResponse<CategoriesAttrResponse> httpResponse = new HttpResponse<>(new Meta());

		CategoriesAttrRequest categoriesAttrRequest = new CategoriesAttrRequest();
		categoriesAttrRequest.setId(attrId);
		categoriesAttrRequest.setCat_id(id);

		CategoriesAttrResponse categoriesAttrResponse = catAttrService.queryCategoriesAttrById(categoriesAttrRequest);

		if (categoriesAttrResponse == null) {
			httpResponse.getMeta().setMsg("查询参数列表失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(categoriesAttrResponse);
		httpResponse.getMeta().setMsg("查询参数列表成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}
}
