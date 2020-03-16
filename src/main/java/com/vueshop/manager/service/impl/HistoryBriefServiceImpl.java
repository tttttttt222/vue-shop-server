package com.vueshop.manager.service.impl;

import com.vueshop.manager.controller.http.request.HistoryBriefInfoRequest;
import com.vueshop.manager.controller.http.request.HistoryBriefQueryRequest;
import com.vueshop.manager.controller.http.request.HistoryEventInfoRequest;
import com.vueshop.manager.controller.http.request.base.PageRequest;
import com.vueshop.manager.controller.http.response.HistoryBriefContinentResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoColletResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoQueryResponse;
import com.vueshop.manager.controller.http.response.HistoryBriefInfoResponse;
import com.vueshop.manager.controller.http.response.HistoryEventInfoResponse;
import com.vueshop.manager.dao.mapper.HistoryBriefDao;
import com.vueshop.manager.dao.mapper.HistoryEventDao;
import com.vueshop.manager.dao.model.HistoryBriefEventInfo;
import com.vueshop.manager.dao.model.HistoryBriefInfo;
import com.vueshop.manager.service.HistoryBriefService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/2/14
 */
@Service
@Slf4j
public class HistoryBriefServiceImpl implements HistoryBriefService {

	@Autowired
	private HistoryBriefDao historyBriefDao;


	@Autowired
	private HistoryEventDao historyEventDao;


	@Autowired
	private TransactionTemplate transactionTemplate;

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
				boolean exsist = false;
				for (HistoryBriefContinentResponse continent : historyBrief.getContinents()) {
					if (continent.getContinent() == historyBriefInfo.getContinent()) {
						continent.getHistoryBriefInfos().add(historyBriefInfo);
						exsist = true;
						break;
					}
				}
				if (!exsist) {
					List<HistoryBriefContinentResponse> continents = historyBrief.getContinents();
					HistoryBriefContinentResponse historyBriefContinentResponse = new HistoryBriefContinentResponse();
					ArrayList<HistoryBriefInfo> hlist = new ArrayList<>();
					hlist.add(historyBriefInfo);
					historyBriefContinentResponse.setHistoryBriefInfos(hlist);
					historyBriefContinentResponse.setContinent(historyBriefInfo.getContinent());
					continents.add(historyBriefContinentResponse);
				}
				count--;
			} else {
				HistoryBriefInfoColletResponse historyBriefInfoColletResponse = new HistoryBriefInfoColletResponse();
				//先创建州数组
				ArrayList<HistoryBriefContinentResponse> clist = new ArrayList<>();
				HistoryBriefContinentResponse historyBriefContinentResponse = new HistoryBriefContinentResponse();
				clist.add(historyBriefContinentResponse);
				//加入具体信息
				ArrayList<HistoryBriefInfo> hlist = new ArrayList<>();
				hlist.add(historyBriefInfo);
				//赋值
				historyBriefContinentResponse.setContinent(historyBriefInfo.getContinent());
				historyBriefContinentResponse.setHistoryBriefInfos(hlist);
				historyBriefInfoColletResponse.setYear(historyBriefInfo.getYear());
				historyBriefInfoColletResponse.setContinents(clist);
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
	public HistoryBriefInfoResponse insertHistoryBrief(HistoryBriefInfoRequest historyBriefInfoRequest,
			HistoryEventInfoRequest historyEventInfoRequest) {
		HistoryBriefInfoResponse historyBriefInfoResponse = new HistoryBriefInfoResponse();
		try {
			transactionTemplate.execute((TransactionCallback) transactionStatus -> {
				historyEventDao.insertHistoryEventInfo(historyEventInfoRequest);
				historyBriefInfoRequest.setEventId(historyEventInfoRequest.getId());
				historyBriefDao.insertHistoryBriefInfo(historyBriefInfoRequest);
				return null;
			});
		} catch (Exception e) {
			log.error("添加历史简介失败", e.getMessage());
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
			log.error("删除历史简介失败", e.getMessage());
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
			log.error("更新历史简介失败", e.getMessage());
			return null;
		}
		BeanUtils.copyProperties(historyBriefInfoRequest, historyBriefInfoResponse);
		return historyBriefInfoResponse;
	}

	@Override
	public HistoryEventInfoResponse queryHistoryBriefDetialById(Long id) {
		HistoryEventInfoResponse historyEventInfoResponse = new HistoryEventInfoResponse();
		HistoryBriefEventInfo historyBriefEventInfo = historyEventDao.queryHistoryEventById(id);
		BeanUtils.copyProperties(historyBriefEventInfo, historyEventInfoResponse);
		return historyEventInfoResponse;
	}

}
