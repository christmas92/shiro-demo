package shiro.listener;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author chenyifei
 * @date 2017-05-27
 */
@Component
public class SessionListener implements org.apache.shiro.session.SessionListener {

	private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.session.SessionListener#onStart(org.apache.shiro.session
	 * .Session)
	 */
	@Override
	public void onStart(Session session) {
		logger.debug("会话创建");
		logger.debug("id:" + session.getId());
		logger.debug("host" + session.getHost());
		logger.debug("timeout" + session.getTimeout());
		logger.debug("startTimestamp" + session.getStartTimestamp());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.session.SessionListener#onStop(org.apache.shiro.session.
	 * Session)
	 */
	@Override
	public void onStop(Session session) {
		logger.debug("会话停止：" + session.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.session.SessionListener#onExpiration(org.apache.shiro.
	 * session.Session)
	 */
	@Override
	public void onExpiration(Session session) {
		logger.debug("会话过期：" + session.getId());
	}

}
