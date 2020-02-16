package com.vueshop.manager.controller.http.response;

import com.vueshop.manager.controller.http.response.base.PageResponse;
import com.vueshop.manager.dao.model.UserInfo;
import lombok.Data;

import java.util.List;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Data
public class UserInfoQueryResponse extends PageResponse {

   private List<UserInfo> users;
}
