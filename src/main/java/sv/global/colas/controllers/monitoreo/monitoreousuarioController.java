/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.monitoreo;

import com.google.gson.Gson;
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
import sv.global.colas.entities.GcEscritorio;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.MonitoreoUsuarioPojo;
import sv.global.colas.pojos.entities.UsuarioPojo;
import sv.global.colas.repositories.GcEscritorioRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/usuariom")
@EnableAsync
public class monitoreousuarioController  extends MainController {

	@Autowired
	private GcUnidadRecepRepository gcUnidadRecepRepository;
	@Autowired
	private GcEscritorioRepository gcEscritorioRepository;
	@Autowired
	private GcUsuarioRepository gcUsuarioRepository;
        Utils utilidad = new Utils();
        
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexMonitoreo(HttpServletRequest request,
            @ModelAttribute MonitoreoUsuarioPojo monitoreoUsuarioPojo, final ModelMap model) {
        model.addAttribute("json", getJosn());
        String nombre="";
        TbUnidadRecep unidad=gcUnidadRecepRepository.findOne(getUnidad());
        nombre=gcUnidadRecepRepository.getNombreUnidadCombinacion(unidad.getCunidadRecep());
        nombre=nombre==null?unidad.getDunidadRecep():nombre;
        model.addAttribute("unidadRecep",nombre);
        return "/monitoreo/musuario";
    }
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET,  headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public List<MonitoreoUsuarioPojo> getUsuariosJson(HttpServletRequest request, @RequestParam(value = "unidad", required = false) String unidad){
        unidad=getUnidad();
        if(unidad==null || "".equals(unidad)){
            unidad=getUnidad();
        }
        List<MonitoreoUsuarioPojo> users=new ArrayList<MonitoreoUsuarioPojo>();
        try{
            List usuarios=gcUsuarioRepository.getUsuariosbyCS(unidad);
            for(int i=0;i<usuarios.size();i++){
                MonitoreoUsuarioPojo monitoreoUsuarioPojo=new MonitoreoUsuarioPojo();
                Object[] obj=null;
                obj=(Object[]) usuarios.get(i);
                monitoreoUsuarioPojo.setNombre((String)obj[0]);
                monitoreoUsuarioPojo.setEscritorioid(Long.parseLong(obj[1]+""));
                monitoreoUsuarioPojo.setEscritorio((String)obj[3]);
                monitoreoUsuarioPojo.setRol((String)obj[4]);
                monitoreoUsuarioPojo.setEstado((String)obj[5]);
                monitoreoUsuarioPojo.setTramites((String)obj[6]);
                monitoreoUsuarioPojo.setSecciones((String)obj[7]);
                monitoreoUsuarioPojo.setIsesion((String)obj[8]);
                monitoreoUsuarioPojo.setIproceso((String)obj[9]);
                monitoreoUsuarioPojo.setToatendidos(((Long)obj[10]).toString());
                users.add(monitoreoUsuarioPojo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return users;
    }
    
    @RequestMapping(value = "/getEscritoriosDisponibles", method = RequestMethod.GET,  headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public List getEscritoriorsDisponiblesJson(HttpServletRequest request, @RequestParam(value = "unidad", required = false) String unidad){
        if(unidad==null || "".equals(unidad)){
            unidad=getUnidad();
        }
        List escritorios =new ArrayList();
        try{
            escritorios =gcEscritorioRepository.allEscritoriosDisponibles(unidad);
        }catch(Exception e){
            e.printStackTrace();
        }
        return escritorios;
    }
    
    @RequestMapping(value = "/guardaEscritorio", method = RequestMethod.GET,  headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public String guardaEscritorioUsuario(HttpServletRequest request, @RequestParam(value = "datos", required = false) String datos){
        String[] arrayDatos=null;
        String result="";
        try{
            if(datos!=null){
                arrayDatos=datos.split(",");
                GcUsuario usuario=gcUsuarioRepository.findOne(arrayDatos[0].toString());
                GcEscritorio escritorio=gcEscritorioRepository.findOne(Long.parseLong(arrayDatos[0]));
                usuario.setNEscritorioId(escritorio);
                gcUsuarioRepository.save(usuario);
                result="success";
            }else{
                result= "error";
            }
        }catch(Exception e){
            e.printStackTrace();
            result= "error";
        }
        return result;
    }

        private String getUnidad() {
            return getUnidadRecep();
        }
    
    public String getJosn(){
        String json="";
        List<MonitoreoUsuarioPojo> users=new ArrayList<MonitoreoUsuarioPojo>();
        try{
            List usuarios=gcUsuarioRepository.getUsuariosbyCS(getUnidad());
            for(int i=0;i<usuarios.size();i++){
                MonitoreoUsuarioPojo monitoreoUsuarioPojo=new MonitoreoUsuarioPojo();
                Object[] obj=null;
                obj=(Object[]) usuarios.get(i);
                monitoreoUsuarioPojo.setNombre((String)obj[0]);
                monitoreoUsuarioPojo.setEscritorioid(Long.parseLong(obj[1]+""));
                monitoreoUsuarioPojo.setEscritorio((String)obj[3]);
                monitoreoUsuarioPojo.setRol((String)obj[4]);
                monitoreoUsuarioPojo.setEstado((String)obj[5]);
                monitoreoUsuarioPojo.setTramites((String)obj[6]);
                monitoreoUsuarioPojo.setSecciones((String)obj[7]);
                monitoreoUsuarioPojo.setIsesion((String)obj[8]);
                monitoreoUsuarioPojo.setIproceso((String)obj[9]);
                monitoreoUsuarioPojo.setToatendidos(((Integer)obj[10]).toString());
                users.add(monitoreoUsuarioPojo);
            }
            json = new Gson().toJson(users);
            System.out.println(json);
        }catch(Exception e){
            e.printStackTrace();
        }
            return  json;
    }
}
