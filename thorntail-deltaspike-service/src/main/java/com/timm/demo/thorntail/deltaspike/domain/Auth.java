package com.timm.demo.thorntail.deltaspike.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Auth {

	private String uid;

	private String code;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
