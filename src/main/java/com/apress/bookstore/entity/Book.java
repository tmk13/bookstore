package com.apress.bookstore.entity;

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
public class Book implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 2229456471204000862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Basic(optional = false)
	@Column(name = "BOOK_TITLE", nullable = false, length = 60)
	private String bookTitle;

	@Basic(optional = false)
	@Column(name = "PUBLISHER", nullable = false, length = 60)
	private String publisherName;

	@Basic(optional = false)
	@Column(name = "PRICE", nullable = false, precision = 6, scale = 2)
	private Float price;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@OrderBy(value = "lastName")
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
	private Set<Author> authors;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@OrderBy(value = "categoryDescription")
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"))
	private Set<Category> categories;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "IMAGE", columnDefinition = "mediumblob")
	private String image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
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
