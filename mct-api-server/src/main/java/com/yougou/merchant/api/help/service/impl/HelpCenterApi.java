/**
 * 
 */
package com.yougou.merchant.api.help.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.help.dao.HelpMapper;
import com.yougou.merchant.api.help.service.IHelpCenterApi;
import com.yougou.merchant.api.help.vo.HelpCenterContent;
import com.yougou.merchant.api.help.vo.HelpCenterImg;
import com.yougou.merchant.api.help.vo.HelpCenterLog;
import com.yougou.merchant.api.help.vo.HelpCenterMenu;

/**
 * @author huang.tao
 *
 */
@Service(value="helpCenterApi")
public class HelpCenterApi implements IHelpCenterApi {
	
	@Resource
	private HelpMapper helpMapper;
	
	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.help.service.IHelpCenterApi#getHelpCenterMenuList()
	 */
	@Override
	public List<HelpCenterMenu> getHelpCenterMenuList() {
		return helpMapper.getHelpCenterMenuList();
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.help.service.IHelpCenterApi#updateMenuData(com.yougou.merchant.api.help.vo.HelpCenterMenu[], java.lang.String)
	 */
	@Override
	@Transactional
	public void updateMenuData(HelpCenterMenu[] menuArr, String operator)
			throws Exception {
		Date logDate = new Date();
		List<HelpCenterLog> centerLogList = new ArrayList<HelpCenterLog>();
		
		//已持久化的菜单
		Map<String, HelpCenterMenu> tempMap = new HashMap<String, HelpCenterMenu>();
		List<HelpCenterMenu> tempList = this.getHelpCenterMenuList();
		if (CollectionUtils.isNotEmpty(tempList)) {
			for (HelpCenterMenu helpCenterMenu : tempList) {
				tempMap.put(helpCenterMenu.getId(), helpCenterMenu);
			}
		}
		if (ArrayUtils.isNotEmpty(menuArr)) {
			for (HelpCenterMenu helpCenterMenu : menuArr) {
				if (StringUtils.isNotBlank(helpCenterMenu.getId())) {
					if (!helpCenterMenu.equals(tempMap.get(helpCenterMenu.getId()))) {
						helpMapper.updateHelpMenu(helpCenterMenu);
						centerLogList.add(new HelpCenterLog(helpCenterMenu.getSubId(), "更新菜单:"+helpCenterMenu.getMenuName(), logDate, operator));
					}
					tempMap.remove(helpCenterMenu.getId());
				} else {
					helpCenterMenu.setId(UUIDGenerator.getUUID());
					helpMapper.insertHelpMenu(helpCenterMenu);
					centerLogList.add(new HelpCenterLog(helpCenterMenu.getSubId(), "新增菜单:"+helpCenterMenu.getMenuName(), logDate, operator));
				}
			}
		}
		
		//删除菜单和菜单所关联的内容
		for (String menuId : tempMap.keySet()) {
			HelpCenterMenu _menu = tempMap.get(menuId);
			helpMapper.deleteHelpMenuById(menuId);
			centerLogList.add(new HelpCenterLog(_menu.getSubId(), "删除菜单:"+_menu.getMenuName(), logDate, operator));
			
			helpMapper.deleteHelpContentByMenuId(menuId);
			centerLogList.add(new HelpCenterLog(_menu.getSubId(), "删除菜单级联删除相关帮助内容:"+_menu.getMenuName(), logDate, operator));
		}
		
		for (HelpCenterLog helpCenterLog : centerLogList) {
			helpCenterLog.setId(UUIDGenerator.getUUID());
			helpMapper.insertHelpLog(helpCenterLog);
		}
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.help.service.IHelpCenterApi#getContentByMenuId(java.lang.String)
	 */
	@Override
	public String getContentByMenuId(String menuId) throws Exception {
		HelpCenterContent content = helpMapper.getContentByMenuId(menuId);
		return content != null ? content.getContent() : null;
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.help.service.IHelpCenterApi#saveOrUpdateContent(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public void saveOrUpdateContent(String helpMenuId, String helpMenuContent,
			String operator) throws Exception {
		HelpCenterContent tempContent = null;
		HelpCenterLog tempLog = null;
		HelpCenterContent content = helpMapper.getContentByMenuId(helpMenuId);
		if (null != content) {
			tempContent = content;
			tempContent.setContent(helpMenuContent);
			tempLog = new HelpCenterLog(helpMenuId, "修改帮助文档内容", new Date(), operator);
		}else {
			tempContent = new HelpCenterContent();
			tempContent.setId(UUIDGenerator.getUUID());
			tempContent.setMenuId(helpMenuId);
			tempContent.setContent(helpMenuContent);
			tempLog = new HelpCenterLog(helpMenuId, "新增帮助文档内容", new Date(), operator);
		}
		tempLog.setId(UUIDGenerator.getUUID());
		helpMapper.insertHelpContent(tempContent);
		helpMapper.insertHelpLog(tempLog);
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.help.service.IHelpCenterApi#getMenuIdBySubId(java.lang.String)
	 */
	@Override
	public String getMenuIdBySubId(String subId) throws Exception {
		HelpCenterMenu menu = helpMapper.getHelpMenuBySubId(subId);
		return menu != null ? menu.getId() : null;
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.help.service.IHelpCenterApi#getMenuById(java.lang.String)
	 */
	@Override
	public HelpCenterMenu getMenuById(String id) throws Exception {
		return helpMapper.getHelpMenuById(id);
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.help.service.IHelpCenterApi#addImageName(java.lang.String)
	 */
	@Override
	public void addImageName(String filename) throws Exception {
		if (isExistImage(filename)) 
			return;
		
		HelpCenterImg img = new HelpCenterImg(filename, new Date(), new Date());
		img.setId(UUIDGenerator.getUUID());
		helpMapper.insertHelpImg(img);
	}

	private boolean isExistImage(String filename) {
		HelpCenterImg img = helpMapper.getHelpImageByName(filename);
		return img != null ? true : false;
	}
	
	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.help.service.IHelpCenterApi#getHelpImageNameList()
	 */
	@Override
	public List<HelpCenterImg> getHelpImageNameList() throws Exception {
		return helpMapper.getHelpImageNameList();
	}

}
