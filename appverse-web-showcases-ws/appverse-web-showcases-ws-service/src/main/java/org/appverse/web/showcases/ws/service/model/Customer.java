package org.appverse.web.showcases.ws.service.model;

import java.io.Serializable;

public class Customer implements Serializable {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Location
	 */
	private String location;
	/**
	 * Age
	 */
	private int age;
	/**
	 * Description
	 */
	private String desc;
	/**
	 * Get Customer name
	 * @return name as string
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set customer name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Get Customer locaiton
	 * @return location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * Set Location
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * Get Age
	 * @return age as int
	 */
	public int getAge() {
		return age;
	}
	/**
	 * Set age
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * Get Description
	 * @return description as string
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * Set Description
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}


}
