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
import java.util.ArrayList;
import java.util.List;

/**
 * 表实体
 * @author yuqs
 * @since 1.0
 */
@Entity
@Table(name = "DF_TABLE")
public class DbTable implements Serializable {
    private Long id;
    /**
     * 表名称
     */
    private String name;
    /**
     * 中文名称
     */
    private String displayName;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 是否选择，该字段不需要持久化，仅仅是方便页面控制选择状态
     */
    private Integer selected;
    /**
     * 是否为从表
     */
    private String subFlag;
    /**
     * 字段集合
     */
    private List<Field> fields = new ArrayList<Field>();
    /**
     * 表单集合
     */
    private List<Form> forms = new ArrayList<Form>();

    public DbTable() {}

    public DbTable(Long id) {
        this.id = id;
    }

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
    @Column(name = "creator", length = 50)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    @Column(name = "createTime", length = 50)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    @OneToMany(mappedBy = "dbTable",cascade = CascadeType.ALL)
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
    @JsonIgnore
    @ManyToMany(mappedBy="tables")
    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }
    @Transient
    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
    @Column(name = "subFlag", length = 10)
    public String getSubFlag() {
        return subFlag;
    }

    public void setSubFlag(String subFlag) {
        this.subFlag = subFlag;
    }

    @Transient
    public boolean isSub() {
        return subFlag != null && subFlag.equals("1") ? true : false;
    }
}
