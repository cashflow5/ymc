/**
 * 
 */
package com.yougou.merchant.api.supplier.service;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.vo.ContactsVo;
import com.yougou.merchant.api.supplier.vo.ContractVo;


/**
 *  招商联系人和合同
 * 
 * @author huang.tao
 *
 */
public interface IContactApi {
	
	/**
	 * 查询联系人列表
	 * 
	 * @param vo
	 * @return
	 */
	public PageFinder<ContactsVo> queryContactListByVo(ContactsVo vo, Query query);
	
	ContactsVo getContactById(String id);
	
	/**
	 * 添加联系人
	 * 
	 * @param vo
	 * @return
	 */
	boolean insertContact(ContactsVo vo);
	
	/**
	 * 修改联系人
	 * 
	 * @param vo
	 * @return
	 */
	boolean updateContact(ContactsVo vo);
	
	//================================================合同
	
	public PageFinder<ContractVo> queryContractListByVo(ContractVo vo, Query query);
	
	ContractVo getContractById(String id);
	
	/**
	 * 添加合同
	 * 
	 * @param vo
	 * @return
	 */
	boolean insertContract(ContractVo vo);
	
	/**
	 * 修改合同
	 * 
	 * @param vo
	 * @return
	 */
	boolean updateContract(ContractVo vo);
}
