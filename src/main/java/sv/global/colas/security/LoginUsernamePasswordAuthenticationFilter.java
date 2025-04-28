/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author josue.henriquez
 */
public class LoginUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter  {
    
    
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String nit = super.obtainUsername(request);		
//		if(nit!=null && nit.contains("-")){
//			nit = nit.replaceAll("-", "");
//		}		
		return nit;
	}
        
     @Override   
         public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
         Authentication auth = super.attemptAuthentication(request, response);
        return auth;
    }
}
