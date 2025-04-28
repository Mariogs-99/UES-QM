/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import com.google.gson.Gson;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfLlamado;
import sv.global.colas.entities.GcConfTiquete;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.MultimediaServicioDto;
import sv.global.colas.repositories.GcMultiServicioRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/crepro")
@EnableAsync
public class MultimedServicioController extends MainController {
    
    @Autowired
    private GcMultiServicioRepository gcConfreproRepository;
    @Autowired
    private GcUnidadRecepRepository unidadRepository;
    Utils utilidad=new Utils();
             
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexConfTiquete(HttpServletRequest request, @ModelAttribute GcConfTiquete gcConfTiquete, final ModelMap model){
            return "confrepro/confrepro";
    } 
    @RequestMapping(value="/cargar", method = RequestMethod.POST)
    public @ResponseBody String cargar(@RequestBody Map<String, Integer> map){
        List<TbUnidadRecep> unidades = (List<TbUnidadRecep>) unidadRepository.getUnidadesEnServicio();
        JSONObject jo=new JSONObject();
        JSONArray junidades=new JSONArray();
        String aux;
        for (int i=0;i<unidades.size();i++){
            aux=unidades.get(i).getCunidadRecep()+";"+unidades.get(i).getDunidadRecep();
            junidades.add(aux);
        }
               jo.put("unidades", junidades);
               String jso=jo.toJSONString();
               //jso=Charset.forName("UTF-8").encode(jso).toString();                    
		return jso;
    }
    
    @RequestMapping(value="/leerLista", method = RequestMethod.POST)
    public @ResponseBody String LeerLista(@RequestBody Map<String, String> map){
        String unidad = map.get("unidad");
         List<MultimediaServicioDto> contenido =  gcConfreproRepository.getLista(unidad);
        // List<MultimediaServicioDto> contenido =  gcConfreproRepository.getListaVacia();
         Gson gson = new Gson();
         String jsonArray = gson.toJson(contenido);
         return jsonArray;
         
    }
    
    // Alcides Nolasco + metodo para actualizar la asignacion de multimedia a unidad de servicio 
    
    @RequestMapping(value="/guardarMedia", method = RequestMethod.POST)
    public @ResponseBody String GuardarMedia(@RequestBody Map<String, String> map){
       
        String unidad = map.get("unidad");
        String media=map.get("media");
        String action=map.get("action");
        String mensaje;
        int orden =0;
        JSONObject jo=new JSONObject();
        String resultado;
        try{
            if (action.equals("delete"))
                gcConfreproRepository.deleteOne(unidad,media);
            else if (action.equals("insert"))
                gcConfreproRepository.insertar(unidad, Integer.parseInt(media), orden);
            resultado="ok";
            mensaje ="datos actualizados";
        }catch (Exception e){
           
            mensaje=e.getMessage();
            resultado="error";
        }
  
            
               jo.put("resultado", resultado);
               jo.put("mensaje", mensaje);
               String jso=jo.toJSONString();
                        
		return jso;
    }
    
