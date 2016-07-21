package com.yougou.merchant.api.supplier.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.merchant.api.common.Constant;
import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.supplier.dao.MerchantMapper;
import com.yougou.merchant.api.supplier.dao.RejectedAddressMapper;
import com.yougou.merchant.api.supplier.dao.SupplierContractMapper;
import com.yougou.merchant.api.supplier.dao.SupplierMapper;
import com.yougou.merchant.api.supplier.service.ISupplierService;
import com.yougou.merchant.api.supplier.vo.MerchantBrandDeducRate;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog.OperationType;
import com.yougou.merchant.api.supplier.vo.MerchantRejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.RejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.SupplierContract;
import com.yougou.merchant.api.supplier.vo.SupplierQueryVo;
import com.yougou.merchant.api.supplier.vo.SupplierVo;
import com.yougou.pay.vo.TradeCurrencyEnum;
import com.yougou.tools.common.utils.DateUtil;
import com.yougou.wms.wpi.warehouse.domain.WarehouseDomain;
import com.yougou.wms.wpi.warehouse.service.IWarehouseCacheService;

@Service(value="supplierService")
public class SupplierServiceImpl implements ISupplierService {
	
	private static final Logger logger = Logger.getLogger(SupplierServiceImpl.class);
	private static final String ALL_SUPPLIER = "ALL_SUPPLIER";
	private static final String SIGN_SUPPLIER = "SIGN_SUPPLIER";
	/*private static final String SIGN_SUPPLIER_DISTRIBUTEDBY = "SIGN_SUPPLIER_DISTRIBUTEDBY";//缓存发货方
	private static final String SIGN_SUPPLIER_ISINVOICE = "SIGN_SUPPLIER_ISINVOICE";//缓存商家是否开票
*/	private static final String SIGN_SUPPLIER_DELIVERY_TYPE = "SIGN_SUPPLIER_DELIVERY_TYPE";//缓存是否海外发货
	private static final String REJECTED_ADDRESS = "REJECTED_ADDRESS";
	private static final int TIME_OUT = 5;

	private static final String DEVLIVERY_TYPE_DOMESTIC = "1";// 国内发货类型
	
	@Resource
	private SupplierMapper supplierMapplier;

	@Resource
	private RejectedAddressMapper rejectedAddressMapplier;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private IWarehouseCacheService warehouseCacheService;
	
	@Resource
	private MerchantMapper merchantMapper;
	
	@Resource
	private SupplierContractMapper supplierContractMapper;

	private SupplierVo getCacheSupplierByMerchant(String merchantCode) {
		SupplierVo supplier = (SupplierVo) redisTemplate.opsForHash().get(SIGN_SUPPLIER, merchantCode);
		if (supplier == null) {
			supplier = this.supplierMapplier.getSupplierByMerchantCode(merchantCode);
			this.redisTemplate.opsForHash().put(SIGN_SUPPLIER, merchantCode, supplier);
			this.redisTemplate.expire(SIGN_SUPPLIER, TIME_OUT, TimeUnit.MINUTES);
		}
		return supplier;
	}
	
