/**
 * 
 */
package com.yougou.merchant.api.supplier.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.merchant.api.supplier.dao.ContactsMapper;
import com.yougou.merchant.api.supplier.dao.MerchantMapper;
import com.yougou.merchant.api.supplier.dao.SupplierMapper;
import com.yougou.merchant.api.supplier.service.ISupplierPurchaseService;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantUser;
import com.yougou.merchant.api.supplier.vo.SupplierUpdateHistory;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

/**
 * 
 * 
 * @author huang.tao
 *
 */
@Service(value="supplierPurchaseService")
public class SupplierPurchaseService implements ISupplierPurchaseService {
	
	@Resource
	private SupplierMapper supplierMapper;
	@Resource
	private ContactsMapper contactsMapper;
	@Resource
	private MerchantMapper merchantMapper;
	
	@Override
	public void insertSupplier(SupplierVo vo) throws Exception {
		supplierMapper.insertSupplier(vo);
	}
	
	@Override
	public void updateSupplier(SupplierVo vo) throws Exception {
		supplierMapper.updateSupplier(vo);
	}
	
	@Override
	public void saveMerchantOperationLog(MerchantOperationLog log)
			throws Exception {
		merchantMapper.insertMerchantLog(log);
	}

	@Override
	@Transactional
	public void deleteSupplierById(String id, String operator) throws Exception {
		// TODO Auto-generated method stub
		SupplierVo vo = new SupplierVo();
		vo.setId(id);
		vo.setUpdateDate(new Date());
		vo.setUpdateTimestamp(System.currentTimeMillis());
		vo.setUpdateUser(operator);
		vo.setIsValid(null);
		
		List<SupplierVo> suppliers = supplierMapper.querySupplierByVo(vo);
		vo.setDeleteFlag(NumberUtils.INTEGER_ZERO);
		
		//标记删除
		supplierMapper.updateSupplier(vo);
		//删除商家联系人
		contactsMapper.deleteContact(id);
		//删除商家账户
		MerchantUser user = new MerchantUser();
		String merchantCode = CollectionUtils.isNotEmpty(suppliers) ? suppliers.get(0).getSupplierCode() : "";
		if (StringUtils.isNotBlank(merchantCode)) {
			user.setMerchantCode(merchantCode);
			List<MerchantUser> _users = merchantMapper.queryMerchantUserList(user );
			if (CollectionUtils.isNotEmpty(_users)) {
				for (MerchantUser _user : _users) {
					user = new MerchantUser();
					user.setId(_user.getId());
					user.setDeleteFlag(NumberUtils.INTEGER_ZERO);
					merchantMapper.updateMerchantUser(user);
				}
			}
		}
	}

	@Override
	@Transactional
	public void deleteSupplierBySupplierCode(String supplierCode, String operator)
			throws Exception {
		SupplierVo _vo = supplierMapper.getSupplierByMerchantCode(supplierCode);
		if (null == _vo) return;
		
		SupplierVo vo = new SupplierVo();
		vo.setId(_vo.getId());
		vo.setUpdateDate(new Date());
		vo.setUpdateTimestamp(System.currentTimeMillis());
		vo.setUpdateUser(operator);
		vo.setDeleteFlag(NumberUtils.INTEGER_ZERO);
		vo.setIsValid(null);
		//标记删除
		supplierMapper.updateSupplier(vo);
		//删除商家联系人
		contactsMapper.deleteContact(_vo.getId());
		//删除商家账户
		MerchantUser user = new MerchantUser();
		if (StringUtils.isNotBlank(supplierCode)) {
			user.setMerchantCode(supplierCode);
			List<MerchantUser> _users = merchantMapper.queryMerchantUserList(user );
			if (CollectionUtils.isNotEmpty(_users)) {
				for (MerchantUser _user : _users) {
					user = new MerchantUser();
					user.setId(_user.getId());
					user.setDeleteFlag(NumberUtils.INTEGER_ZERO);
					merchantMapper.updateMerchantUser(user);
				}
			}
		}
	}

	@Override
	public void insertSupplierUpdateHistory(SupplierUpdateHistory vo)
			throws Exception {
		merchantMapper.insertUpdateHistory(vo);
	}

}
