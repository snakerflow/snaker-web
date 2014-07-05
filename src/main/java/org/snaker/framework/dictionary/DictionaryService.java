package org.snaker.framework.dictionary;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DictionaryService {
	@Autowired
	private IDictionary dictionary;
	public String find(String str, String config) {
		if(StringUtils.isEmpty(str)) return null;
		String findValue = "";
		Map<String, String> items = dictionary.getByName(config);
		Iterator<String> it = items.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String val = items.get(key);
			if(str.contains(key)) {
				findValue = val;
				break;
			}
		}
		return findValue;
	}
}
