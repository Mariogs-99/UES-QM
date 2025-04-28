/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfLlamado;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.CllamadoPojo;
import sv.global.colas.repositories.GcConfLlamadoRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/cllamado")
@SessionAttributes("gcconfllamado")
@EnableAsync
public class ConfLlamadoController extends MainController {
    
    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;
    @Autowired
    private GcConfLlamadoRepository gcConfLlamadoRepository;
    Utils utilidad=new Utils();
             
    @ModelAttribute("gcconfllamado")
    public CllamadoPojo inicial(){
        return new CllamadoPojo();
    }
        
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexConfLlamado(HttpServletRequest request, @ModelAttribute CllamadoPojo cllamadoPojo, final ModelMap model){
        List<GcConfLlamado> GcConfTramiteList;
        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))){
            GcConfTramiteList = gcConfLlamadoRepository.getAllConf();
        }else{
            GcConfTramiteList = gcConfLlamadoRepository.getConfsByCS(getUnidad());
        }
        model.addAttribute("cllamadoList", GcConfTramiteList);
        return "cllamado/cllamados";
    }
    
    @RequestMapping("/newCllamado")
    public String newCllamado(HttpServletRequest request, 
            @ModelAttribute CllamadoPojo cllamadoPojo, final ModelMap model){
            model.addAttribute("gcUnidadesList",getUnidadesList());
            model.addAttribute("unidadRecep",getUnidad());  
            model.addAttribute("gcconfllamado",cllamadoPojo);  
            return "cllamado/newCllamado";
    } 
    
    @RequestMapping(value="{cllamdo}/editCllamado", method = RequestMethod.GET)
    public String editCllamado(HttpServletRequest request, 
            @ModelAttribute CllamadoPojo cllamadoPojo, final ModelMap model,
            @PathVariable(value = "cllamdo") Long cllamdoId){
            model.addAttribute("gcUnidadesList",getUnidadesList());
            GcConfLlamado gcConfLlamado=gcConfLlamadoRepository.findOne(cllamdoId);
            cllamadoPojo.setbActiva(gcConfLlamado.getBActiva());
            cllamadoPojo.setUnidadRecep(gcConfLlamado.getCUnidadRecep().getCunidadRecep());
            cllamadoPojo.setnConfllamadoId(gcConfLlamado.getNConfllamadoId());
            cllamadoPojo.setnIntervaloLlamada(gcConfLlamado.getNIntervaloLlamada());
            cllamadoPojo.setnNumLlamadas(gcConfLlamado.getNNumLlamadas());
            cllamadoPojo.setsDescripcion(gcConfLlamado.getSDescripcion());
            cllamadoPojo.setsMensaje(gcConfLlamado.getSMensaje());
            model.addAttribute("gcconfllamado",cllamadoPojo);  
            return "cllamado/newCllamado";
    }
    
    @RequestMapping(value="{cllamdo}/verCllamado", method = RequestMethod.GET)
    public String verCllamado(HttpServletRequest request, 
            @ModelAttribute CllamadoPojo cllamadoPojo, final ModelMap model,
            @PathVariable(value = "cllamdo") Long cllamdoId){
            //String unidad=getPrincipal().getUnidadRecep();getUnidad()
            String unidad=getUnidad();
            if(unidad==null)unidad=getPrincipal().getUbicacionFisica();
            GcConfLlamado gcConfLlamado=gcConfLlamadoRepository.findOne(cllamdoId);
            cllamadoPojo.setbActiva(gcConfLlamado.getBActiva());
            cllamadoPojo.setUnidadRecep(gcConfLlamado.getCUnidadRecep().getCunidadRecep());
            cllamadoPojo.setnConfllamadoId(gcConfLlamado.getNConfllamadoId());
            cllamadoPojo.setcUnidadRecep(gcConfLlamado.getCUnidadRecep());
            cllamadoPojo.setnIntervaloLlamada(gcConfLlamado.getNIntervaloLlamada());
            cllamadoPojo.setnNumLlamadas(gcConfLlamado.getNNumLlamadas());
            cllamadoPojo.setsDescripcion(gcConfLlamado.getSDescripcion());
            cllamadoPojo.setsMensaje(gcConfLlamado.getSMensaje());
            model.addAttribute("gcconfllamado",cllamadoPojo);  
            return "cllamado/verCllamado";
    }
            
    @RequestMapping(value = "{cllamaod}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request, 
            @ModelAttribute CllamadoPojo cllamadoPojo, final ModelMap model,
            @PathVariable(value = "cllamaod") Long cllamadoId) {
        GcConfLlamado cllamado = gcConfLlamadoRepository.findOne(cllamadoId);
        try{
            gcConfLlamadoRepository.delete(cllamado);
            model.addAttribute("mensaje", "SE ELIMINO EL REGISTRO CORRECTAMENTE!");
        }catch(Exception e){
            model.addAttribute("mensajeError", "ERROR: "+e.getMessage());
        }
        indexConfLlamado( request, null, model);
        return "cllamado/cllamados";
    }
        
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute CllamadoPojo cllamadoPojo, final ModelMap model) {
        try{
            if(cllamadoPojo.getnConfllamadoId()!=null){
                GcConfLlamado cLlamado=gcConfLlamadoRepository.findOne(cllamadoPojo.getnConfllamadoId());
                cLlamado.setCUsuarioModi(getUsuario());
                cLlamado.setFModifica(new Date());
                cLlamado.setNIntervaloLlamada(cllamadoPojo.getnIntervaloLlamada());
                cLlamado.setNNumLlamadas(cllamadoPojo.getnNumLlamadas());
                cLlamado.setSMensaje(cllamadoPojo.getsMensaje());
                cLlamado.setSDescripcion(cllamadoPojo.getsDescripcion());
                gcConfLlamadoRepository.save(cLlamado);
                model.addAttribute("mensaje", "DATOS GUARDADOS CORRECTAMENTE");
            }else{
                GcConfLlamado cLlamado=new GcConfLlamado();
                cLlamado.setCUsuarioCrea(getUsuario());
                cLlamado.setFfVigencia(new Date());
                cLlamado.setFiVigencia(new Date());
                cLlamado.setCUsuarioModi(getUsuario());
                cLlamado.setBActiva((short)1);
                cLlamado.setFModifica(new Date());
                cLlamado.setNIntervaloLlamada(cllamadoPojo.getnIntervaloLlamada());
                cLlamado.setNNumLlamadas(cllamadoPojo.getnNumLlamadas());
                cLlamado.setSDescripcion(cllamadoPojo.getsDescripcion());
                cLlamado.setSMensaje(cllamadoPojo.getsMensaje());
                cLlamado.setCUnidadRecep(gcUnidadRecepRepository.findOne(cllamadoPojo.getUnidadRecep()));
                if(gcConfLlamadoRepository.getCounByCS(cLlamado.getCUnidadRecep().getCunidadRecep())==0){
                    cLlamado.setNConfllamadoId(cLlamado.getNConfllamadoId());
                        if(cLlamado.getNConfllamadoId()==null){
                            cLlamado.setNConfllamadoId(gcConfLlamadoRepository.getTotalId());
                        }
                    gcConfLlamadoRepository.save(cLlamado);
                    model.addAttribute("mensaje", "DATOS GUARDADOS CORRECTAMENTE");
                }else{
                    model.addAttribute("mensajeError", "DUPLICADO DE REGISTRO YA EXISTE UNA CONFIGURACION PARA EL CENTRO DE SERVICIO");
                }
            }
        }catch(Exception e){
            model.addAttribute("mensajeError", "ERROR: "+e.getMessage());
        }
        indexConfLlamado(request,null,model);
        return "cllamado/cllamados";
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

    public String getUnidad() {
       //String unidad = getPrincipal().getUnidadRecep();
        String unidad = "80048";
        if (unidad == null) {
            unidad = getPrincipal().getUbicacionFisica();
        }
        return unidad;
    }
    
}
