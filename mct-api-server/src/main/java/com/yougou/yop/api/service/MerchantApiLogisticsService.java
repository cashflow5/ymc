package com.yougou.yop.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yougou.dto.output.QueryExpressCompanyOuputDto;
import com.yougou.dto.output.QueryLogisticsCompanyOuputDto;
import com.yougou.outside.api.IOutsideOrderApiService;
import com.yougou.outside.api.domain.JitDelivery;
import com.yougou.outside.api.domain.JitDeliveryDetail;
import com.yougou.tools.common.utils.DateUtil;
import com.yougou.wms.wpi.common.exception.WPIBussinessException;
import com.yougou.wms.wpi.expresssoc.domain.ExpressSocDomain;
import com.yougou.wms.wpi.expresssoc.service.IExpressSocDomainService;
import com.yougou.wms.wpi.logisticscompany.domain.LogisticsCompanyDomain;
import com.yougou.wms.wpi.logisticscompany.service.ILogisticsCompanyDomainService;
import com.yougou.yop.api.IMerchantApiLogisticsService;

@Service(value="merchantApiLogisticsService")
public class MerchantApiLogisticsService implements IMerchantApiLogisticsService {

	private static final Log logger = LogFactory.getLog(MerchantApiLogisticsService.class);
	
	@Resource
	private ILogisticsCompanyDomainService logisticsCompanyService;
	
	@Resource
	private IExpressSocDomainService expressSocService;
	
	@Resource
	private IOutsideOrderApiService outsideOrderApiService;
	
	@Override
	public List<QueryLogisticsCompanyOuputDto> getLogisticscompany()
			throws Exception {
		com.yougou.wms.wpi.common.pagefinder.PageFinder<LogisticsCompanyDomain> pageFinder = logisticsCompanyService.queryPageFinderLogisticsCompany(1, Integer.MAX_VALUE, 1, 2, null);
		if (pageFinder == null) {
			throw new RuntimeException("系统内部异常, 请稍后再试.");
		}
		List<LogisticsCompanyDomain> list = pageFinder.getData();
		List<QueryLogisticsCompanyOuputDto> querydtos = new ArrayList<QueryLogisticsCompanyOuputDto>();
		QueryLogisticsCompanyOuputDto dto = null;
		if (CollectionUtils.isNotEmpty(list)) {
			for (LogisticsCompanyDomain domain : list) {
				dto = new QueryLogisticsCompanyOuputDto();
				dto.setLogistics_company_code(domain.getLogisticCompanyCode());
				dto.setLogistics_company_name(domain.getLogisticsCompanyName());
				querydtos.add(dto);
			}
		}
		
		return querydtos;
	}

	@Override
	public List<QueryExpressCompanyOuputDto> getExpresscompany()
			throws Exception {
		List<QueryExpressCompanyOuputDto> list = null;
		try {
			List<ExpressSocDomain> expresss = expressSocService.getExpressSocDomain();
			if (CollectionUtils.isNotEmpty(expresss)) {
				list = new ArrayList<QueryExpressCompanyOuputDto>();
				QueryExpressCompanyOuputDto dto = null;
				for (ExpressSocDomain express : expresss) {
					dto = new QueryExpressCompanyOuputDto();
					dto.setExpress_company_code(express.getExpressNo());
					dto.setExpress_company_name(express.getExpressName());
					list.add(dto);
				}
			}
		} catch (WPIBussinessException e) {
			logger.error("query expressSocService list exception : ", e);
			throw new RuntimeException("系统内部异常, 请稍后再试.");
		} 
		
		return list;
	}

	@Override
	public List<String> getStorageNo(Map<String, Object> parameterMap)
			throws Exception {
		// TODO Auto-generated method stub
		String poNo = (String) parameterMap.get("po_no");  // po 单号
		String warehouseCode = (String) parameterMap.get("warehouse"); //唯品会仓编码
		String sizeStr = (String) parameterMap.get("size"); // 每次获取的数量
		int size = 0;
		try{
			size = Integer.parseInt(sizeStr);
		}catch(Exception e){
			size=20;
			logger.error("唯品会JIT获取入库编号转换获取数量size exception : ", e);
		}
		List<String> storageNos= outsideOrderApiService.getStorageNo(poNo, warehouseCode, size);
		logger.warn("po_no："+poNo+"；warehoseCode:"+warehouseCode+"获取入库编码结果："+storageNos);
		return storageNos;
	}

