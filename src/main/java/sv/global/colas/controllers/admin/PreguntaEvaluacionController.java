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
import sv.global.colas.entities.GcPreguntas;
import sv.global.colas.entities.GcRespuestas;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.PreguntaEvaluacionPojo;
import sv.global.colas.repositories.GcPreguntasRepository;
import sv.global.colas.repositories.GcRespuestasRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author ever.argueta
 */
@Controller
@RequestMapping("/adminpreguntas")
@SessionAttributes("preguntapojo")
@EnableAsync
public class PreguntaEvaluacionController extends MainController{
    
    @Autowired
    private GcPreguntasRepository gcPreguntasRepository;
    @Autowired
    private GcRespuestasRepository gcRespuestasRepository;
     
    @Autowired
    private GcUnidadRecepRepository unidadRepository;
    
    Utils utilidad = new Utils();
    
    @ModelAttribute("preguntapojo")
    public PreguntaEvaluacionPojo preguntapojo() {
        return new PreguntaEvaluacionPojo();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, @ModelAttribute PreguntaEvaluacionPojo preguntaEvaluacionPojo, final ModelMap model) {
        try{
            List<GcPreguntas> listadoPreguntas = null;
            if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
                listadoPreguntas = (List<GcPreguntas>) gcPreguntasRepository.findAll();
            }else{
                listadoPreguntas = (List<GcPreguntas>) gcPreguntasRepository.preguntaYrespuestas(getUnidad());
            }
            for(GcPreguntas pregunta: listadoPreguntas){
                Long id=pregunta.getNPreguntaId();
                List<GcRespuestas> respuestasList=gcRespuestasRepository.respuestasByPregutna(id);
                pregunta.setGcRespuestasList(respuestasList);
            }
            model.addAttribute("gcpreguntaList", listadoPreguntas);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "adminpreguntas/preguntas";
    }
    
         
    @RequestMapping("/newPregunta")
    public String newPregunta(HttpServletRequest request, @ModelAttribute PreguntaEvaluacionPojo preguntaEvaluacionPojo, final ModelMap model){
            preguntaEvaluacionPojo=new PreguntaEvaluacionPojo();
            model.addAttribute("gcUnidadesList",getUnidadesList());
            model.addAttribute("preguntapojo",preguntaEvaluacionPojo);
            return "adminpreguntas/newPregunta";
    }  
         
       @RequestMapping(value="{pregunta}/editPregunta", method = RequestMethod.GET)
    public String editPregunta(HttpServletRequest request, 
            @ModelAttribute PreguntaEvaluacionPojo preguntaEvaluacionPojo, final ModelMap model,
            @PathVariable(value = "pregunta") Long preguntaId){
            GcPreguntas gcPreguntas=gcPreguntasRepository.findOne(preguntaId);
            preguntaEvaluacionPojo.setPregunta(gcPreguntas.getSPregunta());
            List<GcRespuestas> respuestasList=gcRespuestasRepository.respuestasByPregutna(preguntaId);
            String respuess="";
            for(GcRespuestas gcRespuestas : respuestasList ){
                respuess=respuess+gcRespuestas.getSRespuesta()+",";
            }
            if(respuess!=null && respuess.length()>1) respuess=respuess.substring(0, respuess.length()-1);
            preguntaEvaluacionPojo.setRespuestas(respuess);
            preguntaEvaluacionPojo.setUnidad(gcPreguntas.getCUnidadRecep().getCunidadRecep());
            preguntaEvaluacionPojo.setDescripcion(gcPreguntas.getDPregunta());
            preguntaEvaluacionPojo.setPreguntaId(preguntaId);
            model.addAttribute("gcUnidadesList",getUnidadesList());
            model.addAttribute("preguntapojo",preguntaEvaluacionPojo);
            return "adminpreguntas/newPregunta";
    }
      
         
    @RequestMapping(value="{pregunta}/verPregunta", method = RequestMethod.GET)
    public String verPregunta(HttpServletRequest request, 
            @ModelAttribute PreguntaEvaluacionPojo preguntaEvaluacionPojo, final ModelMap model,
            @PathVariable(value = "pregunta") Long preguntaId){
            GcPreguntas gcPreguntas=gcPreguntasRepository.findOne(preguntaId);
            preguntaEvaluacionPojo.setPregunta(gcPreguntas.getSPregunta());
            List<GcRespuestas> respuestasList=gcRespuestasRepository.respuestasByPregutna(preguntaId);
            String respuess="";
            for(GcRespuestas gcRespuestas : respuestasList ){
                respuess=respuess+gcRespuestas.getSRespuesta()+",";
            }
            if(respuess!=null && respuess.length()>1) respuess=respuess.substring(0, respuess.length()-1);
            preguntaEvaluacionPojo.setRespuestas(respuess);
            preguntaEvaluacionPojo.setUnidad(gcPreguntas.getCUnidadRecep().getDunidadRecep());
            preguntaEvaluacionPojo.setDescripcion(gcPreguntas.getDPregunta());
            preguntaEvaluacionPojo.setPreguntaId(preguntaId);
            model.addAttribute("preguntapojo",preguntaEvaluacionPojo);
            return "adminpreguntas/verPregunta";
    }
    
