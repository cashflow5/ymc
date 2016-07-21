package com.yougou.merchant.api.supplier.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.supplier.vo.ExpressTemplate;
import com.yougou.merchant.api.supplier.vo.MerchantRejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.RejectedAddressVo;

/**
 * 商家退换货地址
 * @author zhuang.rb
 *
 */
public interface RejectedAddressMapper {

	/**
	 * 根据商家编码获取退换货地址
	 * @param merchantCode
	 * @return
	 */
	public RejectedAddressVo getSupplierRejectedAddress(String merchantCode);
	
	/**
	 * 查询商家售后退货地址列表
	 * 
	 * @param message
	 * @return List<MerchantRejectedAddressVo>
	 */
	List<MerchantRejectedAddressVo> getMerchantRejectedAddressList(MerchantRejectedAddressVo vo, RowBounds rowBounds);
	Integer getMerchantRejectedAddressListCount(MerchantRejectedAddressVo vo);
	
    /**
     * 根据id查询商家售后退货地址列表
     * 
     * @param message
     * @return MerchantRejectedAddressVo
     */
    MerchantRejectedAddressVo getMerchantRejectedAddressById(String id);
    
    void insertMerchantRejectedAddress(MerchantRejectedAddressVo vo);
    void updateMerchantRejectedAddress(MerchantRejectedAddressVo vo);
    
    /**
     * 快递公司发货模板
     * 
     * @param vo
     */
    void insertExpressTemplate(ExpressTemplate vo);
    void updateExpressTemplate(ExpressTemplate vo);
    ExpressTemplate getExpressTemplateById(String id);
}
