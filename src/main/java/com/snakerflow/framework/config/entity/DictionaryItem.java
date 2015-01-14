package com.snakerflow.framework.config.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 配置字典选项实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "CONF_DICTITEM")
public class DictionaryItem extends DictionaryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1382491728106297904L;
	//字典选项名称
	private String name;
	//编码（手动设置）
	private String code;
	//描述
	private String description;
	//字段选项排序字段
	private Integer orderby;
	//配置字典实体（关联）
	private Dictionary dictionary;
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
	@Column(name = "orderby")
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	@ManyToOne
	@JoinColumn(name="dictionary")
    @JsonIgnore
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	@Column(name = "code", length = 50)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
