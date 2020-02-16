package com.vueshop.manager.controller.http.request;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Data
public class UserInfoRequest {

    private Long id;

    private String username;

    private String password;

    private Integer status;

    private String email;

    private String mobile;

    private Integer rid;
}
