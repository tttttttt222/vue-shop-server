package com.vueshop.manager.controller.http.response;

import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Data
public class UserInfoResponse{

    private Long id;

    private Long rid;

    private String username;

    private String mobile;

    private String email;

    private String token;

    private String password;

    private String createtime;

    private String updatetime;
}
