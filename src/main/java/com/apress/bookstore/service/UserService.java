package com.apress.bookstore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import com.apress.bookstore.dto.RegistrationUserFormDTO;
import com.apress.bookstore.entity.Role;
import com.apress.bookstore.repository.RoleRepository;
import com.apress.bookstore.repository.UserRepository;
import com.apress.bookstore.validator.RegisterUserValidator;
import com.apress.bookstore.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.apress.bookstore.dao.UserDAO;
import com.apress.bookstore.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
public class UserService {

	@Autowired
	private RegisterUserValidator registerUserValidator;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public void validateUser(RegistrationUserFormDTO registrationUserFormDTO, BindingResult result) {
		registerUserValidator.validate(registrationUserFormDTO, result);
	}

	@Transactional
	public User createUser(RegistrationUserFormDTO registrationUserFormDTO) {
		User user = new User();
		user.setUserName(registrationUserFormDTO.getName());
		user.setUserPassword(registrationUserFormDTO.getPassword());
		user.setEnabled(true);

		Role role = getUserRole();

		user.setRoles(new ArrayList<>(Arrays.asList(role)));

		return userRepository.save(user);
	}

	@Transactional(readOnly = true)
    private Role getUserRole() {
        return roleRepository.findRoleByRoleName("ROLE_USER");
    }

//	public boolean createUser(User user) {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		em.getTransaction().begin();
//		user.setId(null);
//		em.merge(user);
//		em.getTransaction().commit();
//		em.close();
//		return true;
//	}

	@Transactional(readOnly = true)
	public boolean isUserNameExist(RegistrationUserFormDTO registrationUserFormDTO) {
//		return userRepository.findByUserNameExists(registrationUserFormDTO.getName());
		return userRepository.existsByUserName(registrationUserFormDTO.getName());
	}

//	public boolean isUserNameExist(User user) {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		@SuppressWarnings("unchecked")
//		List<User> users = em.createNamedQuery("User.available").setParameter("userName", user.getUserName())
//				.getResultList();
//
//		if (users.isEmpty())
//			return true;
//		else
//			return false;
//
//		//return userDao.isUserNameExist(user);
//	}

	@Transactional(readOnly = true)
	public User checkLogin(RegistrationUserFormDTO registrationUserFormDTO) {
		return userRepository.findByUserNameAndUserPassword(registrationUserFormDTO.getName(), registrationUserFormDTO.getPassword());
	}

//	public User checkLogin(User user) {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		@SuppressWarnings("unchecked")
//		List<User> users = em.createNamedQuery("User.find").setParameter("userName", user.getUserName())
//				.setParameter("userPassword", user.getUserPassword())
//				.getResultList();
//
//		if (!users.isEmpty())
//			return users.get(0);
//		else
//			return null;
//		//User u = userDao.checkLogin(user).size() != 0 ? userDao.checkLogin(user).get(0) : null;
//		//return u;
//	}

}