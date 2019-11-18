package com.chinaums.commons;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 请求ids
 *
 * @author cly
 */
public class ReqIds extends Ids {

	private ConcurrentMap<String, Object> attr = new ConcurrentHashMap<>();

	public void setAttr(final String k, final Object v) {
		if (k != null && v != null) {
			attr.put(k, v);
		}
	}

	public Map<String, Object> getAttr() {
		return attr;
	}

	public Object getAttr(final String k) {
		return attr.get(k);
	}

	public <T> T getAttr(final String k, Class<T> clazz) {
		return attr.get(k) != null ? (T) attr.get(k) : null;
	}

	public Set<String> attrName() {
		return attr.keySet();
	}

}
