/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.reportes;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.MonitoreoSeccionPojo;
import sv.global.colas.pojos.entities.MonitoreoTramitePojo;
import sv.global.colas.pojos.entities.ServicioPojo;
import sv.global.colas.repositories.GcServicioRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcZonaRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author LiliHaydeé
 */
@Controller
@RequestMapping("/0710")
@SessionAttributes("monitoreoTramitePojo serviciopojo")
@EnableAsync
public class reporteTiqueEmiAteNoAte extends MainController{
    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;
	 
    @Autowired
    private GcZonaRepository gcZonaRepository;

    @Autowired
    private GcTramiteRepository gcTramiteRepository;
    
    @Autowired
    private GcServicioRepository servicioRepository;
    
    Utils utilidad = new Utils();
    
    @ModelAttribute("monitoreoTramitePojo")
        public MonitoreoTramitePojo monitoreoTramite() {
            MonitoreoTramitePojo monitoreoTramitePojo = new MonitoreoTramitePojo();
            return monitoreoTramitePojo;
        }
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexMonitoreo(HttpServletRequest request,
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo,@ModelAttribute MonitoreoSeccionPojo monitoreoSeccionPojo,@ModelAttribute ServicioPojo servicioP, final ModelMap model) {
            Iterable<GcTramite> tramiteList = gcTramiteRepository.findAll();
            monitoreoTramitePojo.setTramiteList((List<GcTramite>) tramiteList);  
            monitoreoTramitePojo.setUnidadesList(getUnidadesList());
            model.addAttribute("monitoreoTramitePojo", monitoreoTramitePojo);
            monitoreoTramitePojo.setUnidadRecep(getUnidad());
            model.addAttribute("unidadesList", getUnidadesList());
            
            
            Iterable<GcServicios> serviciosList = servicioRepository.getAllSecciones();
            ServicioPojo serviciopojo = (ServicioPojo) model.get("serviciopojo");
            if(serviciopojo==null) serviciopojo = new ServicioPojo();
            serviciopojo.setGcServiciosList((List<GcServicios>) serviciosList);
            model.addAttribute("serviciopojo", serviciopojo);
        return "/reportes/0710";
    }
    
    
    public List<TbUnidadRecep> getUnidadesList(){
        List<TbUnidadRecep> unidades = null;
        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
        //if("ROLE_GC_R1".equals("ROLE_GC_R1")) {
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.getUnidadesEnServicio();
            List<TbUnidadRecep> combinacionUnidades = gcUnidadRecepRepository.getUnidadesCombinaciones();
            for(TbUnidadRecep unidaddes:combinacionUnidades){
                String nombre=gcUnidadRecepRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());//
                //String nombre=unidaddes.getCunidadRecep();
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
        return getUnidadRecep();
    }
}
