package com.vueshop.manager.controller.http.request;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/15
 */
@Data
public class CategoriesAttrRequest {

	//
	private Long id;

	//
	private String attr_name;

	//
	private Long cat_id;

	//
	private String attr_sel;

	//
	private String attr_write;

	//
	private String attr_vals;



}
