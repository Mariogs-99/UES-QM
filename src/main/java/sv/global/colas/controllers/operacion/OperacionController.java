package sv.global.colas.controllers.operacion;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfLlamado;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcJerarquia;
import sv.global.colas.entities.GcJerarquiaSeccion;
import sv.global.colas.entities.GcPreguntas;
import sv.global.colas.entities.GcPreguntasRespuestas;
import sv.global.colas.entities.GcPrioridad;
import sv.global.colas.entities.GcRespuestas;
import sv.global.colas.entities.GcTiquete;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.GcUserLog;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.entities.TbListasValor;
import sv.global.colas.repositories.GcConfLlamadoRepository;
import sv.global.colas.repositories.GcConfTramiteRepository;
import sv.global.colas.repositories.GcEventosRepository;
import sv.global.colas.repositories.GcJerarquiaRepository;
import sv.global.colas.repositories.GcJerarquiaSeccionRepository;
import sv.global.colas.repositories.GcPreguntasRepository;
import sv.global.colas.repositories.GcPreguntasRespuestasRepository;
import sv.global.colas.repositories.GcPrioridadRepository;
import sv.global.colas.repositories.GcRespuestasRepository;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUserLogRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.repositories.TbListasValorRepository;
import sv.global.colas.security.DgiiUserDetailsImpl;

@Controller
@SessionAttributes({"usuario", "tramiteform"})
@EnableAsync
public class OperacionController extends MainController {

    @Autowired
    private GcTiqueteRepository gcTiqueteRepository;

    @Autowired
    private GcTramiteRepository gcTramiteRepository;

    @Autowired
    private GcTramiteRepository gcTramitesRepository;

    @Autowired
    private GcUserLogRepository gcUserLogRepository;

    @Autowired
    private GcUsuarioRepository gcUsuarioRepository;

    @Autowired
    private GcEventosRepository gcEventosRepository;

    @Autowired
    private GcPreguntasRepository gcPreguntasRepository;

    @Autowired
    private GcRespuestasRepository gcRespuestasRepository;

    @Autowired
    private GcPreguntasRespuestasRepository gcPreguntasRespuestasRepository;

    @Autowired
    private GcConfLlamadoRepository gcConfLlamadoRepository;

    @Autowired
    private GcPrioridadRepository gcPrioridadRepository;

    @Autowired
    TbListasValorRepository tbListasValorRepository;

    @Autowired
    GcConfTramiteRepository gcConfTramiteRepository;

    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;

    @Autowired
    private GcJerarquiaRepository gcJerarquiaRepository;

    @Autowired
    private GcJerarquiaSeccionRepository gcJerarquiaSeccionRepository;

    @SuppressWarnings("deprecation")
    Date tiempoProceso = new Date(2015, 11, 1);

    @RequestMapping("/operacion/home")
    public String operacion(ModelMap map) {
        List<GcConfLlamado> confsByCS = gcConfLlamadoRepository.getConfsByCS(getUnidad());
        if (confsByCS.isEmpty()) {
            map.addAttribute("timer1", 30000); //15 segundos
            map.addAttribute("timer2", 35000); //20 segundos
            map.addAttribute("nllamados", 3);
        } else {
            map.addAttribute("timer1", 30000);
            map.addAttribute("timer2", 35000);
            map.addAttribute("nllamados", 2);
        }
        GcUsuario gcUsuario = gcUsuarioRepository.findOne(getUsuario());
        if (gcUsuario == null) {
            map.addAttribute("usrConfigurado", "false");
        } else {
            map.addAttribute("usrConfigurado", "true");
            map.addAttribute("escritorio", gcUsuario.getNEscritorioId().getCIdentificador() + gcUsuario.getNEscritorioId().getNNumEscritorio());
        }
        List<GcUserLog> enPausa = gcUserLogRepository.enPausa(getUsuario());
        if (enPausa.isEmpty()) {
            map.addAttribute("enPausa", "false");
        } else {
            map.addAttribute("enPausa", "true");
        }
        return "operacion/operacion";
    }

