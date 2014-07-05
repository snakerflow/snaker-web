package org.snaker.framework.dictionary.support;

import java.util.Map;

import org.snaker.framework.dictionary.AbstractDictionary;

/**
 * 缓存配置字典管理支持类，从缓存中获取字典数据，提供给实际需要的类
 * @author yuqs
 * @since 0.1
 */
public class CacheDictionary extends AbstractDictionary {
	/**
	 * 根据字典名称，直接从缓存中获取key为name的字典对象。一般为Map<Long, String>类型
	 * @param name
	 * @return
	 */
	@Override
	public Map<String, String> getByName(String name) {
		//TODO
		return null;
	}
}
