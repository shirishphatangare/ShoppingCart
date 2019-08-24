package com.example.springsecuritywebmvc.shopping.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	@Before("com.example.springsecuritywebmvc.shopping.AOP.LoggingPointcutDeclarations.CommonLoggingUserPD() || com.example.springsecuritywebmvc.shopping.AOP.LoggingPointcutDeclarations.CommonLoggingEmpPD() || com.example.springsecuritywebmvc.shopping.AOP.LoggingPointcutDeclarations.CommonLoggingMgrPD()")
	public void logBeforeMethod(final JoinPoint jp) {
		System.out.println("ShoppingCart App Before method: " + jp.getSignature().getName()
			    + ". Class: " + jp.getTarget().getClass().getSimpleName());
	}

	
	@After("com.example.springsecuritywebmvc.shopping.AOP.LoggingPointcutDeclarations.CommonLoggingUserPD() || com.example.springsecuritywebmvc.shopping.AOP.LoggingPointcutDeclarations.CommonLoggingEmpPD() || com.example.springsecuritywebmvc.shopping.AOP.LoggingPointcutDeclarations.CommonLoggingMgrPD()")
	public void logAfterMethod(final JoinPoint jp) {
		System.out.println("ShoppingCart App  After method: " + jp.getSignature().getName()
			    + ". Class: " + jp.getTarget().getClass().getSimpleName());
	}
}
