package com.apress.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.apress.bookstore.dao.BookDAO;
import com.apress.bookstore.entity.Category;
import com.apress.springmvc.configuration.DBManager;

public class CategoryService {

	@Autowired
	private BookDAO bookDao;

	public List<Category> getCategoryList() {
		EntityManager em = DBManager.getManager().createEntityManager();
		@SuppressWarnings("unchecked")
		List<Category> catList = em.createNamedQuery("Category.findAll").getResultList();

		return catList;
		//return bookDao.findAllCategories();
	}

	public Category getCategoryById(long id) {
		List<Category> categoryList = getCategoryList();
		for (Category category : categoryList)
			if (category.getId().equals(id))
				return category;
		return null;
	}
}
