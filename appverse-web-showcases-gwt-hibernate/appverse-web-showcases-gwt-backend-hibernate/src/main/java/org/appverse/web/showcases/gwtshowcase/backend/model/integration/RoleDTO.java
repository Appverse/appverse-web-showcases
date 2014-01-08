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
    /* Difference with EclipseLink
       This does not work for Hibernate as it manages override of annotations in JPA entities different than Eclipselink.
       Overriding this field and adding @Version for optimistic locking would not work (it does with EclipseLink).
       For optimistic locking with Hibernate this has to be added directly in the project classes.
   */
    // This did not work: http://stackoverflow.com/questions/15092204/how-to-override-parameter-in-sublass-of-mappedsuperclass
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