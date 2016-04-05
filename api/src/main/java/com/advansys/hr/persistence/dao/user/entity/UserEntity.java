package com.advansys.hr.persistence.dao.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USER_TABLE")
public class UserEntity extends BaseEntity {

	private String email;
	private String firstName;
	private String lastName;
	private String passWord;

	private String Position;

	private RoleEntity role;
	private DepartmentEntity department;

	private UserEntity manager;

	public UserEntity() {
		this(null, null,null, null);
	}

	public UserEntity(String email, String firstName, String lastName,String passWord) {

		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passWord = passWord;
	}

	@NotNull
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotNull
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "position")
	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public UserEntity getManager() {
		return manager;
	}

	public void setManager(UserEntity manager) {
		this.manager = manager;
	}

	// === Cloneable implementation

	@SuppressWarnings("unchecked")
	@Override
	public UserEntity clone() {
		return new UserEntity(email, firstName, lastName,passWord);
	}

	// === Overrides

	@Override
	public String toString() {
		return "UserEntity{" + "email='" + email + '\'' + ", firstName='"
				+ firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
	}

	public String getPassword() {
		return passWord;
	}

	public void setPassword(String passWord) {
		this.passWord = passWord;
	}
}
