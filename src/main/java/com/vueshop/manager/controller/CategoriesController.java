package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.request.CategoriesInfoQueryRequest;
import com.vueshop.manager.controller.http.request.CategoriesInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.CategoriesInfoQueryResponse;
import com.vueshop.manager.controller.http.response.CategoriesInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.service.CategoriesService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;

	@GetMapping("categories")
	@ResponseBody
	public HttpResponse<CategoriesInfoQueryResponse> queryCategoriesPage(HttpServletRequest request) {
		HttpResponse<CategoriesInfoQueryResponse> httpResponse = new HttpResponse<>(new Meta());

		String pagenum = request.getParameter("pagenum");
		String pagesize = request.getParameter("pagesize");
		String type = request.getParameter("type");
		PageRequest<CategoriesInfoQueryRequest> pageRequest = new PageRequest(Integer.parseInt(pagenum),
				Integer.parseInt(pagesize));
		CategoriesInfoQueryRequest categoriesInfoQueryRequest = new CategoriesInfoQueryRequest();
		categoriesInfoQueryRequest.setType(type);
		pageRequest.setQuery(categoriesInfoQueryRequest);
		CategoriesInfoQueryResponse categoriesInfoQueryResponse = categoriesService
				.queryCategoriesPage(pageRequest);

		httpResponse.setData(categoriesInfoQueryResponse);
		httpResponse.getMeta().setMsg("获取商品分类列表成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


	@PostMapping("categories")
	@ResponseBody
	public HttpResponse<CategoriesInfoResponse> addCategories(HttpServletRequest request,
			@RequestBody CategoriesInfoRequest categoriesInfoRequest) {
		HttpResponse<CategoriesInfoResponse> httpResponse = new HttpResponse<>(new Meta());

		CategoriesInfoResponse categoriesInfoResponse = categoriesService.insertCategories(categoriesInfoRequest);

		if (categoriesInfoResponse == null) {
			httpResponse.getMeta().setMsg("商品分类创建成功");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(categoriesInfoResponse);
		httpResponse.getMeta().setMsg("商品分类创建失败");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


	@GetMapping("categories/{id}")
	@ResponseBody
	public HttpResponse<CategoriesInfoResponse> queryCategoriesById(HttpServletRequest request, @PathVariable long id) {
		HttpResponse<CategoriesInfoResponse> httpResponse = new HttpResponse<>(new Meta());

		CategoriesInfoResponse categoriesInfoResponse = categoriesService.queryCategoriesInfoById(id);

		if (categoriesInfoResponse == null) {
			httpResponse.getMeta().setMsg("查询失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(categoriesInfoResponse);
		httpResponse.getMeta().setMsg("查询成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}


	@PutMapping("categories/{id}")
	@ResponseBody
	public HttpResponse<CategoriesInfoResponse> updateCategoriesInfoById(HttpServletRequest request,
			@PathVariable long id,
			@RequestBody CategoriesInfoRequest CategoriesInfoRequest) {
		HttpResponse<CategoriesInfoResponse> httpResponse = new HttpResponse<>(new Meta());

		CategoriesInfoRequest.setCat_id(id);
		CategoriesInfoResponse categoriesInfoResponse = categoriesService
				.updateCategoriesInfoById(CategoriesInfoRequest);

		if (categoriesInfoResponse == null) {
			httpResponse.getMeta().setMsg("更新失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(categoriesInfoResponse);
		httpResponse.getMeta().setMsg("更新成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}

	@DeleteMapping("categories/{id}")
	@ResponseBody
	public HttpResponse<CategoriesInfoResponse> deleteCategoriesById(HttpServletRequest request,
			@PathVariable long id) {
		HttpResponse<CategoriesInfoResponse> httpResponse = new HttpResponse<>(new Meta());

		CategoriesInfoResponse categoriesInfoResponse = categoriesService.deleteCategoriesById(id);

		if (categoriesInfoResponse == null) {
			httpResponse.getMeta().setMsg("删除失败");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(categoriesInfoResponse);
		httpResponse.getMeta().setMsg("删除成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}

}
