package com.yougou.dto.output;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: QueryCommodityOutputDto</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年6月12日
 */
public class QueryCommodityOutputDto extends PageableOutputDto {

	private static final long serialVersionUID = -3280619796648995676L;
	
	/** 页数据 **/
	private List<Commodity> items = Collections.emptyList();
	
	public QueryCommodityOutputDto() {
		super();
	}

	public QueryCommodityOutputDto(int page_index, int page_size, int total_count) {
		super(page_index, page_size, total_count);
	}

	public List<Commodity> getItems() {
		return items;
	}

	public void setItems(List<Commodity> items) {
		this.items = items;
	}
	
	public static class Commodity implements Serializable {
		private static final long serialVersionUID = -744241858089947020L;
		
		private String commodityNo;//商品编号
	    private String styleNo;//款号
	    private String supplierCode;//款色编码
	    private String commodityName;//商品名称
	    private String aliasName;//商品别名
	    private String catNo;//分类编号
	    private String catName;//分类名称
	    private String catStructName;//分类结构名称
	    private String brandNo;//品牌编号
	    private String brandName;//品牌名称
	    private String brandEnglishName;//品牌英文名称
	    private String colorNo;//颜色编号
	    private String colorName;//颜色
	    private String years;//年份
	    private String merchantCode;//商家编码
	    private Integer status;//商品状态
	    private Double costPrice;//成本价
	    private Double MarkPrice;//市场价
	    private Double sellPrice;//销售价
	    private String defalutPic;//默认图片
	    private String picSmall;//商品小图
	    private String commodityDesc;//宝贝描述
	    private Date createDate;//创建时间
	    private Date sellDate;//上架时间
	    private Date updateDate;//修改时间
	    private Date downDate;//下架时间
	    private Date firstSellDate;//首次上架时间

	    private List<Product> products;//货品信息列表

		public String getCommodityNo() {
			return commodityNo;
		}

		public void setCommodityNo(String commodityNo) {
			this.commodityNo = commodityNo;
		}

		public String getStyleNo() {
			return styleNo;
		}

		public void setStyleNo(String styleNo) {
			this.styleNo = styleNo;
		}

		public String getSupplierCode() {
			return supplierCode;
		}

		public void setSupplierCode(String supplierCode) {
			this.supplierCode = supplierCode;
		}

		public String getCommodityName() {
			return commodityName;
		}

		public void setCommodityName(String commodityName) {
			this.commodityName = commodityName;
		}

		public String getAliasName() {
			return aliasName;
		}

		public void setAliasName(String aliasName) {
			this.aliasName = aliasName;
		}

		public String getCatNo() {
			return catNo;
		}

		public void setCatNo(String catNo) {
			this.catNo = catNo;
		}

		public String getCatName() {
			return catName;
		}

		public void setCatName(String catName) {
			this.catName = catName;
		}

		public String getCatStructName() {
			return catStructName;
		}

		public void setCatStructName(String catStructName) {
			this.catStructName = catStructName;
		}

		public String getBrandNo() {
			return brandNo;
		}

		public void setBrandNo(String brandNo) {
			this.brandNo = brandNo;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}

		public String getBrandEnglishName() {
			return brandEnglishName;
		}

		public void setBrandEnglishName(String brandEnglishName) {
			this.brandEnglishName = brandEnglishName;
		}

		public String getColorNo() {
			return colorNo;
		}

		public void setColorNo(String colorNo) {
			this.colorNo = colorNo;
		}

		public String getColorName() {
			return colorName;
		}

		public void setColorName(String colorName) {
			this.colorName = colorName;
		}

		public String getYears() {
			return years;
		}

		public void setYears(String years) {
			this.years = years;
		}

		public String getMerchantCode() {
			return merchantCode;
		}

		public void setMerchantCode(String merchantCode) {
			this.merchantCode = merchantCode;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Double getCostPrice() {
			return costPrice;
		}

		public void setCostPrice(Double costPrice) {
			this.costPrice = costPrice;
		}

		public Double getMarkPrice() {
			return MarkPrice;
		}

		public void setMarkPrice(Double markPrice) {
			MarkPrice = markPrice;
		}

		public Double getSellPrice() {
			return sellPrice;
		}

		public void setSellPrice(Double sellPrice) {
			this.sellPrice = sellPrice;
		}

		public String getDefalutPic() {
			return defalutPic;
		}

		public void setDefalutPic(String defalutPic) {
			this.defalutPic = defalutPic;
		}

		public String getPicSmall() {
			return picSmall;
		}

		public void setPicSmall(String picSmall) {
			this.picSmall = picSmall;
		}

		public String getCommodityDesc() {
			return commodityDesc;
		}

		public void setCommodityDesc(String commodityDesc) {
			this.commodityDesc = commodityDesc;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getSellDate() {
			return sellDate;
		}

		public void setSellDate(Date sellDate) {
			this.sellDate = sellDate;
		}

		public Date getUpdateDate() {
			return updateDate;
		}

		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}

		public Date getDownDate() {
			return downDate;
		}

		public void setDownDate(Date downDate) {
			this.downDate = downDate;
		}

		public Date getFirstSellDate() {
			return firstSellDate;
		}

		public void setFirstSellDate(Date firstSellDate) {
			this.firstSellDate = firstSellDate;
		}

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}
	}
	
	public static class Product implements Serializable {
		
		private static final long serialVersionUID = 8226067501979371143L;
		
		private String productNo;//货品编号
	    private String sizeNo;//尺码编号
	    private String sizeName;//尺码名称
	    private String thirdPartyInsideCode;//第三方条形码
	    private String insideCode;//货品条码
	    private Integer quantity;
	    private Integer yougouReserved;//优购预留的库存数量
	    private Integer taobaoReserved;//淘宝预留的库存数量
	    private Long weight;//重量
	    private Long height;//高度
	    private Long width;//宽度
	    private Long length;//长度
		public String getProductNo() {
			return productNo;
		}
		public void setProductNo(String productNo) {
			this.productNo = productNo;
		}
		public String getSizeNo() {
			return sizeNo;
		}
		public void setSizeNo(String sizeNo) {
			this.sizeNo = sizeNo;
		}
		public String getSizeName() {
			return sizeName;
		}
		public void setSizeName(String sizeName) {
			this.sizeName = sizeName;
		}
		public String getThirdPartyInsideCode() {
			return thirdPartyInsideCode;
		}
		public void setThirdPartyInsideCode(String thirdPartyInsideCode) {
			this.thirdPartyInsideCode = thirdPartyInsideCode;
		}
		public String getInsideCode() {
			return insideCode;
		}
		public void setInsideCode(String insideCode) {
			this.insideCode = insideCode;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public Integer getYougouReserved() {
			return yougouReserved;
		}
		public void setYougouReserved(Integer yougouReserved) {
			this.yougouReserved = yougouReserved;
		}
		public Integer getTaobaoReserved() {
			return taobaoReserved;
		}
		public void setTaobaoReserved(Integer taobaoReserved) {
			this.taobaoReserved = taobaoReserved;
		}
		public Long getWeight() {
			return weight;
		}
		public void setWeight(Long weight) {
			this.weight = weight;
		}
		public Long getHeight() {
			return height;
		}
		public void setHeight(Long height) {
			this.height = height;
		}
		public Long getWidth() {
			return width;
		}
		public void setWidth(Long width) {
			this.width = width;
		}
		public Long getLength() {
			return length;
		}
		public void setLength(Long length) {
			this.length = length;
		}

	}
	

	
}
