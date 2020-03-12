package com.vueshop.manager.controller.http.request.base;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/12
 */
@Data
public class PageBaseRequest {

	private Integer pagenum;
	private Integer pagesize;

}
