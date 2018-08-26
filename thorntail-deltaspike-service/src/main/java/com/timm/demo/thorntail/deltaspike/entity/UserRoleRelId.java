
package com.timm.demo.thorntail.deltaspike.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class UserRoleRelId implements Serializable {

	private static final long serialVersionUID = -2418598737667600917L;

	@Column(name = "user_id", nullable = false, updatable = false)
	private Long userId;

	@Column(name = "role_id", nullable = false, updatable = false)
	private Long roleId;

	/**
	 *
	 */
	public UserRoleRelId() {
	}

	/**
	 * @param userId
	 * @param roleId
	 */
	public UserRoleRelId(Long userId, Long roleId) {
		setUserId(userId);
		setRoleId(roleId);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserRoleRelId)) {
			return false;
		}
		UserRoleRelId other = (UserRoleRelId) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getUserId(), other.getUserId()).append(getRoleId(), other.getRoleId());
		return builder.isEquals();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getUserId()).append(getRoleId());
		return builder.hashCode();
	}
}
