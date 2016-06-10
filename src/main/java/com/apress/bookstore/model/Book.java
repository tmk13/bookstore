package com.apress.bookstore.model;

import java.io.Serializable;
import java.util.List;

public interface Book extends Serializable {

	public Long getId();

	public void setId(Long id);

	public String getBookTitle();

	public void setBookTitle(String bookTitle);

	public List<Author> getAuthors();

	public void setAuthors(List<Author> authors);

	public List<Category> getCategories();

	public void setCategories(List<Category> categories);

	public String getPublisherName();

	public void setPublisherName(String publisherName);

	public float getPrice();

	public void setPrice(float price);

}