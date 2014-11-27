package com.snakerflow.framework.security.service;

import java.util.List;

import com.snakerflow.framework.orm.Page;
import com.snakerflow.framework.orm.PropertyFilter;
import com.snakerflow.framework.security.dao.RoleDao;
import com.snakerflow.framework.security.entity.Role;
import com.snakerflow.framework.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 角色管理类
 * @author yuqs
 * @since 0.1
 */
@Component
public class RoleManager {
	//注入角色持久化对象
	@Autowired
	private RoleDao roleDao;
	//注入用户管理对象
	@Autowired
	private UserManager userManager;
	
	/**
	 * 保存、更新角色实体
	 * @param entity
	 */
	public void save(Role entity) {
		roleDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的角色
	 * @param id
	 */
	public void delete(Long id) {
		List<User> users = userManager.getAll();
		for(User user : users) {
			for(Role role : user.getRoles()) {
				if(role.getId().longValue() == id.longValue()) {
					user.getRoles().remove(role);
					userManager.save(user);
					break;
				}
			}
		}
		roleDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取角色实体
	 * @param id
	 * @return
	 */
	public Role get(Long id) {
		return roleDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询角色列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Role> findPage(final Page<Role> page, final List<PropertyFilter> filters) {
		return roleDao.findPage(page, filters);
	}
	
	/**
	 * 获取所有角色记录
	 * @return
	 */
	public List<Role> getAll() {
		return roleDao.getAll();
	}
}
