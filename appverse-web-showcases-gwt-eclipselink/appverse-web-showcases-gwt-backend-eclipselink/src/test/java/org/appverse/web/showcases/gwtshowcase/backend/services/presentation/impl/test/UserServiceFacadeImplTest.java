package org.appverse.web.showcases.gwtshowcase.backend.services.presentation.impl.test;

import org.appverse.web.framework.backend.api.helpers.test.AbstractTransactionalTest;
import org.appverse.web.showcases.gwtshowcase.backend.converters.p2b.UserP2BBeanConverter;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.User;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;
import org.appverse.web.showcases.gwtshowcase.backend.services.business.RoleService;
import org.appverse.web.showcases.gwtshowcase.backend.services.business.UserService;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.UserServiceFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserServiceFacadeImplTest extends AbstractTransactionalTest {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	private User user;

	@Autowired
	private UserP2BBeanConverter userP2BBeanConverter;

	@Autowired
	private UserServiceFacade userServiceFacade;

	private void checkUserVOList(final List<UserVO> list) {
        for(UserVO userVO : list){
            checkUserVOAllFields(userVO);
        }
	}

	public void checkUserVOAllFields(final UserVO user) {
		Assert.assertNotNull("Field can't be null", user.getCreated());
		Assert.assertNotNull("Field can't be null", user.getCreatedBy());
		Assert.assertNotNull("Field can't be null", user.getEmail());
		Assert.assertNotNull("Field can't be null", user.getId());
		Assert.assertNotNull("Field can't be null", user.getLastName());
		Assert.assertNotNull("Field can't be null", user.getName());
		Assert.assertNotNull("Field can't be null", user.getPassword());
		Assert.assertNotNull("Field can't be null", user.isActive());
		Assert.assertNotNull("Field can't be null", user.getUpdated());
		Assert.assertNotNull("Field can't be null", user.getUpdatedBy());
		Assert.assertNotNull("Field can't be null", user.getVersion());
	}

	public void checkUserVOAllFieldsWithCollections(final UserVO user) {

		checkUserVOAllFields(user);

		// Check here dependencies (collections, etc)

        // Roles
		Assert.assertNotNull("Roles collection can't be null", user.getRoles());
		Assert.assertEquals("Roles collection size must be 5", 5, user.getRoles().size());
	}

	@Before
	public void initialize() throws Exception {
		// Set ALL first level fields
		user = new User();
		userSetAllFields(user);

		// Add collections and dependencies

        // We add all (5) existing roles
		user.setRoles(roleService.loadRoles());
	}

	@Test
	public void loadUser() throws Exception {
		long id = userService.saveUser(user);
		UserVO userVOAux = userServiceFacade.loadUser(id);

		// Check object and relations
		checkUserVOAllFieldsWithCollections(userVOAux);
	}

	@Test
	public void loadUsers() throws Exception {
		userService.saveUser(user);
		List<UserVO> usersList = userServiceFacade.loadUsers();
        // Remember that loadUsers() retrieves users with 'WithoutDependencies' scope so dependencies
        // are not checked here
        checkUserVOList(usersList);
	}

	@Test
	public void saveUser() throws Exception {
		// New persist
		UserVO userVO = userP2BBeanConverter.convert(user);
		long id = userServiceFacade.saveUser(userVO);
		UserVO userVOAux = userServiceFacade.loadUser(id);
		checkUserVOAllFieldsWithCollections(userVOAux);

		// Update
		userVOAux.setName("new");
		id = userServiceFacade.saveUser(userVOAux);
		userVOAux = userServiceFacade.loadUser(id);
		Assert.assertNotNull(
				"VO has not been able to be retrieved after update", userVOAux);
		Assert.assertEquals("Invalid name in VO object", "new",
				userVOAux.getName());
		checkUserVOAllFieldsWithCollections(userVOAux);
	}

	public void userSetAllFields(final User user) {
		// We don't set audit fields as they are automatically created
		// We don't set id and version fields either as this are database
		// assigned values
        user.setStatus("A");
        user.setCreated(new Date());
        user.setCreatedBy("test");
        user.setUpdated(new Date());
        user.setUpdatedBy("test");
		user.setActive(true);
		user.setEmail("email");
		user.setLastName("lastName");
		user.setName("name");
		user.setPassword("password");
	}
}
