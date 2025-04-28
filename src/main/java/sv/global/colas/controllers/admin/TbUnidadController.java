/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.util.Date;
import sv.global.colas.utils.Utils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcJerarquia;
import sv.global.colas.entities.GcUnidadRecep;
import sv.global.colas.repositories.UnidadRecepRepository;

/**
 *
 * @author Pino
 */
@Controller
@RequestMapping("/unidad")
@SessionAttributes("gcUnidadRecep")
@EnableAsync
public class TbUnidadController  extends MainController{
    
    Utils utilidad = new Utils();
    
    @Autowired
    private  UnidadRecepRepository gcUnidadRecepRepository; 
     
    @ModelAttribute("gcUnidadRecep")
    public GcUnidadRecep tbunidad() {
        return new GcUnidadRecep();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexUnidad(HttpServletRequest request, @ModelAttribute GcUnidadRecep tbUnidadRecep, final ModelMap model) {
        Iterable<GcUnidadRecep> unidadList = null;
        if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            unidadList = gcUnidadRecepRepository.findAll();
        } else {
            unidadList = gcUnidadRecepRepository.findAll();
        }
        model.addAttribute("unidadList", unidadList);
        return "unidad/unidades";
    }

    @RequestMapping("/newUnidad")
    public String irAdministracionNewUnidad(HttpServletRequest request, @ModelAttribute GcUnidadRecep tbUnidadRecep, final ModelMap model) {
        model.addAttribute("gcUnidadRecep", new GcUnidadRecep());
        return "unidad/newUnidad";
    }

    @RequestMapping(value="{unidad}/editUnidad", method = RequestMethod.GET)
    public String editUnidad(HttpServletRequest request, @ModelAttribute GcUnidadRecep gcUnidadRecep, final ModelMap model, @PathVariable(value = "unidad") String unidad){
        GcUnidadRecep tbUnidadRecep=gcUnidadRecepRepository.findOne(unidad);
        model.addAttribute("gcUnidadRecep", tbUnidadRecep);
        return "unidad/newUnidad";
    }
      
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardarUnidad(HttpServletRequest request, @ModelAttribute GcUnidadRecep tbUnidadRecep, final ModelMap model) {
        try{
            tbUnidadRecep = (GcUnidadRecep) model.get("gcUnidadRecep");
            if(tbUnidadRecep.getCunidadRecep()==null || tbUnidadRecep.getCunidadRecep().equals("")){
                tbUnidadRecep.setCunidadRecep(gcUnidadRecepRepository.getMaxUnidadRecep());
            }
            tbUnidadRecep.setCusuario(getUsuario());
            tbUnidadRecep.setMtipoUnidad("CENTRO DE SERVICIO");
            tbUnidadRecep.setBdesplegable(Boolean.TRUE);
            tbUnidadRecep.setCunidad(tbUnidadRecep.getCunidadRecep());
            tbUnidadRecep.setMservicio("M");

            gcUnidadRecepRepository.save(tbUnidadRecep);
            model.addAttribute("mensaje", "UNIDAD GUARDADA CORRECTAMENTE");
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Error: "+e.getMessage());
        } 
        indexUnidad(request, null, model);
        return "unidad/unidades";
    }
        
    @RequestMapping(value = "{unidad}/delete", method = RequestMethod.POST)
    public String eliminarUnidad(HttpServletRequest request, @ModelAttribute GcUnidadRecep gcUnidadRecep, final ModelMap model,@PathVariable(value = "unidad") String unidad) {
        try{
            gcUnidadRecepRepository.delete(unidad);
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Error: "+e.getMessage());
        } 
        indexUnidad(request, null, model);
        return "unidad/unidades";
    }
     
   @RequestMapping(value="{unidad}/verUnidad", method = RequestMethod.GET)
    public String verJerarquia(HttpServletRequest request, @ModelAttribute GcUnidadRecep gcUnidadRecep, final ModelMap model, @PathVariable(value = "unidad") String unidad){
            GcUnidadRecep tbUnidadRecep=gcUnidadRecepRepository.findOne(unidad);
            model.addAttribute("gcUnidadRecep", tbUnidadRecep);
            return "unidad/verUnidad";
    }
    
    
    
}
