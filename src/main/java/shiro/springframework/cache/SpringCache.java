package shiro.springframework.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache.ValueWrapper;

/**
 * @author chenyifei
 * @param <K>
 * @param <V>
 * @date 2017-05-19
 */
public class SpringCache<K, V> implements Cache<K, V> {

	private static final Logger log = LoggerFactory.getLogger(SpringCache.class);

	private org.springframework.cache.Cache cache;

	public SpringCache(org.springframework.cache.Cache cache) {
		if (cache == null) {
			throw new IllegalArgumentException("Cache argument cannot be null.");
		}
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	public V get(K key) throws CacheException {
		if (log.isTraceEnabled()) {
			log.trace("Getting object from cache [" + cache.getName() + "] for key [" + key + "]");
		}
		if (key == null) {
			return null;
		} else {
			ValueWrapper element = cache.get(key);
			if (element == null) {
				if (log.isTraceEnabled()) {
					log.trace("Element for [" + key + "] is null.");
				}
				return null;
			} else {
				return (V) element.get();
			}
		}

	}

	public V put(K key, V value) throws CacheException {
		if (log.isTraceEnabled()) {
			log.trace("Putting object in cache [" + cache.getName() + "] for key [" + key + "]");
		}
		V previous = get(key);
		cache.put(key, value);
		return previous;
	}

	public V remove(K key) throws CacheException {
		if (log.isTraceEnabled()) {
			log.trace("Removing object from cache [" + cache.getName() + "] for key [" + key + "]");
		}
		V previous = get(key);
		cache.evict(key);
		return previous;
	}

	public void clear() throws CacheException {
		if (log.isTraceEnabled()) {
			log.trace("Clearing all objects from cache [" + cache.getName() + "]");
		}
		cache.clear();
	}
	
	public int size() {
		throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
	}

	public Set<K> keys() {
		throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
	}

	public Collection<V> values() {
		throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
	}

	

}
