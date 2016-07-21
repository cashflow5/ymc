package com.yougou.merchant.api.taobao.service;

import com.yougou.merchant.api.taobao.vo.TranslateResult;

/**
 * 淘宝相关接口
 * @author luo.hl
 *
 */
public interface ITaobaoService {
	/**
	 * 
	 * @param itemCatProps  淘宝属性，属性值编码 eg:20000:101078;13021751:146743520;122216347:132721270;20608:6384766;124128491:28398;122216587:3323086;122216629:15354885;122216563:30161;122216628:115801;122216351:30232;1626698:24574746;122216561:115807;34272:115801;34272:115772;20490:3267935;1627207:3232481;1627207:28320;1627207:28326;20549:30106;20549:670;20549:671;20549:29542;20549:28388;20549:672;20549:28389;122216632:14545464;21541:38487;20603:29454;122276315:28688;122216608:3267959;122216515:29535;32960:30232
	 * @param ygCatoryCode  优购三级分类编码
	 * @param resultType  1 返回所有的属性，属性值 绑定未绑定的   2返回已经绑定
	 * @return
	 */
	public TranslateResult translateTBProps2YGProp(String itemCatProps,String ygCatoryCode,int resultType);
}
