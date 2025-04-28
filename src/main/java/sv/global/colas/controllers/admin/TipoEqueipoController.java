/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

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
import sv.global.colas.entities.GcPrioridad;
import sv.global.colas.entities.GcTipoEquipo;
import sv.global.colas.repositories.GcTipoEquipoRepository;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/tequipo")
@SessionAttributes("gctequipo")
@EnableAsync
public class TipoEqueipoController extends MainController  {
    
    @Autowired
    private GcTipoEquipoRepository gcTipoEquipoRepository;
         
    @ModelAttribute("gctequipo")
    public GcPrioridad gcPrioridadList(){
        return new GcPrioridad();
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexPrioridad(HttpServletRequest request, @ModelAttribute GcTipoEquipo gcTipoEquipo, final ModelMap model){
            Iterable<GcTipoEquipo> listaTipoEquipo=gcTipoEquipoRepository.findAll();
            model.addAttribute("listaTipoEquipo", listaTipoEquipo);
            return "tequipo/tequipos";
    }
     
    @RequestMapping("/newTequipo")
    public String newPrioridad(HttpServletRequest request, 
            @ModelAttribute GcTipoEquipo gcTipoEquipo, final ModelMap model){
            model.addAttribute("gctequipo",gcTipoEquipo);
            return "tequipo/newTequipo";
    }
     
   @RequestMapping(value="{tequipo}/editTequipo", method = RequestMethod.GET)
    public String editPrioridad(HttpServletRequest request, 
            @ModelAttribute GcTipoEquipo gcTipoEquipo, final ModelMap model,
            @PathVariable(value = "tequipo") Long tequipoId){
            gcTipoEquipo = gcTipoEquipoRepository.findOne(tequipoId);
            model.addAttribute("gctequipo", gcTipoEquipo);
            return "tequipo/newTequipo";
    }
        
        
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardarPrioridad(HttpServletRequest request, @ModelAttribute GcTipoEquipo gcTipoEquipo, final ModelMap model) {
        try{
            String existe=gcTipoEquipoRepository.getTipoEquipoByName(gcTipoEquipo.getDTipoEquipo());
            if(existe==null || "".equals(existe)){
                if (gcTipoEquipo.getNTipoId()==null ) {
                    gcTipoEquipo.setNTipoId(gcTipoEquipoRepository.getTotalId());
                }
                gcTipoEquipoRepository.save(gcTipoEquipo);
                model.addAttribute("mensaje", "Datos guardados correctamente");
            }else{
                model.addAttribute("mensajeError", "Ya Existe un registro con ese nombre!");
            }
        }catch(Exception e){
            model.addAttribute("mensajeError", "Ocurrio un Error!"+" Error: "+e.getMessage());
        } 
        indexPrioridad(request, null, model);
        return "tequipo/tequipos";
    }
        
    @RequestMapping(value = "{tequipo}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request, 
            @ModelAttribute GcTipoEquipo gcTipoEquipo, final ModelMap model,
            @PathVariable(value = "tequipo") Long tequipoId) {
        GcTipoEquipo tequipo = gcTipoEquipoRepository.findOne(tequipoId);
        try{
            gcTipoEquipoRepository.delete(tequipo);
            gcTipoEquipoRepository.delete(tequipo);
            model.addAttribute("mensaje", "Se elimino el registro correctamente!");
        }catch(Exception e){
            model.addAttribute("mensajeError", "Ocurrio un Error!"+" Error: "+e.getMessage());
        }
        indexPrioridad( request, null, model);
        return "tequipo/tequipos";
    }
     
   @RequestMapping(value="{tequipo}/verTequipo", method = RequestMethod.GET)
    public String verPrioridad(HttpServletRequest request, 
            @ModelAttribute GcTipoEquipo gcTipoEquipo, final ModelMap model,
            @PathVariable(value = "tequipo") Long tequipoId){
            gcTipoEquipo = gcTipoEquipoRepository.findOne(tequipoId);
            model.addAttribute("gctequipo", gcTipoEquipo);
            return "tequipo/verTequipo";
    }
}
