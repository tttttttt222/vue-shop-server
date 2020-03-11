package com.vueshop.manager.controller.http.response;

import com.vueshop.manager.dao.model.HistoryBriefInfo;
import lombok.Data;

/**
 * 项目名称:vue-shop-manager
 * 描述:
 * 创建人:ryw
 * 创建时间:2020/2/15
 */
@Data
public class HistoryBriefInfoColletResponse {

    //
    private Long id;

    //
    private Long year;

    private HistoryBriefInfo historyBriefInfo;


}
