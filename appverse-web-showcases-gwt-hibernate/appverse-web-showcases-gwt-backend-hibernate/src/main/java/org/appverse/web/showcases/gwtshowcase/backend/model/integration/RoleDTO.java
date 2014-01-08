package org.appverse.web.showcases.gwtshowcase.backend.model.integration;

import org.appverse.web.framework.backend.persistence.model.integration.AbstractIntegrationAuditedJPABean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the USER database table.
 */
@Entity
@Table(name = "ROLE")
public class RoleDTO extends AbstractIntegrationAuditedJPABean implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String description;

	private boolean active = true;

	public RoleDTO() {
	}

	@Column(nullable = true, length = 200)
	public String getDescription() {
		return description;
	}

	@Id
	@TableGenerator(name = "ROLE_GEN", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ROLE_SEQ", valueColumnName = "SEQ_COUNT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ROLE_GEN")
	public long getId() {
		return id;
	}

	@Column(nullable = false, unique = true, length = 40)
	public String getName() {
		return name;
	}

	// @Version implies activate JPA Optimistic Locking for this object
	// Sometimes we will just annotate the parent entity of a more complex
	// structure so that we can "lock" the full object taking
	// into account a functional context. Then, when a child node is update we
	// will force the full object version to change
	// making the "lastUpdateBy" field = NULL. Then the EntityListener will
	// update the object to add the last updated by field. (Another option is to
	// annotate childs as well, but always force the update of the parent).
	// This allows to detect that if several users are working with an object
	// (with a complex structure) if the change something in the fields
	// we can apply optimistic locking at full object level.
	// Considerations: Batch updates and inserts does not work with entities
	// annotated with @version.
	// In this cases, the inserts and updates will be one at a time.
	// In summary, we need to study every considering:
	// 1. We want to "lock" (detect optimistic locking) at functional object
	// level (not per entity) and use the strategy previously explained
	// 2. We need to use batch / insert updates or not
	@Override
	@Version
	public long getVersion() {
		return version;
	}

	@Column(nullable = false)
	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setName(final String name) {
		this.name = name;
	}
}