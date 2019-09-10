package com.woniuxy.a_hello;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AppTest {
	@Test
	public void testName() throws Exception {
		//加载shiro.ini配置文件，得到一个工厂
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:com/woniuxy/a_hello/shiro.ini");
		//从工厂中获取一个SecurityManager类的实例
		//SecurityManager是shiro中的大总管，管理各种功能：认证，授权，缓存，会话，加密等等
		SecurityManager sm=factory.getInstance();
		
		//把SecurityManager设置为全局的。（在真实的web环境中，该行代码不用写，而是有一个过滤器完成的）
		SecurityUtils.setSecurityManager(sm);
		// 获取一个Subject对象，该对象代表要访问当前系统的那个主体：人、另外一个程序。
		Subject subject = SecurityUtils.getSubject();
		
		// 认证（动词）
		UsernamePasswordToken token=new UsernamePasswordToken("foo","123");
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("账户不存在！");
			return;
		} catch(IncorrectCredentialsException e) {
			System.out.println("密码不正确！");
			return;
		}
		System.out.println("认证成功："+subject.getPrincipal());
		
		// 授权（动词）
		System.out.println("admin:"+subject.hasRole("admin"));
		System.out.println("guest:"+subject.hasRole("guest"));
		
//		subject.checkRole("guest");
		System.out.println("user:save " + subject.isPermitted("user:save"));
		System.out.println("user:delete " + subject.isPermitted("user:delete"));
		System.out.println("user:update " + subject.isPermitted("user:update"));
		System.out.println("user:find " + subject.isPermitted("user:find"));
		
		subject.checkPermission("user:save");
		// 退出登录
		subject.logout();
	}
	
	@Test
	public void test2() throws Exception {
		DefaultSecurityManager securityManager=new DefaultSecurityManager();
		IniRealm realm=new IniRealm("classpath:com/woniuxy/a_hello/shiro.ini");
		securityManager.setRealm(realm);
		
		//把SecurityManager设置为全局的。（在真实的web环境中，该行代码不用写，而是有一个过滤器完成的）
		SecurityUtils.setSecurityManager(securityManager);
		// 获取一个Subject对象，该对象代表要访问当前系统的那个主体：人、另外一个程序。
		Subject subject = SecurityUtils.getSubject();
		
		// 认证（动词）
		UsernamePasswordToken token=new UsernamePasswordToken("foo","123");
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("账户不存在！");
			return;
		} catch(IncorrectCredentialsException e) {
			System.out.println("密码不正确！");
			return;
		}
		
		System.out.println("认证成功："+subject.getPrincipal());
		
		// 授权（动词）
		System.out.println("admin:"+subject.hasRole("admin"));
		System.out.println("guest:"+subject.hasRole("guest"));
		
		
		
	}
}
