/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.reportes;

import javax.servlet.http.HttpServletRequest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sv.global.colas.controllers.MainController;
import sv.global.colas.pojos.entities.MonitoreoTramitePojo;


/**
 *
 * @author CésarStanley
 */
@Controller
@RequestMapping("/reportes")
@EnableAsync
public class reportesController extends MainController{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexMonitoreo(HttpServletRequest request,
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model) {
        return "/reportes/home";
    }
    
    @RequestMapping(value = "/contribuyente", method = RequestMethod.GET)
    public String indexContribuyente(HttpServletRequest request,
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model) {
        return "/reportes/mTramites";
    }
    
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public String indexUsuario(HttpServletRequest request,
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model) {
        return "/reportes/mUsuarios";
    }
    
    
}
