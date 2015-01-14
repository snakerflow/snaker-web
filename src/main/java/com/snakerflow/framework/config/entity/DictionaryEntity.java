package com.snakerflow.framework.config.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *配置实体抽象类
 * @author yuqs
 * @since 0.1
 */
@MappedSuperclass
public abstract class DictionaryEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5288872906807628038L;
	//主键标识ID
	protected Long id;

	/**
	 * 配置实体的主键生成策略为序列，序列名称为CONF_SEQUENCE
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
