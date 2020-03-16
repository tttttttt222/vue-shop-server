package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.request.HistoryBriefEventInfoAddRequest;
import com.vueshop.manager.controller.http.request.HistoryBriefQueryRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoQueryResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.service.HistoryBriefService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
public class HistoryBriefController {

	@Autowired
	private HistoryBriefService historyBriefService;

	@PostMapping("historyBrief/query")
	@ResponseBody
	public HttpResponse<HistoryBriefInfoQueryResponse> queryHistoryBriefPage(HttpServletRequest request,
			@RequestBody HistoryBriefQueryRequest historyBriefQueryRequest) {
		HttpResponse<HistoryBriefInfoQueryResponse> httpResponse = new HttpResponse<>(new Meta());

		PageRequest<HistoryBriefQueryRequest> pageRequest = new PageRequest<>(historyBriefQueryRequest.getPagenum(),
				historyBriefQueryRequest.getPagesize());
		pageRequest.setQuery(historyBriefQueryRequest);

		HistoryBriefInfoQueryResponse historyBriefInfoQueryResponse = historyBriefService
				.queryHistoryBriefPage(pageRequest);
		httpResponse.setData(historyBriefInfoQueryResponse);
		httpResponse.getMeta().setMsg("获取历史简介列表成功");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}

	@PostMapping("historyBrief/add")
	@ResponseBody
	public HttpResponse<HistoryBriefInfoResponse> addHistoryBrief(HttpServletRequest request,
			@RequestBody HistoryBriefEventInfoAddRequest addRequest) {
		HttpResponse<HistoryBriefInfoResponse> httpResponse = new HttpResponse<>(new Meta());

		if (addRequest.getHistoryBrief().getDisplay() == null) {
			addRequest.getHistoryBrief().setDisplay(1);
		}
		HistoryBriefInfoResponse historyBriefInfoResponse = historyBriefService
				.insertHistoryBrief(addRequest.getHistoryBrief(), addRequest.getHistoryEvent());

		if (historyBriefInfoResponse == null) {
			httpResponse.getMeta().setMsg("历史简介创建成功");
			httpResponse.getMeta().setStatus(400);
			return httpResponse;
		}

		httpResponse.setData(historyBriefInfoResponse);
		httpResponse.getMeta().setMsg("历史简介创建失败");
		httpResponse.getMeta().setStatus(200);
		return httpResponse;
	}
//
//
//    @GetMapping("categories/{id}")
//    @ResponseBody
//    public HttpResponse<CategoriesInfoResponse> queryCategoriesById(HttpServletRequest request, @PathVariable long id) {
//        HttpResponse<CategoriesInfoResponse> httpResponse = new HttpResponse<>(new Meta());
//
//        CategoriesInfoResponse categoriesInfoResponse = categoriesService.queryCategoriesInfoById(id);
//
//        if (categoriesInfoResponse == null) {
//            httpResponse.getMeta().setMsg("查询失败");
//            httpResponse.getMeta().setStatus(400);
//            return httpResponse;
//        }
//
//        httpResponse.setData(categoriesInfoResponse);
//        httpResponse.getMeta().setMsg("查询成功");
//        httpResponse.getMeta().setStatus(200);
//        return httpResponse;
//    }
//
//
//    @PutMapping("categories/{id}")
//    @ResponseBody
//    public HttpResponse<CategoriesInfoResponse> updateCategoriesInfoById(HttpServletRequest request,
//                                                                         @PathVariable long id,
//                                                                         @RequestBody CategoriesInfoRequest CategoriesInfoRequest) {
//        HttpResponse<CategoriesInfoResponse> httpResponse = new HttpResponse<>(new Meta());
//
//        CategoriesInfoRequest.setCat_id(id);
//        CategoriesInfoResponse categoriesInfoResponse = categoriesService
//                .updateCategoriesInfoById(CategoriesInfoRequest);
//
//        if (categoriesInfoResponse == null) {
//            httpResponse.getMeta().setMsg("更新失败");
//            httpResponse.getMeta().setStatus(400);
//            return httpResponse;
//        }
//
//        httpResponse.setData(categoriesInfoResponse);
//        httpResponse.getMeta().setMsg("更新成功");
//        httpResponse.getMeta().setStatus(200);
//        return httpResponse;
//    }
//
//    @DeleteMapping("categories/{id}")
//    @ResponseBody
//    public HttpResponse<CategoriesInfoResponse> deleteCategoriesById(HttpServletRequest request,
//                                                                     @PathVariable long id) {
//        HttpResponse<CategoriesInfoResponse> httpResponse = new HttpResponse<>(new Meta());
//
//        CategoriesInfoResponse categoriesInfoResponse = categoriesService.deleteCategoriesById(id);
//
//        if (categoriesInfoResponse == null) {
//            httpResponse.getMeta().setMsg("删除失败");
//            httpResponse.getMeta().setStatus(400);
//            return httpResponse;
//        }
//
//        httpResponse.setData(categoriesInfoResponse);
//        httpResponse.getMeta().setMsg("删除成功");
//        httpResponse.getMeta().setStatus(200);
//        return httpResponse;
//    }

}
