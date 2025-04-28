/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.pojos.entities.ServicioPojo;
import sv.global.colas.repositories.GcServicioRepository;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/servicio")
@SessionAttributes("serviciopojo")
@EnableAsync
public class ServicioController extends MainController {
    
    @Autowired
    private GcServicioRepository servicioRepository;
     
    @ModelAttribute("serviciopojo")
    public ServicioPojo serviciopojo() {
        ServicioPojo servicio = new ServicioPojo();
        List<GcServicios> servicios = (List<GcServicios>) servicioRepository.getAllSecciones();
        servicio.setGcServiciosList(servicios);
        return servicio;
    }
     
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexServicio(HttpServletRequest request, @ModelAttribute ServicioPojo servicioP, final ModelMap model){
            Iterable<GcServicios> serviciosList = servicioRepository.getAllSecciones();
            ServicioPojo serviciopojo = (ServicioPojo) model.get("serviciopojo");
            if(serviciopojo==null) serviciopojo = new ServicioPojo();
            serviciopojo.setGcServiciosList((List<GcServicios>) serviciosList);
            model.addAttribute("serviciopojo", serviciopojo);
            return "servicio/servicios";
    }
     
    @RequestMapping("/newServicio")
    public String newServicio(HttpServletRequest request, 
            @ModelAttribute GcServicios gcServicios, final ModelMap model){
            model.addAttribute("gcservicio", gcServicios);
            model.addAttribute("maxOrden", servicioRepository.getOrdenBySecciones());
            return "servicio/newServicio";
    }
     
   @RequestMapping(value="{servicio}/editServicio", method = RequestMethod.GET)
    public String editZona(HttpServletRequest request, 
            @ModelAttribute GcServicios gcServicios, final ModelMap model,
            @PathVariable(value = "servicio") Long servicioId){
            gcServicios = servicioRepository.findOne(servicioId);
            gcServicios.setBActiva(gcServicios.getBActiva()==0?2:gcServicios.getBActiva());
            model.addAttribute("gcservicio", gcServicios);
            model.addAttribute("maxOrden", gcServicios.getNOrden());
            return "servicio/newServicio";
    }
        
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute GcServicios gcServicios, final ModelMap model) {
        try{
            if(servicioRepository.getServicioDuplicado(gcServicios.getSNombre(),gcServicios.getNServiciosId()==null?0:gcServicios.getNServiciosId())==0){
                gcServicios.setCUsuarioCrea(getUsuario());
                gcServicios.setCUsuarioModi(getUsuario());
                gcServicios.setFModifica(new Date());
                gcServicios.setFiVigencia(new Date());
                gcServicios.setBActiva((short) (gcServicios.getBActiva()==2?0:gcServicios.getBActiva()));
                gcServicios.setNServiciosId(gcServicios.getNServiciosId());
                if(gcServicios.getNServiciosId()==null){
                    gcServicios.setNServiciosId(servicioRepository.getTotalId());}
                servicioRepository.save(gcServicios);
                model.addAttribute("mensaje", "DATOS GUARDADOS CORRECTAMENTE");
            }else{
                model.addAttribute("mensajeError", " Error: Ya existe una Sección con ese nombre");
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error!"+" Error: "+e.getMessage());
        }
        indexServicio(request, null, model);
        return "servicio/servicios";
    }
     
   @RequestMapping(value="{servicio}/verServicio", method = RequestMethod.GET)
    public String verZona(HttpServletRequest request, 
            @ModelAttribute GcServicios gcServicios, final ModelMap model,
            @PathVariable(value = "servicio") Long servicioId){
            gcServicios = servicioRepository.findOne(servicioId);
            model.addAttribute("gcservicio", gcServicios);
            return "servicio/verServicio";
    }
        
    @RequestMapping(value = "{servicio}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request, 
            @ModelAttribute GcServicios gcServicios, final ModelMap model,
            @PathVariable(value = "servicio") Long servicioId) {
        GcServicios servicio = servicioRepository.findOne(servicioId);
        try{
            if(servicioRepository.getServicioRelacionado(servicioId)==0){
                servicio.setBActiva((short)0);
                servicio.setCUsuarioModi(getUsuario());
                servicio.setFfVigencia(new Date());
                servicioRepository.save(servicio);
                model.addAttribute("mensaje", "SERVICIO ELIMINADO CORRECTAMENTE!");
            }else{
                model.addAttribute("mensajeError", "Error: La seccion esta relacionada con tramites");
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Error: Esta Seccion se encuentra relacionada con tramites, Favor verificar!");
        }
        indexServicio( request, null, model);
        return "servicio/servicios";
    }        
    @RequestMapping(value = "/getOrden", method = RequestMethod.GET)
    public String getOrdenByServicio(HttpServletRequest request,  @ModelAttribute ServicioPojo servicioPojo, final ModelMap model,
            @RequestParam(value = "serunidad", required = false) String serunidad) {
            servicioPojo = (ServicioPojo) model.get("serviciopojo");
            servicioPojo.setOrden(servicioRepository.getOrdenBySecciones());
            model.addAttribute("serviciopojo", servicioPojo);
            return "servicio/newServicio";
    }
}
