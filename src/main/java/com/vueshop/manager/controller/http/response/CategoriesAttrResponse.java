package com.vueshop.manager.controller.http.response;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/15
 */
@Data
public class CategoriesAttrResponse {

	//
	private Long id;

	//
	private Long attr_id;

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
