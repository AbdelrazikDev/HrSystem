package com.advansys.hr.persistence.dao.user.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RequestStatus {

	@Column(name = "STATUS")
	private String value;

	public static final RequestStatus PENDING = new RequestStatus("PENDING");

	public static final RequestStatus IN_PROGRESS = new RequestStatus(
			"IN_PROGRESS");

	public static final RequestStatus WAITING_APPROVAL = new RequestStatus(
			"WAITING_APPROVAL");

	public static final RequestStatus REJECTED = new RequestStatus("REJECTED");

	public static final RequestStatus APPROVED = new RequestStatus("APPROVED");

	public static final RequestStatus COMPLETE = new RequestStatus("COMPLETE");

	protected RequestStatus() {
		super();
	}

	protected RequestStatus(String value) {
		this.value = value;
	}

}
