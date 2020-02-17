package com.vueshop.manager.dao.model;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
@Data
public class MenuInfo {

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

    //
    private String createtime;

    //
    private String updatetime;
}
