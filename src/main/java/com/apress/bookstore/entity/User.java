package com.apress.bookstore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
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
	@Getter
	private Long id;

	@Basic(optional = false)
	@Column(name = "USER_NAME", nullable = false, length = 60)
	@Setter
	@Getter
	private String userName;

	@Basic(optional = false)
	@Column(name = "USER_PASSWORD", nullable = false, length = 60)
	@Setter
	@Getter
	private String userPassword;

	@Basic(optional = false)
	@Column(name = "ENABLED", nullable = false)
	@Setter
	@Getter
	private Boolean enabled;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
							inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	@Setter
	@Getter
	private List<Role> roles;

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
