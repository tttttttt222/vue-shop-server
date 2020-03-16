package com.vueshop.manager.dao.mapper;

import com.vueshop.manager.controller.http.request.HistoryEventInfoRequest;
import com.vueshop.manager.dao.model.HistoryBriefInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/11
 */
public interface HistoryEventDao {

	HistoryBriefInfo queryHistoryEventById(Long id);

	void insertHistoryEventInfo(@Param("historyEvent") HistoryEventInfoRequest historyEventInfoRequest);

	void deleteHistoryEventById(Long id);

	void updateHistoryEventInfo(@Param("historyEvent") HistoryEventInfoRequest historyEventInfoRequest);

}
