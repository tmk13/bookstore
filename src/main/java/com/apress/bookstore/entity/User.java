package com.apress.bookstore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

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
	@Column(name = "ID", nullable = false)
	private Long id;

	@Basic(optional = false)
	@Column(name = "USER_NAME", nullable = false, length = 60)
	private String userName;

	@Basic(optional = false)
	@Column(name = "USER_PASSWORD", nullable = false, length = 60)
	private String userPassword;

	@Basic(optional = false)
	@Column(name = "ENABLED", nullable = false)
	private Boolean enabled;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
							inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	private List<Role> roles;

	public User() {
		id = -1l;
		userName = "";
		userPassword = "";
		enabled = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
