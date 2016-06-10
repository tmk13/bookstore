package com.apress.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class BookEntity implements Book, Comparable<BookEntity> {

	private Long id;
	private String bookTitle;
	private String publisherName;
	private List<Author> authors;
	private List<Category> categories;
	private float price;

	public BookEntity() {
		id = -1l;
		bookTitle = "";
		publisherName = "";
		authors = new ArrayList<>();
		categories = new ArrayList<>();
		price = 0;
	}

	@Override
	public Long getId() {
		return id;
}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getBookTitle() {
		return bookTitle;
	}

	@Override
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Override
	public List<Author> getAuthors() {
		return authors;
	}

	@Override
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String getPublisherName() {
		return publisherName;
	}

	@Override
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	@Override
	public float getPrice() {
		return price;
	}

	@Override
	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Książka — Identyfikator: " + id + ", Tytuł książki: " + bookTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(BookEntity o) {
		return this.bookTitle.compareToIgnoreCase(o.bookTitle);
	}
}