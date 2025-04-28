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
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.ReasignacionPojo;
import sv.global.colas.repositories.GcConfTramiteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/creasignacion")
@SessionAttributes("gcreasignacion")
@EnableAsync
public class reasignacionController extends MainController {

    @Autowired
    private GcConfTramiteRepository gcConfTramiteRepository;
    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;
    @Autowired
    private GcTramiteRepository gcTramiteRepository;

    Utils utilidad = new Utils();

    @ModelAttribute("gcreasignacion")
    public ReasignacionPojo gcreasignacion() {
        ReasignacionPojo reasignacionPojo = new ReasignacionPojo();
        reasignacionPojo.setTramitesList((List<GcTramite>) gcTramiteRepository.findAll());
        //reasignacionPojo.setUnidadesList(getUnidadesList());
        return reasignacionPojo;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexConfReasignacion(HttpServletRequest request, @ModelAttribute ReasignacionPojo reasignacionPojo, final ModelMap model) {
        Iterable<GcConfTramite> creasignacionList = null;
        if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            creasignacionList = gcConfTramiteRepository.listaConfiguracionesGeneral();
        } else {
            creasignacionList = gcConfTramiteRepository.listaConfiguracionesLocal(getUnidad());
        }
        model.addAttribute("gccreasignacionList", creasignacionList);
        return "creasignacion/creasignaciones";
    }

    @RequestMapping("/newCreasignacion")
    public String newCreasignacion(HttpServletRequest request,
            @ModelAttribute ReasignacionPojo reasignacionPojo, final ModelMap model) {
        reasignacionPojo = (ReasignacionPojo) model.get("gcreasignacion");
        reasignacionPojo.setGcConfTramite(new GcConfTramite());
        reasignacionPojo.setTramitesList((List<GcTramite>) gcTramiteRepository.findAll());
        model.addAttribute("gcreasignacion", reasignacionPojo);
        model.addAttribute("ntramite", 0);
        model.addAttribute("comportamiento", 0);
        model.addAttribute("gcUnidadesList",getUnidadesList());   
        return "creasignacion/newCreasignacion";
    }

