package sv.global.colas.controllers.me;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcAlumno;
import sv.global.colas.entities.GcConfTiquete;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcPrioridad;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.GcTiquete;
import sv.global.colas.entities.RcRuc;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.OpcionesPojo;
import sv.global.colas.pojos.entities.PersonaPojo;
import sv.global.colas.repositories.GcAlumnoRepository;
import sv.global.colas.repositories.GcConfTramiteRepository;
import sv.global.colas.repositories.GcPrioridadRepository;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.repositories.PersonWRepository;
import sv.global.colas.repositories.RcRucRepository;
import sv.global.colas.repositories.TbListasValorRepository;
import sv.global.colas.utils.Utils;

@Controller
@EnableAsync
public class OpcionesController extends MainController {

    @Autowired
    private PersonWRepository personWRepository;

    @Autowired
    private Environment env;

    @Autowired
    GcTramiteRepository tramitesRepository;

    @Autowired
    GcPrioridadRepository gcPrioridadRepository;

    @Autowired
    GcTiqueteRepository gcTiqueteRepository;

    @Autowired
    GcUsuarioRepository gcUsuarioRepository;

    @Autowired
    RcRucRepository rcRucRepository;
    
    @Autowired
    GcAlumnoRepository cgAlumnoRepository;

    @Autowired
    GcUnidadRecepRepository gcUnidadRecepRepository;

    @Autowired
    TbListasValorRepository tbListasValorRepository;

    @Autowired
    GcConfTramiteRepository gcConfTramiteRepository;

