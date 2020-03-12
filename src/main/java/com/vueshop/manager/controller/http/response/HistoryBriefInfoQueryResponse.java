package com.vueshop.manager.controller.http.response;

import com.vueshop.manager.controller.http.response.base.PageResponse;
import java.util.List;
import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/14
 */
@Data
public class HistoryBriefInfoQueryResponse extends PageResponse {

   private List<HistoryBriefInfoColletResponse> list;
}
