package com.vueshop.manager.dao.model;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/11
 */
@Data
public class HistoryBriefInfo {

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

	private String eventBrief;

	//
	private Long eventId;

	//
	private Integer display;

	//
	private String createTime;

	//
	private String updateTime;

}
