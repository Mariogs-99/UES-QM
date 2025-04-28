/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.monitoreo;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.MonitoreoSeccionPojo;
import sv.global.colas.pojos.entities.MonitoreoSeccionTramites;
import sv.global.colas.pojos.entities.MonitoreoSeccionUsuarios;
import sv.global.colas.repositories.GcServiciosRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/seccionm")
@EnableAsync
public class monitoreoSeccController  extends MainController {
    
	@Autowired
	GcServiciosRepository gcServiciosRepository;
	@Autowired
	GcTramiteRepository gcTramiteRepository;
        @Autowired
        GcUsuarioRepository gcUsuarioRepository;
        @Autowired
        GcUnidadRecepRepository unidadRepository;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexMonitoreo(HttpServletRequest request, @ModelAttribute MonitoreoSeccionPojo monitoreoSeccionPojo, final ModelMap model) {
        List<GcServicios> servicios =gcServiciosRepository.getAllServicios();
        model.addAttribute("seccionesList", servicios);
        model.addAttribute("monitoreoSeccionPojo",new MonitoreoSeccionPojo());
        try{
            String nombre="";
            TbUnidadRecep unidad=unidadRepository.findOne(getUnidad());
            nombre=unidadRepository.getNombreUnidadCombinacion(unidad.getCunidadRecep());
            nombre=nombre==null?unidad.getDunidadRecep():nombre;
            model.addAttribute("unidadRecep",nombre);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "/monitoreo/mosecciones";
    }
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET,  headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public MonitoreoSeccionPojo getMonitoreoJson(HttpServletRequest request, @RequestParam(value = "seccion", required = false) String seccion){
        MonitoreoSeccionPojo monitoreoSeccionPojo =new MonitoreoSeccionPojo();
        try{
            Long seccionId=Long.parseLong(seccion);
            GcServicios gcServicios=gcServiciosRepository.findOne(seccionId);
            monitoreoSeccionPojo.setSeccionId(gcServicios.getNServiciosId());
            monitoreoSeccionPojo.setScNombre(gcServicios.getSNombre());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("SECCION:"+seccionId);
            System.out.println("UNIDAD:"+getUnidad());
            List datos=gcServiciosRepository.getMonitoreoServicio(seccionId, getUnidad() );
            for(int i=0;i<datos.size();i++){
                Object[] obj=null;
                obj=(Object[]) datos.get(i);
                monitoreoSeccionPojo.setMaximo((String)obj[0]);
                monitoreoSeccionPojo.setMinimo((String)obj[1]);
                monitoreoSeccionPojo.setPromedio((String)obj[2]);
                monitoreoSeccionPojo.setModa(obj[1].toString());
                monitoreoSeccionPojo.setEsperando(Long.parseLong(obj[3]+""));
                monitoreoSeccionPojo.setProcesados(Long.parseLong(obj[4]+""));
            }
            List tramites=gcTramiteRepository.getTramitesBySeccion(seccionId, getUnidad());
            List<MonitoreoSeccionTramites> tramits=new ArrayList<MonitoreoSeccionTramites>();
            for(int i=0;i<tramites.size();i++){
                MonitoreoSeccionTramites tramite=new MonitoreoSeccionTramites();
                Object[] obj=null;
                obj=(Object[]) tramites.get(i);
                tramite.setTramiteId(Long.parseLong(obj[0]+""));
                tramite.setTramite((String)obj[1]);
                tramite.setEsperando(Long.parseLong(obj[2]+""));
                tramite.setProcesados(Long.parseLong(obj[3]+""));
                List serie=gcTramiteRepository.getSeriesTramiteEsperando(tramite.getTramiteId(), getUnidad());
                if(serie!=null){
                    Object[] obj2=null;
                    obj2=(Object[]) serie.get(0);
                    tramite.setHora(Long.parseLong(obj2[0]+""));
                    tramite.setMin45(Long.parseLong(obj2[1]+""));
                    tramite.setMin30(Long.parseLong(obj2[2]+""));
                    tramite.setMin15(Long.parseLong(obj2[3]+""));
                    tramite.setActual(Long.parseLong(obj2[4]+""));
                }else{
                    tramite.setHora(Long.MIN_VALUE);
                    tramite.setMin45(Long.MIN_VALUE);
                    tramite.setMin30(Long.MIN_VALUE);
                    tramite.setMin15(Long.MIN_VALUE);
                    tramite.setActual(Long.MIN_VALUE);
                }
                tramits.add(tramite);
            }
            monitoreoSeccionPojo.setTramites(tramits);
            List usuarios=gcUsuarioRepository.getUsuariosBySeccion(seccionId,getUnidad());
            List<MonitoreoSeccionUsuarios> users=new ArrayList<MonitoreoSeccionUsuarios>();
            for(int i=0;i<usuarios.size();i++){
                MonitoreoSeccionUsuarios usuario=new MonitoreoSeccionUsuarios();
                Object[] obj=null;
                obj=(Object[]) usuarios.get(i);
                usuario.setEscritorioId(Long.parseLong(obj[0]+""));
                usuario.setUsuario((String)obj[1]);
                usuario.setEstado((String)obj[2]);
                users.add(usuario);
            }
            monitoreoSeccionPojo.setUsuarios(users);
        }catch(Exception e){
            e.printStackTrace();
        }
        return monitoreoSeccionPojo;
    }

        private String getUnidad() {
            return getUnidadRecep();
        }
    
    public String reemplazar(String cadena){
        cadena=cadena.replaceAll("á", "&aacute;").
                replaceAll("é", "&eacute;").
                replaceAll("ó", "&oacute;").
                replaceAll("í", "&icute;").
                replaceAll("ú", "&uacute;").
                replaceAll("ñ", "&ntilde;").
                replaceAll("Ñ", "&Ntilde;").
                replaceAll("Á", "&Aacute;").
                replaceAll("É", "&Eacute;").
                replaceAll("Í", "&Iacute;").
                replaceAll("Ó", "&Oacute;").
                replaceAll("Ú", "&Uacute;");
        return cadena;
    }

}
