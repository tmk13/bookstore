package com.apress.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "author")
@NamedQueries({
		@NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a")
})
@AllArgsConstructor
public class Author implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6831374485860388164L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	@Getter
	private Long id;

	@Basic(optional = false)
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	@Setter
	@Getter
	private String firstName;

	@Basic(optional = false)
	@Column(name = "LAST_NAME", nullable = false, length = 20)
	@Setter
	@Getter
	private String lastName;

	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
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
		Author other = (Author) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
