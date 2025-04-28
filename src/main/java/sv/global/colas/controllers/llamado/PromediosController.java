package sv.global.colas.controllers.llamado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcTiquete;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.repositories.GcConfTramiteRepository;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUsuarioRepository;

@Controller
// @SessionAttributes({ "usuario" })
@EnableAsync
public class PromediosController extends MainController {

    @Autowired
    private GcTiqueteRepository gcTiqueteRepository;

    @Autowired
    private GcConfTramiteRepository gcConfTramiteRepository;

    @Autowired
    private GcUsuarioRepository gcUsuarioRepository;

    @Autowired
    private GcTramiteRepository gcTramiteRepository;

    @RequestMapping(value = "/llamado/getPromedios", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Map<String, String>> promediosByTramite(@RequestBody Map<String, String> centro) {
        List<Map<String, String>> promedioslist = new ArrayList<Map<String, String>>();
        List<GcConfTramite> listaTramitesByCS = gcConfTramiteRepository.listaTramitesByCS2(centro.get("centro"));
        for (GcConfTramite gcConfTramite : listaTramitesByCS) {
            Long nTramiteId = gcConfTramite.getNTramiteId().getNTramiteId();
            List<String> cUnidadRecep = new ArrayList<String>();
            cUnidadRecep.add(centro.get("centro"));
            Double promedioTiempoEspera = gcTiqueteRepository.tiempoPromedioEsperaByTramite(cUnidadRecep, nTramiteId);
            Double promedioTiempoAtencion = gcTiqueteRepository.tiempoPromAtencionByTramite(cUnidadRecep, nTramiteId);
            gcConfTramite.setnPromEspera(promedioTiempoEspera);
            gcConfTramite.setnPromAtencion(promedioTiempoAtencion);
            gcConfTramiteRepository.save(gcConfTramite);
            if (promedioTiempoEspera == null) {
                promedioTiempoEspera = (double) 0;
            }
            promedioTiempoAtencion = (promedioTiempoAtencion == null) ? (double) 0 : promedioTiempoAtencion;
            Map<String, String> promedios = new HashMap<String, String>();
            promedios.put("tramite", gcConfTramite.getNTramiteId().getSNombre());
            promedios.put("espera", promedioTiempoEspera.toString());
            promedios.put("atencion", promedioTiempoAtencion.toString());
            promedioslist.add(promedios);
        }
        return promedioslist;
    }

    @RequestMapping(value = "/llamado/getData", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, String> llamadoGetData(@RequestBody Map<String, String> centro) {
        Long idTiquete = gcTiqueteRepository.getProximoTiqueteLLamar(centro.get("centro"));
        Map<String, String> llamar = new HashMap<String, String>();

        if (idTiquete != null) {
            GcTiquete proximoLlamado = gcTiqueteRepository.findOne(idTiquete);
            System.out.println("Proximo LLAMADO: " + proximoLlamado.toString());
            if (proximoLlamado == null) {
                llamar.put("tiquete", "NoTiquete");

            } else {
                /*
                 * Alcides Nolasco: el estado 5 se coloca despues de cada llamado */
                 proximoLlamado.setMEstado(5);
                 gcTiqueteRepository.save(proximoLlamado);
                llamar.put("tiquete", proximoLlamado.getSCorrelativo());
                GcUsuario gcUsuario = gcUsuarioRepository.findOne(proximoLlamado.getcUsuarioAtendio());
                llamar.put("escritorio", gcUsuario.getNEscritorioId().getCIdentificador()
                        + gcUsuario.getNEscritorioId().getNNumEscritorio().toString());
                // Alcides Nolasco, se muestra en pantalla de llamado el nombre del servicio no
                // del tramite
                GcTramite gcTramite = gcTramiteRepository
                        .getTramiteById(proximoLlamado.getNTramiteId().getNTramiteId());
                llamar.put("tramite", gcTramite.getSNombre());
            }
        } else {
            llamar.put("tiquete", "NoTiquete");
        }

        return llamar;
    }
}
