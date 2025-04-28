/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.monitoreo;

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
 * @author Owner
 */
@Controller
@RequestMapping("/monitoreo")
@EnableAsync
public class monitoreoController extends MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexMonitoreo(HttpServletRequest request,
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model) {
        return "/monitoreo/monitoreos";
    }

}
