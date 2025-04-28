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
import org.springframework.web.bind.annotation.SessionAttributes;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.GcZona;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.MonitoreoColasPojo;
import sv.global.colas.pojos.entities.MonitoreoCsPojo;
import sv.global.colas.pojos.entities.MonitoreoSeccionPojo;
import sv.global.colas.pojos.entities.MonitoreoSeccionTramites;
import sv.global.colas.pojos.entities.MonitoreoTramitePojo;
import sv.global.colas.pojos.entities.MonitoreoUsuarioPojo;
import sv.global.colas.pojos.entities.MonitoreoZonasPojo;
import sv.global.colas.repositories.GcServiciosRepository;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcZonaRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/cdservicio")
@SessionAttributes("monitoreoTramitePojo")
@EnableAsync
public class monitoreoCentrosController extends MainController {

    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;

    @Autowired
    private GcZonaRepository gcZonaRepository;

    @Autowired
    private GcTramiteRepository gcTramiteRepository;

    @Autowired
    private GcServiciosRepository gcServiciosRepository;

    @Autowired
    private GcTiqueteRepository gcTiqueteRepository;

    Utils utilidad = new Utils();

    @ModelAttribute("monitoreoTramitePojo")
    public MonitoreoTramitePojo monitoreoTramite() {
        MonitoreoTramitePojo monitoreoTramitePojo = new MonitoreoTramitePojo();
        return monitoreoTramitePojo;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexMonitoreo(HttpServletRequest request,
            @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model) {
        monitoreoTramitePojo.setUnidadesList(gcUnidadRecepRepository.getUnidadesEnServicio());
        monitoreoTramitePojo.setUnidadesList(getUnidadesList());
        model.addAttribute("monitoreoTramitePojo", monitoreoTramitePojo);
        model.addAttribute("unidadesList", getUnidadesList());

        return "/monitoreo/centrodeservicio";
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public MonitoreoCsPojo getMonitoreoJson(HttpServletRequest request, @RequestParam(value = "unidad", required = false) String unidad) {
        MonitoreoCsPojo monitoreoCsPojo = new MonitoreoCsPojo();
        try {
            TbUnidadRecep unidadE = gcUnidadRecepRepository.getUnidadEnServicioE(unidad);
            monitoreoCsPojo.setCsId(unidad);
            monitoreoCsPojo.setNombre(unidadE.getDunidadRecep());

            //Llamar al total de tiquetes atendidos el dia actual
            Integer totalTiquetes = gcTiqueteRepository.getTotalTiquetesUnidad(unidad);
            monitoreoCsPojo.setTotalTiquetes(totalTiquetes);

            // Llamar al promedio de espera de la unidad
            Integer promedioEspera = gcUnidadRecepRepository.getPromedioEsperaUnidad(unidad);
            monitoreoCsPojo.setPromedioEspera(promedioEspera); // Asegúrate de que haya un setter para esto

            List<GcZona> zonas = gcZonaRepository.getZonasByUnidad(unidad);
            List<MonitoreoZonasPojo> monZonas = new ArrayList<MonitoreoZonasPojo>();

            for (GcZona zona : zonas) {
                MonitoreoZonasPojo monitoreoZonasPojo = new MonitoreoZonasPojo();
                monitoreoZonasPojo.setSaturacion(gcZonaRepository.getSaturacionZona(unidad, zona.getNZonaId(), zona.getSNombre()));
                monitoreoZonasPojo.setNombre(zona.getSNombre());
                monitoreoZonasPojo.setZonaId(zona.getNZonaId());

                List<MonitoreoUsuarioPojo> monUsuariosList = new ArrayList();
                List userList = gcUnidadRecepRepository.getUserMonitoreo(zona.getNZonaId().toString());

                for (Object obj : userList) {
                    Object[] userObj = (Object[]) obj;
                    MonitoreoUsuarioPojo monitoreoUsuarioPojo = new MonitoreoUsuarioPojo();
                    monitoreoUsuarioPojo.setNombre((String) userObj[0]);
                    monitoreoUsuarioPojo.setEscritorioid(Long.parseLong(userObj[1].toString()));
                    monitoreoUsuarioPojo.setEscritorio((String) userObj[2]);
                    monitoreoUsuarioPojo.setRol((String) userObj[3]);
                    monitoreoUsuarioPojo.setEstado((String) userObj[4]);
                    monitoreoUsuarioPojo.setTramites((String) userObj[5]);
                    monitoreoUsuarioPojo.setSecciones((String) userObj[6]);
                    monUsuariosList.add(monitoreoUsuarioPojo);
                }
                monitoreoZonasPojo.setMonitoreoUsuarioPojo(monUsuariosList);
                monZonas.add(monitoreoZonasPojo);
            }

            monitoreoCsPojo.setSaturacion(gcUnidadRecepRepository.getCsSaturacion(unidad));
            monitoreoCsPojo.setMonitoreoZonasPojo(monZonas);

            // Llamar a la consulta para obtener los trámites con más tiquetes
            List<Object[]> topTramites = gcTiqueteRepository.getTop3TramitesWithMostTiquetes(unidad);
            monitoreoCsPojo.setTopTramites(topTramites); // Establecer los trámites

            //Llamar a la consulta para los ultimos tramites
            List<String> ultimos = gcTiqueteRepository.getUltimos3TiquetesPorUnidad(unidad);
            monitoreoCsPojo.setUltimosTramites(ultimos);// Establecer los trámites

        } catch (Exception e) {
            e.printStackTrace();
        }
        return monitoreoCsPojo;
    }

    public List<TbUnidadRecep> getUnidadesList() {
        List<TbUnidadRecep> unidades = null;
        if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.getUnidadesEnServicio();
            List<TbUnidadRecep> combinacionUnidades = gcUnidadRecepRepository.getUnidadesCombinaciones();
            for (TbUnidadRecep unidaddes : combinacionUnidades) {
                String nombre = gcUnidadRecepRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
                unidaddes.setDunidadRecep(nombre == null ? unidaddes.getCunidadRecep() : nombre);
            }
            unidades.addAll(combinacionUnidades);
        } else {
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.getUnidadesEnServicio(getUnidad());
            for (TbUnidadRecep unidaddes : unidades) {
                String nombre = gcUnidadRecepRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
                unidaddes.setDunidadRecep(nombre == null ? unidaddes.getDunidadRecep() : nombre);
            }
        }
        return unidades;
    }

    private String getUnidad() {
        return getUnidadRecep();
    }

    @RequestMapping(value = "/getGrafica", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public List getMonitoreoGraficaJson(HttpServletRequest request, @RequestParam(value = "unidad", required = false) String unidad) {
        List todo = new ArrayList();
        String s = "";
        try {
            List<GcServicios> servicios = gcServiciosRepository.getAllServiciosActivo();
            for (GcServicios servicio : servicios) {
                MonitoreoSeccionPojo monitoreoSeccionPojo = new MonitoreoSeccionPojo();
                monitoreoSeccionPojo.setSeccionId(servicio.getNServiciosId());
                monitoreoSeccionPojo.setScNombre(servicio.getSNombre());
                List tramites = gcServiciosRepository.getMonitoreoSecTramites(unidad, servicio.getNServiciosId());
                List<MonitoreoSeccionTramites> arrayTramites = new ArrayList<MonitoreoSeccionTramites>();
                for (int i = 0; i < tramites.size(); i++) {
                    Object[] obj = null;
                    obj = (Object[]) tramites.get(i);
                    MonitoreoSeccionTramites monitoreoSeccionTramites = new MonitoreoSeccionTramites();
//                    monitoreoSeccionTramites.setTramiteId(((Integer)obj[0]).longValue());
                    monitoreoSeccionTramites.setTramiteId(Long.parseLong(obj[0] + ""));
                    s = (String) obj[1];
                    monitoreoSeccionTramites.setTramite(s);
                    monitoreoSeccionTramites.setEsperando(Long.parseLong(obj[2] + ""));
                    arrayTramites.add(monitoreoSeccionTramites);
                }
                monitoreoSeccionPojo.setTramites(arrayTramites);
                todo.add(monitoreoSeccionPojo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todo;
    }

}
