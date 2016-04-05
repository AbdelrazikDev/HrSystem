package com.advansys.hr.persistence.dao.user.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Cloneable {

	
	protected BigDecimal id;

	@Id
	@Column(name = "id")
	@GeneratedValue
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
}