    @RequestMapping(value = "me/getServicios", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<GcServicios> getServicios(ModelMap map) {
        List<GcServicios> listServicios = null;
        List<String> lUnidadRecep = null;
        try {
            String cunidadRecep = getUnidad();
            lUnidadRecep = new ArrayList<String>();
            lUnidadRecep.add(cunidadRecep);
            listServicios = (List<GcServicios>) gcConfTramiteRepository.listaServiciosByCS(lUnidadRecep);
            //listServicios = (List<GcServicios>) removeDuplicates(listTramites);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listServicios;
    }

    @RequestMapping(value = "/me/getTramites", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<GcConfTramite> servicios(@RequestBody Map<String, String> map) {
        List<GcConfTramite> listTramites = null;
        List<String> lUnidadRecep = null;
        try {
            String cunidadRecep = getUnidad();
            lUnidadRecep = new ArrayList<String>();
            lUnidadRecep.add(cunidadRecep);
            listTramites = (List<GcConfTramite>) gcConfTramiteRepository.listaTramitesByCS2(lUnidadRecep, Long.parseLong(map.get("idServicios")));
            for (GcConfTramite tramite : listTramites) {
                tramite.getNTramiteId().setGcUsuarioList(null);
                tramite.getNTramiteId().setGcReservaCitaList(null);
                tramite.getNTramiteId().setGcTiqueteList(null);
                tramite.getNTramiteId().getNServiciosId().setGcTramiteList(null);
                tramite.getNTramiteId().setGcConfTramiteList(null);
                tramite.getCUnidadRecep().setRcTramites(null);
                tramite.getCUnidadRecep().setEdDeclaracioneses(null);
                tramite.getCUnidadRecep().setEdNotaAbonos(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listTramites;
    }

    @RequestMapping(value = "me/getPrioridades", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    List<GcPrioridad> getAllPrioridades(ModelMap map) {
        List<GcPrioridad> listPrioridades = null;
        try {
            listPrioridades = (List<GcPrioridad>) gcPrioridadRepository.getAllPrioridades();
            for (GcPrioridad s : listPrioridades) {
                s.setGcTiqueteList(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPrioridades;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "me/leerTiquete", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String leerTiquete(@RequestBody Map<String, String> map) {
        String r = "";
        String jso = "";
        String cusuario = getUsuario();
        String id = map.get("idTiquete") + "";
        System.out.println("id: " + id);
        r = gcTiqueteRepository.leerTiquete(id, cusuario);
        //GcConfTiquete rimg;
        //rimg=gcTiqueteRepository.getimg(Long.parseLong(id)); 
        JSONObject jo = new JSONObject();
        System.out.println("Resultado de r: " + r);
        jo.put("resultado", r);
        //jo.put("img",rimg.getXImagen());
        jso = jo.toJSONString();
        return jso;
    }

    @RequestMapping(value = "me/insertData", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String insertData(@RequestBody OpcionesPojo opciones, ModelMap map) {
        System.out.println("OPCIONES: " + opciones);
        GcTiquete tiquete = null;
        String jso = "";
        long r = 0;
        try {
            GcTiquete t = null;
            String cusuario = getUsuario();
            String cunidadRecep = getUnidad();
            SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            if (opciones.getIdTiquete() == null || opciones.getIdTiquete() == 0) { // Insertar nuevo
                t = new GcTiquete();
                t.setNTiqueteRea(0);
                t.setSCorrelativo(gcTiqueteRepository.getMaxCorrelativo(opciones.getTramite(), cunidadRecep));
                System.out.println("PRIORIDAD: " + gcPrioridadRepository.findOne(opciones.getPrioridad()));
                t.setNPrioridadId(gcPrioridadRepository.findOne(opciones.getPrioridad()));
                t.setCUnidadRecep(new TbUnidadRecep(cunidadRecep));
                t.setFhLlegada(new Date(gcTiqueteRepository.getServerDateTime().getTime()));
                System.out.println("182 " + t.getFhLlegada());
                System.out.println("Tiquete antes: " + t);
            } else { // Reassignacion
                t = (GcTiquete) gcTiqueteRepository.findOne(opciones.getIdTiquete());
                if (t.getMEstado() == 1 || t.getMEstado() == 3) {
                    gcTiqueteRepository.changeStatusTiquete(cusuario, opciones.getIdTiquete());
                }
                t.setNTiqueteId(null);
                t.setFhfProceso(null);
                t.setFhiProceso(null);
                t.setFhLlamado(null);
                t.setNTiqueteRea(1);
                String sFecha = gcTiqueteRepository.getfhDateReasignacion(opciones.getTramite(), cunidadRecep) == null ? new Date().toString() : gcTiqueteRepository.getfhDateReasignacion(opciones.getTramite(), cunidadRecep);
                Date fhFecha;
                if (sFecha.length() == 19) {
                   
                    fhFecha = (Date) ff.parse(sFecha);
                } else {
                    fhFecha = new Date(sFecha);
                }
                 System.out.println("sFecha.length() == 19 " + fhFecha);
                t.setFhLlegada(fhFecha);
                //t.setFhLlegada(Utils.addMinutesToDate(gcTiqueteRepository.getTiempoHolgura(opciones.getTramite(), cunidadRecep)==null?0:gcTiqueteRepository.getTiempoHolgura(opciones.getTramite(), cunidadRecep),fhFecha));
            }
            if (opciones.getHolgura() == 1) {
                t.setnTiempoHolgura(gcTiqueteRepository.getTiempoHolgura(opciones.getTramite(), cunidadRecep) == null ? 0 : gcTiqueteRepository.getTiempoHolgura(opciones.getTramite(), cunidadRecep));
            }
            t.setcUsuarioCrea(cusuario);
            TbUnidadRecep unidadRecep = (TbUnidadRecep) gcUnidadRecepRepository.getUnidadEnServicioE(cunidadRecep);
//	        unidadRecep.setRcTramites(null);
//	        unidadRecep.setEdDeclaracioneses(null);
//	        unidadRecep.setEdNotaAbonos(null);
            t.setCUnidadRecep(unidadRecep);
            t.setMEstado(1);
            if (opciones.getOpt().equals("1")) {
                t.setNit(Utils.quitarGuionesNIT(opciones.getNit()));
            }
            int countTr = gcTiqueteRepository.verifyIfExistsTramiteAsignado(opciones.getTramite(), cunidadRecep); 
            System.out.println("TRAMITE ASIGNADO: " + countTr);
            if (countTr != 0) {
                if (opciones.getIdTiquete() == null || opciones.getIdTiquete() == 0) {
                    t.setNTramiteId(tramitesRepository.findOne(opciones.getTramite()));
                } else if (t.getNTramiteId().getNTramiteId() != opciones.getTramite()) {
                    t.setNTramiteId(tramitesRepository.findOne(opciones.getTramite()));
                } else {
                    tiquete = new GcTiquete();
                }
//                    t.getNTramiteId().setGcUsuarioList(null);
//                    t.getNTramiteId().getNServiciosId().setGcTramiteList(null);
//                    t.getNTramiteId().setGcConfTramiteList(null);
//                    t.getNTramiteId().setGcReservaCitaList(null);
//                    t.getNTramiteId().setGcTiqueteList(null);
//                    t.getNPrioridadId().setGcTiqueteList(null);
                if (tiquete == null) {
                    if (t.getNTiqueteId() == null) {
                        t.setNTiqueteId(gcTiqueteRepository.getTotalId());
                    }
                    tiquete = (GcTiquete) gcTiqueteRepository.save(t);
                    System.out.println("TIQUETE RES: " + tiquete);
                }
                r = gcTiqueteRepository.sTiquete(t.getSCorrelativo());
            } else {
                tiquete = new GcTiquete();
            }
            System.out.println("Tiquete final: " + t);
            //Aqui le voy a pasar la fecha actual en lugar de GETDATE()
            System.out.println("CORRELATIVO: " + t.getSCorrelativo());

            System.out.println("R: " + r);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("nTiqueteId", r);
        jso = jo.toJSONString();
        return jso;
    }

    @RequestMapping(value = "me/insertData2", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    GcTiquete insertData2(@RequestBody OpcionesPojo opciones, ModelMap map) {
        GcTiquete tiquete = null;
        try {
            GcTiquete t = null;
            String cusuario = getUsuario();
            String cunidadRecep = getUnidad();
            SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            if (opciones.getIdTiquete() == null) { // Insertar nuevo
                t = new GcTiquete();
                t.setNTiqueteRea(0);
                t.setSCorrelativo(gcTiqueteRepository.getMaxCorrelativo(opciones.getTramite(), cunidadRecep));
                t.setNPrioridadId(gcPrioridadRepository.findOne(opciones.getPrioridad()));
                t.setFhLlegada(new Date());
            } else { // Reassignacion
                t = (GcTiquete) gcTiqueteRepository.findOne(opciones.getIdTiquete());
                if (t.getMEstado() == 1 || t.getMEstado() == 3) {
                    gcTiqueteRepository.changeStatusTiquete(cusuario, opciones.getIdTiquete());
                }
                t.setNTiqueteId(null);
                t.setFhfProceso(null);
                t.setFhiProceso(null);
                t.setFhLlamado(null);
                t.setNTiqueteRea(1);
                String sFecha = gcTiqueteRepository.getfhDateReasignacion(opciones.getTramite(), cunidadRecep) == null ? new Date().toString() : gcTiqueteRepository.getfhDateReasignacion(opciones.getTramite(), cunidadRecep);
                Date fhFecha;
                if (sFecha.length() == 19) {
                    fhFecha = (Date) ff.parse(sFecha);
                } else {
                    fhFecha = new Date(sFecha);
                }
                t.setFhLlegada(fhFecha);
                //t.setFhLlegada(Utils.addMinutesToDate(gcTiqueteRepository.getTiempoHolgura(opciones.getTramite(), cunidadRecep)==null?0:gcTiqueteRepository.getTiempoHolgura(opciones.getTramite(), cunidadRecep),fhFecha));
            }
            if (opciones.getHolgura() == 1) {
                t.setnTiempoHolgura(gcTiqueteRepository.getTiempoHolgura(opciones.getTramite(), cunidadRecep) == null ? 0 : gcTiqueteRepository.getTiempoHolgura(opciones.getTramite(), cunidadRecep));
            }
            t.setcUsuarioCrea(cusuario);
            TbUnidadRecep unidadRecep = (TbUnidadRecep) gcUnidadRecepRepository.getUnidadEnServicioE(cunidadRecep);
//	        unidadRecep.setRcTramites(null);
//	        unidadRecep.setEdDeclaracioneses(null);
//	        unidadRecep.setEdNotaAbonos(null);
            t.setCUnidadRecep(unidadRecep);
            t.setMEstado(1);
            if (opciones.getOpt().equals("1")) {
                t.setNit(Utils.quitarGuionesNIT(opciones.getNit()));
            }
            int countTr = gcTiqueteRepository.verifyIfExistsTramiteAsignado(opciones.getTramite(), cunidadRecep);
            if (countTr != 0) {
                if (opciones.getIdTiquete() == null) {
                    t.setNTramiteId(tramitesRepository.findOne(opciones.getTramite()));
                } else if (t.getNTramiteId().getNTramiteId() != opciones.getTramite()) {
                    t.setNTramiteId(tramitesRepository.findOne(opciones.getTramite()));
                } else {
                    tiquete = new GcTiquete();
                }
                //t.getNTramiteId().setGcUsuarioList(null);
                //t.getNTramiteId().getNServiciosId().setGcTramiteList(null);
                //t.getNTramiteId().setGcConfTramiteList(null);
                //t.getNTramiteId().setGcReservaCitaList(null);
                //t.getNTramiteId().setGcTiqueteList(null);
                //t.getNPrioridadId().setGcTiqueteList(null);
                if (tiquete == null) {
                    if (t.getNTiqueteId() == null) {
                        t.setNTiqueteId(gcTiqueteRepository.getTotalId());
                    }
                    tiquete = (GcTiquete) gcTiqueteRepository.saveTiquete(t);
                }
            } else {
                tiquete = new GcTiquete();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tiquete;
    }

    @RequestMapping(value = "me/validarNIT", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PersonaPojo validarNIT(@RequestBody OpcionesPojo opcion, ModelMap map) {
        PersonaPojo persona = null;
        RcRuc rcRuc = null;
        try {
            rcRuc = (RcRuc) rcRucRepository.findByNit(Utils.quitarGuionesNIT(opcion.getNit()));
            if (rcRuc != null) {
                rcRuc.setTbTipoContrib(null);
                persona = new PersonaPojo();
                persona.setNit(Utils.formatNit(rcRuc.getNit()));
                persona.setSNombres(rcRuc.getTbTipoContrib() != null ? rcRuc.getTbTipoContrib().getTbClaseContrib().getCclase().equalsIgnoreCase("N") ? rcRuc.getSNombres() + " " + rcRuc.getS1apeRasoc() + " " + rcRuc.getS2apeAbrev() : rcRuc.getS1apeRasoc() : rcRuc.getSNombres() + " " + rcRuc.getS1apeRasoc() + " " + rcRuc.getS2apeAbrev());
            } else {
                persona = new PersonaPojo();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return persona;
    }
    
    @RequestMapping(value = "me/validarID", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PersonaPojo validarID(@RequestBody OpcionesPojo opcion, ModelMap map) {
        PersonaPojo persona = null;
        GcAlumno gcAlumno = null;
        try {
            gcAlumno = cgAlumnoRepository.findOne(Utils.quitarGuionesNIT(opcion.getNit()));
            if (gcAlumno != null) {
               
                persona = new PersonaPojo();
                persona.setNit(gcAlumno.getNcarnet());
                persona.setSNombres(gcAlumno.getNombre()+" "+gcAlumno.getApellido());
               
            } else {
                persona = new PersonaPojo();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return persona;
    }

    public String getUnidad() {
        return getUnidadRecep();
    }

    //TODO: ya resueleve, era el aja con el url malformada, remover este todo luego
    @RequestMapping(value = "me/getYear", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String gerYear() {

        String year = "";
        SimpleDateFormat f = new SimpleDateFormat("yyyy");
        try {
            Date now = new Date();
            year = f.format(now);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return year;

    }

    private List<GcConfTramite> removeDuplicates(List<GcConfTramite> gcConfTramiteList) {
        List<GcConfTramite> result = new ArrayList<GcConfTramite>();

        Map gcConfTramiteMap = new HashMap();
        for (GcConfTramite c : gcConfTramiteList) {
            if (!gcConfTramiteMap.containsKey(c.getNTramiteId().getNServiciosId().getSNombre())) {
                gcConfTramiteMap.put(c.getNTramiteId().getNServiciosId().getSNombre(), c);
            }
        }

        result.addAll(gcConfTramiteMap.values());
        return result;
    }

    @RequestMapping(value = "/me/getUserName", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, String> getUserName() {
        Map<String, String> map = null;
        try {
            map = new HashMap<String, String>();
            map.put("user", getUsuario());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/me/isExistConfTramite", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, Integer> isExistConfTramite() {
        Map<String, Integer> map = null;
        try {
            String cunidadRecep = getUnidad();
            map = new HashMap<String, Integer>();
            map.put("isExist", gcTiqueteRepository.isExistConfTiquete(cunidadRecep));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

}
