/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

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
import sv.global.colas.entities.GcEquipos;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.repositories.GcEquipoRepository;
import sv.global.colas.repositories.GcTipoEquipoRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/equipo")
@SessionAttributes("gcequipo")
@EnableAsync
public class EquipoController extends MainController {

    @Autowired
    private GcEquipoRepository gcEquipoRepository;

    @Autowired
    private GcTipoEquipoRepository gcTipoEquipoRepository;

    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;
    Utils utilidad = new Utils();

    @ModelAttribute("gcequipo")
    public GcEquipos gcEquiposList() {
        return new GcEquipos();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexEquipo(HttpServletRequest request, @ModelAttribute GcEquipos gcEquipos, final ModelMap model) {
        Iterable<GcEquipos> listaEquipo = null;
        if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            listaEquipo = gcEquipoRepository.getEquiposActivos();
        } else {
            listaEquipo = gcEquipoRepository.getEquiposByCS(getUnidad());
        }
        model.addAttribute("listaEquipo", listaEquipo);
        return "equipo/equipos";
    }

    @RequestMapping("/newEquipo")
    public String newEquipo(HttpServletRequest request,
            @ModelAttribute GcEquipos gcEquipos, final ModelMap model) {
        model.addAttribute("gcequipo", gcEquipos);
        model.addAttribute("gcEquiposList", gcTipoEquipoRepository.findAll());
        model.addAttribute("gcUnidadesList", getUnidadesList());
        return "equipo/newEquipo";
    }

    @RequestMapping(value = "{equipo}/editEquipo", method = RequestMethod.GET)
    public String editEquipo(HttpServletRequest request,
            @ModelAttribute GcEquipos gcEquipos, final ModelMap model,
            @PathVariable(value = "equipo") Long equipoId) {
        gcEquipos = gcEquipoRepository.findOne(equipoId);
        gcEquipos.setBActiva(gcEquipos.getBActiva() == 0 ? 2 : gcEquipos.getBActiva());
        model.addAttribute("unidadesSelect", gcEquipos.getCUnidadRecep().getCunidadRecep());
        gcEquipos.setCUsuarioModi(gcEquipos.getCUnidadRecep().getCunidadRecep());
        gcEquipos.setCUnidadRecep(null);
        model.addAttribute("gcequipo", gcEquipos);
        model.addAttribute("gcEquiposList", gcTipoEquipoRepository.findAll());
        model.addAttribute("gcUnidadesList", getUnidadesList());
        return "equipo/newEquipo";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardarEquipo(HttpServletRequest request, @ModelAttribute GcEquipos gcEquipos, final ModelMap model) {
        try {
            if (gcEquipos.getNEquipoId() != null){
                GcEquipos gcEquipos2 = gcEquipoRepository.findOne(gcEquipos.getNEquipoId());
                gcEquipos.setCUsuarioCrea(gcEquipos2.getCUsuarioCrea());
                gcEquipos.setFfVigencia(gcEquipos2.getFfVigencia());
                gcEquipos.setFiVigencia(gcEquipos2.getFiVigencia());
            } else {
                gcEquipos.setCUsuarioCrea(getUsuario());
                gcEquipos.setFfVigencia(new Date());
                gcEquipos.setFiVigencia(new Date());
            }
            gcEquipos.setCUnidadRecep(gcUnidadRecepRepository.findOne(gcEquipos.getCUsuarioModi()));
            gcEquipos.setCUsuarioModi(getUsuario());
            gcEquipos.setFModifica(new Date());
            gcEquipos.setFfVigencia(new Date());
            gcEquipos.setFiVigencia(new Date());
            gcEquipos.setBActiva(gcEquipos.getBActiva() == 2 ? 0 : gcEquipos.getBActiva());
            if (gcEquipos.getNEquipoId()==null ) {
                    gcEquipos.setNEquipoId(gcEquipoRepository.getTotalId());
                }
            gcEquipoRepository.save(gcEquipos);
            model.addAttribute("mensaje", "Datos guardados correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al tratar de guardar los datos!");
        }
        indexEquipo(request, null, model);
        return "equipo/equipos";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.GET)
    public String guardarEquipoGET(HttpServletRequest request, @ModelAttribute GcEquipos gcEquipos, final ModelMap model) {
        try {
            if (gcEquipos.getNEquipoId() != null) {
                GcEquipos gcEquipos2 = gcEquipoRepository.findOne(gcEquipos.getNEquipoId());
                gcEquipos.setCUsuarioCrea(gcEquipos2.getCUsuarioCrea());
                gcEquipos.setFfVigencia(gcEquipos2.getFfVigencia());
                gcEquipos.setFiVigencia(gcEquipos2.getFiVigencia());
            } else {
                gcEquipos.setCUsuarioCrea(getUsuario());
                gcEquipos.setFfVigencia(new Date());
                gcEquipos.setFiVigencia(new Date());
            }
            gcEquipos.setCUsuarioModi(getUsuario());
            gcEquipos.setFModifica(new Date());
            gcEquipos.setFfVigencia(new Date());
            gcEquipos.setFiVigencia(new Date());
            gcEquipos.setBActiva(gcEquipos.getBActiva() == 2 ? 0 : gcEquipos.getBActiva());
            gcEquipoRepository.save(gcEquipos);
            model.addAttribute("mensaje", "Datos guardados correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al tratar de guardar los datos!");
        }
        indexEquipo(request, null, model);
        return "equipo/equipos";
    }

    @RequestMapping(value = "{equipo}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request,
            @ModelAttribute GcEquipos gcEquipos, final ModelMap model,
            @PathVariable(value = "equipo") Long equipoId) {
        GcEquipos equipo = gcEquipoRepository.findOne(equipoId);
        try {
            gcEquipoRepository.delete(equipo);
            gcEquipoRepository.delete(equipoId);
            model.addAttribute("mensaje", "Se elimino el registro correctamente!");
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Ocurrio un Error!" + " Error: " + e.getMessage());
        }
        indexEquipo(request, null, model);
        return "equipo/equipos";
    }

    @RequestMapping(value = "{equipo}/verEquipo", method = RequestMethod.GET)
    public String verEquipo(HttpServletRequest request,
            @ModelAttribute GcEquipos gcEquipos, final ModelMap model,
            @PathVariable(value = "equipo") Long equipoId) {
        gcEquipos = gcEquipoRepository.findOne(equipoId);
        model.addAttribute("gcequipo", gcEquipos);
        return "equipo/verEquipo";
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
