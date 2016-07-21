package com.yougou.merchant.api.supplier.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.supplier.vo.SupplierHasInvoiceVO;
import com.yougou.merchant.api.supplier.vo.SupplierQueryVo;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

/**
 * 供应商接口
 */
public interface SupplierMapper {
	/**
	 * 查询所有的供应商
	 * @return
	 */
	public List<SupplierVo> getAllSupplier();
	
	/**
	 * 根据供应商（商家）编码返回订单配送方
	 * 
	 * @param merchantCode
	 * @return （0、不入优购仓库；1、入优购仓库）
	 */
	public Integer getSupplierIsInputYougouWarehouse(String merchantCode);
	
	/**
	 * 根据商家编码获取供应商对象
	 * @param merchantCode
	 * @return
	 */
	public SupplierVo getSupplierByMerchantCode(String merchantCode);

	/**
	 * 把原有其他的仓库置为null
	 * @param warehouseCode
	 * @param updateUser
	 * @param updateDate
	 * @throws Exception
	 */
	public void updateSupplierOtherWarehouseCodeNull(@Param("warehouseCode") String warehouseCode, @Param("updateUser") String updateUser, @Param("updateDate") Date updateDate) throws Exception;

	/**
	 * 更新当前商家的仓库编码
	 * @param merchantCode
	 * @param warehouseCode
	 * @param updateUser
	 * @param updateDate
	 * @throws Exception
	 */
	public void updateSupplierWarehouseCode(@Param("merchantCode") String merchantCode, @Param("warehouseCode") String warehouseCode, @Param("updateUser") String updateUser, @Param("updateDate") Date updateDate) throws Exception;
	
	/**
	 * 查询供应商信息
	 * 
	 * @param vo
	 * @return
	 */
	public List<SupplierVo> querySupplierByVo(SupplierVo vo);
	
	/**
	 * 根据商家名称获取供应商对象
	 * @param supplier 商家名称
	 * @return
	 */
	public List<SupplierVo> getSupplierByName(String supplier);
	
	/**
	 * 分页查询供应商信息（供招商后台查询使用的接口）
	 * 
	 * @param vo
	 * @param rowBounds
	 * @return
	 */
	List<SupplierVo> querySupplierListByPage(SupplierQueryVo vo, RowBounds rowBounds);
	
	Integer querySupplierListByCount(SupplierQueryVo vo);
	
	/**
	 * 增加供应商表
	 * 
	 * @param vo
	 */
	void insertSupplier(SupplierVo vo);
	
	void updateSupplier(SupplierVo vo);
	
	/**
	 * 查询供应商是否可以开发票
	 * 
	 * @param vo
	 */
	public SupplierHasInvoiceVO getSupplierInvoiceInfo(String supplierCode);
	
	/**
	 * 根据商家名称获取供应商对象
	 * @param supplier 商家名称
	 * @return
	 */
	public SupplierVo getSupplierById(String supplierId);
	/**
	 * 根据商家编码获取开票方
	 * @param merchantCode
	 * @return
	 */
	public Integer getSupplierIsInvoice(String merchantCode);
	/**
	 * 根据商家编码获取是否海外发货
	 * @param merchantCode
	 * @return
	 */
	public String getSupplierDeliveryType(String merchantCode);
}
