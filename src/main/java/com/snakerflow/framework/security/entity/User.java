package com.snakerflow.framework.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户实体类，继承抽象安全实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "SEC_USER")
public class User extends SecurityEntity
{
	private static final long serialVersionUID = 7446802057673100315L;
	//系统管理员账号类型
	public static final Integer TYPE_ADMIN = 0;
	//普通用户账号类型
	public static final Integer TYPE_GENERAL = 1;
	//登录用户名称
	private String username;
	//密码
	private String password;
	//明文密码
	private String plainPassword;
	//salt
	private String salt;
	//用户姓名
	private String fullname;
	//类型
	private Integer type;
	//电子邮箱
	private String email;
	//联系地址
	private String address;
	//年龄
	private Integer age;
	//性别
	private String sex;
	//是否可用
	private String enabled;
	//所属部门
	private Org org;
	//角色列表（多对多关联）
	private List<Role> roles = new ArrayList<Role>();
	//权限列表（多对多关联）
	private List<Authority> authorities = new ArrayList<Authority>();
	
	public User() {
		
	}

	@Column(name = "username", unique = true, nullable = false, length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "password", length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "fullname", length = 100)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "enabled")
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="SEC_ROLE_USER",joinColumns={ @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="SEC_USER_AUTHORITY",joinColumns={ @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	public List<Authority> getAuthorities() 
	{
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@ManyToOne
	@JoinColumn(name="org", nullable=true)
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Transient
	public String getPlainPassword() {
	    return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
	    this.plainPassword = plainPassword;
	}

	@Column(name = "salt")
	public String getSalt() {
	    return salt;
	}

	public void setSalt(String salt) {
	    this.salt = salt;
	}
}
