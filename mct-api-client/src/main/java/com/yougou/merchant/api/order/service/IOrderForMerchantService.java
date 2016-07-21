package com.yougou.merchant.api.order.service;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.order.vo.QueryAbnormalSaleApplyVo;

/**
 * 招商订单 service
 * 
 * @author mei.jf
 * 
 */
public interface IOrderForMerchantService {

    /**
     * 获取异常售后申请列表
     * 
     * @param query
     *            分页信息
     * @param vo
     *            查询条件vo
     * @return
     */
    public PageFinder<QueryAbnormalSaleApplyVo> getAbnormalSaleApplyList(Query query, QueryAbnormalSaleApplyVo vo) throws Exception;
}
