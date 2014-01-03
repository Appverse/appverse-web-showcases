package org.appverse.web.showcases.gwtshowcase.backend.converters.p2b;

import org.appverse.web.framework.backend.api.converters.AbstractDozerP2BBeanConverter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.springframework.stereotype.Component;

@Component("gwtItemRoleP2BBeanConverter")
public class GWTItemRoleP2BBeanConverter extends
		AbstractDozerP2BBeanConverter<GWTItemVO, Role> {

	public GWTItemRoleP2BBeanConverter() {
		setScopes("gwtItemRole-p2b");
		setBeanClasses(GWTItemVO.class, Role.class);
	}

}
