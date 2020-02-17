package com.vueshop.manager.controller.http.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
@Data
public class MenuInfoResponse {

    //
    private Long id;

    //
    private String authName;

    //
    private String path;

    //
    private Long pid;

    private Integer level;
    //
    private Integer status;

    private Integer display;

    private List<MenuInfoResponse> children = new ArrayList<>();
}
