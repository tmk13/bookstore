package com.apress.bookstore.model;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public interface User extends Serializable {

	public long getId();

	public void setId(long id);

	public String getUserName();

	public void setUserName(String userName);

	public String getUserPassword();

	public void setUserPassword(String userPassword);

}
