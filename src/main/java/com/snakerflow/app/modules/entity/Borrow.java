package com.snakerflow.app.modules.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.snakerflow.framework.flow.entity.FlowEntity;

@Entity
@Table(name = "FLOW_BORROW")
public class Borrow extends FlowEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2523484310463519530L;
	private String operator;
	private String description;
	private Double amount;
	private String operateTime;
	private String repaymentDate;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
}
