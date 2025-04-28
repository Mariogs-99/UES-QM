/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import sv.global.colas.entities.GcAlerta;
import sv.global.colas.entities.GcNotificacion;
import sv.global.colas.entities.GcNotificacionId;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.utils.Utils;

/**
 *
 * @author ever.argueta
 */
public class GcAlertaMachRepositoryImpl implements GcAlertaMachRepository {

    @Autowired
    private GcAlertaRepository alertaRepository;
    @Autowired
    private GcNotificacionRepository notificacionRepository;
    @Autowired
    private GcUsuarioRepository usuarioRepository;

    @Override
    public void machAlertas(String usuario, String tipo) {
        String[] tipos=tipo.split(",");
        List<GcAlerta> listado;
        if(tipos.length>1){
            listado = alertaRepository.getAlertasActivas(tipos[0], new Date());
            for(int i=1;i<tipos.length;i++){
                List<GcAlerta> listado2=alertaRepository.getAlertasActivas(tipos[i], new Date());
                if(listado2!=null){
                    listado.addAll(listado2);
                }
            }
        }else{
            listado = alertaRepository.getAlertasActivas(tipo, new Date());
        }
        
        String datos[] = usuario.split(",");
        GcUsuario user = usuarioRepository.findOne(datos[0]);
        for (GcAlerta alerta : listado) {
            if (alerta.getCUnidadRecep().getCunidadRecep().equalsIgnoreCase(datos[1])) {
                if(user == null) {
                    GcNotificacionId id = new GcNotificacionId(alerta.getNAlertaId(), datos[0]);
                    GcNotificacion notificacion = notificacionRepository.findOne(id);
                    if (Utils.isNullOrEmpty(notificacion)) {
                        notificacion = new GcNotificacion(id);
                        notificacion.setBCorreo(alerta.getBCorreo());
                        notificacion.setBPantalla(alerta.getBPantalla());
                        notificacion.setFhNotificacion(new Date());
                        notificacion.setMEstado("0");
                        notificacion.setGcUsuario(datos[0]);
                        notificacion.setGcAlerta(alerta);
                        try {
                            notificacionRepository.save(notificacion);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (alerta.getNServiciosId().toString().equalsIgnoreCase(user.getNEscritorioId().getNZonaId().getNZonaId().toString())
                            || alerta.getNServiciosId() == null || alerta.getNServiciosId() == 0) {
                        GcNotificacionId id = new GcNotificacionId(alerta.getNAlertaId(), datos[0]);
                        GcNotificacion notificacion = notificacionRepository.findOne(id);
                        if (Utils.isNullOrEmpty(notificacion)) {
                            notificacion = new GcNotificacion(id);
                            notificacion.setBCorreo(alerta.getBCorreo());
                            notificacion.setBPantalla(alerta.getBPantalla());
                            notificacion.setFhNotificacion(alerta.getFhNotificacion());
                            notificacion.setMEstado("0");
                            notificacion.setGcUsuario(datos[0]);
                            notificacion.setGcAlerta(alerta);
                            try {
                                notificacionRepository.save(notificacion);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void readAlerta(Long id, String usuario) {
        GcNotificacionId notifId = new GcNotificacionId(id, usuario);
        GcNotificacion oldNotif = notificacionRepository.findOne(notifId);
        try {
            notificacionRepository.delete(notifId);
            GcNotificacion notificacion = new GcNotificacion(notifId);
            notificacion.setBCorreo(oldNotif.getBCorreo());
            notificacion.setBPantalla(oldNotif.getBPantalla());
            notificacion.setFhNotificacion(new Date());
            notificacion.setMEstado("1");
            notificacion.setGcUsuario(usuario);
            notificacionRepository.save(notificacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
