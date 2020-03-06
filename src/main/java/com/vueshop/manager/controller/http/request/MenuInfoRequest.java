package com.vueshop.manager.controller.http.request;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/6
 */
@Data
public class MenuInfoRequest {
	//
	private Long id;

	//
	private String authName;

	//
	private String path;

	//
	private Long pid;

	//
	private Integer status;

	private Integer display;

	private Integer level;


}
