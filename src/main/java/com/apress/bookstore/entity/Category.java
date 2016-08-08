package com.apress.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "category")
@NamedQueries({
		@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
})
@AllArgsConstructor
public class Category implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3552463141049182692L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	@Getter
	private Long id;

	@Basic(optional = false)
	@Column(name = "DESCRIPTION_CATEGORY", nullable = false, length = 60)
	@Setter
	@Getter
	private String categoryDescription;

	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	@JsonIgnore
	@Getter
	@Setter
	private Set<Book> books;

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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
