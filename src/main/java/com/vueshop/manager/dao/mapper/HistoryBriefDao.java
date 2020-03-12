package com.vueshop.manager.dao.mapper;

import com.vueshop.manager.controller.http.request.HistoryBriefInfoRequest;
import com.vueshop.manager.controller.http.request.HistoryBriefQueryRequest;
import com.vueshop.manager.dao.model.HistoryBriefInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/11
 */
public interface HistoryBriefDao {

	List<HistoryBriefInfo> queryHistoryBriefPage(@Param("query") HistoryBriefQueryRequest historyBriefQueryRequest,
			@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

	Integer queryHistoryBriefPageCount(@Param("query") HistoryBriefQueryRequest historyBriefQueryRequest);

	HistoryBriefInfo queryHistoryBriefInfoById(Long id);

	void insertHistoryBriefInfo(@Param("historyBriefInfo") HistoryBriefInfoRequest historyBriefInfoRequest);

	void deleteHistoryBriefById(Long id);

	void updateHistoryBriefInfo(@Param("historyBriefInfo") HistoryBriefInfoRequest historyBriefInfoRequest);

}
