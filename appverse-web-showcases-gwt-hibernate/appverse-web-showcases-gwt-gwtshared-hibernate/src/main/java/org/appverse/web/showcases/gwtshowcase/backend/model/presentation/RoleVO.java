package org.appverse.web.showcases.gwtshowcase.backend.model.presentation;

import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTAbstractPresentationAuditedBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

public class RoleVO extends GWTAbstractPresentationAuditedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2623521933606397996L;

	private long id;

	/*
	 * // Validation group for delete operation public interface DeleteGroup {
	 * };
	 */

	@Size(min = 1, max = 40, groups = { Default.class })
	@NotNull
	private String name;

	@Size(max = 200, groups = { Default.class })
	private String description;

	private String listPermissions;

	private String listEnvironments;

	/*
	 * @AssertFalse(groups = { DeleteGroup.class })
	 */
	private boolean active = true;


	public RoleVO() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public String getListEnvironments() {
		return listEnvironments;
	}

	public String getListPermissions() {
		return listPermissions;
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

	public void setListEnvironments(final String listEnvironments) {
		this.listEnvironments = listEnvironments;
	}

	public void setListPermissions(final String listPermissions) {
		this.listPermissions = listPermissions;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
