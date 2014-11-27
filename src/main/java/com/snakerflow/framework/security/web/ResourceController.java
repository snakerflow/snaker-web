package com.snakerflow.framework.security.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.snakerflow.framework.orm.Page;
import com.snakerflow.framework.orm.PropertyFilter;
import com.snakerflow.framework.security.entity.Menu;
import com.snakerflow.framework.security.entity.Resource;
import com.snakerflow.framework.security.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 资源管理Controller
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/security/resource")
public class ResourceController {
	//注入资源管理对象
	@Autowired
	private ResourceManager resourceManager;
	
	/**
	 * 分页查询资源，返回资源列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<Resource> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = resourceManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "security/resourceList";
	}
	
	/**
	 * 新建资源时，返回资源编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("resource", new Resource(null));
		return "security/resourceEdit";
	}

	/**
	 * 编辑资源时，返回资源编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("resource", resourceManager.get(id));
		return "security/resourceEdit";
	}
	
	/**
	 * 编辑资源时，返回资源查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("resource", resourceManager.get(id));
		return "security/resourceView";
	}
	
	/**
	 * 新增、编辑资源页面的提交处理。保存资源实体，并返回资源列表视图
	 * @param resource
     * @param parentMenuId
     * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Resource resource, Long parentMenuId) {
		if(parentMenuId != null && parentMenuId.longValue() > 0) {
			Menu menu = new Menu(parentMenuId);
			resource.setMenu(menu);
		}
		resourceManager.save(resource);
		return "redirect:/security/resource";
	}
	
	/**
	 * 根据主键ID删除资源实体，并返回资源列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		resourceManager.delete(id);
		return "redirect:/security/resource";
	}
}
