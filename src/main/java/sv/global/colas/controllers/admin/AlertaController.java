package sv.global.colas.controllers.admin;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcAlerta;
import sv.global.colas.entities.GcZona;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.AlertaPojo;
import sv.global.colas.pojos.entities.TipoUsuarioPojo;
import org.springframework.web.bind.annotation.ResponseBody;
import sv.global.colas.repositories.GcAlertaRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcZonaRepository;
import sv.global.colas.utils.Utils;

@Controller
@RequestMapping("/alerta")
@SessionAttributes("gcalerta")
@EnableAsync
public class AlertaController extends MainController {

    @Autowired
    private GcAlertaRepository gcAlertaRepository;
    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;
    @Autowired
    private GcZonaRepository gcZonaRepository;
    Utils utilidad = new Utils();

    @ModelAttribute("gcalerta")
    public GcAlerta gcAlertaList() {
        return new GcAlerta();
    }

    @RequestMapping("")
    public String iniciar(HttpServletRequest request) {
        return "redirect:/alerta/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, @ModelAttribute GcAlerta gcalerta, final ModelMap model) {
        Iterable<GcAlerta> listadoAlerta = gcAlertaRepository.findAllActivas(new Date());
        model.addAttribute("gcAlertaList", listadoAlerta);
        return "alerta/index";
    }

    @RequestMapping("/newAlerta")    
    public String newAlerta(HttpServletRequest request, @ModelAttribute GcAlerta gcalerta, final ModelMap model) {
        AlertaPojo alertapojo = new AlertaPojo();
        prepareList(alertapojo);
        model.addAttribute("gcalerta", gcalerta);
        model.addAttribute("alertapojo", alertapojo);
        return "alerta/form";
    }

    @RequestMapping(value = "{alerta}/editAlerta", method = RequestMethod.GET)
    public String editAlerta(HttpServletRequest request, @ModelAttribute GcAlerta gcalerta, final ModelMap model,
            @PathVariable(value = "alerta") Long alerta) {
        AlertaPojo alertapojo = new AlertaPojo();
        gcalerta = gcAlertaRepository.findOne(alerta);
        prepareList(alertapojo);
        model.addAttribute("emails", gcalerta.getSCorreoNotifica());
        if (!Utils.isNullOrEmpty(gcalerta.getSCorreoNotifica())) {
            String[] correos = gcalerta.getSCorreoNotifica().split(",");
            gcalerta.setSCorreoNotifica(correos[0]);
            gcalerta.setCUsuarioModi(gcalerta.getCUnidadRecep().getCunidadRecep());
        } else {
            gcalerta.setSCorreoNotifica("");
        }
        model.addAttribute("gcalerta", gcalerta);
        model.addAttribute("alertapojo", alertapojo);
        return "alerta/form";
    }

    @RequestMapping(value = "/getzonas", method = RequestMethod.GET)
    public String getZonasXunidad(HttpServletRequest request,
            @ModelAttribute GcAlerta gcalerta, final ModelMap model,
            @RequestParam(value = "cunidadRecepId", required = false) String unidad) {
        AlertaPojo alertapojo = new AlertaPojo();
        List<GcZona> zonas = (List<GcZona>) gcZonaRepository.getZonasByUnidad(unidad);
        alertapojo.setListadoZonas(zonas);
        model.addAttribute("alertapojo", alertapojo);
        return "alerta/form";
    }

    @RequestMapping(value = "{alerta}/delete", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request, @ModelAttribute GcAlerta gcalerta, final ModelMap model,
            @PathVariable(value = "alerta") Long alerta) {
        gcalerta = gcAlertaRepository.findOne(alerta);
        gcalerta.setBActiva((short) 0);
        gcAlertaRepository.save(gcalerta);
        index(request, null, model);
        return "alerta/index";
    }

