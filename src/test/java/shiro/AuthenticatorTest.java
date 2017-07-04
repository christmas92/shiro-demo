package shiro;

import static org.junit.Assert.assertEquals;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class AuthenticatorTest {

	@Test
	public void testAllSuccessfulStrategyWithSuccess(){
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		PrincipalCollection principalCollection = subject.getPrincipals();
		
		assertEquals(2, principalCollection.asList().size());
		
		System.out.println(JSON.toJSONString(principalCollection.asList()));
		
	}
	
	

	private void login(String configPath) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configPath);
		SecurityManager securityManager = (SecurityManager) factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("chen", "123");

		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
	}

}
