/**
 * 
 */
package com.yougou.merchant.api.supplier.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.supplier.vo.ContactsVo;
import com.yougou.merchant.api.supplier.vo.ContractVo;

/**
 * 联系人
 * 
 * @author huang.tao
 *
 */
public interface ContactsMapper {
	//联系人
	List<ContactsVo> queryContactsByVo(ContactsVo vo, RowBounds rowBounds);
	
	Integer queryContactsCountByVo(ContactsVo vo);
	
	ContactsVo getContactById(String id);
	
	void insertContact(ContactsVo vo);
	
	void updateContact(ContactsVo vo);
	
	void deleteContact(String supplyId);
	
	//合同
	List<ContractVo> queryContractListByVo(ContractVo vo, RowBounds rowBounds);
	
	Integer queryContractCountByVo(ContractVo vo);
	
	ContractVo getContractById(String id);
	
	void insertContract(ContractVo vo);
	
	void updateContract(ContractVo vo);

	List<Map<String, Object>> getSettlementType(List<String> merchantCodes);
}
