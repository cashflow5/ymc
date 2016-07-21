package com.yougou.merchant.api.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.order.vo.QueryAbnormalSaleApplyVo;
import com.yougou.ordercenter.model.order.OrderBuyInfo;
import com.yougou.ordercenter.model.order.OrderConsigneeInfo;
import com.yougou.ordercenter.model.order.OrderSub;
import com.yougou.ordercenter.model.order.OrderSubExpand;

public interface MerchantOrderMapper {

    public OrderConsigneeInfo findByOrderConsigneeInfoId(String consigneeId);

    public OrderBuyInfo findByOrderBuyInfoId(String buyId);

    public List<Map<String, Object>> selectMerchantCancleSaleOrder(Map<String, Object> params);

    public List<OrderSub> selectMerchantOrderList(Map<String, String> map);

    public OrderSubExpand findOrderSubExpandByOrderSubId(String orderSubId);

    public List<OrderSub> queryMerchantOrderList(Map<String, String> map, RowBounds rounds);

    public int queryMerchantOrderCount(Map<String, String> map);

    public List<OrderBuyInfo> getOrderBuyInfoByIds(Map<String, List<String>> map);

    public List<OrderConsigneeInfo> getOrderConsigneeInfoByIds(Map<String, List<String>> map);

    public List<OrderSubExpand> getOrderSubExpandByOrderSubIds(Map<String, List<String>> map);

    public List<QueryAbnormalSaleApplyVo> getAbnormalSaleApplyList(QueryAbnormalSaleApplyVo vo, RowBounds row);

    public Integer getAbnormalSaleApplyListCount(QueryAbnormalSaleApplyVo vo);
}
