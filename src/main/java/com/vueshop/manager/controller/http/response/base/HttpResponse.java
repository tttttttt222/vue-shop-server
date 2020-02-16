package com.vueshop.manager.controller.http.response.base;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Data
public class HttpResponse<T> {

    private T data;

    private Meta meta;

    public HttpResponse(Meta meta) {
        this.meta = meta;
    }

    public HttpResponse() {
    }
}
