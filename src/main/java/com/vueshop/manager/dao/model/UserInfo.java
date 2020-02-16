package com.vueshop.manager.dao.model;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Data
public class UserInfo {

    private Long id;

    private Long rid;

    private String username;

    private String mobile;

    private String email;

    private String token;

    private String password;

    private Integer status;

    private String roleName;

    private String createtime;

    private String updatetime;
}
