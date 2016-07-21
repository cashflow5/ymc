/**
 * 
 */
package com.yougou.merchant.api.help.dao;

import java.util.List;

import com.yougou.merchant.api.help.vo.HelpCenterContent;
import com.yougou.merchant.api.help.vo.HelpCenterImg;
import com.yougou.merchant.api.help.vo.HelpCenterLog;
import com.yougou.merchant.api.help.vo.HelpCenterMenu;

/**
 * 商家帮助中心
 * 
 * @author huang.tao
 *
 */
public interface HelpMapper {
	//日志
	void insertHelpLog(HelpCenterLog log);
	void insertHelpContent(HelpCenterContent content);
	void insertHelpImg(HelpCenterImg img);
	void insertHelpMenu(HelpCenterMenu menu);
	
	void updateHelpContent(HelpCenterContent content);
	HelpCenterContent getContentByMenuId(String menuId);
	void deleteHelpContentByMenuId(String menuId);
	
	List<HelpCenterImg> getHelpImageNameList();
	
	HelpCenterMenu getHelpMenuById(String id);
	HelpCenterMenu getHelpMenuBySubId(String subId);
	/**
	 * 获取商家中心所有帮助类目菜单
	 * @return
	 */
	List<HelpCenterMenu> getHelpCenterMenuList();
	
	HelpCenterImg getHelpImageByName(String picName);
	
	void updateHelpMenu(HelpCenterMenu menu);
	void deleteHelpMenuById(String id);
}
