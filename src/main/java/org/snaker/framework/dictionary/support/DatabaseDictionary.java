package org.snaker.framework.dictionary.support;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.snaker.framework.dictionary.AbstractDictionary;
import org.snaker.framework.dictionary.entity.DictionaryItem;
import org.snaker.framework.dictionary.service.DictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据库配置管理支持类，从数据库获取配置数据，提供给需要配置数据的对象
 * @author yuqs
 * @since 0.1
 */
@Component
public class DatabaseDictionary extends AbstractDictionary {
	@Autowired
	private DictionaryManager dictionaryManager;
	@Override
	public Map<String, String> getByName(String name) {
        List<DictionaryItem> items = dictionaryManager.getItemsByName(name);
        if(items == null || items.isEmpty()) return Collections.emptyMap();
        Map<String, String> dicts = new TreeMap<String, String>();
        for(DictionaryItem item : items) {
            dicts.put(item.getCode(), item.getName());
        }
        return dicts;
	}

}
