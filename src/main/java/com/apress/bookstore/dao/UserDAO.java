package com.apress.bookstore.dao;

import java.util.List;

import com.apress.bookstore.model.User;

public interface UserDAO {

	public boolean insertUser(User user);

	public boolean isAvailableUser(User user);

	public List<User> checkLogin(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

}