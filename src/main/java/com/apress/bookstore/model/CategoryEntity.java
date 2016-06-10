package com.apress.bookstore.model;

public class CategoryEntity implements Category, Comparable<CategoryEntity> {
	private Long id;
	private String categoryDescription;

	public CategoryEntity() {
		id = -1l;
		categoryDescription = "";
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
	public String getCategoryDescription() {
		return categoryDescription;
	}

	@Override
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Override
	public String toString() {
		return "Kategoria — Identyfikator: " + id + ", Opis kategorii: " + categoryDescription;
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
		CategoryEntity other = (CategoryEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(CategoryEntity o) {
		return this.categoryDescription.compareToIgnoreCase(o.categoryDescription);
	}

}