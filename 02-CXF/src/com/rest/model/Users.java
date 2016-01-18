package com.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rest.exception.ErrorResponse;
 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class Users extends ErrorResponse{
 
    @XmlElement(name="uSeRs")
    private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
