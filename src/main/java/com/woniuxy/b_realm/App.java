package com.woniuxy.b_realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class App {
	@Test
	public void testName() throws Exception {
		DefaultSecurityManager securityManager=new DefaultSecurityManager();
		MyRealm realm=new MyRealm();
		securityManager.setRealm(realm);
		
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject=SecurityUtils.getSubject();
		//========================================================================
		UsernamePasswordToken token=new UsernamePasswordToken("andy","456");
		
		
		
		
		//========================================================================
		subject.login(token);
	}
}
