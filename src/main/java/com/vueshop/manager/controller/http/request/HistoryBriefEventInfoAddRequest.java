package com.vueshop.manager.controller.http.request;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/16
 */
@Data
public class HistoryBriefEventInfoAddRequest {


	private HistoryEventInfoRequest historyEvent;

	private HistoryBriefInfoRequest historyBrief;

}
