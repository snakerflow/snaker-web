package com.snakerflow.framework.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 资源实体类，继承抽象安全实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "SEC_RESOURCE")
public class Resource extends SecurityEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5503383469393219319L;
	//资源名称
	private String name;
	//资源值（此处主要作为url资源，及链接路径）
	private String source;
	//是否选择，该字段不需要持久化，仅仅是方便页面控制选择状态
	private Integer selected;
	//资源所属权限列表（多对多关联）
	private List<Authority> authorities = new ArrayList<Authority>();
	//资源所属菜单
	private Menu menu;
	
	public Resource() {}
	
	/**
	 * 构造函数，参数为主键ID
	 * @param id
	 */
	public Resource(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="menu", nullable=true)
    public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "source", length = 200)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "name", unique = true, nullable = false, length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @ManyToMany(mappedBy="resources", fetch = FetchType.EAGER)
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Transient
	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}
}
