/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers;

import java.io.File;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.security.DgiiUserDetailsImpl;
import sv.global.colas.utils.Utils;

/**
 *
 * @author ever.argueta
 */
public class MainController {
    @Autowired
    GcUnidadRecepRepository unidadRepository;

    public HttpServletRequest getRequest() {

        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public String getNit() {
        return !Utils.isNullOrEmpty(getNitLogged()) ? getNitLogged() : getNitLogged();
    }

    public String getNitLogged() {
        return ((DgiiUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNit();
    }

    public DgiiUserDetailsImpl getPrincipal() {
        return ((DgiiUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public Collection<GrantedAuthority> getRoles() {
        return (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public String getPath(String folder) {
        String path = getRequest().getSession().getServletContext()
                .getRealPath(folder);
        if (!path.endsWith(System.getProperty("file.separator"))) {
            path = path + System.getProperty("file.separator");
        }
        return path;
    }

    public File getReportFile(String reportPath) {
        ServletContext context = getRequest().getSession().getServletContext();
        File reportFile = new File(context.getRealPath(reportPath));
        return reportFile;
    }

    public Object getSession(String key) {
        return getRequest().getSession().getAttribute(key);
    }

    public void addSession(String key, Object value) {
        getRequest().getSession().setAttribute(key, value);
    }

    public void removeSession(String key) {
        getRequest().getSession().removeAttribute(key);
    }
    
    public String getUsuario(){
        //return getPrincipal().getUsuario();
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        return username;
    }
    public String getUnidadRecep(){
       String unidad=unidadRepository.getUnidadByUsuario(getUsuario());
        return unidad;
    }
    
      public String getUnidadEnServicio(){
       String dunidad=unidadRepository.getUnidadEnServicio(getUnidadRecep());
       return dunidad;
    }
    
    public String getDisplayname(){
        return getPrincipal().getDisplayName();
    }
}
