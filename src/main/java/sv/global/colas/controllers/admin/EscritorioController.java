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
import sv.global.colas.entities.GcEscritorio;
import sv.global.colas.entities.GcZona;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.EscritorioPojo;
import sv.global.colas.repositories.GcEscritorioRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.repositories.GcZonaRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/escritorio")
@SessionAttributes("escritoriopojo")
@EnableAsync
public class EscritorioController  extends MainController {
         
    @Autowired
    private GcZonaRepository zonaRepository;
     
    @Autowired
    private GcUnidadRecepRepository unidadRepository;
    
    @Autowired
    private GcEscritorioRepository gcEscritorioRepository;
    
    @Autowired
    private GcUsuarioRepository gcUsuarioRepository;
    
    Utils utilidad=new Utils();
    @ModelAttribute("escritoriopojo")
    public EscritorioPojo escritotioPojo() {
        EscritorioPojo escritorio = new EscritorioPojo();
        List<GcZona> zonas = null;
        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            zonas = (List<GcZona>) zonaRepository.getAllZonas();
        }else{
            zonas = (List<GcZona>) zonaRepository.getZonasByUnidad(getUnidad());
        }
        escritorio.setUnidadesList(getUnidadesList());
        escritorio.setZonaList(zonas);
        return escritorio;
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexEscritorio(HttpServletRequest request, @ModelAttribute EscritorioPojo escritorioP, final ModelMap model){
        try{
            Iterable<GcEscritorio> escritorioList=null;
            if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))){
                escritorioList = gcEscritorioRepository.allEscritorios();
            }else{
                escritorioList = gcEscritorioRepository.getEscritoriosByUnidad(getUnidad());
            }
            EscritorioPojo escritorioPojo= (EscritorioPojo) model.get("escritoriopojo");
            escritorioPojo.setEscritorioList((List<GcEscritorio>) escritorioList);
            model.addAttribute("escritorioList", escritorioPojo);
            }catch(Exception e){
                e.printStackTrace();
                model.addAttribute("mensajeError", "OCURRIO UN ERROR AL OBTENER EL LISTADO DE ESCRITORIOS");
            }
            return "escritorio/escritorios";
    }
       
    @RequestMapping("/newEscritorio")//Metodo que llama al html
    public String irAdministracionNewZona(HttpServletRequest request, 
            @ModelAttribute EscritorioPojo escritorioP, final ModelMap model){
            try{
                if("ROLE_GC_R1".equals(utilidad.getTipoUsuario(getRoles()))){ 
                    escritorioP.setZonaList((List<GcZona>) zonaRepository.findAll());
                }else{
                    escritorioP.setZonaList(zonaRepository.getZonasByUnidad(getUnidad()));
                }
                escritorioP.setUnidadesList(getUnidadesList());
            }catch(Exception e){
                e.printStackTrace();
                model.addAttribute("mensajeError", "OCURRIO UN ERROR AL OBTENER EL LISTADO DE ESCRITORIOS");
            }
            model.addAttribute("escritoriopojo", escritorioP);
            return "escritorio/newEscritorio";
    }
     
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(HttpServletRequest request, @ModelAttribute EscritorioPojo escritorioP, final ModelMap model) {
        try{
            GcZona gcZona = zonaRepository.findOne(escritorioP.getcZonaId());
            TbUnidadRecep undiadRecep=unidadRepository.findOne(escritorioP.getCunidadRecepId());
            GcEscritorio gcEscritorio = gcEscritorioRepository.getEscritorioByName( escritorioP.getcIdentificador(),
                                                                                    escritorioP.getnNumEscritorio().longValue(),
                                                                                    escritorioP.getCunidadRecepId(),
                                                                                    escritorioP.getcZonaId());
            if(gcEscritorio==null){
                gcEscritorio=new GcEscritorio();
                gcEscritorio.setCIdentificador(escritorioP.getcIdentificador());
                gcEscritorio.setNNumEscritorio(escritorioP.getnNumEscritorio());
                gcEscritorio.setBActiva((int) (escritorioP.getbActiva()==2?0:escritorioP.getbActiva()));
                gcEscritorio.setCUnidadRecep(undiadRecep);
                gcEscritorio.setNZonaId(gcZona);
                gcEscritorio.setDEscritorio(escritorioP.getdEscritorio());
                gcEscritorio.setCUsuarioCrea(getUsuario());
                gcEscritorio.setCUsuarioModi(getUsuario());
                gcEscritorio.setFiVigencia(new Date());
                gcEscritorio.setBFilaEsp((int)0);
                gcEscritorio.setNEscritorioId(gcEscritorio.getNEscritorioId());
                if(gcEscritorio.getNEscritorioId()==null){
                    gcEscritorio.setNEscritorioId(zonaRepository.getTotalId());
                }
                gcEscritorioRepository.save(gcEscritorio);
                model.addAttribute("mensaje", "ESCRITORIO GUARDADO CORRECTAMENTE");
            }else{
                model.addAttribute("mensajeError", "YA EXISTE EL ESCRITORIO "+gcEscritorio.getCIdentificador()+gcEscritorio.getNNumEscritorio()+" EN LA ZONA " +gcEscritorio.getNZonaId().getSNombre());
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", ""+"ERROR: "+e.getMessage());
        }
        indexEscritorio( request, escritorioP, model);
        return "escritorio/escritorios";
    }

     
    @RequestMapping(value = "/getzonas", method = RequestMethod.GET)
    public String getZonasXunidad(HttpServletRequest request, 
            @ModelAttribute EscritorioPojo escritorioPojo, final ModelMap model,
            @RequestParam(value = "cunidadRecepId", required = false) String unidad) {
            escritorioPojo = (EscritorioPojo) model.get("escritoriopojo");
            List<GcZona> zonas=zonaRepository.getZonasByUnidad(unidad);
            escritorioPojo.setZonaList(zonas);
            model.addAttribute("escritoriopojo", escritorioPojo);
            return "escritorio/newEscritorio";
    }
    
    @RequestMapping(value="{escritorio}/editEscritorio", method = RequestMethod.GET)
    public String editEscritorio(HttpServletRequest request, 
            @ModelAttribute EscritorioPojo escritorioP, final ModelMap model,
            @PathVariable(value = "escritorio") Long escritorioId){
            try{
                GcEscritorio escritorio = gcEscritorioRepository.findOne(escritorioId);
                EscritorioPojo escritorioPojo=(EscritorioPojo)  model.get("escritoriopojo");
                escritorioPojo.setEscritorio(escritorio);
                if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))){ 
                    escritorioPojo.setZonaList(zonaRepository.getZonasByUnidad(escritorio.getCUnidadRecep().getCunidadRecep()));
                }else{
                    escritorioPojo.setZonaList(zonaRepository.getZonasByUnidad(escritorio.getCUnidadRecep().getCunidadRecep()));
                }
                escritorioPojo.setUnidadesList(getUnidadesList());
                escritorioPojo.setcZonaId(escritorio.getNZonaId().getNZonaId());
                escritorioPojo.setdZona(escritorio.getNZonaId().getDZona());
                escritorioPojo.setCunidadRecepId(escritorio.getCUnidadRecep().getCunidadRecep());
                escritorioPojo.setDunidadRecep(escritorio.getCUnidadRecep().getDunidadRecep());
                escritorioPojo.setcEscritorio(escritorio.getNNumEscritorio().intValue());
                escritorioPojo.setnNumEscritorio(escritorio.getNNumEscritorio());
                escritorioPojo.setnEscritorioId(escritorio.getNEscritorioId());
                model.addAttribute("escritoriopojo", escritorioPojo);
            }catch(Exception e){
                e.printStackTrace();
            }
            return "escritorio/editEscritorio";
    }
      
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute EscritorioPojo escritoriopojo, final ModelMap model) {
        try{
            TbUnidadRecep tbUnidadRecep =unidadRepository.findOne(escritoriopojo.getCunidadRecepId());
            GcZona gcZona = zonaRepository.findOne(escritoriopojo.getcZonaId());
            GcEscritorio gcEscritorio = gcEscritorioRepository.getEscritorioByNameEdit(escritoriopojo.getcIdentificador(),
                                                                                       escritoriopojo.getnNumEscritorio(),
                                                                                       tbUnidadRecep,
                                                                                       gcZona,escritoriopojo.getnEscritorioId()==null?0:escritoriopojo.getnEscritorioId()
            );

            if(gcEscritorio==null){
                String usuario = getUsuario();
                //gcEscritorio= gcEscritorioRepository.findOne(escritoriopojo.getnEscritorioId());
                gcEscritorio = new GcEscritorio();
                gcEscritorio.setCIdentificador(escritoriopojo.getcIdentificador());
                gcEscritorio.setNNumEscritorio(escritoriopojo.getnNumEscritorio());
                gcEscritorio.setBActiva((int) (escritoriopojo.getbActiva()==2?0:escritoriopojo.getbActiva()));
                gcEscritorio.setCUnidadRecep( tbUnidadRecep);
                gcEscritorio.setNZonaId(zonaRepository.findOne(escritoriopojo.getcZonaId()));
                gcEscritorio.setCUsuarioModi(usuario);
                gcEscritorio.setFiVigencia(new Date());
                gcEscritorio.setFModifica(new Date());
                gcEscritorio.setCUsuarioCrea(usuario);
                gcEscritorio.setDEscritorio(escritoriopojo.getdEscritorio());
                if (gcEscritorio.getNEscritorioId()==null)
                    gcEscritorio.setNEscritorioId(0L);
                gcEscritorioRepository.save(gcEscritorio);
                model.addAttribute("mensaje", "ESCRITORIO GUARDADO CORRECTAMENTE");
            }else{ 
                model.addAttribute("mensajeError", "YA EXISTE EL ESCRITORIO "+gcEscritorio.getNNumEscritorio()+gcEscritorio.getCIdentificador()+" EN LA ZONA " +gcEscritorio.getNZonaId().getSNombre());
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error: "+e.getMessage());
        }
      
        indexEscritorio( request, escritoriopojo, model);
        return "escritorio/escritorios";
    }
    
    @RequestMapping(value = "{nEscritorioId}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request, 
            @ModelAttribute EscritorioPojo escritorioPojo, final ModelMap model,
            @PathVariable(value = "nEscritorioId") Long nEscritorioId) {
        try{
            GcEscritorio gcEscritorio = gcEscritorioRepository.findOne(nEscritorioId);
            if(gcUsuarioRepository.getUsersEscritorio(gcEscritorio.getNEscritorioId())==0){
                gcEscritorio.setBActiva((short)3);
                gcEscritorio.setCUsuarioModi(getUsuario());
                gcEscritorio.setFfVigencia(new Date());
                gcEscritorioRepository.save(gcEscritorio);
                model.addAttribute("mensaje", "SE ELIMINO EL REGISTRO CORRECTAMENTE");
            }else{
                model.addAttribute("mensajeError", "ESCRITORIO ESTA ASIGNADO A UN USUARIO");
            }
        }catch(Exception e){
            model.addAttribute("mensajeError", "ERROR: "+e.getMessage());
        }
        indexEscritorio( request, escritorioPojo, model);
        return "escritorio/escritorios";
    }
       
    
    @RequestMapping(value="{escritorio}/verEscritorio", method = RequestMethod.GET)
    public String verEscritorio(HttpServletRequest request, 
            @ModelAttribute EscritorioPojo escritorioP, final ModelMap model,
            @PathVariable(value = "escritorio") Long escritorioId){
        EscritorioPojo escritorioPojo=(EscritorioPojo)  model.get("escritoriopojo");
        try{
            GcEscritorio escritorio = gcEscritorioRepository.findOne(escritorioId);
            escritorioPojo.setEscritorio(escritorio);
            List<TbUnidadRecep> unidades = (List<TbUnidadRecep>) unidadRepository.getUnidadesEnServicio();
            List<GcZona> zonas = zonaRepository.getZonasByUnidad(escritorio.getCUnidadRecep().getCunidadRecep());
            escritorioPojo.setUnidadesList(unidades);
            escritorioPojo.setZonaList(zonas);
            escritorioPojo.setcZonaId(escritorio.getNZonaId().getNZonaId());
            escritorioPojo.setdZona(escritorio.getNZonaId().getDZona());
            escritorioPojo.setCunidadRecepId(escritorio.getCUnidadRecep().getCunidadRecep());
            escritorioPojo.setDunidadRecep(escritorio.getCUnidadRecep().getDunidadRecep());
            escritorioPojo.setcEscritorio(escritorio.getNNumEscritorio().intValue());
            escritorioPojo.setnEscritorioId(escritorio.getNEscritorioId());
        }catch(Exception e){
            model.addAttribute("mensajeError", "ERROR: "+e.getMessage());
        }
        model.addAttribute("escritoriopojo", escritorioPojo);
        return "escritorio/verEscritorio";
    }
    
    public List<TbUnidadRecep> getUnidadesList(){
        List<TbUnidadRecep> unidades = null;
        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            unidades = (List<TbUnidadRecep>) unidadRepository.getUnidadesEnServicio();
            List<TbUnidadRecep> combinacionUnidades = unidadRepository.getUnidadesCombinaciones();
            for(TbUnidadRecep unidaddes:combinacionUnidades){
                String nombre=unidadRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
                unidaddes.setDunidadRecep(nombre==null?unidaddes.getCunidadRecep():nombre);
            }
            unidades.addAll(combinacionUnidades);
        }else{
            unidades = (List<TbUnidadRecep>) unidadRepository.getUnidadesEnServicio(getUnidad());
            for(TbUnidadRecep unidaddes:unidades){
                String nombre=unidadRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
                unidaddes.setDunidadRecep(nombre==null?unidaddes.getDunidadRecep():nombre);
            }
        }
        return unidades;
    }

        private String getUnidad() {
            return getUnidadRecep();
        }
}
