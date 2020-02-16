package com.vueshop.manager.controller.http.request.base;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
@Data
public class PageRequest<T> {

    private Integer pagenum;
    private Integer pagesize;
    private Integer pagestart;
    private T query;

    public PageRequest(Integer pagenum, Integer pagesize) {
        this.pagenum = pagenum;
        this.pagesize = pagesize;
        this.pagestart = (pagenum - 1) * pagesize;
    }

    public PageRequest() {
    }

}
