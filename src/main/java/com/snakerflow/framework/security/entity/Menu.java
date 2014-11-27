package com.snakerflow.framework.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 菜单实体类，继承抽象安全实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "SEC_MENU")
public class Menu extends SecurityEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3488405380107404492L;
	//菜单资源的根菜单标识为0
	public static final Long ROOT_MENU = 0L;
	//菜单名称
	private String name;
	//菜单描述
	private String description;
	//排序字段
	private Integer orderby;
	//上级菜单
	private Menu parentMenu;
	//子菜单列表（多对多关联）
	private List<Menu> subMenus = new ArrayList<Menu>();
	
	public Menu() {}
	/**
	 * 构造函数，参数为主键ID
	 * @param id
	 */
	public Menu(Long id) {
		this.id = id;
	}
	
	/**
	 * 构造函数，辅助hql查询
	 * @param id
	 * @param name
	 * @param description
	 */
	public Menu(Long id, String name, String description, Integer orderby) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.orderby = orderby;
	}
	
	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "description", length = 500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne
	@JoinColumn(name="parentMenu")
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="parentMenu")
	public List<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}
	@Column(name = "orderby")
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
}
