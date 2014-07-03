package org.appverse;

import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.UserServiceFacade;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.impl.live.UserRestServiceFacadeImpl;
import org.glassfish.jersey.server.model.internal.ModelHelper;
import org.junit.Test;

import javax.ws.rs.Path;

/**
 * Created by croncon on 02/07/2014.
 */
public class GetAnnotationTest {

    @Test
    public void test() {
        class Test extends UserRestServiceFacadeImpl /*implements UserServiceFacade*/ {
        };

        Object obj3 = new Test();
        Class cls = getAnnotatedResourceClass(obj3.getClass());
        System.out.println(cls);

    }

    public static Class<?> getAnnotatedResourceClass(Class<?> resourceClass) {

        Class<?> cls = resourceClass;
        do {
            if (cls.isAnnotationPresent(Path.class)) {
                return cls;
            }

            for (Class<?> i : cls.getInterfaces()) {
                if (i.isAnnotationPresent(Path.class)) {
                    return i;
                }
            }
        } while ((cls = cls.getSuperclass()) != null);

        return resourceClass;
    }

}
