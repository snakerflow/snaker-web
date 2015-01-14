package com.snakerflow.framework.config.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 表单实体类
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "DF_FORM")
public class Form implements Serializable {
    private static final long serialVersionUID = -1;
    private long id;
    private String name;
    private String displayName;
    private String type;
    private String creator;
    private String createTime;
    private String originalHtml;
    private String parseHtml;
    private int fieldNum = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOriginalHtml() {
        return originalHtml;
    }

    public void setOriginalHtml(String originalHtml) {
        this.originalHtml = originalHtml;
    }

    public String getParseHtml() {
        return parseHtml;
    }

    public void setParseHtml(String parseHtml) {
        this.parseHtml = parseHtml;
    }

    public int getFieldNum() {
        return fieldNum;
    }

    public void setFieldNum(int fieldNum) {
        this.fieldNum = fieldNum;
    }
}
