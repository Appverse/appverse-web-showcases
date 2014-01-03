package org.appverse.web.showcases.gwtshowcase.backend.converters.helpers.utils;

import org.appverse.web.framework.backend.api.helpers.log.AutowiredLogger;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 
 * Map field list to string
 *
 */
public class ConcatListToStringConverter {
	@AutowiredLogger
	private static Logger logger;

	public final static String SEPARATOR = ", ";

	public static <E> String concatFieldFromList(final List<E> list,
			final String fieldName) {

		String ret = "";
		try {
			if (!list.isEmpty()) {
				final E o = list.get(0);
				@SuppressWarnings("rawtypes")
				final Class classObj = o.getClass();
				@SuppressWarnings("unchecked")
				final Method m = classObj.getMethod("get"
						+ Character.toUpperCase(fieldName.charAt(0))
						+ fieldName.substring(1));
				final StringBuilder sb = new StringBuilder();
				int count = 0;
				final int numItems = list.size();
				for (final E obj : list) {
					sb.append((String) m.invoke(obj));
					if (count++ < (numItems - 1)) {
						sb.append(SEPARATOR);
					}
				}
				ret = sb.toString();
			}
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error("Reflection error getting list values", e);
		}

		return ret;
	}
}