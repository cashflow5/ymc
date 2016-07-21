/**
 * 
 */
package com.yougou.merchant.api.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.order.dao.MerchantOrderMapper;
import com.yougou.merchant.api.order.service.IOrderForMerchantService;
import com.yougou.merchant.api.order.vo.QueryAbnormalSaleApplyVo;

/**
 * @author mei.jf
 * 
 */
@Service(value = "orderForMerchantService")
public class OrderForMerchantService implements IOrderForMerchantService {

    @Resource
    private MerchantOrderMapper merchantOrderDao;

    @Override
    public PageFinder<QueryAbnormalSaleApplyVo> getAbnormalSaleApplyList(Query query, QueryAbnormalSaleApplyVo vo) throws Exception {

        int count = merchantOrderDao.getAbnormalSaleApplyListCount(vo);
        PageFinder<QueryAbnormalSaleApplyVo> pageFinder = null;
        if (count > 0) {
            List<QueryAbnormalSaleApplyVo> list = merchantOrderDao.getAbnormalSaleApplyList(vo, new RowBounds(query.getOffset(), query.getPageSize()));
            pageFinder = new PageFinder(query.getOffset(), query.getPageSize(), count, list);
        }
        return pageFinder;
    }

}
