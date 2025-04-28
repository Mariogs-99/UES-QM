    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.util.Date;
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
import sv.global.colas.entities.GcJerarquia;
import sv.global.colas.repositories.GcJerarquiaRepository;
import sv.global.colas.repositories.GcJerarquiaSeccionRepository;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/jerarquia")
@SessionAttributes("gcjerarquia")
@EnableAsync
public class JerarquiaController extends MainController  {
    
    @Autowired
    private GcJerarquiaRepository gcJerarquiaRepository;
    @Autowired
    private GcJerarquiaSeccionRepository gcJerarquiaSeccionRepository;
    
    @ModelAttribute("gcjerarquia")
    public GcJerarquia gcJeararquiasList(){
        return new GcJerarquia();
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexJerarquias(HttpServletRequest request, @ModelAttribute GcJerarquia gcJerarquia, final ModelMap model){
            Iterable<GcJerarquia> listaGcJerarquia=gcJerarquiaRepository.findAll();
            model.addAttribute("listaJerarquias", listaGcJerarquia);
            return "jerarquia/jerarquias";
    }
    
    @RequestMapping("/newJerarquia")
    public String newJerarquia(HttpServletRequest request, 
            @ModelAttribute GcJerarquia gcJerarquia, final ModelMap model){
            model.addAttribute("gcjerarquia",gcJerarquia);
            return "jerarquia/newJerarquia";
    }
    
   @RequestMapping(value="{jerarquia}/editJerarquia", method = RequestMethod.GET)
    public String editJerarquia(HttpServletRequest request, @ModelAttribute GcJerarquia gcJerarquia, final ModelMap model, @PathVariable(value = "jerarquia") Long jerarquia){
            gcJerarquia = gcJerarquiaRepository.findOne(jerarquia);
            gcJerarquia.setBActiva(gcJerarquia.getBActiva()==0?2:gcJerarquia.getBActiva());
            model.addAttribute("gcjerarquia", gcJerarquia);
            return "jerarquia/newJerarquia";
    }
      
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardarJerarquia(HttpServletRequest request, @ModelAttribute GcJerarquia gcJerarquia, final ModelMap model) {
        try{
            if(gcJerarquia.getNJerarquiaId()!=null){
                String existe=gcJerarquiaRepository.getJerarquiaByNameId(gcJerarquia.getSJerarquia(),gcJerarquia.getNJerarquiaId());
                if(existe==null || "".equals(existe)){
                    gcJerarquia.setBActiva(gcJerarquia.getBActiva()==2?0:gcJerarquia.getBActiva());
                    gcJerarquia.setCUsuarioModifica(getUsuario());
                    gcJerarquia.setFfVigencia(new Date());
                    if (gcJerarquia.getNJerarquiaId()==null)
                        gcJerarquia.setNJerarquiaId(gcJerarquiaRepository.getTotalId());
                    gcJerarquiaRepository.save(gcJerarquia);
                    model.addAttribute("mensaje", "JERARQUIA GUARDADA CORRECTAMENTE");
                }else{
                    model.addAttribute("mensajeError", "YA EXISTE UNA JERARQUIA CON ESE NOMBRE!");
                }
            }else{
                String existe=gcJerarquiaRepository.getJerarquiaByName(gcJerarquia.getSJerarquia());
                if(existe==null || "".equals(existe)){
                    gcJerarquia.setBActiva(gcJerarquia.getBActiva()==2?0:gcJerarquia.getBActiva());
                    gcJerarquia.setCUsuarioCrea(getUsuario());
                    gcJerarquia.setCUsuarioModifica(getUsuario());
                    gcJerarquia.setFfVigencia(new Date());
                    gcJerarquia.setFiVigencia(new Date());
                    gcJerarquia.setNJerarquiaId(gcJerarquia.getNJerarquiaId());
                    if(gcJerarquia.getNJerarquiaId()==null)
                        gcJerarquia.setNJerarquiaId(gcJerarquiaRepository.getTotalId());
                    gcJerarquiaRepository.save(gcJerarquia);
                    model.addAttribute("mensaje", "JERARQUIA GUARDADA CORRECTAMENTE");
                }else{
                    model.addAttribute("mensajeError", "YA EXISTE UNA JERARQUIA CON ESE NOMBRE!");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Error: "+e.getMessage());
        } 
        indexJerarquias(request, null, model);
        return "jerarquia/jerarquias";
    }
        
    @RequestMapping(value = "{jerarquia}/delete", method = RequestMethod.POST)
    public String eliminarJerarquia(HttpServletRequest request, @ModelAttribute GcJerarquia gcJerarquia, final ModelMap model,@PathVariable(value = "jerarquia") Long jerarquia) {
        GcJerarquia gcJerarquia2 = gcJerarquiaRepository.findOne(jerarquia);
        try{
            if(gcJerarquia2!=null){
                if(gcJerarquiaSeccionRepository.getRelacionGerarquia(gcJerarquia2.getNJerarquiaId())==0){
                    gcJerarquiaRepository.delete(gcJerarquia2);
                    //gcJerarquiaRepository.delete(gcJerarquia2.getNJerarquiaId());
                    model.addAttribute("mensaje", "SE ELIMINO LA JERARQUIA CORRECTAMENTE!");
                }else{
                    model.addAttribute("mensajeError", "EXISTE UNA CONFIGURACION DE SECCION FAVOR VERIFICAR!");
                }
            }else{
                model.addAttribute("mensaje", "NINGUNA JERARQUIA POR ELIMINAR!");
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "ERROR: "+e.getMessage());
        }
        indexJerarquias( request, null, model);
        return "jerarquia/jerarquias";
    }
     
   @RequestMapping(value="{jerarquia}/verJerarquia", method = RequestMethod.GET)
    public String verJerarquia(HttpServletRequest request, @ModelAttribute GcJerarquia gcJerarquia, final ModelMap model, @PathVariable(value = "jerarquia") Long jerarquia){
            gcJerarquia = gcJerarquiaRepository.findOne(jerarquia);
            model.addAttribute("gcjerarquia", gcJerarquia);
            return "jerarquia/verJerarquia";
    }
}