    @RequestMapping(value="/guardarLista", method = RequestMethod.POST)
    public @ResponseBody String GuardarLista(@RequestBody Map<String, String> map){
        String unidad = map.get("unidad");
        String cadena=map.get("cadena");
        boolean borrado=false;
        String men="*"; 
        int orden=0;
        StringTokenizer st;
        try{
            gcConfreproRepository.borrar(unidad);
            borrado=true;
        }catch (Exception e){
            borrado=false;
            men=e.getMessage();
        }
        if (borrado){
           st = new StringTokenizer(cadena,";");    
           orden=1;
           try{
           while (st.hasMoreElements()){
                  String s=(String) st.nextToken();
                  int x=Integer.parseInt(s);
                  gcConfreproRepository.insertar(unidad, x, orden);
                  orden++;
           }
           } catch (Exception e){
               men=e.getMessage();
               borrado=false;
           }           
        }
        
        
        
        JSONObject jo=new JSONObject();
        String resultado;
        if (borrado)
            resultado="ok";
        else
            resultado="error";
               jo.put("resultado", resultado);
               jo.put("mensaje", men);
               String jso=jo.toJSONString();
               //jso=Charset.forName("UTF-8").encode(jso).toString();                    
		return jso;
    }
   /* @RequestMapping("/newCllamado")
    public String newCllamado(HttpServletRequest request, 
            @ModelAttribute GcConfLlamado gcConfLlamado, final ModelMap model){
            model.addAttribute("gcUnidadesList",getUnidadesList());
            model.addAttribute("gcconfllamado",gcConfLlamado);  
            return "cllamado/newCllamado";
    } 
    
    @RequestMapping(value="{cllamdo}/editCllamado", method = RequestMethod.GET)
    public String editCllamado(HttpServletRequest request, 
            @ModelAttribute GcConfLlamado gcConfLlamado, final ModelMap model,
            @PathVariable(value = "cllamdo") Long cllamdoId){
            model.addAttribute("gcUnidadesList",getUnidadesList());
            gcConfLlamado=gcConfLlamadoRepository.findOne(cllamdoId);
            model.addAttribute("gcconfllamado",gcConfLlamado);  
            return "cllamado/newCllamado";
    }
    
    @RequestMapping(value="{cllamdo}/verCllamado", method = RequestMethod.GET)
    public String verCllamado(HttpServletRequest request, 
            @ModelAttribute GcConfLlamado gcConfLlamado, final ModelMap model,
            @PathVariable(value = "cllamdo") Long cllamdoId){
            String unidad=getPrincipal().getUnidadRecep();
            if(unidad==null)unidad=getPrincipal().getUbicacionFisica();
            GcConfLlamado cllamdo=gcConfLlamadoRepository.findOne(cllamdoId);
            model.addAttribute("gcconfllamado",cllamdo);  
            return "cllamado/verCllamado";
    }
            
    @RequestMapping(value = "{cllamaod}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request, 
            @ModelAttribute GcConfLlamado gcConfLlamado, final ModelMap model,
            @PathVariable(value = "cllamaod") Long cllamadoId) {
        GcConfLlamado cllamado = gcConfLlamadoRepository.findOne(cllamadoId);
        try{
            gcConfLlamadoRepository.delete(cllamado);
            //gcConfLlamadoRepository.delete(cllamadoId);
            model.addAttribute("mensaje", "Se elimino el registro correctamente!");
        }catch(Exception e){
            model.addAttribute("mensajeError", "Ocurrio un Error!"+" Error: "+e.getMessage());
        }
        indexConfLlamado( request, null, model);
        return "cllamado/cllamados";
    }
        
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute GcConfLlamado gcConfLlamado, final ModelMap model) {
        //GcConfLlamado cLlamado=(GcConfLlamado) model.get("gcConfLlamado");
        try{
            if(gcConfLlamado.getNConfllamadoId()!=null){
                GcConfLlamado cLlamado=gcConfLlamadoRepository.findOne(gcConfLlamado.getNConfllamadoId());
                cLlamado.setCUsuarioModi(getUsuario());
                cLlamado.setFModifica(new Date());
                cLlamado.setNIntervaloLlamada(gcConfLlamado.getNIntervaloLlamada());
                cLlamado.setNNumLlamadas(gcConfLlamado.getNNumLlamadas());
                cLlamado.setSMensaje(gcConfLlamado.getSMensaje());
                cLlamado.setSDescripcion(gcConfLlamado.getSDescripcion());
                gcConfLlamadoRepository.save(cLlamado);
                model.addAttribute("mensaje", "Datos guardados correctamente");
            }else{
                gcConfLlamado.setCUsuarioCrea(getUsuario());
                gcConfLlamado.setFfVigencia(new Date());
                gcConfLlamado.setFiVigencia(new Date());
                gcConfLlamado.setCUsuarioModi(getUsuario());
                gcConfLlamado.setBActiva((short)1);
                gcConfLlamado.setFModifica(new Date());
                if(gcConfLlamadoRepository.getCounByCS(gcConfLlamado.getCUnidadRecep().getCunidadRecep())==0){
                    gcConfLlamadoRepository.save(gcConfLlamado);
                    model.addAttribute("mensaje", "Datos guardados correctamente");
                }else{
                    model.addAttribute("mensajeError", "Duplicado de registro, Ya existe una configuración para el centro de servicio");
                }
            }
        }catch(Exception e){
            model.addAttribute("mensajeError", "Ocurrio un Error!"+" Error: "+e.getMessage());
        }
        indexConfLlamado(request,null,model);
        return "cllamado/cllamados";
    }

    public List<TbUnidadRecep> getUnidadesList() {
        List<TbUnidadRecep> unidades = null;
        if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.getUnidadesEnServicio();
        } else {
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.getUnidadesEnServicio(getUnidad());
        }
        return unidades;
    }

    public String getUnidad() {
        String unidad = getPrincipal().getUnidadRecep();
        if (unidad == null) {
            unidad = getPrincipal().getUbicacionFisica();
        }
        return unidad;
    }
 */   
   
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
