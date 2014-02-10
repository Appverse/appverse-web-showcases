/*
 Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

 This Source Code Form is subject to the terms of the Mozilla Public
 License, v. 2.0. If a copy of the MPL was not distributed with this
 file, You can obtain one at http://mozilla.org/MPL/2.0/.

 Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the conditions of the Mozilla Public License v2.0
 are met.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
 SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE)
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.
 */
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
    @Column(nullable = false)
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