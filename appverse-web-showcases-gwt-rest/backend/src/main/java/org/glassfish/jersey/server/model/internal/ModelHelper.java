/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.jersey.server.model.internal;

import javax.ws.rs.Path;

/**
 * NOTICE: this class fixes a bug in Jersey that did not allow the application to register proxied beans created by Spring.
 * This fix was already contributed to Jersey, but we cant wait for it to be released:
 * https://github.com/jersey/jersey/pull/90
 *
 * Common model helper methods.
 *
 * @author Michal Gajdos (michal.gajdos at oracle.com)
 * @author Constantino Cronemberger (cocr at gft.com)
 */
public final class ModelHelper {

    /**
     * Get the class in the provided resource class ancestor hierarchy that
     * is actually annotated with the {@link javax.ws.rs.Path &#64;Path} annotation.
     *
     * @param resourceClass resource class.
     * @return resource class or it's ancestor that is annotated with the {@link javax.ws.rs.Path &#64;Path}
     *         annotation.
     */
    public static Class<?> getAnnotatedResourceClass(Class<?> resourceClass) {

        // traverse the class hierarchy to find the annotation
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

    /**
     * Prevent instantiation.
     */
    private ModelHelper() {
    }
}
