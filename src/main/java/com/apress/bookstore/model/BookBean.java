package com.apress.bookstore.model;

import javax.persistence.EntityManager;

import com.apress.bookstore.entity.Book;


public class BookBean {

	private Book book = new Book(null, null, null, null, null, null);

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public String add() {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		em.getTransaction().begin();
//		book.setId(null);
//		em.persist(book);
//		em.getTransaction().commit();
//		em.close();
//		this.book = new Book();
		return null;
	}
}