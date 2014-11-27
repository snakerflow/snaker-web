package com.snakerflow.app.web.taglibs.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import com.snakerflow.framework.security.entity.Menu;
import com.snakerflow.framework.security.service.MenuManager;
import com.snakerflow.framework.security.shiro.ShiroUtils;
import com.snakerflow.framework.web.TagDTO;
import com.snakerflow.framework.web.TagBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 自定义菜单标签处理类。 根据当前认证实体获取允许访问的所有菜单，并输出特定导航菜单的html
 * @author yuqs
 * @since 0.1
 */
@Component
public class MenuTagBuilder implements TagBuilder {
	// Spring的上下文
	private WebApplicationContext springContext;
	// Servlet的上下文
	private ServletContext servletContext = null;

	@Override
	public String build(TagDTO dto) {
		this.servletContext = dto.getServletContext();
		this.springContext = dto.getSpringContext();
		StringBuffer buffer = new StringBuffer();
		// 获取所有可允许访问的菜单列表
		List<Menu> menus = getAllowedAccessMenu();
		// 循环迭代菜单列表，构成ID、List结构的Map
		Map<Long, List<Menu>> menuMaps = buildMenuTreeMap(menus);
		// 根据Map构造符合左栏菜单显示的html
		buildMenuTreeFolder(buffer, menuMaps, Menu.ROOT_MENU);
		return buffer.toString();
	}

	/**
	 * 循环迭代菜单列表，构成ID、List结构的Map
	 * 
	 * @param menus
	 * @return
	 */
	private Map<Long, List<Menu>> buildMenuTreeMap(List<Menu> menus) {
		Map<Long, List<Menu>> menuMap = new TreeMap<Long, List<Menu>>();
		for (Menu menu : menus) {
			/**
			 * 判断是否有上一级菜单，如果有，则添加到上一级菜单的Map中去 如果没有上一级菜单，把该菜单作为根节点
			 */
			Long parentMenuId = menu.getParentMenu() == null ? Menu.ROOT_MENU
					: menu.getParentMenu().getId();
			if (!menuMap.containsKey(parentMenuId)) {
				List<Menu> subMenus = new ArrayList<Menu>();
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			} else {
				List<Menu> subMenus = menuMap.get(parentMenuId);
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			}
		}
		return menuMap;
	}

	/**
	 * 获取当前登录账号所有允许访问的菜单列表
	 * 
	 * @return
	 */
	private List<Menu> getAllowedAccessMenu() {
		MenuManager menuManager = springContext.getBean(MenuManager.class);
		return menuManager.getAllowedAccessMenu(ShiroUtils.getUserId());
	}

	/**
	 * 构建菜单目录
	 * 
	 * @param buffer
	 *            html信息
	 * @param menuMap
	 * @param menuId
	 */
	private void buildMenuTreeFolder(StringBuffer buffer,
			Map<Long, List<Menu>> menuMap, Long menuId) {
		List<Menu> treeFolders = menuMap.get(menuId);
		if (treeFolders == null) {
			return;
		}
		for (Menu menu : treeFolders) {
			List<Menu> treeNodes = menuMap.get(menu.getId());
			if((treeNodes == null || treeNodes.isEmpty()) && StringUtils.isEmpty(menu.getDescription())) {
				continue;
			}
			buffer.append("<dl>");
			buffer.append("<dt id='sidebar_goods_manage'><i class='pngFix'></i>");
			buffer.append(menu.getName());
			buffer.append("</dt>");
			buffer.append("<dd>");
			buffer.append("<ul>");
			/**
			 * 有子菜单时，将子菜单添加到当前节点上
			 */
			buildMenuTreeNode(buffer, treeNodes);
			buffer.append("</ul>");
			buffer.append("</dd>");
			buffer.append("</dl>");
		}
	}

	/**
	 * 循环子菜单资源，并构造tree型html语句
	 * 
	 * @param buffer
	 * @param treeNodes
	 */
	private void buildMenuTreeNode(StringBuffer buffer, List<Menu> treeNodes) {
		if (treeNodes == null) {
			return;
		}
		for (Menu menu : treeNodes) {
			buffer.append("<li>");
			buffer.append("<a href='");
			buffer.append(servletContext.getContextPath());
			buffer.append(menu.getDescription());
			buffer.append("' target='mainFrame' ");
			buffer.append(">");
			buffer.append(menu.getName());
			buffer.append("</a>");
			buffer.append("</li>");
		}
	}
}
