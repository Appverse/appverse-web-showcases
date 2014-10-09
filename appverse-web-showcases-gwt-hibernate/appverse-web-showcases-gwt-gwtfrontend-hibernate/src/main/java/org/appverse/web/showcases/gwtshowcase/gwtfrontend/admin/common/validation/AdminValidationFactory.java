package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.common.validation;

import javax.validation.Validator;

import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

public class AdminValidationFactory extends AbstractGwtValidatorFactory {
	/**
	 * Validator marker for the Validation Sample project. Only the classes and groups listed
	 * in the {@link GwtValidation} annotation can be validated.
	 */
	@GwtValidation({UserVO.class, RoleVO.class})
	public interface GwtValidator extends Validator {
		
	}
	
	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}

}