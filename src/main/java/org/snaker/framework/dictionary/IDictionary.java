package org.snaker.framework.dictionary;

import java.util.Map;

/**
 * 配置字典管理接口，向平台提供字典数据
 * @author yuqs
 * @since 0.1
 */
public interface IDictionary {
	/**
	 * 根据字典名称，获取配置字典数据对象
	 * @param name
	 * @return Map<String, String> 选项主键ID、选项名称的字典映射集合
	 */
	public abstract Map<String, String> getByName(String name);
}
