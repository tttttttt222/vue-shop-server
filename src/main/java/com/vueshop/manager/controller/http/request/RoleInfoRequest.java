package com.vueshop.manager.controller.http.request;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/17
 */
@Data
public class RoleInfoRequest {

	//
	private Long id;

	//
	private String roleName;

	//
	private String roleDesc;

	//
	private Integer status;


}
