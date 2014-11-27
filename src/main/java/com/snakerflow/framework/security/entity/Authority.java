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
 * 权限实体类，继承抽象安全实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "SEC_AUTHORITY")
public class Authority extends SecurityEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8349705525996917628L;
	//权限名称
	private String name;
	//权限描述
	private String description;
	//是否选择，该字段不需要持久化，仅仅是方便页面控制选择状态
	private Integer selected;
	//权限管辖的资源列表（多对多关联）
	private List<Resource> resources = new ArrayList<Resource>();
	//权限所属的角色列表（多对多关联）
	private List<Role> roles = new ArrayList<Role>();
    //权限包含的用户列表（多对多关联）这里表示：用户既可以指定角色，也可指定单独的权限
    private List<User> users = new ArrayList<User>();
    
    public Authority() {}
    
    /**
     * 构造函数，参数为主键ID
     * @param id
     */
    public Authority(Long id) {
    	this.id = id;
    }
	
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
	@JoinTable(name="SEC_AUTHORITY_RESOURCE",joinColumns={ @JoinColumn(name = "AUTHORITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID") })
	public List<Resource> getResources()
	{
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@ManyToMany(mappedBy="authorities")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

    @ManyToMany(mappedBy="authorities")
	public List<User> getUsers() {
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
