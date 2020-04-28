package com.vueshop.manager.controller.http.response;

import com.vueshop.manager.controller.http.response.base.PageResponse;
import com.vueshop.manager.dao.model.HistoryBriefInfo;
import java.util.List;
import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/4/22
 */
@Data
public class HistoryBriefInfoNewQueryResponse extends PageResponse {

	private List<HistoryBriefInfo> list;
}
