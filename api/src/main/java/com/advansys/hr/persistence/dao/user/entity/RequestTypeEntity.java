package com.advansys.hr.persistence.dao.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "REQUEST_TYPE_TABLE")
public class RequestTypeEntity extends BaseEntity {

	private String name;

	private String Form;

	private boolean approvalNeeded;

	private UserEntity approvedBy;

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Column(length = 3000)
	public String getForm() {
		return Form;
	}

	public void setForm(String form) {
		Form = form;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public UserEntity getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(UserEntity approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Column(columnDefinition = "BIT DEFAULT 0")
	public boolean isApprovalNeeded() {
		return approvalNeeded;
	}

	public void setApprovalNeeded(boolean approvalNeeded) {
		this.approvalNeeded = approvalNeeded;
	}

}
