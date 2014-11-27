package com.snakerflow.framework.security.shiro;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import com.snakerflow.framework.security.entity.Authority;
import com.snakerflow.framework.security.entity.Resource;
import com.snakerflow.framework.security.service.ResourceManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 授权元数据根据两部分构成：
 * 1、数据库中动态生成，由注入的resourceManager根据资源、权限构造资源-权限的键值对
 * 2、使用spring的注入filterChainDefinitions，在配置文件中定义默认的安全定义，如登录页面，首页等
 * @author yuqs
 * @since 0.1
 */
public class ShiroDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {
	private static Log log = LogFactory.getLog(ShiroDefinitionSectionMetaSource.class);
	//注入资源管理对象
	@Autowired
	private ResourceManager resourceManager;
	//注入默认的授权定义
	private String filterChainDefinitions;
	//格式化数据，符合shiro的格式，如：perms["admin"]
	public static final String PREMISSION_FORMAT = "perms[\"{0}\"]";
	
	@Override
	public Section getObject() throws Exception {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		//由注入的资源管理对象获取所有资源数据，并且Resource的authorities的属性是EAGER的fetch类型
		List<Resource> resources = resourceManager.getAll();
		for(Resource resource : resources) {
			if(StringUtils.isEmpty(resource.getSource())) {
				continue;
			}
			for (Authority entity : resource.getAuthorities()) {
				//如果资源的值为分号分隔，则循环构造元数据。分号分隔好处是对一批相同权限的资源，不需要逐个定义
				if(resource.getSource().indexOf(";") != -1) {
					String[] sources = resource.getSource().split(";");
					for(String source : sources) {
					    putDefinitionSection(section, source, entity.getName());
					}
				} else {
				    putDefinitionSection(section, resource.getSource(), entity.getName());
				}
			}
		}
        section.put("/**", "user");
		return section;
	}
	
	private void putDefinitionSection(Section section, String key, String value) {
	    log.info("加载数据库权限：【key=" + key + "\tvalue=" + value + "】");
	    section.put(key, MessageFormat.format(PREMISSION_FORMAT, value));
	}
	
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}
	
	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}
	
	@Override
	public boolean isSingleton() {
		return false;
	}


}
