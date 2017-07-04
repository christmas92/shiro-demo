package shiro.credential;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import shiro.constant.Constants;

/**
 * @author chenyifei
 * @date 2017-05-26
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

	private final CacheManager cacheManager;
	private String passwordRetryCacheName;
	private Cache<String, AtomicInteger> passwordReplyCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public String getPasswordRetryCacheName() {
		return passwordRetryCacheName;
	}
	
	public void setPasswordRetryCacheName(String passwordRetryCacheName) {
		this.passwordRetryCacheName = passwordRetryCacheName;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		AtomicInteger replyCount = passwordReplyCache.get(username);
		if (replyCount == null) {
			replyCount = new AtomicInteger(0);
			passwordReplyCache.put(username, replyCount);
		}
		if (replyCount.incrementAndGet() > Constants.PASSWORD_REPLY_LIMIT) {
			System.out.println(Calendar.getInstance().getTime());
			logger.warn("username: " + username + " tried to login more than " + Constants.PASSWORD_REPLY_LIMIT
					+ " times in period");
			throw new ExcessiveAttemptsException("用户名: " + username + " 密码连续输入错误超过" + Constants.PASSWORD_REPLY_LIMIT
					+ "次，锁定1分钟！");
		}
		boolean match = super.doCredentialsMatch(token, info);
		if (match) {
			passwordReplyCache.remove(username);
		}
		return match;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(passwordRetryCacheName, "you must set passwordReplyCacheName!");
		this.passwordReplyCache = cacheManager.getCache(passwordRetryCacheName);
	}

}
