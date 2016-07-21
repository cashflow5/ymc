package com.yougou.merchant.api.supplier.service;

import java.util.List;
import java.util.Map;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.vo.MerchantBrandDeducRate;
import com.yougou.merchant.api.supplier.vo.MerchantRejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.RejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.SupplierQueryVo;
import com.yougou.merchant.api.supplier.vo.SupplierVo;
import com.yougou.pay.vo.TradeCurrencyEnum;

/**
 * 供应商接口
 * @author zhuang.rb
 *
 */
public interface ISupplierService {

	/**
	 * 获取所有供应商信息
	 * @return
	 */
	List<SupplierVo> getAllSupplier();
	
	/**
	 * 根据供应商（商家）编码返回订单配送方
	 * 
	 * @param merchantCode
	 * @return （0、优购配送；1、商家配送优购开发票；2、商家配送并开具发票 3、表示由商家海外直发，不开发票）
	 * @throws Exception [商家编码为KOREA、SPYG的，不做任何查询，直接抛出异常 ]
	 */
	Integer getSupplierDistributionSide(String merchantCode) throws Exception;
	
	/**
	 * 根据商家编码获取供应商对象
	 * @param merchantCode
	 * @return	SupplierVo
	 */
	SupplierVo getSupplierByMerchantCode(String merchantCode) throws Exception;
	
	/**
	 * 根据商家编码获取供应商币种(默认人民币)
	 * @param merchantCode
	 * @return	TradeCurrencyEnum
	 */
	TradeCurrencyEnum getTradeCurrencyByMerchantCode(String merchantCode)  throws Exception;
	
	/**
	 * 根据商家编码获取退换货地址
	 * @param merchantCode
	 * @return	RejectedAddressVo
	 * @throws Exception
	 */
	RejectedAddressVo getSupplierRejectedAddress(String merchantCode) throws Exception;
	
	/**
	 * 根据商家编码获取退换货地址
	 * @param merchantCode
	 * @return
	 * @throws Exception
	 */
	RejectedAddressVo getMerchantRejectedAddress(String merchantCode) throws Exception;
	
	/**
	 * 根据商家编码获取退换货地址(包含优购自营)
	 * @param merchantCode
	 * @return
	 * @throws Exception
	 */
	RejectedAddressVo getContainYougouRejectedAddress(String merchantCode) throws Exception;
	
	/**
	 * 更新当前商家的仓库编码	
	 * @param merchantCode
	 * @param warehouseCode
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	boolean updateMerchantWarehouseCode(String merchantCode, String warehouseCode, String updateUser) throws Exception;
	
	/**
	 * 根据商家编码查询仓库编码和名称 
	 * <p>
	 * key包含: <b>warehouseCode|warehouseName</b>
	 * </p>
	 * @param merchantCode
	 * @return 
	 * @throws Exception
	 */
	Map<String, String> getWarehouseByMerchantCode(String merchantCode) throws Exception;
	
	/**
	 * 查询商家信息
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List<SupplierVo> querySupplierByVo(SupplierVo vo) throws Exception;
	
	/**
	 * 根据商家名称获取供应商对象
	 * @param supplier 商家名称
	 * @return
	 */
	SupplierVo getSupplierByName(String supplierName) throws Exception;
	
	/**
	 * 分页查询供应商信息（供招商后台查询使用的接口）
	 * 
	 * @param vo
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PageFinder<SupplierVo> querySupplierListByPage(SupplierQueryVo vo, Query query) throws Exception;
	
	/**
	 * 保存商家的退换货地址
	 * @param merchantRejectedAddress
	 * @return
	 */
	boolean saveMerchantRejectedAddress(MerchantRejectedAddressVo merchantRejectedAddress);
	
	/**
	 * 根据商家编码列表返回对应的商家是否由优购开具(true)还是商家开具（false）
	 * @param merchantCodes
	 * @return
	 * @throws IllegalArgumentException
	 */
	Map<String, Boolean> getMerchantIsYugouInvoice(List<String> merchantCodes) throws IllegalArgumentException;
	
	/**
	 * 根据商家ID获取供应商对象
	 * @param supplier 商家名称
	 * @return
	 */
	SupplierVo getSupplierById(String supplierId) throws Exception;
	
	/**
	 * 财务确认已收款。
	 * 
	 * @param contractNo 合同编号
	 * @param type deposit/usefee
	 * @param payStyle 1:本期收款 2:上期收款
	 * @return Map<String,String>.result = success/failure,Map.msg = "错误信息"，如果result=success，没有错误信息。
	 */
	Map<String, String> confirmContractReceivedPayment(String contractNo, String type, int payStyle);
	
	/**
	 * 财务确认已退款 
	 * 
	 * @param contractNo 合同编号
	 * @param type deposit/usefee
	 * @return Map<String,String>.result = success/failure,Map.msg = "错误信息"，如果result=success，没有错误信息。
	 */
	Map<String, String> confirmContractRefund(String contractNo, String type);
	
/*	*//**
	 * 是否商家发货
	 * @param merchantCode 商家编号
	 * @return true 商家发货  ; false 优购发货
	 *//*
	boolean isDistributedByMerchant(String merchantCode)  throws Exception;
	
	*//**
	 * 是否商家开票
	 * @param merchantCode 商家编号
	 * @return true 商家开票  ; false 优购开票
	 *//*
	boolean isInvoicedByMerchant(String merchantCode)  throws Exception;*/
	
/*	*//**
	 * 是否海外直发
	 * @param merchantCode 商家编号
	 * @return true 海外发货  ; false 国内发货
	 *//*
	boolean isDeliveredOverseas(String merchantCode)  throws Exception;
	*/
	/**
	 * 是否可以开发票
	 * @param merchantCode 商家编号
	 * @return true 可以开发票  ; false 不可以开发票
	 *//*
	boolean canInvoice(String merchantCode)  throws Exception;*/
	
	/**
	 * 获取发货方式：海外直发、国内发货
	 * @param merchantCode 商家编号
	 * @return ‘1’ 为国内发货， ‘2’ 为商家海外直发 
	 */
	String getDeliveryType(String merchantCode)  throws Exception;
	
	/**
	 * 分页查询供应商信息（品牌扣点信息）
	 * @param merchantCode
	 * @param updateTimeStart
	 * @param updateTimeEnd
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PageFinder<MerchantBrandDeducRate> queryMerchantBrandDeductRate(List<String> merchantCode, 
			String updateTimeStart, String updateTimeEnd, Query query) throws Exception;
}
