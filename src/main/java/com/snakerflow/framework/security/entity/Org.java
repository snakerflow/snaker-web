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
 * 部门实体类，继承抽象安全实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "SEC_ORG")
public class Org extends SecurityEntity
{
	private static final long serialVersionUID = 7297765946510001885L;
	//根部门ID号默认为0
	public static final Long ROOT_ORG_ID = 0l;
	//上级部门
    private Org parentOrg;
    //部门名称
    private String name;
    //是否激活状态
    private String active;
    //部门全路径
    private String fullname;
    //部门描述
    private String description;
    //部门类型（扩展使用）
    private String type;
    //部门管辖的所有用户列表（一对多关联）
    private List<User> users = new ArrayList<User>();
    //部门管辖的所有下级部门列表（一对多关联）
    private List<Org> orgs = new ArrayList<Org>();
    
    public Org() {}
    
    /**
     * 构造函数，参数为主键ID
     * @param id
     */
    public Org(Long id) {
    	this.id = id;
    }

    @Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "active")
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "fullname", length = 200)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type", length = 200)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "org",cascade = CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "parentOrg",cascade = CascadeType.ALL)
	public List<Org> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}

	@ManyToOne
	@JoinColumn(name="parentOrg", nullable=true)
	public Org getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Org parentOrg) {
		this.parentOrg = parentOrg;
	}
}
