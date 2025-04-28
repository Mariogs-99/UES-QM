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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcZona;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.ZonaPojo;
import sv.global.colas.repositories.GcEscritorioRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcZonaRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Ever Argueta
 */
@Controller
@RequestMapping("/zona")
@SessionAttributes("zonapojo")
@EnableAsync
public class ZonaController extends MainController {

    @Autowired
    private GcZonaRepository zonaRepository;
    @Autowired
    private GcUnidadRecepRepository unidadRepository;
    
    @Autowired
    private GcEscritorioRepository gcEscritorioRepository;
    Utils utilidad = new Utils();

    @ModelAttribute("zonapojo")
    public ZonaPojo usuariopojo() {
        ZonaPojo zona = new ZonaPojo();
        zona.setUnidadesList(getUnidadesList());
        return zona;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexZona(HttpServletRequest request, @ModelAttribute ZonaPojo zonapojo, final ModelMap model) {
        Iterable<GcZona> zonaList = null;
        if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            zonaList = zonaRepository.getAllZonas();
        } else {
            zonaList = zonaRepository.getZonasByUnidad(getUnidad());
        }
        ZonaPojo zonaPojo = (ZonaPojo) model.get("zonapojo");
        zonaPojo.setZonaList((List<GcZona>) zonaList);
        model.addAttribute("zonaList", zonaPojo);
        return "zona/zonas";
    }

