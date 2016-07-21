/**
 * 
 */
package com.yougou.kaidian.common.constant;

/**
 * 商家中心 - 缓存相关常量
 * 
 * @author huang.tao
 *
 */
public class CacheConstant {
	
	/** 款色编码分类缓存 */
	public static final String C_COMMODITY_CATEGORY_KEY = "com.yougou.kaidian.commodity.category";
	/** redis存储商品描述字符串更新key */
	public static final String C_COMMODITY_PIC_DESC_KEY = "com.yougou.kaidian.commodity.pic.desc";
	/** 批量导入商品资料-error文件 */
	public static final String C_COMMODITY_IMPORT_ERROR_KEY = "com.yougou.kaidian.commodity.import.error";
	/** 首页商品相关数量缓存 */
	public static final String C_COMMODITY_SHOUYE_TIPS_KEY = "com.yougou.kaidian.commodity.shouye.tips";
	/** 商品销量 */
	public static final String C_COMMODITY_SALENUM_KEY = "com.yougou.kaidian.commodity.salenum";
	
	
	/** 导入库存  错误列表 */
	public static final String C_STOCK_ERROR_KEY = "com.yougou.kaidian.stock.import.error";
	/** 导入库存 进度条（数值） */
	public static final String C_STOCK_PROGRESS_BAR_KEY = "com.yougou.kaidian.stock.progress.bar";
	
	
	/** 角度图jms消息 */
	public static final String C_IMAGE_MASTER_JMS_KEY = "com.yougou.kaidian.image.master.jms";
	/** 描述图jms消息 */
	public static final String C_IMAGE_BATCH_JMS_KEY = "com.yougou.kaidian.image.batch.jms";
	
	
	/** 订单提醒 */
	public static final String C_ORDER_SHOPREMIND_KEY = "com.yougou.kaidian.order.shopremind";
	/** 待处理的售后提醒 */
	public static final String C_AFTERSALE_REMIND_KEY = "com.yougou.kaidian.asm.aftersaleremind";
	
	/** 首页订单报表数据 */
	public static final String C_ORDER_SHOUYE_SELL_KEY = "com.yougou.kaidian.order.shouye.sell";
	
	/** 缓存用户最后一次登录时间 */
	public static final String C_USER_LOGIN_TIME_KEY = "com.yougou.kaidian.user.login.time";
	/** 缓存用户本次登录时间 */
	public static final String C_USER_LOGIN_TIME_TEMP_KEY = "com.yougou.kaidian.user.login.time.temp";
	
	/** 缓存商家不同意拦截的订单号 */
	public static final String C_ORDER_INTERCEPT_NO_KEY = "com.yougou.kaidian.merchant.order.intercept.no";
	
	/** 缓存商家找回密码的用户ID和时间 */
	public static final String C_USER_RESETPASSWORD_ID_TIME = "com.yougou.kaidian.merchant.user.resetpassword.id";
	
	/** 缓存商家绑定邮箱 */
	public static final String C_USER_ACTIVATEPASSWORD_ID_TIME = "com.yougou.kaidian.merchant.user.activatepassword.id";
	
	/** 缓存培训中心课程学习人数 */
	public static final String C_TRIANING_TOTAL_CLICK_KEY = "com.yougou.kaidian.training.id";
	
	/** 缓存商家当天的合同编号 */
	public static final String C_MERCHANT_CONTRACT_NO_KEY = "com.yougou.kaidian.merchant.contract.no";
	
	/** 缓存添加商家的草稿 */
	public static final String C_MERCHANT_NAME_KEY = "com.yougou.kaidian.merchant.name";
	
	/**
	 * 缓存用户与权限资源（对象类型菜单对应）
	 */
	public static final String C_USER_AUTH = "com.yougou.kaidian.user.auth";
	/**
	 * 缓存用户与权限资源（String类型URL资源）
	 */
	public static final String C_USER_REOURCE_AUTH = "com.yougou.kaidian.user.resource.auth";
	/**
	 * 缓存所有资源（String类型URL资源）
	 */
	public static String C_All_RESOURCE = "com.yougou.kaidian.all.resource";
	
	
	
	public final static String API_APPKEY_SET_KEY = "api:api.appkey:set";
	
	public final static String API_HASH_KEY = "api:api:hash";
	
	public final static String API_IS_SAVE_RESULT_HASH_KEY = "api:api.is.save.result:hash";
	
	public final static String APPKEY_SECRET_KEY = "api:appkey.secret:hash";
	
