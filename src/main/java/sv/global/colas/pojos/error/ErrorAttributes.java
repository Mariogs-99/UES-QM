/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.error;

import java.util.Map;
import org.springframework.web.context.request.RequestAttributes;

/**
 *
 * @author josue.henriquez
 */
public interface ErrorAttributes {
    /**
	 * Returns a {@link Map} of the error attributes. The map can be used as the model of
	 * an error page {@link ModelAndView}, or returned as a {@link ResponseBody}.
	 * @param requestAttributes the source request attributes
	 * @param includeStackTrace if stack trace elements should be included
	 * @return a map of error attributes
	 */
	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
			boolean includeStackTrace);

	/**
	 * Return the underlying cause of the error or {@code null} if the error cannot be
	 * extracted.
	 * @param requestAttributes the source request attributes
	 * @return the {@link Exception} that caused the error or {@code null}
	 */
	public Throwable getError(RequestAttributes requestAttributes);
}
