package com.apress.bookstore.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UserEntity implements User {

	private long id;
	private String userName;
	private String userPassword;

	public UserEntity() {
		id = -1l;
		userName = "";
		userPassword = "";
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getUserPassword() {
		return userPassword;
	}

	@Override
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
