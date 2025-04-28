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
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.repositories.GcConfTramiteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/ctramite")
@SessionAttributes("gcctramite")
@EnableAsync
public class ConfTramiteController  extends MainController{
    
    @Autowired
    private GcConfTramiteRepository gcConfTramiteRepository;
    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;
    @Autowired
    private GcTramiteRepository gcTramiteRepository;
    Utils utilidad = new Utils();
         
    @ModelAttribute("gcctramite")
    public GcConfTramite gcctramite(){
        return new GcConfTramite();
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexConfTramite(HttpServletRequest request, @ModelAttribute GcConfTramite gcConfTramite, final ModelMap model){
            Iterable<GcConfTramite> ctramiteList=null;
            if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
                ctramiteList=gcConfTramiteRepository.listaConfiguracionesTramiteGeneral();
            }else{
                ctramiteList=gcConfTramiteRepository.listaConfiguracionesTramiteLocal(getUnidad());
            }
            model.addAttribute("gcctramiteList", ctramiteList);
            return "ctramite/ctramites";
    }
     
    @RequestMapping("/newCtramite")
    public String newCtramite(HttpServletRequest request, 
            @ModelAttribute GcConfTramite gcConfTramite2, final ModelMap model){
            GcConfTramite gcConfTramite =new GcConfTramite();
            model.addAttribute("gcUnidadesList",getUnidadesList());   
            model.addAttribute("gcTramiteList",gcTramiteRepository.getAllTramites());            
            model.addAttribute("gcctramite",gcConfTramite);            
            return "ctramite/newCtramite";
    }
     
   @RequestMapping(value="{ctramite}/editCtramite", method = RequestMethod.GET)
    public String editEquipo(HttpServletRequest request, 
            @ModelAttribute GcConfTramite gcConfTramite, final ModelMap model,
            @PathVariable(value = "ctramite") Long ctramiteId){
            gcConfTramite=gcConfTramiteRepository.findOne(ctramiteId);
            model.addAttribute("unidadesSelect",gcConfTramite.getCUnidadRecep().getCunidadRecep());   
            gcConfTramite.setCUnidadRecep(null);
            model.addAttribute("gcUnidadesList",getUnidadesList());   
            model.addAttribute("gcTramiteList",gcTramiteRepository.getAllTramites());
            model.addAttribute("gcctramite",gcConfTramite);
            return "ctramite/newCtramite";
    }
        
        
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardarConfPOST(HttpServletRequest request, @ModelAttribute GcConfTramite gcConfTramite, final ModelMap model) {
        try{
            if(gcConfTramite.getNConfTraId()!=null){
                GcConfTramite gcConfTramite2=gcConfTramiteRepository.findOne(gcConfTramite.getNConfTraId());  
                gcConfTramite2.setCUsuarioModi(getUsuario());
                gcConfTramite2.setFModifica(new Date());
                gcConfTramite2.setNAtencionProm(gcConfTramite.getNAtencionProm());
                gcConfTramite2.setNTiempoEspera(gcConfTramite.getNTiempoEspera());
                gcConfTramite2.setNTiempoHolgura(gcConfTramite.getNTiempoHolgura());
                gcConfTramite2.setNConfTraId(gcConfTramite2.getNConfTraId());
                gcConfTramiteRepository.save(gcConfTramite2);
                model.addAttribute("mensaje", "Datos guardados correctamente");
            }else{
                    gcConfTramite.setCUsuarioCrea(getUsuario());
                    gcConfTramite.setCUnidadRecep(gcUnidadRecepRepository.getUnidadEnServicioE(gcConfTramite.getCUsuarioModi()));
                    gcConfTramite.setFfVigencia(new Date());
                    gcConfTramite.setFiVigencia(new Date());
                    gcConfTramite.setCUsuarioModi(getUsuario());
                    gcConfTramite.setFModifica(new Date());
                    //gcConfTramite.getNConfTraId();
                    gcConfTramiteRepository.save(gcConfTramite);
                    model.addAttribute("mensaje", "Datos guardados correctamente");
                }
            
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al tratar de guardar los datos!");
        } 
        indexConfTramite(request, null, model);
        return "ctramite/ctramites";
    }
            
