package shiro.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * @author chenyifei
 * @date 2017-05-23
 */
@Controller
@RequestMapping("")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("login")
	public String loginView() {
		return "login";
	}

	@GetMapping("logout")
	public String Logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:login";
	}

	@PostMapping("login")
	@ResponseBody
	public Object login(String username, String password,
			@RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe) {
		String error = null;
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		//设置记住密码
		System.out.println(1 == rememberMe);
		token.setRememberMe(1 == rememberMe);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "账号不存在";
		} catch (IncorrectCredentialsException e) {
			error = "密码错误";
		} catch (AuthenticationException e) {
			error = "其他错误" + e.getMessage();
		}
		if (error != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("error", error);
			return map;
		} else {
			return true;
		}
	}

	@GetMapping("index")
	public String indexView() {
		// logger.debug(JSON.toJSONString(springCacheManager.getCacheNames()));
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		logger.debug("sessionId: " + session.getId());
		logger.debug("startTimestamp" + session.getStartTimestamp());
		logger.debug("lastAccessTime" + session.getLastAccessTime());
		return "index";
	}

	@GetMapping("test")
	@ResponseBody
	public Object test() {
		return true;
	}
}
