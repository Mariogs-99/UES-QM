/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 *
 * @author josue.henriquez
 */
public class DgiiFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

    protected final Log logger = LogFactory.getLog(getClass());

    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
    
      public DgiiFilterInvocationSecurityMetadataSource(){
    	this.requestMap = null;
    }
    
      public DgiiFilterInvocationSecurityMetadataSource(String clazz) {
    	LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
    	try {    		     		
    		 DgiiSecurityBuilder instance = (DgiiSecurityBuilder)Class.forName(clazz).newInstance();    		 
    		 for(Map.Entry<String, String> entry:instance.getUrlsAndRoles().entrySet()){
    			 AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(entry.getKey());    	
    		     requestMap.put(requestMatcher, SecurityConfig.createListFromCommaDelimitedString(entry.getValue()));    		     
    		 }    		 
    		} catch( Exception e ) {
    			e.printStackTrace();
    		} 
        this.requestMap = requestMap;
    }
      
      
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
    
}
