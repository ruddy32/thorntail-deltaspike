
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
@Table(name = "role", schema = "sample")
@SequenceGenerator(name = "role_id_seq", sequenceName = "sample.role_id_seq", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = Role.SELECT_BY_NAME, query = "select r from Role r where r.name = :name"),
		@NamedQuery(name = Role.REMOVE_BY_ID, query = "delete from Role r where r.id = :id") })
public class Role implements Serializable {

	private static final long serialVersionUID = 9085805868993959754L;

	public static final String SELECT_BY_NAME = "role.selectByName";

	public static final String REMOVE_BY_ID = "role.removeById";

	@Id
	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
	private Long id;

	@Column(name = "name", nullable = false, unique = true, length = 64)
	private String name;

	@Column(name = "description", length = 256)
	private String description;

	/**
	 *
	 */
	public Role() {
	}

	/**
	 * @param name
	 */
	public Role(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
