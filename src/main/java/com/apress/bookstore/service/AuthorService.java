package com.apress.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.bookstore.dao.BookDAO;
import com.apress.bookstore.entity.Author;
import com.apress.springmvc.configuration.DBManager;

@Service
public class AuthorService {

	@Autowired
	private BookDAO bookDao;

	public List<Author> getAuthorList() {
		EntityManager em = DBManager.getManager().createEntityManager();
		@SuppressWarnings("unchecked")
		List<Author> allAuthors = em.createNamedQuery("Author.findAll").getResultList();

		return allAuthors;
		//return bookDao.findAllAuthors();
	}

	public Author getAuthorById(long id) {
		List<Author> authorList = getAuthorList();
		for (Author author : authorList)
			if (author.getId().equals(id))
				return author;
		return null;
	}
}
