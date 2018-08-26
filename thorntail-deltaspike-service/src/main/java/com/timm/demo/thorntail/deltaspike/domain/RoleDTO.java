package com.timm.demo.thorntail.deltaspike.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleDTO {

	private String name;

	private String description;

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
}