    @RequestMapping(value = "/guardar", method = RequestMethod.GET)
    public String guardarConfGET(HttpServletRequest request, @ModelAttribute GcConfTramite gcConfTramite, final ModelMap model) {
        try{
            if(gcConfTramite.getNConfTraId()!=null){
                GcConfTramite gcConfTramite2=gcConfTramiteRepository.findOne(gcConfTramite.getNConfTraId());                
                gcConfTramite2.setCUsuarioModi(getUsuario());
                gcConfTramite2.setFModifica(new Date());
                gcConfTramite2.setNAtencionProm(gcConfTramite.getNAtencionProm());
                gcConfTramite2.setNTiempoEspera(gcConfTramite.getNTiempoEspera());
                gcConfTramite2.setNTiempoHolgura(gcConfTramite.getNTiempoHolgura());
                gcConfTramiteRepository.save(gcConfTramite2);
                model.addAttribute("mensaje", "Datos guardados correctamente");
            }else{
                GcConfTramite gcConfTramite2=gcConfTramiteRepository.existe(gcConfTramite.getCUnidadRecep().getCunidadRecep(),gcConfTramite.getNTramiteId().getNTramiteId());
                if(gcConfTramite2==null){
                    gcConfTramite.setCUsuarioCrea(getUsuario());
                    gcConfTramite.setFfVigencia(new Date());
                    gcConfTramite.setFiVigencia(new Date());
                    gcConfTramite.setCUsuarioModi(getUsuario());
                    gcConfTramite.setFModifica(new Date());
                    gcConfTramiteRepository.save(gcConfTramite);
                    model.addAttribute("mensaje", "Datos guardados correctamente");
                }else{
                    if(gcConfTramite2.getNTiempoEspera()==null && gcConfTramite2.getNTiempoHolgura()==null && gcConfTramite2.getNAtencionProm().intValue()==0){
                        gcConfTramite2.setCUsuarioModi(getUsuario());
                        gcConfTramite2.setFModifica(new Date());
                        gcConfTramite2.setNAtencionProm(gcConfTramite.getNAtencionProm());
                        gcConfTramite2.setNTiempoEspera(gcConfTramite.getNTiempoEspera());
                        gcConfTramite2.setNTiempoHolgura(gcConfTramite.getNTiempoHolgura());
                        gcConfTramiteRepository.save(gcConfTramite2);
                        model.addAttribute("mensaje", "Datos guardados correctamente");
                    }else{
                        model.addAttribute("mensajeError", "Duplicado de registro: Existe una configuración para este tramite en "+gcConfTramite.getCUnidadRecep().getCunidadRecep()+"!");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al tratar de guardar los datos!");
        } 
        indexConfTramite(request, null, model);
        return "ctramite/ctramites";
    }
        
    @RequestMapping(value = "{ctramite}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request, 
            @ModelAttribute GcConfTramite gcConfTramite, final ModelMap model,
            @PathVariable(value = "ctramite") Long ctramiteId) {
        GcConfTramite confTramite = gcConfTramiteRepository.findOne(ctramiteId);
        try{
            gcConfTramiteRepository.delete(confTramite);
            gcConfTramiteRepository.delete(ctramiteId);
            model.addAttribute("mensaje", "Se elimino el registro correctamente!");
        }catch(Exception e){
            model.addAttribute("mensajeError", "Ocurrio un Error!"+" Error: "+e.getMessage());
        }
        indexConfTramite( request, null, model);
        return "ctramite/ctramites";
    }
     
   @RequestMapping(value="{ctramite}/verCtramite", method = RequestMethod.GET)
    public String verEquipo(HttpServletRequest request, 
            @ModelAttribute GcConfTramite gcConfTramite, final ModelMap model,
            @PathVariable(value = "ctramite") Long ctramiteId){
            gcConfTramite = gcConfTramiteRepository.findOne(ctramiteId);
            model.addAttribute("ctramite", gcConfTramite);
            return "ctramite/verCtramite";
    }

    public List<TbUnidadRecep> getUnidadesList(){
        List<TbUnidadRecep> unidades = null;
        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.getUnidadesEnServicio();
            List<TbUnidadRecep> combinacionUnidades = gcUnidadRecepRepository.getUnidadesCombinaciones();
            for(TbUnidadRecep unidaddes:combinacionUnidades){
                String nombre=gcUnidadRecepRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
                unidaddes.setDunidadRecep(nombre==null?unidaddes.getCunidadRecep():nombre);
            }
            unidades.addAll(combinacionUnidades);
        }else{
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.getUnidadesEnServicio(getUnidad());
            for(TbUnidadRecep unidaddes:unidades){
                String nombre=gcUnidadRecepRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
                unidaddes.setDunidadRecep(nombre==null?unidaddes.getDunidadRecep():nombre);
            }
        }
        return unidades;
    }

        private String getUnidad() {
            return getUnidadRecep();
        }
}