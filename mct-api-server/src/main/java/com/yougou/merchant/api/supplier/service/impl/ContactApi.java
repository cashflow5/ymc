/**
 * 
 */
package com.yougou.merchant.api.supplier.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yougou.merchant.api.common.MerchantLogTools;
import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.supplier.dao.ContactsMapper;
import com.yougou.merchant.api.supplier.dao.MerchantMapper;
import com.yougou.merchant.api.supplier.service.IContactApi;
import com.yougou.merchant.api.supplier.vo.ContactsVo;
import com.yougou.merchant.api.supplier.vo.ContractVo;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog.OperationType;

/**
 * @author huang.tao
 *
 */
@Service(value="contactApi")
public class ContactApi implements IContactApi {

	private static final Logger logger = Logger.getLogger(ContactApi.class);
	
	@Resource
	private ContactsMapper contactsMapper;
	@Resource
	private MerchantMapper merchantMapper;
	
	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.supplier.service.IContactApi#queryContactListByVo(com.yougou.merchant.api.supplier.vo.ContactsVo, com.yougou.merchant.api.supplier.common.Query)
	 */
	@Override
	public PageFinder<ContactsVo> queryContactListByVo(ContactsVo vo,
			Query query) {
		Integer count = contactsMapper.queryContactsCountByVo(vo);
		List<ContactsVo> list = contactsMapper.queryContactsByVo(vo, new RowBounds(query.getOffset(), query.getPageSize()));
		PageFinder<ContactsVo> pageFinder = new PageFinder<ContactsVo>(query.getPage(), query.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public PageFinder<ContractVo> queryContractListByVo(ContractVo vo, Query query) {
		Integer count = contactsMapper.queryContractCountByVo(vo);
		List<ContractVo> list = contactsMapper.queryContractListByVo(vo, new RowBounds(query.getOffset(), query.getPageSize()));
		PageFinder<ContractVo> pageFinder = new PageFinder<ContractVo>(query.getPage(), query.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public ContactsVo getContactById(String id) {
		return contactsMapper.getContactById(id);
	}

	@Override
	public boolean insertContact(ContactsVo vo) {
		try {
			contactsMapper.insertContact(vo);
			
			//添加账户日志
			MerchantOperationLog _userlog = new MerchantOperationLog();
			_userlog.setId(UUIDGenerator.getUUID());
			_userlog.setMerchantCode(vo.getSupplierCode());
			_userlog.setOperator(vo.getOperationtor());
			_userlog.setOperated(new Date());
			_userlog.setOperationType(OperationType.CONTACT);
			_userlog.setOperationNotes(MerchantLogTools.buildContactOperationNotes(null, vo));
			merchantMapper.insertMerchantLog(_userlog);
		} catch (Exception e) {
			logger.error(MessageFormat.format("{0} | 添加联系人异常。", vo.getSupplier()), e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateContact(ContactsVo vo) {
		try {
			contactsMapper.updateContact(vo);
			
			//添加账户日志
			MerchantOperationLog _userlog = new MerchantOperationLog();
			_userlog.setId(UUIDGenerator.getUUID());
			_userlog.setMerchantCode(vo.getSupplierCode());
			_userlog.setOperator(vo.getOperationtor());
			_userlog.setOperated(new Date());
			_userlog.setOperationType(OperationType.CONTACT);
			_userlog.setOperationNotes(MerchantLogTools.buildContactOperationNotes(contactsMapper.getContactById(vo.getId()), vo));
			merchantMapper.insertMerchantLog(_userlog);
		} catch (Exception e) {
			logger.error(MessageFormat.format("{0} | 修改联系人异常。", vo.getSupplier()), e);
			return false;
		}
		return true;
	}

	@Override
	public ContractVo getContractById(String id) {
		return contactsMapper.getContractById(id);
	}

	@Override
	public boolean insertContract(ContractVo vo) {
		try {
			contactsMapper.insertContract(vo);
			
			//添加账户日志
			MerchantOperationLog _userlog = new MerchantOperationLog();
			_userlog.setId(UUIDGenerator.getUUID());
			_userlog.setMerchantCode(vo.getSupplierCode());
			_userlog.setOperator(vo.getOperationtor());
			_userlog.setOperated(new Date());
			_userlog.setOperationType(OperationType.CONTRACT);
			_userlog.setOperationNotes(MerchantLogTools.builContractOperationNotes(null, vo));
			merchantMapper.insertMerchantLog(_userlog);
		} catch (Exception e) {
			logger.error(MessageFormat.format("{0} | 添加合同异常。", vo.getSupplier()), e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateContract(ContractVo vo) {
		try {
			contactsMapper.updateContract(vo);
			
			//添加账户日志
			MerchantOperationLog _userlog = new MerchantOperationLog();
			_userlog.setId(UUIDGenerator.getUUID());
			_userlog.setMerchantCode(vo.getSupplierCode());
			_userlog.setOperator(vo.getOperationtor());
			_userlog.setOperated(new Date());
			_userlog.setOperationType(OperationType.CONTRACT);
			_userlog.setOperationNotes(MerchantLogTools.builContractOperationNotes(this.getContractById(vo.getId()), vo));
			merchantMapper.insertMerchantLog(_userlog);
		} catch (Exception e) {
			logger.error(MessageFormat.format("{0} | 修改合同异常。", vo.getSupplier()), e);
			return false;
		}
		return true;
	}

}
