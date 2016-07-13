package com.apress.bookstore.service;

import com.apress.bookstore.entity.Category;
import com.apress.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<Category> getCategoryList() {
		return (List<Category>)categoryRepository.findAll();
	}

//	public List<Category> getCategoryList() {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		@SuppressWarnings("unchecked")
//		List<Category> catList = em.createNamedQuery("Category.findAll").getResultList();
//
//		return catList;
//		//return bookDao.findAllCategories();
//	}

	@Transactional(readOnly = true)
	public Category getCategoryById(long id) {
		return categoryRepository.findOne(id);
	}

//	public Category getCategoryById(long id) {
//		List<Category> categoryList = getCategoryList();
//		for (Category category : categoryList)
//			if (category.getId().equals(id))
//				return category;
//		return null;
//	}
}
