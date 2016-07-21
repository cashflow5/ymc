package com.yougou.merchant.api.supplier.service.impl;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yougou.merchant.api.supplier.dao.ContactsMapper;
import com.yougou.merchant.api.supplier.service.IContractNewApi;

@Service(value="contractNewApi")
public class ContractNewApi implements IContractNewApi {
	
	private static final Logger logger = Logger.getLogger(ContractNewApi.class);
	@Resource
	private ContactsMapper contactsMapper;
	@Override
	public Map<String, Integer> getSettlementType(List<String> merchantCodes) {
		logger.warn("入参为："+StringUtils.join(merchantCodes, ","));
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<Map<String, Object>> listMap = contactsMapper.getSettlementType(merchantCodes);
		if(CollectionUtils.isNotEmpty(listMap)){
			for(Map<String, Object> mp: listMap){
				map.put(MapUtils.getString(mp, "supplier_code"),MapUtils.getInteger(mp, "clearing_form"));
			}
		}
		logger.warn("返回结果："+StringUtils.join(map.entrySet(),","));
		return map;
	}

}
