package com.snakerflow.framework.security.service;

import java.util.List;

import com.snakerflow.framework.orm.Page;
import com.snakerflow.framework.orm.PropertyFilter;
import com.snakerflow.framework.security.dao.OrgDao;
import com.snakerflow.framework.security.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 部门管理类
 * @author yuqs
 * @since 0.1
 */
@Component
public class OrgManager {
	//注入部门持久化对象
	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 保存部门实体
	 * @param entity
	 */
	public void save(Org entity) {
		orgDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的部门
	 * @param id
	 */
	public void delete(Long id) {
		orgDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取部门实体
	 * @param id
	 * @return
	 */
	public Org get(Long id) {
		return orgDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询部门列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Org> findPage(final Page<Org> page, final List<PropertyFilter> filters) {
		return orgDao.findPage(page, filters);
	}
	
	/**
	 * 获取所有部门记录
	 * @return
	 */
	public List<Org> getAll() {
		return orgDao.getAll();
	}
	
	/**
	 * 根据parentId获取所有下级部门
	 * @param parentId
	 * @return
	 */
	public List<Org> getByParent(Long parentId) {
		if(parentId == null || parentId == Org.ROOT_ORG_ID) {
			return getAll();
		}
		return orgDao.find("from Org org where org.parentOrg=?", new Org(parentId));
	}
}
