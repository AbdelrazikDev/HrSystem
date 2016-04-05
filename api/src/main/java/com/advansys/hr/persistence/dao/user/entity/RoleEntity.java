package com.advansys.hr.persistence.dao.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE_TABLE")
public class RoleEntity extends BaseEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
