package com.snakerflow.framework.security.service;

import java.util.List;

import org.hibernate.SQLQuery;
import com.snakerflow.framework.orm.Page;
import com.snakerflow.framework.orm.PropertyFilter;
import com.snakerflow.framework.security.dao.ResourceDao;
import com.snakerflow.framework.security.entity.Authority;
import com.snakerflow.framework.security.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 资源管理类
 * @author yuqs
 * @since 0.1
 */
@Component
public class ResourceManager {
	//注入资源持久化对象
	@Autowired
	private ResourceDao resourceDao;
	//注入权限持久化对象
	@Autowired
	private AuthorityManager authorityManager;
	
	/**
	 * 保存资源实体
	 * @param entity
	 */
	public void save(Resource entity) {
		resourceDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除资源实体
	 * @param id
	 */
	public void delete(Long id) {
		List<Authority> authorities = authorityManager.getAll();
		for(Authority auth : authorities) {
			for(Resource resource : auth.getResources()) {
				if(resource.getId().longValue() == id.longValue()) {
					auth.getResources().remove(resource);
					authorityManager.save(auth);
					break;
				}
			}
		}
		resourceDao.delete(id);
	}
	
	public void updateByMenuId(Long menuId) {
		String hql = "from Resource as r where r.menu.id=?";
		Resource resource = resourceDao.findUnique(hql, menuId);
		if(resource != null) {
			resource.setMenu(null);
			resourceDao.save(resource);
		}
	}
	
	/**
	 * 根据主键ID获取资源实体
	 * @param id
	 * @return
	 */
	public Resource get(Long id) {
		return resourceDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询资源列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Resource> findPage(final Page<Resource> page, final List<PropertyFilter> filters) {
		return resourceDao.findPage(page, filters);
	}
	
	/**
	 * 查询所有资源记录
	 * @return
	 */
	public List<Resource> getAll() {
		return resourceDao.getAll();
	}
	
	/**
	 * 根据用户ID查询该用户具有权限访问的资源与不需要授权的资源
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Resource> getAuthorizedResource(Long userId) {
		String sql = " select re.id, re.name, re.source, re.menu from sec_user u " + 
					" left outer join sec_role_user ru on u.id=ru.user_id " + 
					" left outer join sec_role r on ru.role_id=r.id " + 
					" left outer join sec_role_authority ra on r.id = ra.role_id " + 
					" left outer join sec_authority a on ra.authority_id = a.id " + 
					" left outer join sec_authority_resource ar on a.id = ar.authority_id " + 
					" left outer join sec_resource re on ar.resource_id = re.id " + 
					" where u.id=? and re.menu is not null " +
					" union all " +
					" select re.id, re.name, re.source, re.menu from sec_resource re " + 
					" where re.menu is not null and not exists (select ar.authority_id from sec_authority_resource ar where ar.resource_id = re.id)";
		SQLQuery query = resourceDao.createSQLQuery(sql, userId);
		query.addEntity(Resource.class);
		return query.list();
	}
}
