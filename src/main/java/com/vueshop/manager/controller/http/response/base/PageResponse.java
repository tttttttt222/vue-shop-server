package com.vueshop.manager.controller.http.response.base;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
@Data
public class PageResponse {

    private Integer total;

    private Integer pagenum;

}
