package org.appverse.web.showcases.gwtshowcase.backend.converters.p2b;

import org.appverse.web.framework.backend.api.converters.AbstractDozerP2BBeanConverter;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.springframework.stereotype.Component;

@Component("roleP2BBeanConverter")
public class RoleP2BBeanConverter extends
		AbstractDozerP2BBeanConverter<RoleVO, Role> {

	public RoleP2BBeanConverter() {
		setScopes("role-p2b");
		setBeanClasses(RoleVO.class, Role.class);
	}
}
