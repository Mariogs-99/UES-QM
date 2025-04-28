package sv.global.colas.controllers.me;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcReservaCita;
import sv.global.colas.entities.GcTiquete;
import sv.global.colas.pojos.entities.ReservaCitaPojo;
import sv.global.colas.repositories.GcPrioridadRepository;
import sv.global.colas.repositories.GcReservaCitaRepository;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.repositories.TbListasValorRepository;
import sv.global.colas.utils.Estados;
import sv.global.colas.utils.Utils;

@Controller
@EnableAsync
public class ReservaCitaController extends MainController {

    @Autowired
    GcReservaCitaRepository gcReservaCitaRepository;

    @Autowired
    GcUnidadRecepRepository gcUnidadRecepRepository;

    @Autowired
    GcTiqueteRepository gcTiqueteRepository;

    @Autowired
    GcUsuarioRepository gcUsuarioRepository;

    @Autowired
    TbListasValorRepository tbListasValorRepository;

    @Autowired
    GcTramiteRepository gcTramiteRepository;

    @Autowired
    GcPrioridadRepository gcPrioridadRepository;

    @RequestMapping(value = "/rcta/data/{str}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    List<ReservaCitaPojo> getData(@PathVariable(value = "str") String str) {
        List<ReservaCitaPojo> cita = null;
        List<String> lUnidadRecep = null;
        //SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
        Utils util = null;
        try {
            util = new Utils();
            String cunidadRecep = getUnidad();
            lUnidadRecep = new ArrayList<String>();
            lUnidadRecep.add(cunidadRecep);
            gcReservaCitaRepository.disableReservaCitaVencidas(cunidadRecep);
            List<GcReservaCita> rcta = (List<GcReservaCita>) gcReservaCitaRepository.getData(lUnidadRecep);
            cita = new ArrayList<ReservaCitaPojo>();
            for (GcReservaCita rs : rcta) {
                ReservaCitaPojo cpojo = new ReservaCitaPojo();
                cpojo.setAreaServicio(rs.getNTramiteId().getNServiciosId().getSNombre());
                cpojo.setCodigoVerificacion(rs.getsCodVerifica());
                cpojo.setCorreo(rs.getSCorreo());
                cpojo.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(rs.getFhReservacion()));
                cpojo.setHora(new SimpleDateFormat("hh:mm:ss").format(rs.getFhReservacion()));
                cpojo.setNit(Utils.formatNit(rs.getNit()));
                cpojo.setTelefono(Utils.formatTelefono(rs.getNTelefono()));
                cpojo.setTramite(rs.getNTramiteId().getSNombre());
                cpojo.setUnidad(rs.getCUnidadRecep().getDunidadRecep());
                cpojo.setEstado(Estados.getEstado(rs.getbEstado()));
                cpojo.setIdReservaCita(rs.getNReservaCitaId());
                cpojo.setIdTramite(rs.getNTramiteId().getNTramiteId());
                if (str.equals("Todos")) {
                    cita.add(cpojo);
                } else if (util.contains(str, cpojo)) {
                    cita.add(cpojo);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cita;
    }

    @RequestMapping(value = "/rcta/enableReservacion", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Integer enableReservacion(@RequestBody ReservaCitaPojo rctaPojo) {
        Integer result = 0;
        SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        GcTiquete gcTiquete = null;
        try {
            String cunidadRecep = getUnidad();
            int countTr = gcTiqueteRepository.verifyIfExistsTramiteAsignado(rctaPojo.getIdTramite(), cunidadRecep);
            if (countTr > 0) {

                String correlativo = gcTiqueteRepository.getMaxCorrelativo(rctaPojo.getIdTramite(), cunidadRecep);
                Date fhReservacion = ff.parse(rctaPojo.getFecha());
                fhReservacion.setTime(new Date().getTime());
                Date date = dateFormat.parse(dateFormat.format(fhReservacion));
                gcTiquete = new GcTiquete();
                gcTiquete.setCUnidadRecep(gcUnidadRecepRepository.getUnidadEnServicioE(cunidadRecep));
                gcTiquete.setNTramiteId(gcTramiteRepository.getTramiteById(rctaPojo.getIdTramite()));
                gcTiquete.setNTiqueteRea(0);
                gcTiquete.setSCorrelativo(correlativo);
                gcTiquete.setNit(Utils.quitarGuionesNIT(rctaPojo.getNit()));
                gcTiquete.setMEstado(1);
                gcTiquete.setFhLlegada(date);
                gcTiquete.setcUsuarioCrea(getUsuario());
                gcTiquete.setNReservaCitaId(gcReservaCitaRepository.getReservaCitaById(rctaPojo.getIdReservaCita()));
                Date now = new Date();
                if (now.after(date)) {
                    gcTiquete.setNPrioridadId(gcPrioridadRepository.getPrioridadeById(new Long(1)));
                    gcTiquete.setNReservaCitaId(null);
                } else {
                    gcTiquete.setNPrioridadId(gcPrioridadRepository.getPrioridadeById(new Long(2)));
                }
                GcTiquete t = (GcTiquete) gcTiqueteRepository.save(gcTiquete);
                if (t != null) {
                    gcReservaCitaRepository.actualizarEstado(4, rctaPojo.getIdReservaCita());
                    result = Integer.parseInt(t.getNTiqueteId().toString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/rcta/confirmaCita", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, String> confirmaCita(@RequestBody Map<String, String> map) {

        Long citaId = Long.valueOf(map.get("citaId"));
        GcReservaCita cita = gcReservaCitaRepository.getReservaCitaById(citaId);

        Map<String, String> data = new HashMap<String, String>();
        SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        if (cita == null) {
            data.put("mensaje", "Código de cita no existe en esta unidad receptora");
            return data;
        }

        if (cita.getbEstado().intValue() == 4) {
            data.put("mensaje", "El código de cita ya fue usado, pida nueva cita");
            return data;
        }

        String cunidadRecep = getUnidad();
        if (!cunidadRecep.equalsIgnoreCase(cita.getCUnidadRecep().getCunidad())) {
            data.put("mensaje", "Error, su cita es en otra oficina: " + cita.getCUnidadRecep().getDunidadRecep());
            return data;
        }

        Date fhReservacion = cita.getFhReservacion();
        Date now = new Date();

        // Agregar verificación de 10 minutos antes y después de la hora de la cita
        Calendar calendar = Calendar.getInstance();

        // 10 minutos antes
        calendar.setTime(fhReservacion);
        calendar.add(Calendar.MINUTE, -10);
        Date tenMinutesBefore = calendar.getTime();

        // 10 minutos después
        calendar.setTime(fhReservacion);
        calendar.add(Calendar.MINUTE, 10);
        Date tenMinutesAfter = calendar.getTime();

        // Validar si el usuario está dentro de los 10 minutos permitidos antes o después de la cita
        if (now.before(tenMinutesBefore) || now.after(tenMinutesAfter)) {
            data.put("mensaje", "Error, solo puede confirmar su cita 10 minutos antes o después de la hora programada: " + dateFormat.format(fhReservacion));
            return data;
        }

        // Verificación de la fecha de la cita
        if (!ff.format(now).equals(ff.format(fhReservacion))) {
            data.put("mensaje", "Error, su cita está programada para otra fecha diferente a hoy: " + dateFormat.format(fhReservacion));
            return data;
        }

        String result = "0";
        GcTiquete gcTiquete = null;

        try {
            int countTr = gcTiqueteRepository.verifyIfExistsTramiteAsignado(cita.getNTramiteId().getNTramiteId(), cunidadRecep);
            if (countTr > 0) {
                String correlativo = gcTiqueteRepository.getMaxCorrelativo(cita.getNTramiteId().getNTramiteId(), cunidadRecep);

                gcTiquete = new GcTiquete();
                gcTiquete.setCUnidadRecep(gcUnidadRecepRepository.getUnidadEnServicioE(cunidadRecep));
                gcTiquete.setNTramiteId(cita.getNTramiteId());
                gcTiquete.setNTiqueteRea(0);
                gcTiquete.setSCorrelativo(correlativo);
                gcTiquete.setNit(Utils.quitarGuionesNIT(cita.getNit()));
                gcTiquete.setMEstado(1);
                gcTiquete.setFhLlegada(fhReservacion);
                gcTiquete.setcUsuarioCrea(getUsuario());
                gcTiquete.setNReservaCitaId(cita);

                if (now.after(fhReservacion)) {
                    gcTiquete.setNPrioridadId(gcPrioridadRepository.getPrioridadeById(new Long(1)));
                    gcTiquete.setNReservaCitaId(null);
                } else {
                    gcTiquete.setNPrioridadId(gcPrioridadRepository.getPrioridadeById(new Long(2)));
                }

                GcTiquete t = gcTiqueteRepository.save(gcTiquete);
                if (t != null) {
                    gcReservaCitaRepository.actualizarEstado(4, citaId);
                    result = t.getNTiqueteId().toString();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            data.put("mensaje", "Ocurrió un error en el sistema y no es posible atender citas por esta vez, genere un ticket para su trámite");
            return data;
        }

        data.put("mensaje", "ok");
        data.put("ticket", result);
        return data;
    }

    public String getUnidad() {
        return getUnidadRecep();
    }

}
