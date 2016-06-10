package com.apress.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
@Entity
@Table(name = "user")
@NamedQueries({
		@NamedQuery(name = "User.find", query = "SELECT u FROM User u WHERE u.userName = :userName AND u.userPassword = :userPassword"),
		@NamedQuery(name = "User.available", query = "SELECT u.id FROM User u WHERE u.userName = :userName"),
})
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5016574667394468414L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
	@Column(name = "USER_NAME", nullable = false, length = 60)
	private String userName;

	@Basic(optional = false)
	@Column(name = "USER_PASSWORD", nullable = false, length = 60)
	private String userPassword;

	public User() {
		id = -1;
		userName = "";
		userPassword = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
