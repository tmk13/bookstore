package com.apress.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.apress.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.apress.bookstore.dao.BookDAO;
import com.apress.bookstore.entity.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Transactional(readOnly = true)
	public List<Author> getAuthorList() {
		return (List<Author>)authorRepository.findAll();
	}

//	public List<Author> getAuthorList() {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		@SuppressWarnings("unchecked")
//		List<Author> allAuthors = em.createNamedQuery("Author.findAll").getResultList();
//
//		return allAuthors;
//		//return bookDao.findAllAuthors();
//	}

	@Transactional(readOnly = true)
	public Author getAuthorById(long id) {
		return authorRepository.findOne(id);
	}

//	public Author getAuthorById(long id) {
//		List<Author> authorList = getAuthorList();
//		for (Author author : authorList)
//			if (author.getId().equals(id))
//				return author;
//		return null;
//	}
}
