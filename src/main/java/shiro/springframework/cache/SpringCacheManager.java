package shiro.springframework.cache;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenyifei
 * @date 2017-05-19
 */
public class SpringCacheManager implements CacheManager {

	private static final Logger log = LoggerFactory.getLogger(SpringCacheManager.class);

	private org.springframework.cache.CacheManager manager;

	public org.springframework.cache.CacheManager getCacheManager() {
		return manager;
	}

	public void setCacheManager(org.springframework.cache.CacheManager manager) {
		this.manager = manager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if (log.isTraceEnabled()) {
			log.trace("Acquiring EhCache instance named [" + name + "]");
		}
		org.springframework.cache.Cache cache = manager.getCache(name);
		return new SpringCache<>(cache);
	}
	

}
