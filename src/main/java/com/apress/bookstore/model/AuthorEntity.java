package com.apress.bookstore.model;

public class AuthorEntity implements Author, Comparable<AuthorEntity> {

	private Long id;
	private String firstName;
	private String lastName;

	public AuthorEntity() {
		id = -1l;
		firstName = "";
		lastName = "";
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
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return "Author — Id: " + id + ", Imię: " + firstName + ", Nazwisko: " + lastName;
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
		AuthorEntity other = (AuthorEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(AuthorEntity o) {
		int result = this.lastName.compareToIgnoreCase(o.lastName);
		return result != 0 ? result : this.firstName.compareToIgnoreCase(o.firstName);
	}

}