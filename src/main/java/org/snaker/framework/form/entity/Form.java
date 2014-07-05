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

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表单实体类
 * @author yuqs
 * @since 1.0
 */
@Entity
@Table(name = "DF_FORM")
public class Form implements Serializable {
    private Long id;
    /**
     * 表单名称
     */
    private String name;
    /**
     * 中文名称
     */
    private String displayName;
    /**
     * 表单类型
     */
    private String type;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 表单内容
     */
    private String html;
    /**
     * 依赖的表
     */
    private List<DbTable> tables = new ArrayList<DbTable>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false, length = 200)
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
    @Column(name = "type", length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name=" html", columnDefinition="CLOB", nullable=true)
    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="DF_FORM_TABLE",joinColumns={ @JoinColumn(name = "FORM_ID") }, inverseJoinColumns = { @JoinColumn(name = "TABLE_ID") })
    public List<DbTable> getTables() {
        return tables;
    }

    public void setTables(List<DbTable> tables) {
        this.tables = tables;
    }
}
