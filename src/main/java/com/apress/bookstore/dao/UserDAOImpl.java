package com.apress.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.apress.bookstore.model.User;
import com.apress.bookstore.model.UserEntity;

@Service
public class UserDAOImpl implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean insertUser(User user) {
		String sql = "INSERT INTO user (USER_NAME, USER_PASSWORD) VALUES(?, ?)";

		int rows = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, user.getUserName().trim());
				preparedStatement.setString(2, user.getUserPassword().trim());
			}
		});

		return rows == 1 ? true : false;
	}

	@Override
	public boolean isAvailableUser(User user) {

		String sql = "SELECT user.ID FROM user WHERE user.USER_NAME = ?";

		List<Long> list = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, user.getUserName().trim());
			}
		}, new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong("ID");
			}
		});

		return list.size() == 0 ? true : false;
	}

	@Override
	public List<User> checkLogin(User user) {

		String sql = "SELECT * FROM user WHERE user.USER_NAME = ? AND user.USER_PASSWORD = ?";

		List<User> list = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, user.getUserName().trim());
				preparedStatement.setString(2, user.getUserPassword().trim());
			}
		}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new UserEntity();
				user.setId(rs.getLong("ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserPassword(rs.getString("USER_PASSWORD"));
				return user;
			}
		});

		return list;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

}
