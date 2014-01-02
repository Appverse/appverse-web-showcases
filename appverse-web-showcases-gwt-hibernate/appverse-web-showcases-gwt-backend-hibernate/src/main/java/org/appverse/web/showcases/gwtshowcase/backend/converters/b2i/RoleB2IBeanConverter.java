package org.appverse.web.showcases.gwtshowcase.backend.converters.b2i;

import org.appverse.web.framework.backend.api.converters.AbstractDozerB2IBeanConverter;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.appverse.web.showcases.gwtshowcase.backend.model.integration.RoleDTO;
import org.springframework.stereotype.Component;

@Component("roleB2IBeanConverter")
public class RoleB2IBeanConverter extends
		AbstractDozerB2IBeanConverter<Role, RoleDTO> {

	public RoleB2IBeanConverter() {
		setScopes("role-b2i-complete", "role-b2i-without-dependencies");
		setBeanClasses(Role.class, RoleDTO.class);
	}
}
