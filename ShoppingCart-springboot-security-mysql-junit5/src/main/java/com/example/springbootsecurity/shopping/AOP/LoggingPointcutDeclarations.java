package com.example.springbootsecurity.shopping.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingPointcutDeclarations {

	@Pointcut("execution( * com.example.springbootsecurity.shopping.user.*.*(..) )")
	public void CommonLoggingUserPD() {
		
	}
	
	@Pointcut("execution( * com.example.springbootsecurity.shopping.employee.*.*(..) )")
	public void CommonLoggingEmpPD() {
		
	}
	
	@Pointcut("execution( * com.example.springbootsecurity.shopping.manager.*.*(..) )")
	public void CommonLoggingMgrPD() {
		
	}
	
}
	

