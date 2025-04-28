/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.socket;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcNotificacion;
import sv.global.colas.pojos.entities.MensajePojo;
import sv.global.colas.pojos.entities.TipoUsuarioPojo;
import sv.global.colas.repositories.GcAlertaMachRepositoryImpl;
import sv.global.colas.repositories.GcNotificacionRepository;

/**
 *
 * @author ever.argueta
 */
@Controller
@EnableAsync
public class MensajeController extends MainController {

    private GcNotificacionRepository gcNotificacionRepository;
    private List<GcNotificacion> notificaciones = new ArrayList<GcNotificacion>();
    private List<MensajePojo> mensajes = new ArrayList<MensajePojo>();
    private TipoUsuarioPojo usuario = new TipoUsuarioPojo();
    private GcAlertaMachRepositoryImpl alertaMachRepository;

    @Autowired
    public MensajeController(GcNotificacionRepository gcNotificacionRepository, GcAlertaMachRepositoryImpl alertaMachRepository) {
        this.gcNotificacionRepository = gcNotificacionRepository;
        this.alertaMachRepository = alertaMachRepository;
    }

    @MessageMapping("/hello")
    public void greeting(TipoUsuarioPojo user) throws Exception {
        this.usuario = user;
    }
    
    @MessageMapping("/clear")
    public void clear() {
        this.mensajes.clear();
    }
    
    @MessageMapping("/read")
    public void read(MensajePojo mensaje) {
        alertaMachRepository.readAlerta(mensaje.getId(), mensaje.getUsuario());
    }
    
    @MessageMapping("/list")
    @SendTo("/topic/stocks")
    public List<MensajePojo> getStock() {
        
        alertaMachRepository.machAlertas(this.usuario.getUsuario(), this.usuario.getTipo());
        String datos[] = this.usuario.getUsuario().split(",");

        notificaciones = gcNotificacionRepository.findNotificaciones(datos[0]);

        for (GcNotificacion notificacion : notificaciones) {
            MensajePojo mensaje = new MensajePojo();
            mensaje.setId(notificacion.getGcNotificacionId().getNAlertaId());
            mensaje.setUsuario(notificacion.getGcNotificacionId().getCUsuario());
            mensaje.setMensaje(notificacion.getGcAlerta().getSMensaje());
            mensaje.setTipo(notificacion.getGcAlerta().getSTipo());
            mensaje.setCorreo(notificacion.getBCorreo());
            mensaje.setPantalla(notificacion.getBPantalla());
            mensaje.setCreacion(notificacion.getFhNotificacion());
            mensajes.add(mensaje);
        }

        return mensajes;
    }
}
