package org.appverse.web.showcases.gwtshowcase.backend.services.business.impl.test;

import org.appverse.web.framework.backend.api.helpers.test.AbstractTransactionalTest;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.User;
import org.appverse.web.showcases.gwtshowcase.backend.services.business.RoleService;
import org.appverse.web.showcases.gwtshowcase.backend.services.business.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserServiceImplTest extends AbstractTransactionalTest {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	private User user = new User();

	@Before
	public void initialize() throws Exception {
		// Set first level fields
		user.setStatus(User.STATUS_ACTIVE);
		user.setEmail("email");
		user.setLastName("lastName");
		user.setName("name");
		user.setPassword("password");
        user.setCreated(new Date());
        user.setCreatedBy("test");

		// Add collections

        // We add the 4 regular roles (as we have 5 we remove the extra that will be used later to test
        // roles additions)
        List<Role> roleCollection = roleService.loadRoles();
        roleCollection.remove(4);

        user.setRoles(roleCollection);
	}

    private void checkUser(User user) {
        Assert.assertNotNull("Entity can't be null", user);

        // Check collections
        Assert.assertNotNull("Roles collection can't be null", user.getRoles());
        Assert.assertTrue("Roles collection size must be 4", user.getRoles()
                .size() == 4);
    }

    /*
        Take into account in this method we do not check collections size as the
        loadUsers() retrieves users using WithoutDependencies converter.
        The reason is that usually when you retrieve all the list of objects you do not need all the details /
        dependencies. For performance reasons, when you require this information for a particular object then
        the required dependencies are retrieved.
     */
    private void checkUserList(List<User> list) {
        Assert.assertNotNull("Entity list has not been able to be retrieved",
                list);
        Assert.assertTrue("Entity list is empty", list.size() > 0);
    }

	@Test
	public void persistAndModifyNewUser() throws Exception {
		// Persist new
		final long resultPk = userService.saveUser(user);
		Assert.assertNotEquals("The returned entity id can't be 0", 0L, resultPk);

		// Retrieve
		User userAux = userService.loadUser(resultPk);
		checkUser(userAux);

		// Modify and persist
		userAux.setName("new");
		userService.saveUser(userAux);
		userAux = userService.loadUser(resultPk);
		Assert.assertEquals("The value was not properly updated", "new",
                userAux.getName());
		checkUser(userAux);
	}

    @Test
    public void removeFromUserDependencies() throws Exception{
        // Element removal from collections check (converters remove-orphan test)

        // Retrieve existing user (1)
        User userAux = userService.loadUser(1L);
        checkUser(userAux);

        // Role dependency removal
        long roleId = userAux.getRoles().get(0).getId();
		userAux.getRoles().remove(0);

        // Other dependencies removal....

		long resultPk = userService.saveUser(userAux);

        // We retrieve the user
		userAux = userService.loadUser(resultPk);

        // Role dependency check (we check a role has been removed)
		Assert.assertEquals("The list of entities size is not correct", 3L,
                userAux.getRoles().size());

        // Other dependency check

    }

    @Test
    public void addToUserDependencies() throws Exception{
        // Element add to collections check (converters cummulative test)

        // Retrieve existing user (1)
        User userAux = userService.loadUser(1L);
        checkUser(userAux);

        // Role dependency addition (we add the extra role (5) to he user
        userAux.getRoles().add(roleService.loadRole(5L));

        // Other dependencies addition...
        userService.saveUser(userAux);

        // We retrieve the user
        userAux = userService.loadUser(1L);

        // Role dependency check (we check the extra role is added)
        Assert.assertEquals("The list of entities size is not correct", 5L,
                userAux.getRoles().size());

        // Other dependency check


    }


    @Test
	public void retrieveAllUsers() throws Exception {
		final long resultPk = userService.saveUser(user);
		Assert.assertNotSame("The returned entity id can't be 0", 0L, resultPk);

		final List<User> result = userService.loadUsers();
		checkUserList(result);
	}
}