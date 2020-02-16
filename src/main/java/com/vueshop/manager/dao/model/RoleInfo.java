package com.vueshop.manager.dao.model;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/16
 */
@Data
public class RoleInfo {
    //
    private Long id;

    //
    private String roleName;

    //
    private String roleDesc;

    //
    private Integer status;

    //
    private String createtime;

    //
    private String updatetime;
}
