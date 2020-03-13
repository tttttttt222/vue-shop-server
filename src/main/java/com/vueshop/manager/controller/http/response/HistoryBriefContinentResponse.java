package com.vueshop.manager.controller.http.response;

import com.vueshop.manager.dao.model.HistoryBriefInfo;
import java.util.List;
import lombok.Data;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/13
 */
@Data
public class HistoryBriefContinentResponse {

	//0:东亚 1:西亚 2:东欧 3:西欧 4:非洲 5:澳洲 6:北美 7:南美
	private Integer continent;

	private List<HistoryBriefInfo> historyBriefInfos;

}
