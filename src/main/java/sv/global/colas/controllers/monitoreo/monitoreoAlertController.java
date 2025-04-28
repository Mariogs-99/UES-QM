/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.monitoreo;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.MonitoreoTramitePojo;
import sv.global.colas.repositories.GcMonitoreoRepository;
import sv.global.colas.repositories.GcServiciosRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/alrts")
@EnableAsync
public class monitoreoAlertController extends MainController {
    
    @Autowired
    private GcMonitoreoRepository gcRepo;
    @Autowired
    private GcServiciosRepository gcSer;
    @Autowired
    GcUnidadRecepRepository unidadRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexMonitoreo(HttpServletRequest request,
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model) {        try{
            String nombre="";
            TbUnidadRecep unidad=unidadRepository.findOne(getUnidad());
            nombre=unidadRepository.getNombreUnidadCombinacion(unidad.getCunidadRecep());
            nombre=nombre==null?unidad.getDunidadRecep():nombre;
            model.addAttribute("unidadRecep",nombre);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "/monitoreo/malertas";
    }
    
    @RequestMapping(value = "/getAlerta", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody String getAlerta(HttpServletRequest request,@RequestParam(value = "id", required = false) String id) {
        String unidad=getUnidadRecep();
        String jso="";
        JSONObject jo=new JSONObject();
        JSONObject jo_interno;
        JSONArray lista = new JSONArray();
        List<String> zonas;
        List<GcServicios> ListaServicios=gcSer.getAllServicios();
        String secciones="";
        //iniciamos con alertas de tramites
        zonas = gcRepo.findZonas(unidad);
        for (int i=0;i<zonas.size();i++){
            String z=zonas.get(i)+"";            
            List<String> tram=gcRepo.findtramites(z, unidad);
            String znon=gcRepo.findNzona(z, unidad);
            for (int j=0; j<tram.size();j++){
                 String resultado=gcRepo.findalertas(tram.get(j)+"", unidad, z);
                 StringTokenizer st;
                 if (resultado==null){
                     continue;
                 }
                 st = new StringTokenizer(resultado,";"); 
                 int index=0;
                 jo_interno=new JSONObject();
                 while (st.hasMoreElements()){
                     String s=(String) st.nextToken();
                     s=s.trim();
                     switch (index){
                         case 0:
                             jo_interno.put("hora", s);
                             break;
                         case 1:
                              jo_interno.put("esperado", s);
                             break;
                         case 2:
                              jo_interno.put("porcentaje", s);
                             break; 
                          case 3:
                              s=s+" en Zona "+znon;
                              s=s.replaceAll("á","&aacute;");
                              s=s.replaceAll("é","&eacute;");
                              s=s.replaceAll("í","&iacute;");
                              s=s.replaceAll("ó","&oacute;");
                              s=s.replaceAll("ú","&uacute;");
                              s=s.replaceAll("Á","&Aacute;");
                              s=s.replaceAll("É","&Eacute;");
                              s=s.replaceAll("Í","&Iacute;");
                              s=s.replaceAll("Ó","&Oacute;");
                              s=s.replaceAll("Ú","&Uacute;");
                              s=s.replaceAll("ñ","&ntilde;");
                              s=s.replaceAll("Ñ","&Ntilde;");
                              jo_interno.put("nombre", s);
                             break; 
                     }
                     index++;
                 }
                 jo_interno.put("tipo", "TRAMITE");
                 jo_interno.put("capacidad", "0");
                 lista.add(jo_interno);
            }
        }
        
        
        for (int i=0;i<ListaServicios.size();i++){
            GcServicios z = ListaServicios.get(i);
            secciones=gcSer.getSeccionSaturacion(z.getNServiciosId(), unidad);
            //System.out.println(secciones);
            StringTokenizer st;
                 //System.out.println("Resul: "+resultado+"<<-"+tram.get(j)+","+unidad+','+z);
                 if (secciones==null){
                     continue;
                 }
                 st = new StringTokenizer(secciones,";"); 
                 int index=0;
                 jo_interno=new JSONObject();
                 while (st.hasMoreElements()){
                     String s=(String) st.nextToken();
                     s=s.trim();
                     switch (index){
                         case 0:
                             jo_interno.put("hora", s);
                             break;
                         case 1:
                              jo_interno.put("esperado", s);
                             break;
                         case 2:
                              jo_interno.put("porcentaje", s);
                             break; 
                          case 3:
                              s=s.replaceAll("á","&aacute;");
                              s=s.replaceAll("é","&eacute;");
                              s=s.replaceAll("í","&iacute;");
                              s=s.replaceAll("ó","&oacute;");
                              s=s.replaceAll("ú","&uacute;");
                              s=s.replaceAll("Á","&Aacute;");
                              s=s.replaceAll("É","&Eacute;");
                              s=s.replaceAll("Í","&Iacute;");
                              s=s.replaceAll("Ó","&Oacute;");
                              s=s.replaceAll("Ú","&Uacute;");
                              s=s.replaceAll("ñ","&ntilde;");
                              s=s.replaceAll("Ñ","&Ntilde;");
                              jo_interno.put("nombre", s);
                             break; 
                     }
                     index++;
                 }
                 jo_interno.put("tipo", "SECCION");                 
                 lista.add(jo_interno);
        }
                
        //alertas de zona
        zonas = gcRepo.zona_id(unidad);
        for (int i=0;i<zonas.size();i++){
            String z=zonas.get(i);
            String[] aux=z.split(";");
            String resultado=gcRepo.findZonas(unidad, aux[1], aux[0]);
            StringTokenizer st;
            //System.out.println("Resul: "+resultado+"<<-"+z);
            if (resultado==null){
               continue;
            }
            st = new StringTokenizer(resultado,";"); 
            int index=0;
            jo_interno=new JSONObject();
            while (st.hasMoreElements()){
                   String s=(String) st.nextToken();
                   s=s.trim();
                   switch (index){
                         case 0:
                             jo_interno.put("hora", s);
                             break;
                         case 1:
                              jo_interno.put("esperado", s);
                             break;
                         case 2:
                              jo_interno.put("porcentaje", s);
                             break; 
                          case 3:
                              s=s.replaceAll("á","&aacute;");
                              s=s.replaceAll("é","&eacute;");
                              s=s.replaceAll("í","&iacute;");
                              s=s.replaceAll("ó","&oacute;");
                              s=s.replaceAll("ú","&uacute;");
                              s=s.replaceAll("Á","&Aacute;");
                              s=s.replaceAll("É","&Eacute;");
                              s=s.replaceAll("Í","&Iacute;");
                              s=s.replaceAll("Ó","&Oacute;");
                              s=s.replaceAll("Ú","&Uacute;");
                              s=s.replaceAll("ñ","&ntilde;");
                              s=s.replaceAll("Ñ","&Ntilde;");
                              jo_interno.put("nombre", aux[0]);
                             break; 
                          case 4:
                              jo_interno.put("capacidad", s);
                             break; 
                     }
                     index++;
                 }
                 jo_interno.put("tipo", "ZONA");
                 lista.add(jo_interno);
            }        
        // alertas de Centro de servicio
        
        String resultado=gcRepo.findUnidad(unidad);
        StringTokenizer st;
        //System.out.println("Resul: "+resultado+"<<-"+unidad);
        if (resultado!=null){
            st = new StringTokenizer(resultado,";"); 
                 int index=0;
                 jo_interno=new JSONObject();
                 while (st.hasMoreElements()){
                     String s=(String) st.nextToken();
                     s=s.trim();
                     switch (index){
                         case 0:
                             jo_interno.put("hora", s);
                             break;
                         case 1:
                              jo_interno.put("esperado", s);
                             break;
                         case 2:
                              jo_interno.put("porcentaje", s);
                             break; 
                          case 3:
                              s=s.replaceAll("á","&aacute;");
                              s=s.replaceAll("é","&eacute;");
                              s=s.replaceAll("í","&iacute;");
                              s=s.replaceAll("ó","&oacute;");
                              s=s.replaceAll("ú","&uacute;");
                              s=s.replaceAll("Á","&Aacute;");
                              s=s.replaceAll("É","&Eacute;");
                              s=s.replaceAll("Í","&Iacute;");
                              s=s.replaceAll("Ó","&Oacute;");
                              s=s.replaceAll("Ú","&Uacute;");
                              s=s.replaceAll("ñ","&ntilde;");
                              s=s.replaceAll("Ñ","&Ntilde;");
                              jo_interno.put("nombre", s);
                             break; 
                          case 4:
                              jo_interno.put("capacidad", s);
                             break;    
                     }
                     index++;
                 }
                 jo_interno.put("tipo", "CENTRO SERVICIO");          
                 lista.add(jo_interno);
            }        
        
        jo.put("lista",lista);
        jso=jo.toJSONString();
        return jso;
    }//fin getAll



    public String getUnidad(){
        return getUnidadRecep();
    }
}