	/**
	 * 短信验证码
	 */
	public final static String C_USER_MOBILE_PHONE_CODE = "com.yougou.kaidian.user.mobile.phone.code";
	/**
	 * 修改密码邮件发送
	 */
	public final static String C_USER_UPDATEPASSWORD_ID_TIME = "com.yougou.kaidian.merchant.user.updatepassword.id";
	/**
	 * 用户是否进入某方法缓存，如果第一步没有验证成功，不允许访问第二步url
	 */
	public final static String C_USER_ENTER_METHOD = "com.yougou.kaidian.merchant.user.enter.method";
	/**
	 * 缓存所有账号的权限菜单（左处菜单个数）
	 */
	public static final String C_USERS_AUTH_COUNT = "com.yougou.kaidian.merchant.users.auth.count";
	/**
	 * 商家设置安全库存的标志
	 */
	public static final String C_MERCHANT_SET_SAFE_STOCK = "com.yougou.kaidian.merchant.set.safe.stock";
	/**
	 * 数据报表——品类分析——三级分类汇总数据缓存
	 */
	public static final String C_MERCHANT_THREESTRUCT_SUMDATA = "com.yougou.kaidian.merchant.threestruct.sumdata";
	/**
	 * 数据报表——导出商品分析
	 */
	public static final String C_MERCHANT_EXPORT_BI_PRODANALYSE = "com.yougou.kaidian.merchant.export.bi.prodanalyse";
	/** 数据报表——导出商品进度值（数值） */
	public static final String C_MERCHANT_EXPORT_BI_PROGRESS_BAR_KEY = "com.yougou.kaidian.merchant.export.bi.progress.bar";
	/**
	 * 数据报表之用户预置模板指标
	 */
	public static final String C_MERCHANT_BI_USER_DEFAULT_TEMPLATE_INDEX = "com.yougou.kaidian.merchant.bi.user.default.template.index";
	/**
	 * 账号异地登录，发送短信提醒记录
	 */
	public static final String C_MERCHANT_LOGIN_NAME_SECURITY = "com.yougou.kaidian.merchant.login.name.security";
	
	/** 代售商品——导出商品进度值（数值） */
	public static final String C_EXPORT_WAIT_SALE_COMMODIT_KEY = "com.yougou.kaidian.export.wait.sale.commodit";
	
	
	/**
	 * 导出代售——导出商品
	 */
	public static final String C_EXPORT_WAIT_SALE_COMMODIT_PRODANALYSE = "com.yougou.kaidian.export.wait_sale。commodit.prodanalyse";
	
	
	/** 尺寸——导出商品进度值（数值） */
	public static final String C_EXPORT_WAIT_SALE_COMMODIT_SIZE_KEY = "com.yougou.kaidian.export.wait.sale.size.commodit";
	
	
	/**
	 * 尺寸——导出商品
	 */
	public static final String C_EXPORT_WAIT_SALE_COMMODIT_SIZE_PRODANALYSE = "com.yougou.kaidian.export.wait.sale.commodit.size.prodanalyse";
	
	/*商家创建草稿用**/
	public static final String KEY_UPDATE_TIME = "updateTime";
	public static final String KEY_SUPPLIER_NAME = "supplierName";
	public static final String KEY_SUPPLIER = "supplier";
	public static final String KEY_SUPPLIER_CONTRACT = "supplierContract";
	public static final String KEY_SUPPLIER_EXPAND = "supplierExpand";
	public static final String KEY_ATTACHMENT_FORM = "attachmentForm";
	public static final String KEY_CONTACTS_FORM = "contactsFormVo";
	public static final String KEY_MERCHANT_USER = "merchantUser";
	public static final String KEY_REJECT_ADDRESS = "rejectAddress";
	public static final String KEY_UPDATE_USER = "updateUser";
	
	/** 收藏商品（数值） */
	public static final String C_EXPORT_FAVORITE_CLASSIFY_KEY = "com.yougou.kaidian.export.favorite.classify.commodit";
	
	/**
	 *收藏夹-收藏商品
	 */
	public static final String C_EXPORT_FAVORITE_CLASSIFY_PRODANALYSE = "com.yougou.kaidian.export.favorite.classify.prodanalyse";
	/**
	 * 优购关闭appkey的标志，不允许商家修改，只能优购方修改或调度修改
	 */
	public static final String APPKEY_YOUGOU_STATUS = "appkey.yougou.status";
}
