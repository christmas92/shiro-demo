package shiro.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

/**
 * @author chenyifei
 * @date 2017-05-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config.xml"})
public class UserServiceTest {

	@Autowired
	private IUserService userService;

	/**
	 * Test method for {@link shiro.service.impl.UserServiceImpl#getByUsername(java.lang.String)}.
	 */
	@Test
	public void testGetByUsername() {
		System.out.println(JSON.toJSONString(userService.getByUsername("admin")));
	}
	
	@Test
	public void testListRoles() {
		System.out.println(JSON.toJSONString(userService.listRoles("admin")));
	}
	
	@Test
	public void testListPermissions() {
		System.out.println(JSON.toJSONString(userService.listPermissions("admin")));
	}

}
