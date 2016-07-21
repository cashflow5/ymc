package com.yougou.merchant.api.active.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yougou.merchant.api.active.dao.MerchantOfficialActiveMapper;
import com.yougou.merchant.api.active.service.IMerchantOfficialActiveService;
import com.yougou.merchant.api.active.vo.ActivityEnrollMerchantDetailsVo;
import com.yougou.merchant.api.active.vo.ActivityEnrollMerchantVo;
import com.yougou.merchant.api.active.vo.MerchanteEnrollxamineVo;
import com.yougou.merchant.api.active.vo.QueryActivityVo;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.dao.MerchantSupplierExpandMapper;
@Service(value="merchantOfficialActiveServiceImpl")
public class MerchantOfficialActiveServiceImpl implements IMerchantOfficialActiveService{

	@Resource
	private MerchantOfficialActiveMapper merchantOfficialActiveMapper;
	
	@Resource
	private MerchantSupplierExpandMapper merchantSupplierExpandMapper;
	
	private static final Logger logger = Logger.getLogger(MerchantOfficialActiveServiceImpl.class);
	
	@Override
	public List<ActivityEnrollMerchantVo> queryActivityEnrollMerchant(QueryActivityVo vo ,Query query) {
		logger.error("[merchantCode]:"+vo.getMerchantCode()+"[activeId]"+vo.getActiveId()+"[commodityNo]"+vo.getCommodityNo()+"【merchantName】"+vo.getMerchantCode());
		try {
			Map<String,Object>   map=new HashMap<String, Object>();
			map.put("merchantName", vo.getMerchantName());
			map.put("merchantCode", vo.getMerchantCode());
			map.put("activeId", vo.getActiveId());
			map.put("commodityNo", vo.getCommodityNo());
			map.put("start", query.getOffset());
			map.put("pageSize", query.getPageSize());
			List<ActivityEnrollMerchantVo>   enrollMerchantList=new ArrayList<ActivityEnrollMerchantVo>();
			List<ActivityEnrollMerchantVo>  merchantList= merchantOfficialActiveMapper.queryActivityEnrollMerchant(map);
			orderByActivity(enrollMerchantList,merchantList);
			return enrollMerchantList;
			
		} catch (Exception e) {
			logger.error("queryActivityEnrollMerchant"+e);
		}
		return null;
	}
	private void  orderByActivity(List<ActivityEnrollMerchantVo>   enrollMerchantList,List<ActivityEnrollMerchantVo>   merchantList){
		if(merchantList!=null && merchantList.size()>0){
			for (int i = 0; i < merchantList.size(); i++) {
				ActivityEnrollMerchantVo  vo=merchantList.get(i);
				if("2".equals(vo.getStatus())){
					enrollMerchantList.add(vo);
				}
				
			}
			for (int i = 0; i < merchantList.size(); i++) {
				ActivityEnrollMerchantVo  vo=merchantList.get(i);
				if(!"2".equals(vo.getStatus())){
					enrollMerchantList.add(vo);
				}
				
			}
			
		}
		
	}
	@Override
	public List<ActivityEnrollMerchantDetailsVo> queryActivityEnrollMerchantDetails(QueryActivityVo vo ,Query  query) {
		logger.error("[merchantCode]:"+vo.getMerchantCode()+"[activeId]"+vo.getActiveId()+"[commodityNo]"+vo.getCommodityNo()+"【merchantName】"+vo.getMerchantCode());
		try {
			Map<String,Object>   map=new HashMap<String, Object>();
			map.put("commodityNo", vo.getCommodityNo());
			map.put("merchantName", vo.getMerchantName());
			map.put("merchantCode", vo.getMerchantCode());
			map.put("activeName", vo.getActiveName());
			map.put("activeId", vo.getActiveId());
			map.put("start", query.getOffset());
			map.put("pageSize", query.getPageSize());
			return merchantOfficialActiveMapper.queryActivityEnrollMerchantDetails(map);
		} catch (Exception e) {
			logger.error("queryActivityEnrollMerchantDetails"+e);
		}
		return null;
	}

	@Override
	public MerchanteEnrollxamineVo merchanteEnrollExamine(List<String>  examineList,String status,String auditRemark,String activeId) {
		logger.error("[status]:"+status+"[auditRemark]"+auditRemark);
		MerchanteEnrollxamineVo vo=new MerchanteEnrollxamineVo();
		try {
			Map<String,Object>   map=new HashMap<String, Object>();
			map.put("examineList", examineList);
			map.put("status", status);
			map.put("auditRemark", auditRemark);
			map.put("activeId", activeId);
			//检查是否有异常信息
			List<Map<String,Object>>  list=merchantOfficialActiveMapper.checkExamineEnrollInfo(map);
			if(list!=null && list.size()>0){
				StringBuffer sb=new StringBuffer();
				sb.append("审批异常:");
				for (int i = 0; i < list.size() ; i++) {
					sb.append((list.get(i).get("active_name"))+",");
				}
				vo.setSuccess(false);
				vo.setErrorMsg(sb.toString());
			}else{
				merchantOfficialActiveMapper.merchanteEnrollExamine(map);
				vo.setSuccess(true);
			}
		} catch (Exception e) {
			vo.setSuccess(false);
			vo.setErrorMsg("审批失败");
			logger.error("merchanteEnrollExamine"+e);
		}
		return vo;
	}

