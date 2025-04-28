/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.util.Calendar;
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
import sv.global.colas.entities.GcPrioridad;
import sv.global.colas.repositories.GcPrioridadRepository;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/prioridad")
@SessionAttributes("gcprioridad")
@EnableAsync
public class PrioridadController extends MainController {

    @Autowired
    private GcPrioridadRepository gcPrioridadRepository;

    @ModelAttribute("gcprioridad")
    public GcPrioridad gcPrioridadList() {
        return new GcPrioridad();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPrioridad(HttpServletRequest request, @ModelAttribute GcPrioridad gcPrioridad, final ModelMap model) {
        Iterable<GcPrioridad> ListaPrioridades = gcPrioridadRepository.getAllPrioridades();
        model.addAttribute("gcPrioridadList", ListaPrioridades);
        return "prioridad/prioridades";
    }

    @RequestMapping("/newPrioridad")
    public String newPrioridad(HttpServletRequest request,
            @ModelAttribute GcPrioridad gcPrioridad, final ModelMap model) {
        model.addAttribute("gcprioridad", gcPrioridad);
        return "prioridad/newPrioridad";
    }

    @RequestMapping(value = "{prioridad}/editPrioridad", method = RequestMethod.GET)
    public String editPrioridad(HttpServletRequest request,
            @ModelAttribute GcPrioridad gcPrioridad, final ModelMap model,
            @PathVariable(value = "prioridad") Long prioridadId) {
        gcPrioridad = gcPrioridadRepository.findOne(prioridadId);
        model.addAttribute("gcprioridad", gcPrioridad);
        return "prioridad/newPrioridad";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardarPrioridad(HttpServletRequest request, @ModelAttribute GcPrioridad gcPrioridad, final ModelMap model) {
        System.out.println("ENTRA AL METODO /guardar");
        System.out.println("ID PRIORIDAD: " + gcPrioridad.getNPrioridadId());
        Long idPrioridad = 0L;
        if (gcPrioridad.getNPrioridadId() == null) {
            idPrioridad = 0L;
        } else {
            idPrioridad = gcPrioridad.getNPrioridadId();
        }
        System.out.println("DUPLICADO? : " + gcPrioridadRepository.
                getPrioridadDuplicada(gcPrioridad.getSNombre(), idPrioridad));
        try {
            if (gcPrioridadRepository.getPrioridadDuplicada(gcPrioridad.getSNombre(), idPrioridad) == 0) {
                System.out.println("PRIORIDAD: " + gcPrioridad.toString());
                gcPrioridad.setCUsuarioCrea(getUsuario());
                gcPrioridad.setCUsuarioModi(getUsuario());
                gcPrioridad.setFModifica(new Date());
                Date fechaActual = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaActual);
                calendar.add(Calendar.YEAR, 10);
                Date nuevaFecha = calendar.getTime();
                gcPrioridad.setFfVigencia(nuevaFecha);
                gcPrioridad.setFiVigencia(new Date());
                gcPrioridad.setBActiva((short) 1);
                if (gcPrioridad.getBFilaEsp() == null) {
                    gcPrioridad.setBFilaEsp((long) 0);
                }
                gcPrioridad.setNPrioridadId(gcPrioridad.getNPrioridadId());
                if (gcPrioridad.getNPrioridadId() == null) {
                    gcPrioridad.setNPrioridadId(gcPrioridadRepository.getTotalID());
                }
                gcPrioridadRepository.save(gcPrioridad);
                model.addAttribute("mensaje", "Datos guardados correctamente");
            } else {
                System.out.println("YA EXISTE UNA PRIORIDAD CON ESE NOMBRE");
                model.addAttribute("mensajeError", "Error: Ya existe una prioridad con ese nombre");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error a tratar de guardar el registro!");
        }
        indexPrioridad(request, null, model);
        System.out.println("RETORNANDO A PRIORIDADES...");
        return "prioridad/prioridades";
    }

    @RequestMapping(value = "{prioridad}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request,
            @ModelAttribute GcPrioridad gcPrioridad, final ModelMap model,
            @PathVariable(value = "prioridad") Long prioridadId) {
        GcPrioridad prioridad = gcPrioridadRepository.findOne(prioridadId);
        try {
            prioridad.setBActiva((short) 0);
            prioridad.setCUsuarioModi(getUsuario());
            prioridad.setFfVigencia(new Date());
            gcPrioridadRepository.save(prioridad);
            model.addAttribute("mensaje", "Se elimino el registro correctamente!");
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Ocurrio un Error!" + " Error: " + e.getMessage());
        }
        indexPrioridad(request, null, model);
        return "prioridad/prioridades";
    }

    @RequestMapping(value = "{prioridad}/verPrioridad", method = RequestMethod.GET)
    public String verPrioridad(HttpServletRequest request,
            @ModelAttribute GcPrioridad gcPrioridad, final ModelMap model,
            @PathVariable(value = "prioridad") Long prioridadId) {
        gcPrioridad = gcPrioridadRepository.findOne(prioridadId);
        model.addAttribute("gcprioridad", gcPrioridad);
        return "prioridad/verPrioridad";
    }
}
