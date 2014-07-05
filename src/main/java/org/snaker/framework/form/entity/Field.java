/*
 *  Copyright 2013-2014 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.snaker.framework.form.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 字段实体
 * @author yuqs
 * @since 1.0
 */
@Entity
@Table(name = "DF_FIELD")
public class Field implements Serializable {
    private Long id;
    /**
     * 所属表对象
     */
    private DbTable dbTable;
    /**
     * 字段名称
     */
    private String name;
    /**
     * 中文名称
     */
    private String displayName;
    /**
     * 字段类型
     */
    private String type;
    /**
     * 是否必填
     */
    private String required;
    /**
     * 长度
     */
    private Integer dataLength;
    /**
     * 位数
     */
    private Integer dataDigit;
    /**
     * 数据格式
     */
    private String dataFormat;
    /**
     * 默认值
     */
    private String defaultValue;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "name", unique = true, nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "displayName", length = 200)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    @Column(name = "type", length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "required", length = 10)
    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
    @Column(name = "dataLength")
    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }
    @Column(name = "dataDigit")
    public Integer getDataDigit() {
        return dataDigit;
    }

    public void setDataDigit(Integer dataDigit) {
        this.dataDigit = dataDigit;
    }
    @Column(name = "dataFormat", length = 50)
    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }
    @Column(name = "defaultValue", length = 200)
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    @ManyToOne
    @JoinColumn(name="dbTable")
    @JsonIgnore
    public DbTable getDbTable() {
        return dbTable;
    }

    public void setDbTable(DbTable dbTable) {
        this.dbTable = dbTable;
    }
}
