package com.yougou.merchant.api.supplier.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.merchant.api.supplier.dao.BackOrderMapper;
import com.yougou.merchant.api.supplier.service.IBackOrderService;
import com.yougou.merchant.api.supplier.vo.BackOrderDetailVo;
import com.yougou.merchant.api.supplier.vo.BackOrderVo;

@Service(value="backOrderService")
public class BackOrderServiceImpl implements IBackOrderService {
	
	@Resource
	private BackOrderMapper backOrderMapper;
	
	public  Logger logger = LoggerFactory.getLogger(BackOrderServiceImpl.class);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public boolean addBackOrderFromWms(BackOrderVo backOrderVo) throws Exception {
		
		if(backOrderVo != null){
			logger.info("WMS系统调用addBackOrderFromWms接口保存退回单信息:{}", backOrderVo);
			try {
				int count = backOrderMapper.queryBackOrderCountById(backOrderVo.getId());
				if(count <= 0){
					//保存退回单
					backOrderMapper.insertBackOrderInfo(backOrderVo);
					List<BackOrderDetailVo> detailList = backOrderVo.getDetailList();
					//保存退回单详情
					if(detailList !=null && detailList.size() >0 ){
						backOrderMapper.batchInsertBackOrderDetailInfo(detailList);		
					}	
				}else{
					logger.warn("WMS系统调用addBackOrderFromWms接口保存退回单信息重复操作，退回单已存在，退回单：{}",backOrderVo.getBackCode());
				}
			} catch (Exception e) {
				logger.error("WMS系统调用addBackOrderFromWms接口保存退回单信息异常：", e);
				throw new Exception(e);
			}			
		}
		
		return true;
	}
	
	
}
