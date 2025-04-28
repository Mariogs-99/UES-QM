/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.math.BigInteger;
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
import sv.global.colas.entities.GcTramite;
import sv.global.colas.repositories.GcServicioRepository;
import sv.global.colas.repositories.GcTramiteRepository;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/tramite")
@SessionAttributes("gctramite")
@EnableAsync
public class TramiteController extends MainController {
    
    @Autowired
    private GcTramiteRepository gcTramiteRepository;
    
    @Autowired
    private GcServicioRepository gcServicioRepository;
         
    @ModelAttribute("gctramite")
    public GcTramite gcTramiteList() {
        return new GcTramite();
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexTramite(HttpServletRequest request, @ModelAttribute GcTramite gcTramite, final ModelMap model){
            Iterable<GcTramite> ListaTramites=gcTramiteRepository.getAllTramites();
            model.addAttribute("gcTramiteList", ListaTramites);
            return "tramite/tramites";
    }
     
    @RequestMapping("/newTramite")
    public String newTramite(HttpServletRequest request, 
            @ModelAttribute GcTramite gcTramite, final ModelMap model){
            Iterable<GcServicios> listServicios=gcServicioRepository.getAllSecciones();
            model.addAttribute("serviciosList", listServicios);
            gcTramite.setNOrden(gcTramiteRepository.getOrdenMax());
            model.addAttribute("gctramite",gcTramite);
            return "tramite/newTramite";
    }
     
   @RequestMapping(value="{tramite}/editTramite", method = RequestMethod.GET)
    public String editTramite(HttpServletRequest request, 
            @ModelAttribute GcTramite gcTramite, final ModelMap model,
            @PathVariable(value = "tramite") Long tramiteId){
            gcTramite = gcTramiteRepository.findOne(tramiteId);
            //gcTramite.setBEscalamiento(gcTramite.getBEscalamiento()==0?2:gcTramite.getBEscalamiento());
            gcTramite.setbReservaCita(gcTramite.getbReservaCita()==null?0:gcTramite.getbReservaCita()==0?2:gcTramite.getbReservaCita());
            gcTramite.setBNitRequerido(gcTramite.getBNitRequerido()==0?2:gcTramite.getBNitRequerido());
            model.addAttribute("serviciosList", gcServicioRepository.findAll());
            model.addAttribute("gctramite", gcTramite);
            return "tramite/newTramite";
    }
        
        
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute GcTramite gcTramite, final ModelMap model) {
        try{
            if(gcTramiteRepository.getTramiteDuplicado(gcTramite.getSNombre(),gcTramite.getNTramiteId()==null?0:gcTramite.getNTramiteId())==0){
                gcTramite.setCUsuarioCrea(getUsuario());
                gcTramite.setCUsuarioModi(getUsuario());
                gcTramite.setFModifica(new Date());
                gcTramite.setFiVigencia(new Date());
                gcTramite.setBActiva((short) 1);
                gcTramite.setNPeso( BigInteger.ONE);
                gcTramite.setBEscalamiento((short)0);
                gcTramite.setbReservaCita(gcTramite.getbReservaCita()==2?0:gcTramite.getbReservaCita());
                gcTramite.setBNitRequerido(gcTramite.getBNitRequerido()==2?0:gcTramite.getBNitRequerido());
                /*if (gcTramite.getNTramiteId()==null)
                    gcTramite.setNTramiteId(gcTramiteRepository.getTotalId());
                */
                gcTramiteRepository.save(gcTramite);
                model.addAttribute("mensaje", "TRAMITE GUARDADO CORRECTAMENTE");
            }else{
                model.addAttribute("mensajeError", " Error: YA EXISTE UN TRAMITE CON ESE NOMBRE");
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al momento de Guardar los datos!"+e.getMessage());
        }
        indexTramite(request, null, model);
        return "tramite/tramites";
    }
        
    @RequestMapping(value = "{tramite}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request, 
            @ModelAttribute GcTramite gcTramite, final ModelMap model,
            @PathVariable(value = "tramite") Long tramiteId) {
        GcTramite tramite = gcTramiteRepository.findOne(tramiteId);
        try{
            if(gcTramiteRepository.getTramiteRelacionados(tramiteId)==0){
                tramite.setBActiva((short)0);
                tramite.setCUsuarioModi(getUsuario());
                tramite.setFfVigencia(new Date());
                gcTramiteRepository.save(tramite);
                model.addAttribute("mensaje", "SE ELIMINO EL REGISTRO CORRECTAMENTE!");
            }else{
                model.addAttribute("mensaje", "Error: TRAMITE ESTA ASIGNADO A UN USUARIO O EXISTE UNA CONFIGURACION POR CENTRO DE SERVICIO!");
            }
        }catch(Exception e){
            model.addAttribute("mensajeError", "Ocurrio un Error!"+" Error: "+e.getMessage());
        }
        indexTramite( request, null, model);
        return "tramite/tramites";
    }
     
   @RequestMapping(value="{tramite}/verTramite", method = RequestMethod.GET)
    public String verTramite(HttpServletRequest request, 
            @ModelAttribute GcTramite gcTramite, final ModelMap model,
            @PathVariable(value = "tramite") Long tramiteId){
            gcTramite = gcTramiteRepository.findOne(tramiteId);
            model.addAttribute("gctramite", gcTramite);
            return "tramite/verTramite";
    }       
    @RequestMapping(value = "/getOrden", method = RequestMethod.GET)
    public String getOrdenByServicio(HttpServletRequest request,  @ModelAttribute GcTramite gcTramite, final ModelMap model,
            @RequestParam(value = "nServiciosId", required = false) Long nServiciosId) {
            gcTramite = (GcTramite) model.get("gctramite");
            gcTramite.setNOrden(gcTramiteRepository.getOrdenByTramite(nServiciosId));
            model.addAttribute("gctramite", gcTramite);
            return "tramite/newTramite";
    }
}
