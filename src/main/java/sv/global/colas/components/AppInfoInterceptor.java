package sv.global.colas.components;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppInfoInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private String appName;

        @Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            if (modelAndView != null) {
                modelAndView.getModelMap().addAttribute("appName",appName);
            }
            super.postHandle(request, response, handler, modelAndView);
	}

}