	@Override
	public int queryActivityEnrollMerchantCount(QueryActivityVo vo) {
		try {
			logger.error("[merchantCode]:"+vo.getMerchantCode()+"[activeId]"+vo.getActiveId()+"[commodityNo]"+vo.getCommodityNo()+"【merchantName】"+vo.getMerchantCode());
			Map<String,Object>   map=new HashMap<String, Object>();
			map.put("merchantName", vo.getMerchantName());
			map.put("merchantCode", vo.getMerchantCode());
			map.put("activeId", vo.getActiveId());
			map.put("commodityNo", vo.getCommodityNo());
			return merchantOfficialActiveMapper.queryActivityEnrollMerchantCount(map);
		} catch (Exception e) {
			logger.error("queryActivityEnrollMerchantCount："+e);
		}
		return 0;
	}

	@Override
	public int queryActivityEnrollMerchantDetailsCount(QueryActivityVo vo) {
		logger.error("[merchantCode]:"+vo.getMerchantCode()+"[activeId]"+vo.getActiveId()+"[commodityNo]"+vo.getCommodityNo()+"【merchantName】"+vo.getMerchantCode());
		try {
			Map<String,Object>   map=new HashMap<String, Object>();
			map.put("commodityNo", vo.getCommodityNo());
			map.put("merchantName", vo.getMerchantName());
			map.put("activeName", vo.getActiveName());
			map.put("activeId", vo.getActiveId());
			map.put("merchantCode", vo.getMerchantCode());
			
			return merchantOfficialActiveMapper.queryActivityEnrollMerchantDetailsCount(map);
			
		} catch (Exception e) {
			logger.error("queryActivityEnrollMerchantDetailsCount"+e);
		}
		return 0;
	}

//	@Override
//	public int queryCouponAmount(QueryActivityVo vo) {
//		logger.error("[merchantCode]:"+vo.getMerchantCode()+"[activeId]"+vo.getActiveId()+"[commodityNo]"+vo.getCommodityNo());
//		try {
//			Map<String,Object>   map=new HashMap<String, Object>();
//			map.put("activeId", vo.getActiveId());
//			map.put("merchantCode", vo.getMerchantCode());
//			map.put("commodityNo", vo.getCommodityNo());
//			return merchantOfficialActiveMapper.queryCouponAmount(map);
//		} catch (Exception e) {
//			logger.error("queryCouponAmount"+e);
//		}
//		return 0;
//	}
	
	@Override
	public int queryCouponAmount(QueryActivityVo vo) {
		logger.error("[merchantCode]:"+vo.getMerchantCode()+"[activeId]"+vo.getActiveId()+"[commodityNo]"+vo.getCommodityNo());
		int result = 0;
		if( StringUtils.isNotEmpty(vo.getActiveId()) ){
			
			try {
				Map<String,Object>   map=new HashMap<String, Object>();
				map.put("activeId", vo.getActiveId());
				map.put("merchantCode", vo.getMerchantCode());
				map.put("commodityNo", vo.getCommodityNo());
				result =  merchantOfficialActiveMapper.queryCouponAmount(map);
			} catch (Exception e) {
				logger.error("queryCouponAmount-step1:"+e);
			}
		}
		
		if( result==0 ){
			try{
				result = merchantSupplierExpandMapper.queryCouponAmountByMerchantCode( vo.getMerchantCode() );
			} catch (Exception e) {
				logger.error("queryCouponAmount-step2:"+e);
			}
			
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryAmountMap(Map<String, Object> map) {
		try {
			List<Map<String, Object>>   list=new ArrayList<Map<String, Object>>(); 
			if(map!=null){
				  Iterator<String> keys = map.keySet().iterator();
				   while(keys.hasNext()) {
					   Map<String, Object>   activeMap=new HashMap<String, Object>();
					   String commodityNo = (String) keys.next();
					   String activeId=(String)map.get(commodityNo);
					   activeMap.put("commodityNo", commodityNo);
					   activeMap.put("activeId", activeId);
					   list.add(activeMap);
				  } 
			}
			Map<String,List<Map<String, Object>>>   queryMap=new HashMap<String, List<Map<String, Object>>>();
			queryMap.put("activeIdList", list);
			return merchantOfficialActiveMapper.queryAmountMap(queryMap);
		} catch (Exception e) {
			logger.error("queryAmountMap"+e);
		}
		return null;
	}
	
	
	

}
