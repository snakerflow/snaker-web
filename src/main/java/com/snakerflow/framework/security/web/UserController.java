package com.snakerflow.framework.security.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.snakerflow.framework.orm.Page;
import com.snakerflow.framework.orm.PropertyFilter;
import com.snakerflow.framework.security.entity.Org;
import com.snakerflow.framework.security.entity.Role;
import com.snakerflow.framework.security.entity.User;
import com.snakerflow.framework.security.service.RoleManager;
import com.snakerflow.framework.security.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户管理Controller
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/security/user")
public class UserController {
	//注入用户管理对象
	@Autowired
	private UserManager userManager;
	//注入角色管理对象
	@Autowired
	private RoleManager roleManager;
	
	/**
	 * 分页查询用户，返回用户列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<User> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = userManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "security/userList";
	}
	
	/**
	 * 新建用户时，返回用户编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleManager.getAll());
		return "security/userEdit";
	}

	/**
	 * 编辑用户时，返回用户编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		User entity = userManager.get(id);
		List<Role> roles = roleManager.getAll();
		List<Role> roless = entity.getRoles();
		for(Role role : roles) {
			for(Role selRole : roless) {
				if(role.getId().longValue() == selRole.getId().longValue())
				{
					role.setSelected(1);
				}
				if(role.getSelected() == null)
				{
					role.setSelected(0);
				}
			}
		}
		model.addAttribute("user", userManager.get(id));
		model.addAttribute("roles", roles);
		return "security/userEdit";
	}
	
	/**
	 * 编辑用户时，返回用户查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userManager.get(id));
		return "security/userView";
	}
	
	/**
	 * 新增、编辑用户页面的提交处理。保存用户实体，并返回用户列表视图
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(User user, Long[] orderIndexs, Long parentOrgId) {
		if(orderIndexs != null) {
			for(Long order : orderIndexs) {
				Role role = new Role();
				role.setId(order);
				user.getRoles().add(role);
			}
		}
		if(parentOrgId != null && parentOrgId.longValue() > 0) {
			Org parent = new Org(parentOrgId);
			user.setOrg(parent);
		}
		userManager.save(user);
		return "redirect:/security/user";
	}
	
	/**
	 * 根据主键ID删除用户实体，并返回用户列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userManager.delete(id);
		return "redirect:/security/user";
	}
}
