package com.apress.bookstore.model;

import java.io.Serializable;

public interface Category extends Serializable {

	public Long getId();

	public void setId(Long id);

	public String getCategoryDescription();

	public void setCategoryDescription(String categoryDescription);

}