package com.vueshop.manager.controller.http.request;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/15
 */
@Data
public class CategoriesInfoRequest {

	//
	private Long cat_id;

	//
	private String cat_name;

	//
	private Long cat_pid;

	//
	private Integer cat_level;

	//
	private Integer cat_deleted;

	//
	private Integer state;
}
