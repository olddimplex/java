package util;

import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public final class HttpUtil {

	private static final Pattern ACCEPTS_JSON_PATTERN = Pattern.compile("(.*,?application|text)/json,?.*");

	private HttpUtil() {
	}

	/**
	 * Checks if the given {@link ServletRequest} is an instance of {@link HttpServletRequest}
	 * 
	 * @param request
	 * @return
	 */
    public static boolean isHttpRequest(final ServletRequest request) {
        return HttpServletRequest.class.isAssignableFrom(request.getClass());
    }

	/**
	 * Checks the Accept header.
	 *
	 * @param request
	 * @return
	 */
	public static boolean acceptsJSON(final HttpServletRequest request) {
		final String acceptHeader = request.getHeader("Accept");
		return acceptHeader != null && ACCEPTS_JSON_PATTERN.matcher(acceptHeader).matches();
	}

	/**
	 * Utility method
	 *
	 * @param request
	 * @param paramName
	 * @param defaultPageNumber
	 * @return
	 */
	public static Integer getParamAsInt(final HttpServletRequest request, final String paramName, final Integer defaultPageNumber) {
		try {
			return Integer.valueOf(request.getParameter(paramName));
		} catch (final Exception e) {
			return defaultPageNumber;
		}
	}
}