    // @RequestMapping("/ini_tra")
    // public String iniciarTramite(ModelMap map) {
    // turno= "";
    // botones.setBtnTramite("false");
    // // llamando. = Boolean.FALSE;
    // return "operacion/operacion";
    // }
    @RequestMapping(value = "/siguiente", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, Object> siguiente(@RequestBody Map<String, Long> map) {
        Map siguiente = new HashMap();

        if (map.containsKey("gcTiqueteId") && map.get("gcTiqueteId") != null && map.get("gcTiqueteId") != 0) {
            try {
                GcTiquete gcTiqueteNoAtendido = gcTiqueteRepository.findOne(map.get("gcTiqueteId"));
                if (gcTiqueteNoAtendido != null) {
                    gcTiqueteNoAtendido.setMEstado(5);
                    gcTiqueteNoAtendido.setcUsuarioAtendio(getUsuario());
                    System.out.println("GUARDANDO USUARIO ATENDIO: " + getUsuario());
                    gcTiqueteRepository.save(gcTiqueteNoAtendido);
                } else {
                    System.out.println("El GcTiquete con el ID especificado no existe en la base de datos.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error en el módulo de operación en el método de siguiente al tratar de cambiar el estado del tiquete.");
            }
        }

        try {
            GcTiquete gcTiqueteEnAtencion = siguiente();
            System.out.println("Siguiente Tickete: " + gcTiqueteEnAtencion);

            if (gcTiqueteEnAtencion == null) {
                siguiente.put("turno", "No hay clientes en la cola");
                return siguiente;
            }

            String currentUser = getUsuario();

            // Si la validación pasa, asignamos cUsuarioAtendio ahora
            gcTiqueteEnAtencion.setcUsuarioAtendio(currentUser);
            gcTiqueteEnAtencion.setMEstado(2);
            gcTiqueteRepository.save(gcTiqueteEnAtencion);  // Guardar después de asignar el usuario

            // Procedemos con la lógica si la validación pasa
            String turno = "Llamando al número: " + gcTiqueteEnAtencion.getSCorrelativo() + " Tramite: " + gcTiqueteEnAtencion.getNTramiteId().getSNombre();
            siguiente.put("turno", turno);
            siguiente.put("gcTiqueteId", gcTiqueteEnAtencion.getNTiqueteId());
            siguiente.put("sCorrelativo", gcTiqueteEnAtencion.getSCorrelativo());
            siguiente.put("gcPrioridad", gcTiqueteEnAtencion.getNPrioridadId().getNPrioridadId());

            Map llamar = new HashMap();
            llamar.put("tiquete", gcTiqueteEnAtencion.getSCorrelativo());

            GcUsuario gcUsuario = gcUsuarioRepository.findOne(currentUser);
            llamar.put("escritorio", gcUsuario.getNEscritorioId().getCIdentificador() + gcUsuario.getNEscritorioId().getNNumEscritorio().toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ocurrió un error en el modulo de operación en el método de siguiente al tratar de determinar el siguiente.");
        }

        return siguiente;
    }

    private GcTiquete siguiente() {
        String usuario = getUsuario();  // El operador actual
        String unidad = getUnidad();

        // Intentamos obtener un escalamiento si existe
        GcTiquete nextEscalamiento = gcTiqueteRepository.getNextEscalamiento(usuario);
        if (nextEscalamiento != null) {
            if (nextEscalamiento.getcUsuarioAtendio() != null && nextEscalamiento.getcUsuarioAtendio().equals(usuario)) {
                // Si el tiquete fue atendido previamente por el mismo usuario, lo saltamos
                return null;
            }
            nextEscalamiento.setMEstado(2);  // Estado de tiquete en atención
            Date fhLlamado = new Date(gcTiqueteRepository.getServerDateTime().getTime());
            nextEscalamiento.setFhLlamado(fhLlamado);
            return nextEscalamiento;
        } else {
            // Buscamos el siguiente tiquete disponible
            List<Long> nextId = gcTiqueteRepository.getNextId(unidad, usuario);
            if (nextId == null || nextId.isEmpty()) {
                return null;  // No hay más tiquetes en la cola
            }
            Long l = Long.parseLong(nextId.get(0) + "");
            GcTiquete findOne = gcTiqueteRepository.findOne(l);

            //findOne.setMEstado(2);  // Estado que indica que el tiquete está en atención
            Date fhLlamado = new Date(gcTiqueteRepository.getServerDateTime().getTime());
            findOne.setFhLlamado(fhLlamado);
            gcTiqueteRepository.save(findOne);
            return findOne;
        }
    }

    public String getUnidad() {
        return getUnidadRecep();
    }

//        private String getUnidad() {
//	        String unidad = getPrincipal().getUnidadRecep();
//	        if (unidad == null) {
//	            unidad = getPrincipal().getUbicacionFisica();
//	        }
//                unidad = gcUnidadRecepRepository.getCsCombinacion(unidad);
//	        return unidad;
//	}
    @RequestMapping(value = "/iniciar_tramite", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    TiqueteDao initTram(@RequestBody Map<String, Long> map) {
        TiqueteDao miTiquete = new TiqueteDao();
        try {
            GcTiquete gcTiquete = gcTiqueteRepository.findOne(map.get("gcTiqueteId"));
            System.out.println("Tickete: " + gcTiquete);
            gcTiquete.setMEstado(3);
            Date fhiProceso = new Date(gcTiqueteRepository.getServerDateTime().getTime());
            System.out.println("fhiProceso: " + fhiProceso);
            gcTiquete.setFhiProceso(fhiProceso);
            gcTiqueteRepository.save(gcTiquete);

            SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
            miTiquete.setFhiProceso(sdf.format(fhiProceso));
            miTiquete.setsCorrelativo(gcTiquete.getSCorrelativo());
            miTiquete.setnTramiteId(gcTiquete.getNTramiteId().getSNombre());
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método de iniciar tramite.");
        }
        return miTiquete;
    }

    @RequestMapping(value = "/finalizar_tramite", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    TiqueteDao finalizarTram(@RequestBody Map<String, Long> map) {
        TiqueteDao dao = new TiqueteDao();
        try {
            GcTiquete gcTiquete = gcTiqueteRepository.findOne(map.get("gcTiqueteId"));
            gcTiquete.setMEstado(4);
            gcTiquete.setFhfProceso(new Date(gcTiqueteRepository.getServerDateTime().getTime()));
            gcTiqueteRepository.save(gcTiquete);
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método de finalizar tramite.");
        }
        return dao;
    }

    @RequestMapping(value = "/escalamiento", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    TiqueteDao escalamientoTram(@RequestBody Map<String, Long> map) {
        TiqueteDao dao = new TiqueteDao();
        try {
            GcTiquete gcTiquete = gcTiqueteRepository.findOne(map.get("gcTiqueteId"));
            gcTiquete.setMEstado(4);
            gcTiquete.setFhfProceso(new Date(gcTiqueteRepository.getServerDateTime().getTime()));
            gcTiquete.setNTiqueteRea(1);
            gcTiqueteRepository.save(gcTiquete);
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método de finalizar tramite.");
        }
        return dao;
    }

    @RequestMapping(value = "/reasignar", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    TiqueteDao reasginarmientoTram(@RequestBody Map<String, Long> map) {
        TiqueteDao dao = new TiqueteDao();
        try {
            GcTiquete gcTiquete = gcTiqueteRepository.findOne(map.get("gcTiqueteId"));
            gcTiquete.setMEstado(1);

            GcPrioridad gcPrioridad = gcPrioridadRepository.findOne(new Long(2));
            gcTiquete.setNPrioridadId(gcPrioridad);
            gcTiqueteRepository.save(gcTiquete);
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método de finalizar tramite.");
        }
        return dao;
    }

    @RequestMapping(value = "/llamarDeNuevo", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Integer enableReservacion(@RequestBody Map<String, Long> map) {
        try {
            GcTiquete gcTiquete = gcTiqueteRepository.findOne(map.get("gcTiqueteId"));
            if (gcTiquete != null || gcTiquete.getMEstado() == 5) {
                gcTiquete.setMEstado(2);
                Date fhLlamado = new Date(gcTiqueteRepository.getServerDateTime().getTime());
                gcTiquete.setFhLlamado(fhLlamado);
                gcTiqueteRepository.save(gcTiquete);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método de llamar de nuevo.");
        }
        return 0;
    }

    @RequestMapping(value = "/tramites", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    List<GcTramite> tramites(ModelMap map) {
        List<GcTramite> listTramites = null;
        try {
            listTramites = (List<GcTramite>) gcTramitesRepository.getTramitesEscalamiento();
            for (GcTramite tramite : listTramites) {
                tramite.setGcUsuarioList(null);
                tramite.setGcReservaCitaList(null);
                tramite.getNServiciosId().setGcTramiteList(null);
                tramite.setGcConfTramiteList(null);
                tramite.setGcTiqueteList(null);
            }
        } catch (Exception ex) {
            System.out.println("Ocurrió un error en el modulo de operación en el método de obtener tramite.");
        }
        return listTramites;
    }

    @RequestMapping(value = "/pausa", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, String> pausa(@RequestBody Map<String, Long> request, ModelMap map) {
        Map<String, String> mapa = new HashMap<String, String>();
        try {
            GcUserLog gcUserLog = new GcUserLog();
            GcUsuario cUsuario = gcUsuarioRepository.findOne(getUsuario());
            gcUserLog.setCUsuario(cUsuario);
            gcUserLog.setFhiEvento(new Date(gcTiqueteRepository.getServerDateTime().getTime()));
            gcUserLog.setNEventoId(gcEventosRepository.findOne(request.get("nEventoId")));
            gcUserLogRepository.save(gcUserLog);
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método pausa.");
        }
        return mapa;
    }

    @RequestMapping(value = "/finPausa", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, String> finPausa(@RequestBody Map<String, Long> request, ModelMap map) {
        Map<String, String> mapa = new HashMap<String, String>();
        try {
            List<GcUserLog> enPausa = gcUserLogRepository.enPausa(getUsuario());
            if (!enPausa.isEmpty()) {
                GcUserLog gcUserLog = enPausa.get(0);
                gcUserLog.setFhfEvento(new Date(gcTiqueteRepository.getServerDateTime().getTime()));
                gcUserLogRepository.save(gcUserLog);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método fin pausa.");
        }
        return mapa;
    }

    @RequestMapping(value = "/getPreguntas", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    List<Map<String, Object>> getPreguntas(ModelMap map) {
        List<GcPreguntas> gcPreguntasList = gcPreguntasRepository.preguntaYrespuestas(getUnidad());
        List<Map<String, Object>> preguntayRespuestasList = new ArrayList<Map<String, Object>>();
        try {
            for (GcPreguntas gcPregunta : gcPreguntasList) {
                Long nPreguntaId = gcPregunta.getNPreguntaId();
                Map<String, Object> gcPreguntas = new HashMap<String, Object>();
                gcPreguntas.put("dPregunta", gcPregunta.getSPregunta());
                gcPreguntas.put("nPreguntaId", gcPregunta.getNPreguntaId());
                gcPreguntas.put("nRespuestaId", null);
                List<GcRespuestas> respuestasByPregutna = gcRespuestasRepository.respuestasByPregutna(nPreguntaId);
                List<Map<String, Object>> gcRespuestasList = new ArrayList<Map<String, Object>>();
                for (GcRespuestas gcRespuesta : respuestasByPregutna) {
                    Map<String, Object> gcRespuestas = new HashMap<String, Object>();
                    gcRespuestas.put("nRespuestaId", gcRespuesta.getNRespuestaId());
                    gcRespuestas.put("sRespuesta", gcRespuesta.getSRespuesta());
                    gcRespuestasList.add(gcRespuestas);
                }
                gcPreguntas.put("gcRespuestasList", gcRespuestasList);
                preguntayRespuestasList.add(gcPreguntas);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método getPreguntas al tratar de obtener las preguntas.");
        }
        return preguntayRespuestasList;
    }

    @RequestMapping(value = "/setRespuestas", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Map<String, Object>> setRespuestas(@RequestBody Map<String, List> respuestas, ModelMap map) {
        List<Map<String, Object>> preguntayRespuestasList = new ArrayList<Map<String, Object>>();
        try {
            List<Map<String, Double>> list = respuestas.get("gcRespuestasList");
            List<GcPreguntasRespuestas> respuestasList = new ArrayList<GcPreguntasRespuestas>();
            for (Map<String, Double> map2 : list) {
                GcPreguntasRespuestas gcRespuestas = new GcPreguntasRespuestas();
                gcRespuestas.setCUsuario(getUsuario());
                gcRespuestas.setFhRespondio(new Date(gcTiqueteRepository.getServerDateTime().getTime()));
                gcRespuestas.setNRespuestaId(gcRespuestasRepository.findOne(map2.get("nRespuestaId").longValue()));
                respuestasList.add(gcRespuestas);
            }
            gcPreguntasRespuestasRepository.save(respuestasList);
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método setRespuesta al tratar guardar las respuesta en la base de datos.");
        }
        return preguntayRespuestasList;
    }

    @RequestMapping(value = "/operacion/getTramites", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    List<Map<String, String>> servicios(ModelMap map) {
        List<Map<String, String>> response = new ArrayList<Map<String, String>>();
        List<GcConfTramite> listTramites = null;
        List<String> lUnidadRecep = null;
        try {
            String cunidadRecep = getPrincipal().getUbicacionFisica();
            lUnidadRecep = new ArrayList<String>();
            List<TbListasValor> unidadRecep = (List<TbListasValor>) tbListasValorRepository.getUnidadesReceptoras(cunidadRecep);
            if (!unidadRecep.isEmpty()) {
                for (TbListasValor tl : unidadRecep) {
                    lUnidadRecep.add(tl.getId().getClista());
                }
            } else {
                lUnidadRecep.add(cunidadRecep);
            }
            listTramites = (List<GcConfTramite>) gcConfTramiteRepository.listaTramitesByCS(lUnidadRecep);
            for (GcConfTramite tramite : listTramites) {
                Map<String, String> mapa = new HashMap<String, String>();
                int countTr = gcTiqueteRepository.verifyIfExistsTramiteAsignado(tramite.getNTramiteId().getNTramiteId(), cunidadRecep);
                if (countTr != 0) {
                    mapa.put("nTramiteId", tramite.getNTramiteId().getNTramiteId().toString());
                    mapa.put("sNombre", tramite.getNTramiteId().getSNombre().toString());
                    response.add(mapa);
                }
            }
        } catch (Exception ex) {

        }
        return response;
    }

    @RequestMapping(value = "/operacion/getEscalamiento/{idTiquete}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    List<Map<String, String>> escalamiento(@PathVariable(value = "idTiquete") Long idTiquete, ModelMap map) {
        List<Map<String, String>> response = new ArrayList<Map<String, String>>();
        if (idTiquete.equals(null)) {
            Map<String, String> mapa = new HashMap<String, String>();
            mapa.put("nTramiteId", "");
            mapa.put("sNombre", "idTiquete es null");
            response.add(mapa);
            return response;
        } else {
            try {
                List<GcJerarquia> jeraruiaByTiqueteIdList = gcJerarquiaRepository.getJeraruiaByTiqueteId(idTiquete);
                for (GcJerarquia gcJerarquia : jeraruiaByTiqueteIdList) {
                    Map<String, String> mapa = new HashMap<String, String>();
                    mapa.put("nTramiteId", gcJerarquia.getNJerarquiaId().toString());
                    mapa.put("sNombre", gcJerarquia.getSJerarquia());
                    response.add(mapa);
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error en el modulo de operación en el método getEscalamiento obtener la lista de escalamientos.");
            }
        }
        return response;
    }

    @RequestMapping(value = "/operacion/realizarEscalamiento", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, String> realizarEscalamiento(@RequestBody Map<String, Long> escalamiento) {

        GcTiquete tiqueteEscalamiento = gcTiqueteRepository.findOne(escalamiento.get("idTiquete"));
        Map<String, String> response = new HashMap<String, String>();
        try {
            if (tiqueteEscalamiento == null) {
                response.put("reponse", "fail");
                return response;
            }
            tiqueteEscalamiento.setNTiqueteId(null);
            tiqueteEscalamiento.setNTiqueteRea(2);
            tiqueteEscalamiento.setMEstado(1);
            escalamiento.get("jerarquiaId");
            GcJerarquiaSeccion jerarquiaSeccionByTiquete = gcJerarquiaSeccionRepository.getJerarquiaSeccionByTiquete(escalamiento.get("idTiquete"), escalamiento.get("jerarquiaId"));
            tiqueteEscalamiento.setnJrqSecId(jerarquiaSeccionByTiquete.getNJrqSecId().intValue());
            gcTiqueteRepository.save(tiqueteEscalamiento);
            response.put("reponse", "OK");
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el modulo de operación en el método realizarEscalamiento");
        }
        return response;
    }

}
