package com.snakerflow.framework.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 角色实体类，继承抽象安全实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "SEC_ROLE")
public class Role extends SecurityEntity
{
	private static final long serialVersionUID = 2041148498753846675L;
	//角色名称
    private String name;
    //角色描述
    private String description;
    //是否选择，该字段不需要持久化，仅仅是方便页面控制选择状态
    private Integer selected;
    //角色拥有的权限列表（多对多关联）
    private List<Authority> authorities = new ArrayList<Authority>();
    //角色所包含的用户列表（多对多关联）
    private List<User> users = new ArrayList<User>();

    @Column(name = "name", unique = true, nullable = false, length = 200)
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="SEC_ROLE_AUTHORITY",joinColumns={ @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	public List<Authority> getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

    @ManyToMany(mappedBy="roles")
	public List<User> getUsers() 
	{
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Transient
	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}
}
