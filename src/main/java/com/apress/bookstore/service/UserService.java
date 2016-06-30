package com.apress.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.bookstore.dao.UserDAO;
import com.apress.bookstore.entity.User;
import com.apress.springmvc.configuration.DBManager;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;

	public boolean createUser(User user) {
		EntityManager em = DBManager.getManager().createEntityManager();
		em.getTransaction().begin();
		user.setId(null);
		em.merge(user);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public boolean isAvailableUser(User user) {
		EntityManager em = DBManager.getManager().createEntityManager();
		@SuppressWarnings("unchecked")
		List<User> users = em.createNamedQuery("User.available").setParameter("userName", user.getUserName())
				.getResultList();

		if (users.isEmpty())
			return true;
		else
			return false;

		//return userDao.isAvailableUser(user);
	}

	public User checkLogin(User user) {
		EntityManager em = DBManager.getManager().createEntityManager();
		@SuppressWarnings("unchecked")
		List<User> users = em.createNamedQuery("User.find").setParameter("userName", user.getUserName())
				.setParameter("userPassword", user.getUserPassword())
				.getResultList();

		if (!users.isEmpty())
			return users.get(0);
		else
			return null;
		//User u = userDao.checkLogin(user).size() != 0 ? userDao.checkLogin(user).get(0) : null;
		//return u;
	}

}