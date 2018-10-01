
package com.timm.demo.thorntail.deltaspike.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "sample")
@SequenceGenerator(name = "user_id_seq", sequenceName = "sample.user_id_seq", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = User.COUNT_BY_UID, query = "select count(u) from User u where u.uid = :uid"),
		@NamedQuery(name = User.SELECT_BY_UID, query = "select u from User u where u.uid = :uid"),
		@NamedQuery(name = User.REMOVE_BY_ID, query = "delete from User u where u.id = :id") })
public class User implements Serializable {

	private static final long serialVersionUID = 2751812042788505484L;

	public static final String COUNT_BY_UID = "User.countByUid";

	public static final String SELECT_BY_UID = "User.selectByUid";

	public static final String REMOVE_BY_ID = "User.removeById";

	@Id
	@Column(name = "user_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	private Long id;

	@Column(name = "uid", nullable = false, unique = true, length = 64)
	private String uid;

	@Column(name = "name", nullable = false, length = 256)
	private String name;

	/**
	 *
	 */
	public User() {
	}

	/**
	 * @param name
	 */
	public User(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
