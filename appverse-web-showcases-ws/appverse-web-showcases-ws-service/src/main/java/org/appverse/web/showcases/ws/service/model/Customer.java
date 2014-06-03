package org.appverse.web.showcases.ws.service.model;

import java.io.Serializable;

public class Customer implements Serializable {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String name;
 private String location; 
 private int age; 
 private String desc;
 
 public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	 
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
 
 
}
