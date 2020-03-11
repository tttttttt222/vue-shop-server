package com.vueshop.manager.dao.mapper;

import com.vueshop.manager.controller.http.request.GoodsInfoRequest;
import com.vueshop.manager.dao.model.GoodsInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/3
 */
public interface GoodsInfoDao {

	List<GoodsInfo> queryGoodsPage(@Param("query") GoodsInfoRequest goodsInfoRequest,
			@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

	Integer queryGoodsPageCount(@Param("query") GoodsInfoRequest goodsInfoRequest);

	GoodsInfo queryGoodsInfoById(Long id);

	void insertGoodsInfo(@Param("goodsInfo") GoodsInfoRequest goodsInfoRequest);

	void deleteGoodsById(Long id);

	void updateGoodsInfo(@Param("goodsInfo") GoodsInfoRequest goodsInfoRequest);
}
