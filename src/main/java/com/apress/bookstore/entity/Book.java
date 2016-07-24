package com.apress.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NamedQueries({
		@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
		@NamedQuery(name = "Book.findByCategory", query = "SELECT DISTINCT b FROM Book b"
				+ " JOIN b.categories cats"
				+ " WHERE cats.categoryDescription = :category"),
		@NamedQuery(name = "Book.findByKeyWord", query = "SELECT DISTINCT b FROM Book b"
				+ " JOIN b.authors auths"
				+ " WHERE b.bookTitle LIKE :keyWord"
				+ " OR auths.firstName LIKE :keyWord"
				+ " OR auths.lastName LIKE :keyWord")
})
@NoArgsConstructor
public class Book implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 2229456471204000862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	@Getter
	private Long id;

	@Basic(optional = false)
	@Column(name = "BOOK_TITLE", nullable = false, length = 60)
	@Setter
	@Getter
	private String bookTitle;

	@Basic(optional = false)
	@Column(name = "PUBLISHER", nullable = false, length = 60)
	@Setter
	@Getter
	private String publisherName;

	@Basic(optional = false)
	@Column(name = "PRICE", nullable = false, precision = 6, scale = 2)
	@Setter
	@Getter
	private Float price;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@OrderBy(value = "lastName")
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
	@Setter
	@Getter
	private Set<Author> authors;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@OrderBy(value = "categoryDescription")
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"))
	@Setter
	@Getter
	private Set<Category> categories;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "IMAGE", columnDefinition = "mediumblob")
	@Setter
	@Getter
	private String image;

	public Book(String bookTitle, String publisherName, Float price, Set<Author> authors, Set<Category> categories, String image) {
		this.bookTitle = bookTitle;
		this.publisherName = publisherName;
		this.price = price;
		this.authors = authors;
		this.categories = categories;
		this.image = image;
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
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
