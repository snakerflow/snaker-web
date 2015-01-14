package com.snakerflow.framework.config.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 配置字典实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "CONF_DICTIONARY")
public class Dictionary extends DictionaryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7610108657592274423L;
	//名称
	private String name;
	//中文名称
	private String cnName;
	//描述
	private String description;
	//字典选项
	private List<DictionaryItem> dictionaryItems = new ArrayList<DictionaryItem>();
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
	@OneToMany(mappedBy = "dictionary",cascade = CascadeType.ALL)
	public List<DictionaryItem> getDictionaryItems() {
		return dictionaryItems;
	}
	public void setDictionaryItems(List<DictionaryItem> dictionaryItems) {
		this.dictionaryItems = dictionaryItems;
	}
	@Column(name = "cn_name", nullable = false, length = 200)
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
}