	@Override
	public Boolean importDeliveryDetail(Map<String, Object> parameterMap) {
		// TODO Auto-generated method stub
		JitDelivery jitDelivery = new JitDelivery();
		String poNo = (String) parameterMap.get("po_no"); // po 单号
		String deliveryNo = (String) parameterMap.get("delivery_no"); //物流单号
		String warehouse = (String) parameterMap.get("warehouse"); //唯品会仓编码
		String arrivalTime = (String) parameterMap.get("arrival_time"); //要求到货时间
		String deliveryListStr = (String) parameterMap.get("delivery_list"); // 出仓单明细，即：发货明细
		String deliveryTime = (String) parameterMap.get("delivery_time"); // 发货时间
		String raceTime = (String) parameterMap.get("race_time"); // 预计收货时间
		String storageNo = (String) parameterMap.get("storage_no"); //入库编号
		String orderSubNo = (String) parameterMap.get("order_sub_no"); //优购子订单号		
		String carrierCode = (String) parameterMap.get("carrier_code"); //承运商编码	
		
		Date arrivalDate = DateUtil.parseDate(arrivalTime, "yyyy-MM-dd HH:mm:ss");
		Date deliveryDate = DateUtil.parseDate(deliveryTime, "yyyy-MM-dd HH:mm:ss");
		
		jitDelivery.setArrivalTime( arrivalDate);
		jitDelivery.setDeliveryNo(deliveryNo);
		jitDelivery.setDeliveryTime(deliveryDate);
		jitDelivery.setPoNo(poNo);
		jitDelivery.setStorageNo(storageNo);
		jitDelivery.setYougouOrderNO(orderSubNo);		
		jitDelivery.setWarehouseCode(warehouse);
		jitDelivery.setCarrierCode(carrierCode);
		
		List<JitDeliveryDetail> deliveryDetailList = getDeliveryDetailList(
				poNo, deliveryListStr, storageNo);
		jitDelivery.setDetailList(deliveryDetailList);
		;
		logger.warn("po_no："+poNo+"；warehoseCode:"+warehouse+"导入出仓单明细参数："+ToStringBuilder.reflectionToString(jitDelivery));
		Boolean result = outsideOrderApiService.importDeliveryDetail(jitDelivery);
		logger.warn("po_no："+poNo+"；warehoseCode:"+warehouse+"导入出仓单明细结果："+result);
		return result;
	}
	
	/**
	 * 从出仓单明细字段中拆分获取明细信息封装至DTO中
	 * @param poNo
	 * @param deliveryListStr
	 * @param storageNo
	 * @return
	 */
	private List<JitDeliveryDetail> getDeliveryDetailList(String poNo,
			String deliveryListStr, String storageNo) {
		List<Map<String,Object>> deliveryList = new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(deliveryListStr)){			
			String[] deliveryArr = deliveryListStr.trim().split("#");
			for(String delivery : deliveryArr){
				Map<String,Object> map = new HashMap<String,Object>(); 
				String[] deliveryInfos = delivery.split(",");
				if(deliveryInfos.length!=4){
					 logger.error("po_no："+poNo+";storage_no:"+storageNo + "在导入出仓单明细时，明细数据格式不正确！");
					 throw new RuntimeException("po_no："+poNo+";storage_no:"+storageNo + "在导入出仓单明细时，明细数据格式不正确！");
				}
				for(String deliveryInfo : deliveryInfos){
					String[] deliveryEntry = deliveryInfo.split("=");
					map.put(deliveryEntry[0], deliveryEntry[1]);
				}
				deliveryList.add(map);
			}
		}else{
			 logger.error("po_no："+poNo+";storage_no:"+storageNo + "在导入出仓单明细时，明细为空！");
			 throw new RuntimeException("po_no："+poNo+";storage_no:"+storageNo + "在导入出仓单明细时，明细为空！");
		}
		
		List<JitDeliveryDetail> deliveryDetailList = new ArrayList<JitDeliveryDetail>();
		for(Map<String,Object> map : deliveryList){
			JitDeliveryDetail deliveryDetail = new JitDeliveryDetail();
			int amount = 0;
			try{
				 amount = Integer.parseInt((String) map.get("amount"));
			}catch(Exception e){
				 logger.error("po_no："+poNo+";storage_no:"+storageNo + "在导入出仓单明细时，商品数量不是整数数字格式！");
				 throw new RuntimeException("po_no："+poNo+";storage_no:"+storageNo + "在导入出仓单明细时，商品数量不是整数数字格式！");
			}
			deliveryDetail.setAmount(String.valueOf(amount));
			deliveryDetail.setBarcode((String)map.get("barcode"));
			deliveryDetail.setBoxNo((String)map.get("box_no"));
			deliveryDetail.setPickNo((String)map.get("pick_no"));
			deliveryDetailList.add(deliveryDetail);
		}
		return deliveryDetailList;
	}
	
	public static void main(String[] args) {
		
		Date date = DateUtil.parseDate("2016-07-09 12:10:09", "yyyy-MM-dd HH:mm:ss");
		System.out.println(DateUtil.format(date,"yyyy-MM-dd HH:mm:ss"));
	}

}
