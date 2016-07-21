package com.yougou.merchant.api.taobao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.yougou.merchant.api.taobao.dao.TaobaoMapper;
import com.yougou.merchant.api.taobao.enums.PropResult;
import com.yougou.merchant.api.taobao.vo.PropItem;
import com.yougou.merchant.api.taobao.vo.PropValue;
import com.yougou.merchant.api.taobao.vo.TranslateResult;

@Service(value = "taobaoServiceImpl")
public class TaobaoServiceImpl implements ITaobaoService {

	@Resource
	TaobaoMapper taobaoMapper;

	private static final Logger log = LoggerFactory
			.getLogger(TaobaoServiceImpl.class);

	@Override
	public TranslateResult translateTBProps2YGProp(String itemCatProps,
			String ygCatoryCode,int resultType) {
		TranslateResult result = new TranslateResult();
		try {
			if (StringUtils.isEmpty(itemCatProps)) {
				result.setError(true);
				result.setErrorMessage("参数错误，淘宝属性、属性值字符串不能为空");
				return result;
			}
			if (StringUtils.isEmpty(ygCatoryCode)) {
				result.setError(true);
				result.setErrorMessage("参数错误，优购三级分类不能为空");
				return result;
			}
			if (resultType!=1&&resultType!=2) {
				result.setError(true);
				result.setErrorMessage("参数错误，返回类型只能是1或者2");
				return result;
			}
			// 查询分类绑定
			TranslateResult translateResult = taobaoMapper
					.selectTaobaoYougouItemCat(ygCatoryCode);
			if (null == translateResult) {
				result.setError(true);
				result.setErrorMessage("优购分类编码:" + ygCatoryCode + ",没有绑定淘宝分类");
				return result;
			}
			result.setTbCategoryCode(translateResult.getTbCategoryCode());
			result.setTbCategoryName(translateResult.getTbCategoryName());
			result.setTbCategoryFullName(translateResult.getTbCategoryFullName());
			// 查询属性绑定
			List<PropItem> propItemList = taobaoMapper.selectTaobaoYougouProp(
					translateResult.getTbCategoryCode(), ygCatoryCode);
			if (null == propItemList) {
				result.setError(true);
				result.setErrorMessage("优购分类编码:" + ygCatoryCode + ",没有绑定绑定淘宝属性");
				return result;
			}

			Map<String, PropItem> propItemMap = convertProp2Map(propItemList);

			// 查询属性值绑定
			List<PropValue> propItemValueList = taobaoMapper
					.selectTaobaoYougouPropValue(
							translateResult.getTbCategoryCode(), ygCatoryCode);
			if (null == propItemValueList) {
				result.setError(true);
				result.setErrorMessage("优购分类编码:" + ygCatoryCode
						+ ",没有绑定绑定淘宝属性值");
				return result;
			}

			Map<String, PropValue> propValueMap = convertPropValue2Map(propItemValueList);

			Map<String, List<PropValue>> propMap = new HashMap<String, List<PropValue>>();
			String[] propAndValues = itemCatProps.split(";");
			PropValue propValue = null;
			if (propAndValues == null) {
				result.setError(true);
				result.setErrorMessage("参数错误，淘宝属性、属性值字符串格式错误，无法解析");
				return result;
			}
			// 解析属性，属性值
			List<PropItem> propItems = new ArrayList<PropItem>();
			PropItem item = null;
			for (String p : propAndValues) {
				String[] ps = p.split(":");
				if (ps != null && ps.length == 2) {
					List<PropValue> prpValues = propMap.get(ps[0]);
					if (prpValues == null) {
						prpValues = new ArrayList<PropValue>();
						item = taobaoMapper.selectTaobaoPropById(ps[0]);
						if (null == item) {
							item = new PropItem();
							item.setYgPropNo(ps[0]);
							item.setPropResult(PropResult.NOTFOUND);
						} else {
							PropItem mapPropItem = propItemMap.get(item.getTbPropNo());
							if (null == mapPropItem) {
								item.setPropResult(PropResult.NOTBIND);
							} else {
								item.setPropResult(PropResult.HAVEBIND);
								item.setYgPropNo(mapPropItem.getYgPropNo());
							}
						}
						if(resultType==2&&item.getPropResult()!=PropResult.HAVEBIND){
							continue;
						}
						item.setPropVals(prpValues);
						propItems.add(item);
					}
					// 判断属性值在系统中是否存在
					propValue = taobaoMapper.selectTaobaoPropValueById(ps[1]);
					if (null == propValue) {
						propValue = new PropValue();
						propValue.setTbPropValueNo(ps[1]);
						propValue.setPropResult(PropResult.NOTFOUND);
					} else {
						PropValue mapPropValue = propValueMap.get(propValue
								.getTbPropValueNo());
						if (null == mapPropValue) {
							propValue.setPropResult(PropResult.NOTBIND);
						} else {
							propValue.setPropResult(PropResult.HAVEBIND);
							propValue.setYgPropValueNo(mapPropValue
									.getYgPropValueNo());
						}
					}
					if(resultType==2&&propValue.getPropResult()!=PropResult.HAVEBIND){
						continue;
					}
					prpValues.add(propValue);
					propMap.put(ps[0], prpValues);
				} else {
					result.setError(true);
					result.setErrorMessage("参数错误，淘宝属性、属性值字符串格式错误，无法解析");
					return result;
				}
			}
			result.setYgProps(propItems);
			result.setError(false);
			return result;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setError(true);
			result.setErrorMessage(e.getMessage());
			return result;
		}
	}

	private Map<String, PropItem> convertProp2Map(List<PropItem> list) {
		Map<String, PropItem> map = new HashMap<String, PropItem>();
		for (PropItem item : list) {
			map.put(item.getTbPropNo(), item);
		}
		return map;
	}

	private Map<String, PropValue> convertPropValue2Map(List<PropValue> list) {
		Map<String, PropValue> map = new HashMap<String, PropValue>();
		for (PropValue item : list) {
			map.put(item.getTbPropValueNo(), item);
		}
		return map;
	}
}
