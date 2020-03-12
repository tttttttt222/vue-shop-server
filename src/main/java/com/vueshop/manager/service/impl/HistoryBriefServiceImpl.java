package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.request.CategoriesInfoQueryRequest;
import com.vueshop.manager.controller.http.request.CategoriesInfoRequest;
import com.vueshop.manager.controller.http.request.HistoryBriefInfoRequest;
import com.vueshop.manager.controller.http.request.HistoryBriefQueryRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.CategoriesInfoQueryResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoColletResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoQueryResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoResponse;
import com.vueshop.manager.dao.mapper.CategoriesInfoDao;
import com.vueshop.manager.dao.mapper.HistoryBriefDao;
import com.vueshop.manager.dao.model.CategoriesInfo;
import com.vueshop.manager.dao.model.HistoryBriefInfo;
import com.vueshop.manager.service.HistoryBriefService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/14
 */
@Service
@Slf4j
public class HistoryBriefServiceImpl implements HistoryBriefService {

	@Autowired
	private HistoryBriefDao historyBriefDao;

	@Override
	public HistoryBriefInfoQueryResponse queryHistoryBriefPage(PageRequest<HistoryBriefQueryRequest> pageRequest) {
		HistoryBriefInfoQueryResponse res = new HistoryBriefInfoQueryResponse();

		List<HistoryBriefInfo> historyBriefInfos = historyBriefDao
				.queryHistoryBriefPage(pageRequest.getQuery(), pageRequest.getPagestart(), pageRequest.getPagesize());
		Integer count = historyBriefDao.queryHistoryBriefPageCount(pageRequest.getQuery());

		LinkedHashMap<Long, HistoryBriefInfoColletResponse> yearDepMap = new LinkedHashMap<>();
		for (HistoryBriefInfo historyBriefInfo : historyBriefInfos) {
			HistoryBriefInfoColletResponse historyBrief = yearDepMap.get(historyBriefInfo.getYear());
			if (historyBrief != null) {
				historyBrief.getHistoryBriefInfos().add(historyBriefInfo);
				count--;
			} else {
				HistoryBriefInfoColletResponse historyBriefInfoColletResponse = new HistoryBriefInfoColletResponse();
				ArrayList<HistoryBriefInfo> hlist = new ArrayList<>();
				hlist.add(historyBriefInfo);
				historyBriefInfoColletResponse.setHistoryBriefInfos(hlist);
				historyBriefInfoColletResponse.setYear(historyBriefInfo.getYear());
				yearDepMap.put(historyBriefInfo.getYear(), historyBriefInfoColletResponse);
			}
		}
		ArrayList<HistoryBriefInfoColletResponse> list = new ArrayList<>();
		for (Entry<Long, HistoryBriefInfoColletResponse> map : yearDepMap.entrySet()) {
			list.add(map.getValue());
		}

		res.setTotal(count);
		res.setPagenum(pageRequest.getPagenum());
		res.setList(list);
		return res;
	}


	@Override
	public HistoryBriefInfoResponse insertHistoryBrief(HistoryBriefInfoRequest historyBriefInfoRequest) {
		HistoryBriefInfoResponse historyBriefInfoResponse = new HistoryBriefInfoResponse();
		try {
			historyBriefDao.insertHistoryBriefInfo(historyBriefInfoRequest);
		} catch (Exception e) {
			log.error("添加分类失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(historyBriefInfoRequest, historyBriefInfoResponse);
		return historyBriefInfoResponse;
	}


	@Override
	public HistoryBriefInfoResponse deleteHistoryBriefById(Long id) {
		HistoryBriefInfoResponse historyBriefInfoResponse = new HistoryBriefInfoResponse();
		try {
			historyBriefDao.deleteHistoryBriefById(id);
		} catch (Exception e) {
			log.error("删除失败", e.getMessage());
			return null;
		}
		return historyBriefInfoResponse;
	}

	@Override
	public HistoryBriefInfoResponse updateHistoryBriefInfoById(HistoryBriefInfoRequest historyBriefInfoRequest) {
		HistoryBriefInfoResponse historyBriefInfoResponse = new HistoryBriefInfoResponse();
		try {
			historyBriefDao.updateHistoryBriefInfo(historyBriefInfoRequest);
		} catch (Exception e) {
			log.error("更新失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(historyBriefInfoRequest, historyBriefInfoResponse);
		return historyBriefInfoResponse;
	}

}
