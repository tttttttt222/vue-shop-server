package com.vueshop.manager.controller.http.response;

import com.vueshop.manager.controller.http.response.base.PageResponse;
import com.vueshop.manager.dao.model.CategoriesInfo;
import java.util.List;
import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Data
public class CategoriesInfoResponse extends PageResponse {

   //
   private Long cat_id;

   //
   private String cat_name;

   //
   private Long cat_pid;

   //
   private Integer cat_level;

   //
   private Integer cat_deleted;

   //
   private Integer state;

   //
   private String createTime;

   //
   private String updateTime;
}