	/*private Integer getCacheIsInputYougouWarehouseByMerchant(String merchantCode) {
		Integer distributionSide = (Integer) redisTemplate.opsForHash().get(SIGN_SUPPLIER_DISTRIBUTEDBY, merchantCode);
		if (distributionSide == null) {
			distributionSide = this.supplierMapplier.getSupplierIsInputYougouWarehouse(merchantCode);
			this.redisTemplate.opsForHash().put(SIGN_SUPPLIER_DISTRIBUTEDBY, merchantCode, distributionSide);
			this.redisTemplate.expire(SIGN_SUPPLIER_DISTRIBUTEDBY, TIME_OUT, TimeUnit.MINUTES);
		}
		return distributionSide;
	}
	
	private Integer getCacheIsInvoiceByMerchant(String merchantCode) {
		Integer isInvoice = (Integer) redisTemplate.opsForHash().get(SIGN_SUPPLIER_ISINVOICE, merchantCode);
		if (isInvoice == null) {
			isInvoice = this.supplierMapplier.getSupplierIsInvoice(merchantCode);
			this.redisTemplate.opsForHash().put(SIGN_SUPPLIER_ISINVOICE, merchantCode, isInvoice);
			this.redisTemplate.expire(SIGN_SUPPLIER_ISINVOICE, TIME_OUT, TimeUnit.MINUTES);
		}
		return isInvoice;
	}
	*/
	private String getCacheDeliveryTypeByMerchant(String merchantCode) {
		String deliveryType = (String) redisTemplate.opsForHash().get(SIGN_SUPPLIER_DELIVERY_TYPE ,merchantCode);
		if (deliveryType == null) {
			deliveryType = this.supplierMapplier.getSupplierDeliveryType(merchantCode);
			if(deliveryType == null){
				deliveryType = DEVLIVERY_TYPE_DOMESTIC;// 默认国内发货
			}
			this.redisTemplate.opsForHash().put(SIGN_SUPPLIER_DELIVERY_TYPE, merchantCode, deliveryType);
			this.redisTemplate.expire(SIGN_SUPPLIER_DELIVERY_TYPE, TIME_OUT, TimeUnit.MINUTES);
		}
		return deliveryType;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SupplierVo> getAllSupplier() {
	    //logger.warn("[merchantCode]:getAllSupplier");
	    List<SupplierVo> lstSuppliers = (List<SupplierVo>) redisTemplate.opsForHash().get(ALL_SUPPLIER, ALL_SUPPLIER);
	    if (lstSuppliers == null) {
	      lstSuppliers = this.supplierMapplier.getAllSupplier();
	      redisTemplate.opsForHash().put(ALL_SUPPLIER, ALL_SUPPLIER, lstSuppliers);
	      redisTemplate.expire(ALL_SUPPLIER, TIME_OUT, TimeUnit.MINUTES);
	    }
	    return lstSuppliers;
	}

	/**
	 * 根据供应商（商家）编码返回订单配送方
	 * 
	 * @param merchantCode
	 * @return （0、优购配送；1、商家配送优购开发票；2、商家配送并开具发票 3、表示由商家海外直发，不开发票）
	 * @throws Exception [商家编码为KOREA、SPYG的，不做任何查询，直接抛出异常 ]
	 */
	@Override
	public Integer getSupplierDistributionSide(String merchantCode) throws Exception {
		if(StringUtils.isEmpty(merchantCode) 
				|| Constant.MERCHANT_CODE_BELLE.equalsIgnoreCase(merchantCode.trim() )
						|| Constant.MERCHANT_CODE_KOREA.equalsIgnoreCase(merchantCode.trim() ) ) {
			logger.error("[merchantCode]:getSupplierDistributionSide="+merchantCode);
			throw new Exception("请输入商家编码，并且确认是招商供应商的商家编码！");
		}
		Integer distributionSide = null;
		String deliveryType = "";
		SupplierVo supplier = getCacheSupplierByMerchant(merchantCode);
		if (supplier != null) {
			distributionSide = supplier.getIsInputYougouWarehouse();
			deliveryType = supplier.getDeliveryType();
		}
		if (distributionSide == null ) {
			logger.error("找不到该供应商信息[merchantCode]:getSupplierDistributionSide="+merchantCode);
			throw new Exception("找不到该供应商信息");
		}
		
		if (distributionSide == 1 || distributionSide == 2) {
			return 0;
		}
		if (deliveryType != null && Constant.DELIVERY_TYPE_OVERSEAS.equals(deliveryType.trim()) ) {
			return 3;
		}
		if (supplier.getIsInvoice() == null || supplier.getIsInvoice() == 0) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * 根据商家编码获取供应商对象
	 * @param merchantCode
	 * @return
	 */
	public SupplierVo getSupplierByMerchantCode(String merchantCode)  throws Exception {
		if(StringUtils.isEmpty(merchantCode)) {
			logger.error("[merchantCode]:getSupplierByMerchantCode="+merchantCode);
			throw new Exception("请输入商家编码！");
		}
	    SupplierVo supplier = getCacheSupplierByMerchant(merchantCode);
	    if (supplier == null) {
	      logger.error("不存在当前供应商，请核对您的供应商编码[merchantCode]:getSupplierByMerchantCode="+merchantCode);
	      throw new Exception("不存在当前供应商，请核对您的供应商编码！");
	    }
		return supplier;
	}
	
	/**
	 * 根据商家编码获取供应商币种(默认人民币)
	 * @param merchantCode
	 * @return
	 */
	public TradeCurrencyEnum getTradeCurrencyByMerchantCode(String merchantCode)  throws Exception {
		if(StringUtils.isEmpty(merchantCode)) {
			logger.error("[merchantCode]:getTradeCurrencyByMerchantCode="+merchantCode);
			throw new Exception("请输入商家编码！");
		}
	    SupplierVo supplier = getCacheSupplierByMerchant(merchantCode);
	    if (supplier == null) {
	    	logger.error("不存在当前供应商，请核对您的供应商编码[merchantCode]:getTradeCurrencyByMerchantCode="+merchantCode);
	      throw new Exception("不存在当前供应商，请核对您的供应商编码！");
	    }
		if(supplier.getTradeCurrency() == null || "".equals(supplier.getTradeCurrency())){
			return TradeCurrencyEnum.TRADE_CURRENCY_CNY;
		}
		return TradeCurrencyEnum.valueOf(supplier.getTradeCurrency());
	}

	/**
	 * 根据商家编码获取退换货地址
	 * @param merchantCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public RejectedAddressVo getSupplierRejectedAddress(String merchantCode) throws Exception {
		if(StringUtils.isEmpty(merchantCode)) {
			logger.error("[merchantCode]:getSupplierRejectedAddress="+merchantCode);
			throw new Exception("请输入商家编码！");
		}
		RejectedAddressVo rejectedAddress = (RejectedAddressVo) this.redisTemplate.opsForHash().get(REJECTED_ADDRESS, merchantCode);
		if (rejectedAddress == null) {
			rejectedAddress = this.rejectedAddressMapplier.getSupplierRejectedAddress(merchantCode);
			redisTemplate.opsForHash().put(REJECTED_ADDRESS, merchantCode, rejectedAddress);
			redisTemplate.expire(REJECTED_ADDRESS, TIME_OUT, TimeUnit.MINUTES);
		}
		if(rejectedAddress == null) {
			logger.error("不存在当前商家的退换货地址[merchantCode]:getSupplierRejectedAddress="+merchantCode);
			throw new Exception("不存在当前商家的退换货地址！");
		}
		return rejectedAddress;
	}
	
	/**
	 * 根据商家编码获取退换货地址
	 * @param merchantCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public RejectedAddressVo getMerchantRejectedAddress(String merchantCode) throws Exception {
		if(StringUtils.isEmpty(merchantCode)) {
			logger.error("[merchantCode]:getMerchantRejectedAddress="+merchantCode);
			throw new Exception("请输入商家编码！");
		}
		RejectedAddressVo rejectedAddress = rejectedAddressMapplier.getSupplierRejectedAddress(merchantCode);
		return rejectedAddress;
	}
	/**
	 * 根据商家编码获取退换货地址(包含优购自营)
	 * @param merchantCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public RejectedAddressVo getContainYougouRejectedAddress(String merchantCode) throws Exception {
		RejectedAddressVo rejectedAddress = getSupplierRejectedAddress(merchantCode);
		if(rejectedAddress == null) {
			rejectedAddress = new RejectedAddressVo();
			// 校验商家是否绑定虚拟仓库
			Map<String, ?> temporaryMap = warehouseCacheService.getWarehouseByMerchantCode(merchantCode);
			if (MapUtils.isEmpty(temporaryMap)) {
				logger.error("供应商未绑定虚拟仓库." + merchantCode);
				throw new Exception("供应商未绑定虚拟仓库");
			}
			String warehouseCode = temporaryMap.keySet().iterator().next();
			WarehouseDomain warehouseDomain = warehouseCacheService.getWarehouse(warehouseCode);
			rejectedAddress.setSupplierCode(merchantCode);
			rejectedAddress.setSupplierName(warehouseDomain.getMerchantName());
			rejectedAddress.setConsigneeName(warehouseDomain.getReceiver());
			rejectedAddress.setConsigneePhone(warehouseDomain.getMobilePhone());
			rejectedAddress.setConsigneeTell(warehouseDomain.getTelPhone());
			rejectedAddress.setWarehouseAdress(warehouseDomain.getWarehouseAddress());
			rejectedAddress.setWarehouseArea("");
			rejectedAddress.setWarehousePostcode(warehouseDomain.getZipCode());
		}
		return rejectedAddress;
	}
	
	/**
	 * 更新当前商家的仓库编码	
	 * @param merchantCode
	 * @param warehouseCode
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean updateMerchantWarehouseCode(String merchantCode, String warehouseCode, String updateUser) throws Exception {
		if (StringUtils.isEmpty(merchantCode)) {
			logger.error("请输入商家编码[merchantCode]:updateMerchantWarehouseCode=" + merchantCode);
			throw new Exception("请输入商家编码！");
		}
		if (StringUtils.isEmpty(warehouseCode)) {
			logger.error("请输入仓库编码[warehouseCode]:updateMerchantWarehouseCode=" + warehouseCode);
			throw new Exception("请输入仓库编码！");
		}
		if (StringUtils.isEmpty(updateUser)) {
			logger.error("请输入更新操作人[updateUser]:updateMerchantWarehouseCode=" + updateUser);
			throw new Exception("请输入更新操作人！");
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date updateDate = format.parse(format.format(new Date()));
		this.supplierMapplier.updateSupplierOtherWarehouseCodeNull(
				warehouseCode, updateUser, updateDate);
		this.supplierMapplier.updateSupplierWarehouseCode(merchantCode,
				warehouseCode, updateUser, updateDate);
		return true;
	}

	@Override
	public List<SupplierVo> querySupplierByVo(SupplierVo vo) throws Exception {
		return supplierMapplier.querySupplierByVo(vo);
	}

	@Override
	public SupplierVo getSupplierByName(String supplierName) throws Exception {
		List<SupplierVo> list = supplierMapplier.getSupplierByName(supplierName);
		if( null!= list && list.size()>0 ){
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageFinder<SupplierVo> querySupplierListByPage(SupplierQueryVo vo,
			Query query) throws Exception {
		List<SupplierVo> list = supplierMapplier.querySupplierListByPage(vo, new RowBounds(query.getOffset(), query.getPageSize()));
		Integer count = supplierMapplier.querySupplierListByCount(vo);
		PageFinder<SupplierVo> pageFinder = new PageFinder<SupplierVo>(query.getPage(), query.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public Map<String, String> getWarehouseByMerchantCode(String merchantCode)
			throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		Map<String, String> map = warehouseCacheService.getWarehouseByMerchantCode(merchantCode);
		if (MapUtils.isNotEmpty(map)) {
			for (String key : map.keySet()) {
				resultMap.put(Constant.WAREHOUSECODE, key);
				resultMap.put(Constant.WAREHOUSENAME, map.get(key));
				break;
			}
		}
		return resultMap;
	}
	
	@Override
	@Transactional
	public boolean saveMerchantRejectedAddress(MerchantRejectedAddressVo merchantRejectedAddress){
		String merchantCode = merchantRejectedAddress.getSupplierCode();
		String id = null;
		MerchantOperationLog operationLog = new MerchantOperationLog();
		operationLog.setId(UUIDGenerator.getUUID());
		operationLog.setMerchantCode(merchantCode);
		operationLog.setOperated(new Date());
		operationLog.setOperationType(OperationType.AFTER_SERVICE);
		operationLog.setOperator(merchantRejectedAddress.getCreaterPerson());
		try {
			if (merchantRejectedAddress != null && (id = merchantRejectedAddress.getId()) != null && id.trim().length() > 0) {
				RejectedAddressVo rejectedAddressVo = null;
				rejectedAddressVo = getMerchantRejectedAddress(merchantCode);
				StringBuffer content = new StringBuffer();
				if(rejectedAddressVo != null){
					String temp = merchantRejectedAddress.getConsigneeName();
					if(temp != null && !temp.equals(rejectedAddressVo.getConsigneeName())){
						content.append("收货人姓名由【").append(rejectedAddressVo.getConsigneeName()).append("】改成【").append(temp).append("】<br>");
					}
					temp = merchantRejectedAddress.getConsigneePhone();
					if(temp != null && !temp.equals(rejectedAddressVo.getConsigneePhone())){
						content.append("收货人手机由【").append(rejectedAddressVo.getConsigneePhone()).append("】改成【").append(temp).append("】<br>");
					}
					temp = merchantRejectedAddress.getConsigneeTell();
					if(temp != null && !temp.equals(rejectedAddressVo.getConsigneeTell())){
						content.append("收货人电话由【").append(rejectedAddressVo.getConsigneeTell()).append("】改成【").append(temp).append("】<br>");
					}
					temp = merchantRejectedAddress.getWarehousePostcode();
					if(temp != null && !temp.equals(rejectedAddressVo.getWarehousePostcode())){
						content.append("收货人邮编由【").append(rejectedAddressVo.getWarehousePostcode()).append("】改成【").append(temp).append("】<br>");
					}
					temp = merchantRejectedAddress.getWarehouseArea();
					if(temp != null && !temp.equals(rejectedAddressVo.getWarehouseArea())){
						content.append("收货人地区由【").append(rejectedAddressVo.getWarehouseArea()).append("】改成【").append(temp).append("】<br>");
					}
					temp = merchantRejectedAddress.getWarehouseAdress();
					if(temp != null && !temp.equals(rejectedAddressVo.getWarehouseAdress())){
						content.append("收货人地址由【").append(rejectedAddressVo.getWarehouseAdress()).append("】改成【").append(temp).append("】<br>");
					}
				}
				operationLog.setOperationNotes(content.toString());
				rejectedAddressMapplier.updateMerchantRejectedAddress(merchantRejectedAddress);
			} else {
				merchantRejectedAddress.setId(UUIDGenerator.getUUID());
				rejectedAddressMapplier.insertMerchantRejectedAddress(merchantRejectedAddress);
				operationLog.setOperationNotes("商家新增退货地址");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if(operationLog != null && !"".equals(operationLog.getOperationNotes())){
			merchantMapper.insertMerchantLog(operationLog);
		}
		return true;
	}
	
	public Map<String, Boolean> getMerchantIsYugouInvoice(List<String> merchantCodes) throws IllegalArgumentException {
		int size = 0;
		if(merchantCodes != null && !merchantCodes.isEmpty()) {
			size = merchantCodes.size();
		} else {
			throw new IllegalArgumentException("传入的商家编码列表为空！");
		}
		Map<String, Boolean> mapMerchantInvoice = new HashMap<String, Boolean>(size);
		for(String merchantCode : merchantCodes) {
			if(merchantCode != null) {
				if(!mapMerchantInvoice.containsKey(merchantCode)) {
					SupplierVo supplierVo = getCacheSupplierByMerchant(merchantCode);
					if(supplierVo != null && supplierVo.getIsInvoice() == 1){
						mapMerchantInvoice.put(merchantCode, false);
					} else {
						mapMerchantInvoice.put(merchantCode, true);
					}
				}
			} else {
				throw new IllegalArgumentException("传入的商家编码为" + merchantCode);
			}
		}
		return mapMerchantInvoice;
	}
	
	@Override
	public SupplierVo getSupplierById(String supplierId) throws Exception {
		return supplierMapplier.getSupplierById(supplierId);
	}
	
	/**
	 * 财务确认已收款。
	 * 
	 * @param contractNo 合同编号
	 * @param type deposit/usefee
	 * @param payStyle 1:本期收款 2:上期收款
	 * @return Map<String,String>.result = success/failure,Map.msg = "错误信息"，如果result=success，没有错误信息。
	 */
	@Override
	public Map<String, String> confirmContractReceivedPayment(String contractNo, String type, int payStyle){
		logger.error("财务调用接口确认已收款！contractNo="+contractNo +",type="+type);
		Map<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isBlank(contractNo)){
			resultMap.put("msg", "合同编号contractNo不能为空！");
			resultMap.put("result", "failure");
			logger.error("财务调用接口确认已收款：合同编号contractNo不能为空！");
			return resultMap;
		}
		if(!"deposit".equals(type) && !"usefee".equals(type)){
			resultMap.put("msg", "类型type不正确！type="+type);
			resultMap.put("result", "failure");
			logger.error("财务调用接口确认已收款：类型type不正确！type="+type);
			return resultMap;
		}
		try{
			SupplierContract supplierContract = supplierContractMapper.selectSupplierContractByContractNo(contractNo);
			if(null == supplierContract){
				resultMap.put("msg", "没找到合同编号对应的合同！contractNo="+contractNo);
				resultMap.put("result", "failure");
				logger.error("财务调用接口确认已收款：没找到合同编号对应的合同！contractNo="+contractNo);
				return resultMap;
			}
			
			if("deposit".equals(type)){
				boolean flag = true;
				if(1 == payStyle){
					if(!"1".equals(supplierContract.getDepositStatus()) && !"7".equals(supplierContract.getDepositStatus())){
						flag = false;
					}else if("7".equals(supplierContract.getDepositStatus()) || !"1".equals(supplierContract.getIsTransferDeposit())){
						supplierContract.setDepositStatus("2");
					}else{
						supplierContract.setDepositStatus("8");
					}
				}else if(2 == payStyle){
					if(!"1".equals(supplierContract.getDepositStatus()) && !"8".equals(supplierContract.getDepositStatus())){
						flag = false;
					}else if("8".equals(supplierContract.getDepositStatus()) || null == supplierContract.getDeposit() ||
							supplierContract.getDeposit().compareTo(BigDecimal.ZERO) <= 0){
						supplierContract.setDepositStatus("2");
					}else{
						supplierContract.setDepositStatus("7");
					}
				}				
				if(!flag){
					resultMap.put("msg", "合同的保证金收款状态不是待收款！contractNo="+contractNo);
					resultMap.put("result", "failure");
					logger.error("财务调用接口确认已收款：合同的保证金收款状态不是待收款！contractNo="+contractNo);
					return resultMap;
				}
			}
			
			if("usefee".equals(type) && !"1".equals(supplierContract.getUseFeeStatus())){
				resultMap.put("msg", "合同的使用费收款状态不是待收款！contractNo="+contractNo);
				resultMap.put("result", "failure");
				logger.error("财务调用接口确认已收款：合同的使用费收款状态不是待收款！contractNo="+contractNo);
				return resultMap;
			}
			if(2 == payStyle){
				SupplierContract parentContract = supplierContractMapper.selectSupplierContractByContractId(supplierContract.getParentContractId());
				if(!"6".equals(parentContract.getDepositStatus())){
					resultMap.put("msg", "上期合同的保证金收款状态不是转入下期中！contractNo="+contractNo);
					resultMap.put("result", "failure");
					logger.error("财务调用接口确认已收款：上期合同的保证金收款状态不是转入下期中！contractNo="+contractNo);
					return resultMap;
				}
				
				parentContract.setDepositStatus("5");
				parentContract.setUpdateTime(DateUtil.getDateTime(new Date(System.currentTimeMillis())));
				parentContract.setUpdateUser("财务调用接口");
				supplierContractMapper.updateSupplierContract(parentContract);
			}
			supplierContract.setUpdateTime(DateUtil.getDateTime(new Date(System.currentTimeMillis())));
			supplierContract.setUpdateUser("财务调用接口");
			if("usefee".equals(type)){
				supplierContract.setUseFeeStatus("2");
			}
			supplierContractMapper.updateSupplierContract(supplierContract);
		}catch(Exception e){
			resultMap.put("msg", "更新合同出现异常！contractNo="+contractNo);
			logger.error("财务调用接口确认已收款：更新合同出现异常！contractNo="+contractNo+e);
			resultMap.put("result", "failure");
			return resultMap;
		}
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 财务确认已退款 
	 * 
	 * @param contractNo 合同编号
	 * @param type deposit/usefee
	 * @return Map<String,String>.result = success/failure,Map.msg = "错误信息"，如果result=success，没有错误信息。
	 */
	public Map<String, String> confirmContractRefund(String contractNo, String type){
		logger.error("财务调用接口确认已退款！contractNo="+contractNo +",type="+type);
		Map<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isBlank(contractNo)){
			resultMap.put("msg", "合同编号contractNo不能为空！");
			resultMap.put("result", "failure");
			logger.error("财务调用接口确认已退款：合同编号contractNo不能为空！");
			return resultMap;
		}
		if(!"deposit".equals(type) && !"usefee".equals(type)){
			resultMap.put("msg", "类型type不正确！type="+type);
			resultMap.put("result", "failure");
			logger.error("财务调用接口确认已退款：类型type不正确！type="+type);
			return resultMap;
		}
		try{
			SupplierContract supplierContract = supplierContractMapper.selectSupplierContractByContractNo(contractNo);
			if(null == supplierContract){
				resultMap.put("msg", "没找到合同编号对应的合同！contractNo="+contractNo);
				resultMap.put("result", "failure");
				logger.error("财务调用接口确认已退款：没找到合同编号对应的合同！contractNo="+contractNo);
				return resultMap;
			}
			if("deposit".equals(type) && !"3".equals(supplierContract.getDepositStatus())){
				resultMap.put("msg", "合同的保证金收款状态不是退款中！contractNo="+contractNo);
				resultMap.put("result", "failure");
				logger.error("财务调用接口确认已退款：合同的保证金收款状态不是退款中！contractNo="+contractNo);
				return resultMap;
			}
			
			if("usefee".equals(type) && !"3".equals(supplierContract.getUseFeeStatus())){
				resultMap.put("msg", "合同的使用费收款状态不是退款中！contractNo="+contractNo);
				resultMap.put("result", "failure");
				logger.error("财务调用接口确认已退款：合同的使用费收款状态不是退款中！contractNo="+contractNo);
				return resultMap;
			}
			
			supplierContract.setUpdateTime(DateUtil.getDateTime(new Date(System.currentTimeMillis())));
			supplierContract.setUpdateUser("财务调用接口");
			if("deposit".equals(type)){
				supplierContract.setDepositStatus("4");
			}else if("usefee".equals(type)){
				supplierContract.setUseFeeStatus("4");
			}
			supplierContractMapper.updateSupplierContract(supplierContract);
		}catch(Exception e){
			resultMap.put("msg", "更新合同出现异常！contractNo="+contractNo);
			logger.error("财务调用接口确认已退款：更新合同出现异常！contractNo="+contractNo+e);
			resultMap.put("result", "failure");
			return resultMap;
		}
		resultMap.put("result", "success");
		return resultMap;
	}

	/**
	 * 获取发货方式：海外直发、国内发货
	 * @param merchantCode 商家编号
	 * @return ‘1’ 为国内发货， ‘2’ 为商家海外直发 
	 */
	@Override
	public String getDeliveryType(String merchantCode) throws Exception {
		if(StringUtils.isEmpty(merchantCode) 
				|| Constant.MERCHANT_CODE_BELLE.equalsIgnoreCase(merchantCode.trim() )
						|| Constant.MERCHANT_CODE_KOREA.equalsIgnoreCase(merchantCode.trim() ) ) {
			logger.error("[merchantCode]:isDeliveredOverseas="+merchantCode);
			throw new Exception("请输入真实存在的商家编码，并且确认是招商供应商的商家编码！");
		}
		String deliveryType = getCacheDeliveryTypeByMerchant(merchantCode);//发货类型 :'1' 国内发货  '2' 海外直发 
		if ( deliveryType  == null) {
//			logger.error("找不到该供应商的海外直发信息[merchantCode]:getDeliveryType="+merchantCode);
//			throw new Exception("找不到该供应商海外直发信息");
			return DEVLIVERY_TYPE_DOMESTIC;
		}else{
			return deliveryType;
		}
	}

	/**
	 * 查询供应商品牌扣点信息
	 */
	@Override
	public PageFinder<MerchantBrandDeducRate> queryMerchantBrandDeductRate(List<String> merchantCode, String updateTimeStart,String updateTimeEnd,
			Query query) throws Exception {
		int total = supplierContractMapper.queryMerchantBrandDeductRateCount(merchantCode, updateTimeStart, updateTimeEnd);
		List<MerchantBrandDeducRate> list = supplierContractMapper.queryMerchantBrandDeductRateList(merchantCode, updateTimeStart, updateTimeEnd, query);
		PageFinder<MerchantBrandDeducRate> pageFinder = new PageFinder<MerchantBrandDeducRate>(query.getPage(), 
				query.getPageSize(), total, list);
		return pageFinder;
	}


	/**
	 * 是否商家发货
	 * @param merchantCode 商家编号
	 * @return true 商家发货  ; false 优购发货
	 *//*
	@Override
	public boolean isDistributedByMerchant(String merchantCode) throws Exception{
		if(StringUtils.isEmpty(merchantCode) 
				|| Constant.MERCHANT_CODE_BELLE.equalsIgnoreCase(merchantCode.trim() )
						|| Constant.MERCHANT_CODE_KOREA.equalsIgnoreCase(merchantCode.trim() ) ) {
			logger.error("[merchantCode]:isDistributedByMerchant="+merchantCode);
			throw new Exception("请输入商家编码，并且确认是招商供应商的商家编码！");
		}
		Integer distributionSide = getCacheIsInputYougouWarehouseByMerchant(merchantCode);//发货方  商家发货 -- 0:不入优购仓库，商家发货；  优购发货--  1：入优购仓库，优购发货；2:不入优购仓库，优购发货
		if (distributionSide == null) {
			logger.error("找不到该供应商的是否入优购仓库商家发货的信息[merchantCode]:isDistributedByMerchant="+merchantCode);
			throw new Exception("找不到该供应商发货方的信息");
		}else{
			if( distributionSide == 1 || distributionSide == 2){
				return false;
			}else{
				return true;
			}
		}
	}

	*//**
	 * 是否商家开票
	 * @param merchantCode 商家编号
	 * @return true 商家发货  ; false 优购开票
	 *//*
	@Override
	public boolean isInvoicedByMerchant(String merchantCode)  throws Exception {
		if(StringUtils.isEmpty(merchantCode) 
				|| Constant.MERCHANT_CODE_BELLE.equalsIgnoreCase(merchantCode.trim() )
						|| Constant.MERCHANT_CODE_KOREA.equalsIgnoreCase(merchantCode.trim() ) ) {
			logger.error("[merchantCode]:isInvoicedByMerchant="+merchantCode);
			throw new Exception("请输入商家编码，并且确认是招商供应商的商家编码！");
		}
		Integer isInvoice = getCacheIsInvoiceByMerchant(merchantCode);//销售发票开具方 1：是商家，0：否
		if (isInvoice == null) {
			logger.error("找不到该供应商的销售发票开具方信息[merchantCode]:isInvoicedByMerchant="+merchantCode);
			throw new Exception("找不到该供应商销售发票开具方信息");
		}else{
			if( isInvoice == 0 ){
				return false;
			}else{
				return true;
			}
		}
	}*/

	/**
	 * 是否海外直发
	 * @param merchantCode 商家编号
	 * @return true 海外发货  ; false 国内发货
	 *//*
	@Override
	public boolean isDeliveredOverseas(String merchantCode) throws Exception {
		if(StringUtils.isEmpty(merchantCode) 
				|| Constant.MERCHANT_CODE_BELLE.equalsIgnoreCase(merchantCode.trim() )
						|| Constant.MERCHANT_CODE_KOREA.equalsIgnoreCase(merchantCode.trim() ) ) {
			logger.error("[merchantCode]:isDeliveredOverseas="+merchantCode);
			throw new Exception("请输入商家编码，并且确认是招商供应商的商家编码！");
		}
		String deliveryType = getCacheDeliveryTypeByMerchant(merchantCode);//发货类型 :'1' 国内发货  '2' 海外直发 
		if ( deliveryType  == null) {
			logger.error("找不到该供应商是否海外直发信息[merchantCode]:isDeliveredOverseas="+merchantCode);
			throw new Exception("找不到该供应商是否海外直发信息");
		}else{
			if( deliveryType.indexOf("1")!=-1 ){
				return false;
			}else{
				return true;
			}
		}
	}*/

	/**
	 * 是否可以开发票
	 * @param merchantCode 商家编号
	 * @return true 可以开发票  ; false 不可以开发票
	 *//*
	@Override
	public boolean canInvoice(String merchantCode) throws Exception {
		if( StringUtils.isEmpty(merchantCode) ) {
			logger.error("[merchantCode]:isDeliveredOverseas="+merchantCode);
			throw new Exception("请输入商家编码，并且确认是招商供应商的商家编码！");
		}else if( Constant.MERCHANT_CODE_BELLE.equalsIgnoreCase(merchantCode.trim()) || 
			Constant.MERCHANT_CODE_KOREA.equalsIgnoreCase(merchantCode.trim()  ){
			return true;// 自营的 可以开票
		}
		String deliveryType = getCacheDeliveryTypeByMerchant(merchantCode);//发货类型 :'1' 国内发货  '2' 海外直发 
		if ( deliveryType  == null) {
			logger.error("找不到该供应商是否海外直发信息[merchantCode]:isDeliveredOverseas="+merchantCode);
			throw new Exception("找不到该供应商是否海外直发信息,无法确定是否可以开发票");
		}else{
			if( deliveryType.indexOf("1")!=-1 ){//国内直发
				return true;
			}else{//海外直发 --不可以开票。
				return false;
			}
		}
	}*/
}