    @RequestMapping(value="{pregunta}/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, 
            @ModelAttribute PreguntaEvaluacionPojo preguntaEvaluacionPojo, final ModelMap model,
            @PathVariable(value = "pregunta") Long preguntaId){ 
            try{
                GcPreguntas gcPreguntas=gcPreguntasRepository.findOne(preguntaId);
                List<GcRespuestas> resp=gcRespuestasRepository.respuestasByPregutna(gcPreguntas.getNPreguntaId());
                gcRespuestasRepository.delete(resp);
                for(GcRespuestas gcRespuestas:resp){
                    gcRespuestasRepository.delete(gcRespuestas.getNRespuestaId());
                }
                gcPreguntasRepository.delete(gcPreguntas.getNPreguntaId());
                model.addAttribute("mensaje", "DATO ELIMINADO CORRECTAMENTE");
            }catch(Exception e){
                e.printStackTrace();
                model.addAttribute("mensajeError", "Ocurrio un Error al tratar de Eliminar el registro!");
            } 
            index(request, null, model);
            return "adminpreguntas/preguntas";           
    }
      
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute PreguntaEvaluacionPojo preguntaEvaluacionPojo, final ModelMap model) {
        GcPreguntas gcPreguntas=null;
        try{
            if(preguntaEvaluacionPojo.getPreguntaId()!=null){
                gcPreguntas=gcPreguntasRepository.findOne(preguntaEvaluacionPojo.getPreguntaId());
                List<GcRespuestas> resp=gcRespuestasRepository.respuestasByPregutna(gcPreguntas.getNPreguntaId());
                gcRespuestasRepository.delete(resp);
                for(GcRespuestas gcRespuestas:resp){
                    gcRespuestasRepository.delete(gcRespuestas.getNRespuestaId());
                }
            }else{
                gcPreguntas=new GcPreguntas();
                gcPreguntas.setCUsuarioCrea(getUsuario());
                gcPreguntas.setFfVigencia(new Date());
                gcPreguntas.setFiVigencia(new Date());
            }
            gcPreguntas.setCUsuarioModi(getUsuario());
            gcPreguntas.setFModifica(new Date());
            gcPreguntas.setCUnidadRecep(unidadRepository.findOne(preguntaEvaluacionPojo.getUnidad()));
            gcPreguntas.setNPonderacion(BigInteger.ONE);
            gcPreguntas.setSPregunta(preguntaEvaluacionPojo.getPregunta());
            gcPreguntas.setDPregunta(preguntaEvaluacionPojo.getDescripcion());
            gcPreguntasRepository.save(gcPreguntas);
            if(preguntaEvaluacionPojo.getRespuestas()!=null){
                String[] respuestasList=preguntaEvaluacionPojo.getRespuestas().split(",");
                for(String resp:respuestasList){
                    GcRespuestas gcRespuestas=new GcRespuestas();
                    gcRespuestas.setCUsuarioCrea(getUsuario());
                    gcRespuestas.setCUsuarioModi(getUsuario());
                    gcRespuestas.setFModifica(new Date());
                    gcRespuestas.setFfVigencia(new Date());
                    gcRespuestas.setFiVigencia(new Date());
                    gcRespuestas.setNEscala(BigInteger.ONE);
                    gcRespuestas.setSRespuesta(resp);
                    gcRespuestas.setNPreguntaId(gcPreguntas);
                    gcRespuestasRepository.save(gcRespuestas);
                }
            }
            model.addAttribute("mensaje", "DATOS GUARDADOS CORRECTAMENTE");
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al tratar de guardar los datos!");
        } 
        index(request, null, model);
        return "adminpreguntas/preguntas";
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
