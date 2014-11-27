package com.snakerflow.framework.flow.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yuqs
 */
@Entity
@Table(name = "FLOW_APPROVAL")
public class Approval extends FlowEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1280430261161731105L;
	private String operator;
    private String operateTime;
    private String result;
    private String content;
    private String taskName;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
