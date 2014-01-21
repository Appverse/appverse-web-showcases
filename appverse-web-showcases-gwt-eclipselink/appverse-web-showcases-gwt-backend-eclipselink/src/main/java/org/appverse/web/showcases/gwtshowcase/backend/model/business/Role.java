package org.appverse.web.showcases.gwtshowcase.backend.model.business;

import org.appverse.web.framework.backend.api.model.business.AbstractBusinessAuditedBean;


public class Role extends AbstractBusinessAuditedBean {

	private long id;

	private String name;

	private String description;

	private boolean active = true;

	public Role() {
	}

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

}