    @RequestMapping(value = "{alerta}/verAlerta", method = RequestMethod.GET)
    public String verAlerta(HttpServletRequest request, @ModelAttribute GcAlerta gcalerta, final ModelMap model,
            @PathVariable(value = "alerta") Long alerta) {
        gcalerta = gcAlertaRepository.findOne(alerta);
        model.addAttribute("gcalerta", gcalerta);
        return "alerta/show";
    }

    @RequestMapping(value = "/saveAl", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute GcAlerta gcalerta, final ModelMap model) {
        if (!Utils.isNullOrEmpty(gcalerta)) {
                if (!Utils.isNullOrEmpty(gcalerta.getNAlertaId())) {
                GcAlerta old = gcAlertaRepository.findOne(gcalerta.getNAlertaId());
                gcalerta.setCUsuarioCrea(old.getCUsuarioCrea());
                gcalerta.setFModifica(new Date());
                gcalerta.setBActiva((short) 1);
                gcalerta.setFhNotificacion(new Date());
                gcalerta.setCUnidadRecep(gcUnidadRecepRepository.getUnidadEnServicioE(gcalerta.getCUsuarioModi()));
                gcalerta.setCUsuarioModi(getUsuario());
            } else {
                gcalerta.setCUsuarioCrea(getUsuario());
                gcalerta.setCUnidadRecep(gcUnidadRecepRepository.getUnidadEnServicioE(gcalerta.getCUsuarioModi()));
                gcalerta.setCUsuarioModi(getUsuario());
                gcalerta.setFhNotificacion(new Date());
                gcalerta.setBActiva((short) 1);
                if (gcalerta.getBCorreo()) {
                    enviarCorreos(gcalerta);
                }
            }
            gcAlertaRepository.save(gcalerta);
            return "redirect:/alerta/";
        } else {
            return "alerta/form";
        }
    }

    @RequestMapping(value = "/getusuario", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public TipoUsuarioPojo getUsuario(HttpServletRequest request) {
        TipoUsuarioPojo usuario = new TipoUsuarioPojo();
//        usuario.setUsuario(getUsuario() + "," + getUnidad());
         usuario.setUsuario("ever.argueta" + "," + getUnidad());
        String tipo = ""; // Tipo Tecnico
        Collection<GrantedAuthority> roles = getRoles();
        for (GrantedAuthority rol : roles) {
            if (rol.getAuthority().equalsIgnoreCase("ROLE_GC_R3")) {
                tipo += "GC_R3,";
                //break;
            }
            if (rol.getAuthority().equalsIgnoreCase("ROLE_GC_R4")) {
                tipo +="GC_R4,";
                //break;
            }
        }
        tipo=tipo.substring(0, tipo.length()-1);
        usuario.setTipo(tipo);
        return usuario;
    }

    public void prepareList(AlertaPojo alertapojo) {
        alertapojo.setListadoUnidades(getUnidadesList());
        List<GcZona> zonas = (List<GcZona>) gcZonaRepository.getAllZonasActivas();
        alertapojo.setListadoZonas(zonas);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    public void enviarCorreos(GcAlerta alerta) {
        String[] listadoCorreos = alerta.getSCorreoNotifica().split(",");
        for (String too : listadoCorreos) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            String tipo="";
            switch (Integer.parseInt(alerta.getSTipo())) {
                case 0: tipo = "AVISO";
                     break;
                case 1: tipo = "ADVERTENCIA";
                         break;
                default: tipo = "ALERTA";
                     break;
            }
            String cuerpo=alerta.getCUnidadRecep().getDunidadRecep()+"\r\n \r\n \r\n \r\n";
            cuerpo+=alerta.getSMensaje()+"\r\n \r\n \r\n";
            cuerpo+="Vigencia: "+sdf.format( alerta.getFfVigencia())+" HASTA: "+sdf.format(alerta.getFfVigencia())+" \r\n";
            try {
                if(too!=null && !"".equals(too)) gcAlertaRepository.enviarCorreo(too.replaceAll(",", ""),tipo, cuerpo,"informativo.dgii@mh.gob.sv","Asistencia - Ministerio de Hacienda");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
