package com.vueshop.manager.controller.http.request;

import com.vueshop.manager.controller.http.request.base.PageBaseRequest;
import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/15
 */
@Data
public class HistoryBriefQueryRequest extends PageBaseRequest {

	//
	private Long id;

	//
	private Long year;

	//
	private Long month;

	//
	private Long date;

	//0:东亚 1:西亚 2:东欧 3:西欧 4:非洲 5:澳洲 6:北美 7:南美
	private Integer continent;

	//
	private String contry;

	//
	private String city;

	//
	private String mainPerson;

	//
	private String period;

	//
	private String eventName;

	//
	private Long eventId;

	//
	private Integer display;

	private Long yearStart;

	private Long yearEnd;


}
