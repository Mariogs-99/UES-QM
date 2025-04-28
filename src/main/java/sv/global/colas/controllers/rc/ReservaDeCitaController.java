package sv.global.colas.controllers.rc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import sv.global.colas.controllers.MainController;
import sv.global.colas.controllers.operacion.TiqueteDao;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcReservaCita;
import sv.global.colas.entities.GcTiquete;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.repositories.GcConfTramiteRepository;
import sv.global.colas.repositories.GcReservaCitaRepository;
import sv.global.colas.repositories.GcServiciosRepository;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;

@Controller
@SessionAttributes({"usuario", "tramiteform"})
@EnableAsync
public class ReservaDeCitaController extends MainController {

    @Autowired
    private GcTiqueteRepository gcTiqueteRepository;

    @Autowired
    GcUnidadRecepRepository gcUnidadRecepRepository;

    @Autowired
    private GcServiciosRepository gcServiciosRepository;

    @Autowired
    private GcTramiteRepository gcTramiteRepository;

    @Autowired
    private GcConfTramiteRepository gcConfTramiteRepository;

    @Autowired
    private GcReservaCitaRepository gcReservaCitaRepository;

    @RequestMapping("/rcita")
    public String pruebas(Model model) {
        return "rcita/rcita";
    }

    @RequestMapping(value = "/rcita/getsucursales", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    List<Map<String, String>> getSucursales() {

        List<TbUnidadRecep> unidades = null;
        List<Map<String, String>> tramites = new ArrayList<Map<String, String>>();
        try {
            unidades = (List<TbUnidadRecep>) gcUnidadRecepRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (TbUnidadRecep tbUnidadRecep : unidades) {
            Map<String, String> tramite = new HashMap<String, String>();
            tramite.put("cUnidadResepId", tbUnidadRecep.getCunidadRecep().toString());
            tramite.put("sNombre", tbUnidadRecep.getDunidadRecep().toString());
            tramites.add(tramite);
        }
        return tramites;
    }

    @RequestMapping(value = "/rcita/getServicios", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    List<Map<String, String>> getServicios() {
//		List<GcServicios> allServiciosActivo = gcServiciosRepository.getAllServiciosActivo();
        List<GcConfTramite> listaTramitesByCS2 = null;

        List<Map<String, String>> tramites = new ArrayList<Map<String, String>>();
        try {
            listaTramitesByCS2 = gcConfTramiteRepository.listaTramitesByCS3();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (GcConfTramite gcConfTramite : listaTramitesByCS2) {
            Map<String, String> tramite = new HashMap<String, String>();
            tramite.put("nServiciosId", gcConfTramite.getNTramiteId().getNTramiteId().toString());
            tramite.put("sNombre", gcConfTramite.getNTramiteId().getSNombre());
            tramites.add(tramite);
        }
        return tramites;
    }

    @RequestMapping(value = "/rcita/commit", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, String> servicios(@RequestBody Map<String, String> map) {
//		List<GcConfTramite> listTramites = null;
        GcReservaCita gcReservaCita = new GcReservaCita();
        gcReservaCita.setbEstado(6);
        TbUnidadRecep cUnidadRecep = gcUnidadRecepRepository.findOne(map.get("cUnidadResepId"));
        gcReservaCita.setCUnidadRecep(cUnidadRecep);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        Date fhReservacion = null;
        try {
            fhReservacion = sdf.parse(map.get("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        gcReservaCita.setNit(map.get("nCarnet"));
        gcReservaCita.setFhReservacion(fhReservacion);
        String nTelefono = map.get("nTelefono");//Aqui va el numero de telefono 
        gcReservaCita.setNTelefono(nTelefono);
        GcTramite nTramiteId = gcTramiteRepository.findOne(Long.valueOf(map.get("nTramiteId")));//Tramite a realizar 
        gcReservaCita.setNTramiteId(nTramiteId);
        //TODO: aqui he removido codigo de llamada a la base para que compile
        //TODO: $2a$10$/FV665.E7MpAFhISecNu9OMyFek9bP15Sg0nrjXP7CirfIHgWLNwu ever.argueta
        String nConfirmacion = "A0"; //+ //gcReservaCitaRepository.getCode(map.get("cUnidadResepId"));
        gcReservaCita.setsCodVerifica(nConfirmacion); // Aqui Codigo de verificacion 
        gcReservaCita.setSCorreo(map.get("sCorreo"));//Correo
        gcReservaCita.setSObservaciones(map.get("sObservaciones"));// observaciones 
        gcReservaCitaRepository.save(gcReservaCita);
        nConfirmacion = gcReservaCita.getNReservaCitaId().toString();
        Map<String, String> conf = new HashMap<String, String>();
        conf.put("code", nConfirmacion);
        conf.put("oficina", cUnidadRecep.getDunidadRecep());
        return conf;
    }

}