    @RequestMapping("/newZona")
    public String irAdministracionNewZona(HttpServletRequest request,
            @ModelAttribute ZonaPojo zonapojo, final ModelMap model) {
        ZonaPojo zonap = new ZonaPojo();
        ZonaPojo zonaPojo = (ZonaPojo) model.get("zonapojo");
        zonap.setUnidadesList(zonaPojo.getUnidadesList());
        zonap.setSnombre(getNextString(zonaRepository.getMaxZona(getUnidad())));
        model.addAttribute("zonapojo", zonap);
        return "zona/newZona";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(HttpServletRequest request, @ModelAttribute ZonaPojo zonapojo, final ModelMap model) {
        GcZona gcZona = new GcZona();
        String usuario = getUsuario();
        gcZona.setSNombre(zonapojo.getSnombre());
        gcZona.setBActiva(zonapojo.getbActiva()==(int)2?(int)0:zonapojo.getbActiva());
        gcZona.setNSillasEspera(zonapojo.getnSillasEspera());
        gcZona.setCUnidadRecep(unidadRepository.getUnidadEnServicioE(zonapojo.getCunidadRecep()));
        gcZona.setDZona(zonapojo.getdZona());
        gcZona.setCUsuarioCrea(usuario);
        gcZona.setCUsuarioModi(usuario);
        gcZona.setFiVigencia(new Date());
        gcZona.setNZonaId(zonapojo.getnZonaId());        
        try {
            String nombre = zonaRepository.getZonasByUnidadName(gcZona.getCUnidadRecep().getCunidadRecep(), gcZona.getSNombre());
            if (nombre == null || "".equals(nombre)) {
               if (gcZona.getNZonaId()==null ) {
                    gcZona.setNZonaId(zonaRepository.getTotalId());
                }
                zonaRepository.save(gcZona);
                model.addAttribute("mensaje", "ZONA GUARDADA CORRECTAMENTE");
            } else {
                model.addAttribute("mensajeError", "DUPLICADO DE NOMBRE DE ZONA /n YA EXISTE UNA ZONA CON ESE NOMBRE");
            }

        } catch (Exception e) {
            model.addAttribute("mensajeError", "ERROR: " + e.getMessage());
        }
        indexZona(request, zonapojo, model);
        return "zona/zonas";
    }

    @RequestMapping(value = "{zonar}/editZona", method = RequestMethod.GET)
    public String editZona(HttpServletRequest request,
            @ModelAttribute ZonaPojo zonapojo, final ModelMap model,
            @PathVariable(value = "zonar") Long zonar) {
        GcZona zona = zonaRepository.findOne(zonar);
        ZonaPojo zonaPojo = (ZonaPojo) model.get("zonapojo");
        zonaPojo.setZona(zona);
        zonaPojo.setbActiva(zona.getBActiva());
        zonaPojo.setDunidadRecep(unidadRepository.getUnidadEnServicio(zona.getCUnidadRecep().getCunidadRecep()));
        zonaPojo.setUnidadesList(getUnidadesList());
        model.addAttribute("zonapojo", zonaPojo);
        return "zona/editZona"; 
    }


    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute ZonaPojo zonapojo, final ModelMap model) {
        try {
            GcZona gcZona = zonaRepository.findOne(zonapojo.getnZonaId());
            GcZona gcZona2 = zonaRepository.getZonasByUnidadNameId(gcZona.getCUnidadRecep().getCunidadRecep(), zonapojo.getSnombre(), zonapojo.getnZonaId());
            if (gcZona2 == null) {
                String usuario = getUsuario();
                gcZona.setSNombre(zonapojo.getSnombre());
                gcZona.setBActiva(zonapojo.getbActiva());
                gcZona.setNSillasEspera(zonapojo.getnSillasEspera());
                gcZona.setDZona(zonapojo.getdZona());
                gcZona.setCUsuarioModi(usuario);
                gcZona.setFiVigencia(new Date());
                gcZona.setFModifica(new Date());
                gcZona.setNZonaId(zonapojo.getnZonaId());
                if (gcZona.getNZonaId()==null)
                   gcZona.setNZonaId(0L);
                zonaRepository.save(gcZona);
                model.addAttribute("mensaje", "ZONA GUARDADA CORRECTAMENTE");
            }else{
                model.addAttribute("mensajeError", "YA EXISTE LA ZONA "+gcZona.getSNombre()+" PARA EL "+ gcZona.getCUnidadRecep().getDunidadRecep());
            }
        } catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "OCURRIO UN ERROR AL GUARDAR LA ZONA!");
        }
        indexZona(request, zonapojo, model);
        return "zona/zonas";
    }

    @RequestMapping(value = "{zonar}/verZona", method = RequestMethod.GET)
    public String mostrar(HttpServletRequest request,
            @ModelAttribute ZonaPojo zonaP, final ModelMap model,
            @PathVariable(value = "zonar") Long zonar) {
        GcZona zona = zonaRepository.findOne(zonar);
        ZonaPojo zonaPojo = (ZonaPojo) model.get("zonapojo");
        zonaPojo.setZona(zona);
        zonaPojo.setDunidadRecep(unidadRepository.getUnidadEnServicio(zona.getCUnidadRecep().getCunidadRecep()));
        model.addAttribute("zonapojo", zonaPojo);
        return "zona/verZona";
    }

    @RequestMapping(value = "{zona}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request,
            @ModelAttribute ZonaPojo zonap, final ModelMap model,
            @PathVariable(value = "zona") Long zonaId) {
        GcZona zona = zonaRepository.findOne(zonaId);
        try {
            if(gcEscritorioRepository.getEscritoriosByZona(zona.getNZonaId())==0){
                zona.setFfVigencia(new Date());
                zona.setCUsuarioModi(getUsuario());
                zonaRepository.save(zona);
                model.addAttribute("mensaje", "SE ELIMINO EL REGISTRO CORRECTAMENTE");
            }else{
                model.addAttribute("mensajeError","LA ZONA TIENE ASOCIADO ESCRITORIOS");
            }
        } catch (Exception e) {
            model.addAttribute("mensajeError", "OCURRIO UN ERROR!" + " ERROR: " + e.getMessage());
        }
        indexZona(request, zonap, model);
        return "zona/zonas";
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

    public String getUnidad(){
        return getUnidadRecep();
    }
    
    public String getNextString(String letra){
        String[] letras={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int posicion=0;
        int correlativo=0;
        for(String let:letras){
            if(let.equals(letra))
                posicion=correlativo+1;
            correlativo++;
        }
        return letras[posicion];
    }
        
    @RequestMapping(value = "/getzona", method = RequestMethod.GET)
    public String getZonasXunidad(HttpServletRequest request, 
            @ModelAttribute ZonaPojo zonaPojo, final ModelMap model,
            @RequestParam(value = "cunidadRecepId", required = false) String unidad) {
            zonaPojo = (ZonaPojo) model.get("zonapojo");
            zonaPojo.setSnombre(getNextString(zonaRepository.getMaxZona(unidad)));
            model.addAttribute("zonapojo", zonaPojo);
            return "zona/newZona";
    }
}
