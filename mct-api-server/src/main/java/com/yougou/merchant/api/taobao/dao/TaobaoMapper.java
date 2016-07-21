package com.yougou.merchant.api.taobao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yougou.merchant.api.taobao.vo.PropItem;
import com.yougou.merchant.api.taobao.vo.PropValue;
import com.yougou.merchant.api.taobao.vo.TranslateResult;

public interface TaobaoMapper {
	public PropItem selectTaobaoPropById(@Param("pid")String pid);
	
	public PropValue selectTaobaoPropValueById(@Param("vid")String vid);
	
	public TranslateResult selectTaobaoYougouItemCat(@Param("ygCatoryCode")String ygCatoryCode);
	
	public List<PropItem>selectTaobaoYougouProp(@Param("taobaoCid")String taobaoCid,@Param("yougouCatNo")String yougouCatNo);
	
	public List<PropValue>selectTaobaoYougouPropValue(@Param("taobaoCid")String taobaoCid,@Param("yougouCatNo")String yougouCatNo);
}