    @RequestMapping(value = "{creasignacion}/editCreasignacion", method = RequestMethod.GET)
    public String editreasignacion(HttpServletRequest request,
            @ModelAttribute ReasignacionPojo reasignacionPojo, final ModelMap model,
            @PathVariable(value = "creasignacion") Long creasignacionId) {
        reasignacionPojo = (ReasignacionPojo) model.get("gcreasignacion");
        reasignacionPojo.setGcConfTramite(gcConfTramiteRepository.findOne(creasignacionId));
        model.addAttribute("gcreasignacion", reasignacionPojo);
        model.addAttribute("unidadesSelect",reasignacionPojo.getGcConfTramite().getCUnidadRecep().getCunidadRecep());  
        model.addAttribute("ntramite", reasignacionPojo.getGcConfTramite().getNTramiteId().getNTramiteId());
        model.addAttribute("gcUnidadesList",getUnidadesList());   
        model.addAttribute("comportamiento", reasignacionPojo.getGcConfTramite().getnComportamiento()==0?4:reasignacionPojo.getGcConfTramite().getnComportamiento());
        return "creasignacion/newCreasignacion";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardarReasignacion(HttpServletRequest request, @ModelAttribute ReasignacionPojo reasignacionPojo, final ModelMap model) {
        try {
            Long comportamiento = reasignacionPojo.getGcConfTramite().getnComportamiento();
            reasignacionPojo.getGcConfTramite().setnComportamiento(comportamiento == 4 ? 0 : comportamiento);
            if (reasignacionPojo.getGeneral() == 0) {
                List<GcTramite> listaTramites = (List<GcTramite>) gcTramiteRepository.findAll();
                for (GcTramite tramite : listaTramites) {
                    GcConfTramite confTramite = gcConfTramiteRepository.existe(reasignacionPojo.getUnidadRecep(), tramite.getNTramiteId());
                    if (confTramite != null) {
                        confTramite.setFModifica(new Date());
                        confTramite.setCUsuarioModi(getUsuario());
                        confTramite.setnComportamiento(reasignacionPojo.getGcConfTramite().getnComportamiento());
                        confTramite.setnPeso(reasignacionPojo.getGcConfTramite().getnPeso());
                        gcConfTramiteRepository.save(confTramite);
                    } else {
                        confTramite = new GcConfTramite();
                        confTramite.setCUsuarioCrea(getUsuario());
                        confTramite.setCUsuarioModi(getUsuario());
                        confTramite.setFModifica(new Date());
                        confTramite.setFfVigencia(new Date());
                        confTramite.setFiVigencia(new Date());
                        confTramite.setnComportamiento(reasignacionPojo.getGcConfTramite().getnComportamiento());
                        confTramite.setNTramiteId(tramite);
                        confTramite.setCUnidadRecep(gcUnidadRecepRepository.findOne(reasignacionPojo.getUnidadRecep()));
                        confTramite.setnPeso(reasignacionPojo.getGcConfTramite().getnPeso());
                        confTramite.setNAtencionProm(BigInteger.ZERO);
                        
                        gcConfTramiteRepository.save(confTramite);
                    }
                }
                model.addAttribute("mensaje", "DATOS GUARDADOS CORRECTAMENTE");
            } else {
                if (reasignacionPojo.getGcConfTramite().getNConfTraId() != null) {
                    GcConfTramite confTramite = gcConfTramiteRepository.findOne(reasignacionPojo.getGcConfTramite().getNConfTraId());
                    confTramite.setCUsuarioModi(getUsuario());
                    confTramite.setFModifica(new Date());
                    confTramite.setnComportamiento(reasignacionPojo.getGcConfTramite().getnComportamiento());
                    confTramite.setnPeso(reasignacionPojo.getGcConfTramite().getnPeso());
                    gcConfTramiteRepository.save(confTramite);
                    model.addAttribute("mensaje", "DATOS GUARDADOS CORRECTAMENTE");
                } else {
                    GcConfTramite confTramite = new GcConfTramite();
                    confTramite.setCUsuarioCrea(getUsuario());
                    confTramite.setCUsuarioModi(getUsuario());
                    confTramite.setFModifica(new Date());
                    confTramite.setFfVigencia(new Date());
                    confTramite.setFiVigencia(new Date());
                    confTramite.setNAtencionProm(BigInteger.ZERO);
                    confTramite.setNTramiteId(reasignacionPojo.getGcConfTramite().getNTramiteId());
                    confTramite.setCUnidadRecep(gcUnidadRecepRepository.findOne(reasignacionPojo.getUnidadRecep()));
                    confTramite.setnComportamiento(reasignacionPojo.getGcConfTramite().getnComportamiento());
                    confTramite.setnPeso(reasignacionPojo.getGcConfTramite().getnPeso());
                    confTramite.getNConfTraId();
                        if(confTramite.getNConfTraId()==null){
                            confTramite.setNConfTraId(gcConfTramiteRepository.getTotalId());
                        }
                    if (gcConfTramiteRepository.existe(reasignacionPojo.getUnidadRecep(), confTramite.getNTramiteId().getNTramiteId()) != null) {
                        model.addAttribute("mensajeError", "ERROR: YA EXISTE UNA CONFIGURACION DE REASIGNACION");
                    } else {
                        gcConfTramiteRepository.save(confTramite);
                        model.addAttribute("mensaje", "DATOS GUARDADOS CORRECTAMENTE");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "ERROR: "+e.getMessage());
        }
        indexConfReasignacion(request, null, model);
        return "creasignacion/creasignaciones";
    }

    @RequestMapping(value = "{creasignacion}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request,
            @ModelAttribute ReasignacionPojo reasignacionPojo, final ModelMap model,
            @PathVariable(value = "creasignacion") Long creasignacionId) {
        GcConfTramite confTramite = gcConfTramiteRepository.findOne(creasignacionId);
        try {
            confTramite.setnComportamiento(null);
            confTramite.setnPeso(null);
            confTramite.setCUsuarioModi(getUsuario());
            confTramite.setFModifica(new Date());
            gcConfTramiteRepository.save(confTramite);
            model.addAttribute("mensaje", "REGISTRO ELIMINADO CORRECTAMENTE");
        } catch (Exception e) {
            model.addAttribute("mensajeError", "ERROR: " + e.getMessage());
        }
        indexConfReasignacion(request, null, model);
        return "creasignacion/creasignaciones";
    }

    @RequestMapping(value = "{creasignacion}/verCreasignacion", method = RequestMethod.GET)
    public String verCreasignacionList(HttpServletRequest request,
            @ModelAttribute ReasignacionPojo reasignacionPojo, final ModelMap model,
            @PathVariable(value = "creasignacion") Long creasignacionId) {
        reasignacionPojo.setGcConfTramite(gcConfTramiteRepository.findOne(creasignacionId));
        model.addAttribute("gcreasignacion", reasignacionPojo);
        return "creasignacion/verCreasignacion";
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
