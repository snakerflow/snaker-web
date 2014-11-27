package com.snakerflow.framework.security.service;

import java.util.List;

import org.hibernate.SQLQuery;
import com.snakerflow.framework.orm.Page;
import com.snakerflow.framework.orm.PropertyFilter;
import com.snakerflow.framework.security.dao.MenuDao;
import com.snakerflow.framework.security.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 菜单管理类
 * @author yuqs
 * @since 0.1
 */
@Component
public class MenuManager {
	//注入菜单持久化对象
	@Autowired
	private MenuDao menuDao;
	//注入资源管理对象
	@Autowired
	private ResourceManager resourceManager;
	
	/**
	 * 保存菜单实体
	 * @param entity
	 */
	public void save(Menu entity) {
		menuDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除菜单实体
	 * @param id
	 */
	public void delete(Long id) {
		resourceManager.updateByMenuId(id);
		menuDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取菜单实体
	 * @param id
	 * @return
	 */
	public Menu get(Long id) {
		return menuDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询菜单列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Menu> findPage(final Page<Menu> page, final List<PropertyFilter> filters) {
		return menuDao.findPage(page, filters);
	}
	
	/**
	 * 获取所有菜单
	 * @return
	 */
	public List<Menu> getAll() {
		return menuDao.getAll();
	}
	
	/**
	 * 根据用户ID查询该用户允许访问的所有菜单列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getAllowedAccessMenu(Long userId) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from (");
		//获取Sec_Menu表中定义且未关联资源表Sec_Resource的所有菜单列表
		sqlBuffer.append(" select m.id,m.name,m.parent_menu,m.description,m.orderby from sec_menu m ");
		sqlBuffer.append(" where not exists (select re.id from sec_resource re where re.menu = m.id)" );
		sqlBuffer.append(" union all ");
		//获取Sec_Resource表中已关联且未设置权限的菜单列表
		sqlBuffer.append(" select m.id,m.name,m.parent_menu,re.source as description,m.orderby from sec_resource re ");
		sqlBuffer.append(" left outer join sec_menu m on re.menu = m.id  ");
		sqlBuffer.append(" where re.menu is not null and not exists (select ar.authority_id from sec_authority_resource ar where ar.resource_id = re.id)");
		sqlBuffer.append(" union all ");
		//获取Sec_Resource表中已关联且设置权限，并根据当前登录账号拥有相应权限的菜单列表
		sqlBuffer.append(" select m.id,m.name,m.parent_menu,re.source as description,m.orderby from sec_user u ");
		sqlBuffer.append(" left outer join sec_role_user ru on u.id=ru.user_id ");
		sqlBuffer.append(" left outer join sec_role r on ru.role_id=r.id ");
		sqlBuffer.append(" left outer join sec_role_authority ra on r.id = ra.role_id ");
		sqlBuffer.append(" left outer join sec_authority a on ra.authority_id = a.id ");
		sqlBuffer.append(" left outer join sec_authority_resource ar on a.id = ar.authority_id ");
		sqlBuffer.append(" left outer join sec_resource re on ar.resource_id = re.id ");
		sqlBuffer.append(" left outer join sec_menu m on re.menu = m.id ");
		sqlBuffer.append(" where u.id=? and re.menu is not null ");
		sqlBuffer.append(") tbl order by orderby");
		SQLQuery query = menuDao.createSQLQuery(sqlBuffer.toString(), userId);
		query.addEntity(Menu.class);
		return query.list();
	}
}
