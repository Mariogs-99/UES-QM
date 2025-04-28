/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.reportes;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.MonitoreoSeccionPojo;
import sv.global.colas.pojos.entities.MonitoreoTramitePojo;
import sv.global.colas.pojos.entities.ServicioPojo;
import sv.global.colas.pojos.entities.UsuarioPojo;
import sv.global.colas.repositories.GcAdminRepositoryImpl;
import sv.global.colas.repositories.GcServicioRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.repositories.GcZonaRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author CésarStanley
 */
@Controller
@RequestMapping("/0715")
@SessionAttributes("monitoreoTramitePojo serviciopojo UsuarioPojo")
@EnableAsync
public class reporteTiempoAten extends MainController{
    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;
	 
    @Autowired
    private GcZonaRepository gcZonaRepository;

    @Autowired
    private GcTramiteRepository gcTramiteRepository;
    
    @Autowired
    private GcServicioRepository servicioRepository;
    
    @Autowired
    private GcUsuarioRepository gcUsuarioRepository;
    
    Utils utilidad = new Utils();
    
    @Autowired
    private GcAdminRepositoryImpl adminRepository;
    
    @ModelAttribute("monitoreoTramitePojo")
        public MonitoreoTramitePojo monitoreoTramite() {
            MonitoreoTramitePojo monitoreoTramitePojo = new MonitoreoTramitePojo();
            return monitoreoTramitePojo;
        }
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexMonitoreo(HttpServletRequest request,
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo,@ModelAttribute UsuarioPojo usuariopojo,@ModelAttribute MonitoreoSeccionPojo monitoreoSeccionPojo,@ModelAttribute ServicioPojo servicioP, final ModelMap model) {
            Iterable<GcTramite> tramiteList = gcTramiteRepository.findAll();
            //monitoreoTramitePojo.setTramiteList((List<GcTramite>) tramiteList);  
            model.addAttribute("tramitesList", tramiteList);
            monitoreoTramitePojo.setUnidadesList(getUnidadesList());
            model.addAttribute("monitoreoTramitePojo", monitoreoTramitePojo);
            monitoreoTramitePojo.setUnidadRecep(getUnidad());
            model.addAttribute("unidadesList", getUnidadesList());
            UsuarioPojo Usuariopojo = new UsuarioPojo();
            Iterable<GcUsuario> usuarioList = adminRepository.getAllUser();
            Usuariopojo.setUsuarioList((List<GcUsuario>) usuarioList);
            model.addAttribute("usuariopojo", Usuariopojo);
            
            Iterable<GcServicios> serviciosList = servicioRepository.getAllSecciones();
            ServicioPojo serviciopojo = (ServicioPojo) model.get("serviciopojo");
            if(serviciopojo==null) serviciopojo = new ServicioPojo();
            serviciopojo.setGcServiciosList((List<GcServicios>) serviciosList);
            model.addAttribute("serviciopojo", serviciopojo);
        return "/reportes/0715";
    }
    
    
    @RequestMapping(value = "/getTramites", method = RequestMethod.GET)
    public String getTramitesXSeccion(HttpServletRequest request, 
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model,
            @RequestParam(value = "servicio", required = false) String servicio) {
            Iterable<GcTramite> tramiteList = null;
            if(!servicio.trim().equals("0")){
                tramiteList = (Iterable<GcTramite>) gcTramiteRepository.getTramitesByService(Long.parseLong(servicio));
            }else{
                tramiteList = (Iterable<GcTramite>) gcTramiteRepository.getAllTramites();
            } 
            model.addAttribute("tramitesList", tramiteList);
            model.addAttribute("serviciopojo", new ServicioPojo());
            model.addAttribute("usuariopojo", new UsuarioPojo());
            return "/reportes/0715";
    }
    
    
    @RequestMapping(value = "/getUsuarios", method = RequestMethod.GET)
    public String getUsuariosXSeccion(HttpServletRequest request, 
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model,
            @RequestParam(value = "servicio", required = false) Long servicio,
            @RequestParam(value = "unidad", required = false) String unidad) {
            UsuarioPojo Usuariopojo = new UsuarioPojo();
            List usuarioList = gcUsuarioRepository.getUsuariosBySeccion(servicio, unidad);
            Usuariopojo.setUsuarioList(setListToObjectUser(usuarioList));
            model.addAttribute("usuariopojo", Usuariopojo);
            model.addAttribute("tramitesList", new GcTramite());
            model.addAttribute("serviciopojo", new ServicioPojo());
            return "/reportes/0715";
    }
    
    
    
    public List<GcUsuario> setListToObjectUser(List userList){
        List<GcUsuario> users = null;
        try{
            users = new ArrayList<GcUsuario>();
            for(int i=0;i<userList.size();i++){
                Object[] uList = (Object[]) userList.get(i);
                GcUsuario u = new GcUsuario();
                for(int j=0;j<uList.length;j++){       
                    u.setCUsuario(uList[1].toString());
                }
                users.add(u);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return users;
    }
    
    
    public List<TbUnidadRecep> getUnidadesList(){
        List<TbUnidadRecep> unidades = null;
        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
        //if("ROLE_GC_R1".equals("ROLE_GC_R1")) {
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.getUnidadesEnServicio();
            List<TbUnidadRecep> combinacionUnidades = gcUnidadRecepRepository.getUnidadesCombinaciones();
            for(TbUnidadRecep unidaddes:combinacionUnidades){
                String nombre=gcUnidadRecepRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
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
