package com.apress.bookstore.model;

import java.io.Serializable;

public interface Author extends Serializable {

	public Long getId();

	public void setId(Long id);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getFullName();

}