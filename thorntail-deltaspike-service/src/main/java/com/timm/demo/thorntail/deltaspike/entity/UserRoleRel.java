
package com.timm.demo.thorntail.deltaspike.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_role_rel", schema = "sample")
public class UserRoleRel implements Serializable {

	private static final long serialVersionUID = 7426837537324291431L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, updatable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false, updatable = false)) })
	private UserRoleRelId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	private Role role;

	/**
	 *
	 */
	public UserRoleRel() {
	}

	/**
	 * @param user
	 * @param role
	 */
	public UserRoleRel(User user, Role role) {
		setId(new UserRoleRelId(user.getId(), role.getId()));
		setUser(user);
		setRole(role);
	}

	public UserRoleRelId getId() {
		return id;
	}

	public void setId(UserRoleRelId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User/Role relation [id=" + id.toString() + "]";
	}
}
