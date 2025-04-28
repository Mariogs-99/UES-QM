/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.error;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import sv.global.colas.pojos.error.DefaultErrorAttributes;
import sv.global.colas.pojos.error.ErrorAttributes;

/**
 *
 * @author josue.henriquez
 */
@Controller
@EnableAsync
public class ErrorController {
    @Value("${error.path:/error}")
	private String errorPath;

    private final ErrorAttributes errorAttributes;

    
	public ErrorController() {	
		this.errorAttributes = new DefaultErrorAttributes();
	}
	
    @RequestMapping(value = "${error.path:/error}")
	@ResponseBody	
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}
    
    @RequestMapping(value = "/error", produces = "text/html")    
	public ModelAndView errorHtml(HttpServletRequest request) {
    	Map<String, Object> errorAttributes = getErrorAttributes(request, false);
    	
		return new ModelAndView("errors/error", errorAttributes);
	}

    private boolean getTraceParameter(HttpServletRequest request) {
		String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}
		return !"false".equals(parameter.toLowerCase());
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request,
			boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return this.errorAttributes.getErrorAttributes(requestAttributes,
				includeStackTrace);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			}
			catch (Exception ex) {
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
