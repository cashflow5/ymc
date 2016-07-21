/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Api监控预警
 * 
 * @author huang.tao
 *
 */
public class MonitorEarlyWarning implements Serializable {
	
	private static final long serialVersionUID = -5693969468095820607L;
	
	private String id;
	
	//appKey 对应到数据库的appkey
	private String appKey;
	//appKeyHolder持有者
	private String appKeyHolder;
	private String timeQuantum;
	private Integer warmAppkeyCallCount;
	private Integer warmDayCallCount;
	private Integer warmRateCount;
	private Integer warmSuccessCount;
	
	/** appKey 日调用次数预警明细 */
	private MonitorAppkeyWarnDetail appKeyDetail;
	/** api 日调用次数预警明细 */
	private List<MonitorDayWarnDetail> dayDetails;
	/** api调用成功率预警明细 */
	private List<MonitorSuccRateWarnDetail> succRateDetails;
	/** api调用频率预警明细 */
	private List<MonitorRateWarnDetail> rateWarnDetails;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppKeyHolder() {
		return appKeyHolder;
	}
	public void setAppKeyHolder(String appKeyHolder) {
		this.appKeyHolder = appKeyHolder;
	}
	public String getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public Integer getWarmAppkeyCallCount() {
		return warmAppkeyCallCount;
	}
	public void setWarmAppkeyCallCount(Integer warmAppkeyCallCount) {
		this.warmAppkeyCallCount = warmAppkeyCallCount;
	}
	public Integer getWarmDayCallCount() {
		return warmDayCallCount;
	}
	public void setWarmDayCallCount(Integer warmDayCallCount) {
		this.warmDayCallCount = warmDayCallCount;
	}
	public Integer getWarmRateCount() {
		return warmRateCount;
	}
	public void setWarmRateCount(Integer warmRateCount) {
		this.warmRateCount = warmRateCount;
	}
	public Integer getWarmSuccessCount() {
		return warmSuccessCount;
	}
	public void setWarmSuccessCount(Integer warmSuccessCount) {
		this.warmSuccessCount = warmSuccessCount;
	}
	public MonitorAppkeyWarnDetail getAppKeyDetail() {
		return appKeyDetail;
	}
	public void setAppKeyDetail(MonitorAppkeyWarnDetail appKeyDetail) {
		this.appKeyDetail = appKeyDetail;
	}
	public List<MonitorDayWarnDetail> getDayDetails() {
		return dayDetails;
	}
	public void setDayDetails(List<MonitorDayWarnDetail> dayDetails) {
		this.dayDetails = dayDetails;
	}
	public List<MonitorSuccRateWarnDetail> getSuccRateDetails() {
		return succRateDetails;
	}
	public void setSuccRateDetails(List<MonitorSuccRateWarnDetail> succRateDetails) {
		this.succRateDetails = succRateDetails;
	}
	public List<MonitorRateWarnDetail> getRateWarnDetails() {
		return rateWarnDetails;
	}
	public void setRateWarnDetails(List<MonitorRateWarnDetail> rateWarnDetails) {
		this.rateWarnDetails = rateWarnDetails;
	}
	
	
}
