package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.request.HistoryBriefInfoRequest;
import com.vueshop.manager.controller.http.request.HistoryBriefQueryRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoQueryResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoResponse;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/3
 */
public interface HistoryBriefService {


	HistoryBriefInfoQueryResponse queryHistoryBriefPage(PageRequest<HistoryBriefQueryRequest> pageRequest);

	HistoryBriefInfoResponse insertHistoryBrief(HistoryBriefInfoRequest historyBriefInfoRequest);

	HistoryBriefInfoResponse deleteHistoryBriefById(Long id);

	HistoryBriefInfoResponse updateHistoryBriefInfoById(HistoryBriefInfoRequest historyBriefInfoRequest);
}
