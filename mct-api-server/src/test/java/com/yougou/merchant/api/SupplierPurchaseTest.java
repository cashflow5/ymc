package com.yougou.merchant.api;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.supplier.service.ISupplierPurchaseService;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog.OperationType;
import com.yougou.merchant.api.supplier.vo.SupplierUpdateHistory;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SupplierPurchaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private ISupplierPurchaseService purchaseService;
    
    @Test
    public void insertSupplier() throws Exception {
    	SupplierVo vo = new SupplierVo();
    	vo.setId(UUIDGenerator.getUUID());
    	vo.setAccount("sgdfgdfg");
    	vo.setAddress("sdgdgfhgh");
    	vo.setBalanceTraderCode("sdgdfg");
    	vo.setBalanceTraderName("sdgdfgfgh");
    	vo.setBank("sdgdfg");
    	vo.setBankLocal("sdgdg");
    	vo.setBusinessLicense("sgdfg");
    	vo.setBusinessLocal("sgdfg");
    	vo.setBusinessValidity("sgdg");
    	vo.setContact("sgdfg");
    	vo.setConTime(NumberUtils.INTEGER_ONE);
    	vo.setCouponsAllocationProportion(1.1);
    	vo.setCreator("huang.tao");
    	vo.setDeleteFlag(NumberUtils.INTEGER_ONE);
    	vo.setDutyCode("sgdfg");
    	vo.setEmail("sdfdg");
    	vo.setEnglishName("english");
    	vo.setFax("sgdfg");
    	vo.setInstitutional("sdgdfg");
    	vo.setInventoryCode("sgdgfh");
    	vo.setIsConfig(NumberUtils.INTEGER_ONE);
    	vo.setIsHongKong(1);
    	vo.setIsInputYougouWarehouse(1);
    	vo.setIsUseYougouWms(1);
    	vo.setIsValid(1);
    	vo.setLoginAccount("sgdg");
    	vo.setLoginPassword("sdgdfg");
    	vo.setPayType("sg");
    	vo.setPosSourceNo("sgdfg");
    	vo.setRemark("sgdfg");
    	vo.setSetOfBooksCode("sdgdfg");
    	vo.setSetOfBooksName("sgdfgdf");
    	vo.setShipmentType(1);
    	vo.setSimpleName("sgdg");
    	vo.setSubBank("sdgdg");
    	vo.setSupplier("sdgdfgfghfgh名称");
    	vo.setSupplierCode("SPsdfdgfghfh");
    	vo.setSupplierType("普通供应商");
    	vo.setTallageNo("sdfgdfgfg");
    	vo.setTaxpayer("sdgdfg");
    	vo.setTaxRate(20.0);
    	vo.setTelePhone("1354657678678");
    	vo.setTradeCurrency("sgdg");
    	vo.setUpdateDate(new Date());
    	vo.setUpdateTimestamp(System.currentTimeMillis());
    	vo.setUpdateUser("huang.tao1");
    	vo.setUrl("sdgdfg");
    	
    	purchaseService.insertSupplier(vo);
    }

    @Test
    public void updateSupplier() throws Exception {
    	SupplierVo vo = new SupplierVo();
    	vo.setId("f13d8e5e8d1442ccacbd41a0dee2c677");
    	vo.setAccount("_sgdfgdfg");
    	vo.setAddress("_sdgdgfhgh");
    	vo.setBalanceTraderCode("_sdgdfg");
    	vo.setBalanceTraderName("_sdgdfgfgh");
    	vo.setBank("_sdgdfg");
    	vo.setBankLocal("_sdgdg");
    	vo.setBusinessLicense("_sgdfg");
    	vo.setBusinessLocal("_sgdfg");
    	vo.setBusinessValidity("_sgdg");
    	vo.setContact("_sgdfg");
    	vo.setConTime(100);
    	vo.setCouponsAllocationProportion(21.1);
    	vo.setCreator("huang.tao_");
    	vo.setDeleteFlag(NumberUtils.INTEGER_ONE);
    	vo.setDutyCode("sgdfg_");
    	vo.setEmail("sdfdg_");
    	vo.setEnglishName("_english");
    	vo.setFax("sgdfg_");
    	vo.setInstitutional("_sdgdfg");
    	vo.setInventoryCode("_sgdgfh");
    	vo.setIsConfig(NumberUtils.INTEGER_ONE);
    	vo.setIsHongKong(100);
    	vo.setIsInputYougouWarehouse(100);
    	vo.setIsUseYougouWms(100);
    	vo.setIsValid(100);
    	vo.setLoginAccount("_sgdg");
    	vo.setLoginPassword("_sdgdfg");
    	vo.setPayType("sg_");
    	vo.setPosSourceNo("_sgdfg");
    	vo.setRemark("sgdfg_");
    	vo.setSetOfBooksCode("_sdgdfg");
    	vo.setSetOfBooksName("_sgdfgdf");
    	vo.setShipmentType(100);
    	vo.setSimpleName("sgdg_");
    	vo.setSubBank("sdgdg_");
    	vo.setSupplier("_sdgdfgfghfgh名称");
    	vo.setSupplierCode("_SPsdfdgfghfh");
    	vo.setSupplierType("_普通供应商");
    	vo.setTallageNo("_sdfgdfgfg");
    	vo.setTaxpayer("_sdgdfg");
    	vo.setTaxRate(200.0);
    	vo.setTelePhone("_1354657678678");
    	vo.setTradeCurrency("_sgdg");
    	vo.setUpdateDate(new Date());
    	vo.setUpdateTimestamp(System.currentTimeMillis());
    	vo.setUpdateUser("_huang.tao1");
    	vo.setUrl("sdgdfg_");
    	
    	purchaseService.updateSupplier(vo);
    }
    
    @Test
    public void saveMerchantOperationLog() throws Exception {
    	MerchantOperationLog vo = new MerchantOperationLog();
    	vo.setId(UUIDGenerator.getUUID());
    	vo.setMerchantCode("sdfsdf");
    	vo.setOperated(new Date());
    	vo.setOperationNotes("测试");
    	vo.setOperationType(OperationType.ACCOUNT);
    	vo.setOperator("sdgdfg");
    	
        purchaseService.saveMerchantOperationLog(vo);
    }

    @Test
    public void deleteSupplierById() throws Exception {
        purchaseService.deleteSupplierById("f13d8e5e8d1442ccacbd41a0dee2c677", "xcdf");
    }
    
    @Test
    public void deleteSupplierBySupplierCode() throws Exception {
        purchaseService.deleteSupplierBySupplierCode("SPsdfdgfghfh", "sgdg");
    }
//    
//    @Test
//    public void insertSupplierUpdateHistory() throws Exception {
//    	SupplierUpdateHistory vo = new SupplierUpdateHistory();
//    	vo.setId(UUIDGenerator.getUUID());
//    	vo.setField("sgdfg");
//    	vo.setOperator("dgdfg");
//    	vo.setOperatorTime(new Date());
//    	vo.setProcessing("sdgdfg");
//    	vo.setSupplierId("dfgdfghfhgh");
//    	vo.setUpdateAfter("xxxx");
//    	vo.setUpdateBefore("sdgdgfg");
//    	
//    	purchaseService.insertSupplierUpdateHistory(vo);
//    }
    
}
