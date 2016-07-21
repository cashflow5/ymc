package com.yougou.merchant.core.datasource;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 以事务为边界进行读写分离
 * 
 * @author luo.zj
 * @since 2012-08-21 16:00
 */
@Aspect
@Component
@Order(100)
public class DataSourceAdvice {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
	public void transactionalPointcut() {
	}

	@Before("transactionalPointcut()")
	public void before(final JoinPoint joinPoint) throws NoSuchMethodException {
		//如果拥有事务上下文，则将连接绑定到事务上下文中 
		if (!TransactionSynchronizationManager.isSynchronizationActive()) {
			DataSourceSwitcher.setDataSource("master");
		}
	}

	@After("transactionalPointcut()")
	public void after(final JoinPoint joinPoint) throws NoSuchMethodException {
		//如果拥有事务上下文，则将连接绑定到事务上下文中 
		if (!TransactionSynchronizationManager.isSynchronizationActive()) {
			DataSourceSwitcher.clearDataSource();
		}
	}

	@AfterThrowing("transactionalPointcut()")
	public void afterThrowing(final JoinPoint joinPoint) throws NoSuchMethodException {
		DataSourceSwitcher.clearDataSource();
	}
}
