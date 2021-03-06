package com.vueshop.manager.dao.model;

import java.util.List;
import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/11
 */
@Data
public class HistoryBriefEventInfo {

	//
	private Long id;

	//
	private String eventTitle;

	//简介
	private String eventBrief;

	//内容
	private String eventContennt;

	//
	private String eventCateIds;

	//
	private String createTime;

	//
	private String updateTime;

	private List<HistoryBriefEventCateInfo> tag;

}
