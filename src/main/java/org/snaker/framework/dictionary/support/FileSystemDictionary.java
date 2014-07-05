package org.snaker.framework.dictionary.support;

import java.util.Map;

import org.snaker.framework.dictionary.AbstractDictionary;

/**
 * 文件配置管理支持类，从文件系统中获取配置数据，提供给需要配置数据的对象
 * 文件配置数据，一般为无管理界面且基本上不改动的配置，如性别等
 * 该类后期可能被DatabaseSupport替代。可以简单配置的db管理。
 * @author yuqs
 * @since 0.1
 */
public class FileSystemDictionary extends AbstractDictionary {
	/**
	 * 
	 */
	@Override
	public Map<String, String> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
