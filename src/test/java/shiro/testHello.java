package shiro;

import static org.junit.Assert.assertEquals;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class testHello {

	@Test
	public void testHelloShiro() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = (SecurityManager) factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("chen", "123");

		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertEquals(true, subject.isAuthenticated());

		subject.logout();
	}

	@Test
	public void testCustomRealm() {

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("chenyifei", "123");

		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(true, subject.isAuthenticated());

		subject.logout();

	}

	@Test
	public void testCustomMultiRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		assertEquals(true, subject.isAuthenticated());
		
		subject.logout();
	}
	
	@Test
	public void testJDBCRealm(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("chenyifei", "123456");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(true, subject.isAuthenticated());
		
		subject.logout();
		
	}
	

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
	}

}
