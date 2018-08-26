package com.timm.demo.thorntail.deltaspike.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDTO {

	private String uid;

	private String name;

	private String[] roles;

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

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
