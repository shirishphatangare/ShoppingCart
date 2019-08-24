package com.example.springsecuritywebmvc.shopping.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingPointcutDeclarations {

	@Pointcut("execution( * com.example.springsecuritywebmvc.shopping.user.*.*(..) )")
	public void CommonLoggingUserPD() {
		
	}
	
	@Pointcut("execution( * com.example.springsecuritywebmvc.shopping.employee.*.*(..) )")
	public void CommonLoggingEmpPD() {
		
	}
	
	@Pointcut("execution( * com.example.springsecuritywebmvc.shopping.manager.*.*(..) )")
	public void CommonLoggingMgrPD() {
		
	}
	
}
	

