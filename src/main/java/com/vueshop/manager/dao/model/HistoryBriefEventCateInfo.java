package com.vueshop.manager.dao.model;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/11
 */
@Data
public class HistoryBriefEventCateInfo {


	//
	private Long id;

	//
	private String eventCateName;

	//
	private Long pid;

	//
	private Integer level;

	//
	private Integer state;

	//0:地区 1:人物
	private Integer cateType;

	//
	private String createTime;

	//
	private String updateTime;
}
