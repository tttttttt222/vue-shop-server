package com.vueshop.manager.service;

import com.vueshop.manager.controller.http.request.HistoryBriefInfoRequest;
import com.vueshop.manager.controller.http.request.HistoryBriefQueryRequest;
import com.vueshop.manager.controller.http.request.HistoryEventInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoNewQueryResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoQueryResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoResponse;
import com.vueshop.manager.controller.http.response.HistoryEventInfoResponse;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/3
 */
public interface HistoryBriefService {


	HistoryBriefInfoQueryResponse queryHistoryBriefPage(PageRequest<HistoryBriefQueryRequest> pageRequest);

	HistoryBriefInfoResponse insertHistoryBrief(HistoryBriefInfoRequest historyBriefInfoRequest,
			HistoryEventInfoRequest historyEventInfoRequest);

	HistoryBriefInfoResponse deleteHistoryBriefById(Long id, Long eventId);

	HistoryBriefInfoResponse updateHistoryBriefInfoById(HistoryBriefInfoRequest historyBriefInfoRequest,
			HistoryEventInfoRequest historyEventInfoRequest);

	HistoryEventInfoResponse queryHistoryBriefDetialById(Long id);

	HistoryBriefInfoNewQueryResponse queryHistoryBriefPageNew(PageRequest<HistoryBriefQueryRequest> pageRequest);
}
