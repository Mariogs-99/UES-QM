package sv.global.colas.controllers.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcEscritorio;
import sv.global.colas.entities.GcSeguridadUsuario;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.UsuarioPojo;
import sv.global.colas.pojos.security.PersonW;
import sv.global.colas.repositories.GcAdminRepositoryImpl;
import sv.global.colas.repositories.GcEscritorioRepository;
import sv.global.colas.repositories.GcSeguridadUsuarioRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.repositories.PersonWRepository;
import sv.global.colas.utils.Utils;

@Controller
@RequestMapping("/admin")
@SessionAttributes("usuariopojo")
@EnableAsync
public class AdminController extends MainController {

    @Autowired
    private PersonWRepository personWRepository;
    @Autowired
    private GcTramiteRepository tramiteRepository;
    @Autowired
    private GcUsuarioRepository usuarioRepository;
    @Autowired
    private GcAdminRepositoryImpl adminRepository;
    @Autowired
    private GcEscritorioRepository escritorioRepository;
    @Autowired
    private GcUnidadRecepRepository unidadRepository;
    @Autowired
    private GcSeguridadUsuarioRepository secUsuarioRepository;

    Utils utilidad = new Utils();

    @ModelAttribute("usuariopojo")
    public UsuarioPojo usuariopojo() {
        UsuarioPojo usuario = new UsuarioPojo();
        prepareList(usuario, getUnidad());
        return usuario;
    }

    @RequestMapping("")
    public String iniciar_usuarios(HttpServletRequest request) {
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model) {
        List<GcUsuario> usuarioList = adminRepository.getAllUser();
        UsuarioPojo usuario = (UsuarioPojo) model.get("usuariopojo");
        usuario.setUsuarioList(usuarioList);
        model.addAttribute("usuariopojo", usuario);
        return "admin/confiUsuarios";
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String agregar(HttpServletRequest request, @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model) {
        UsuarioPojo usuario = (UsuarioPojo) model.get("usuariopojo");
        System.out.println("Usuario Pojo: " + usuario);
        prepareList(usuario, getUnidad());
        usuario.setcUsuario("");
        usuario.setdUsuario("");
        usuario.setEscritorio(new GcEscritorio());
        model.addAttribute("usuariopojo", usuario);
        return "admin/form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request,
            @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model) {
        try {
            UsuarioPojo usuario = (UsuarioPojo) model.get("usuariopojo");
            usuariopojo.setAddTramite(usuario.getAddTramite());
            adminRepository.save(usuariopojo);
            model.addAttribute("mensaje", "SE GUARDO EL USUARIO CORRECTAMENTE!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al momento de guardar la información!");
        }
        index(request, usuariopojo, model);
        return "admin/confiUsuarios";
    }

    @RequestMapping(value = "{user}/editar", method = RequestMethod.GET)
    public String editar(HttpServletRequest request,
            @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model,
            @PathVariable(value = "user") String user) {
        try {
            GcUsuario usuario = usuarioRepository.findOne(user);
            List<GcTramite> listado = tramiteRepository.getTramitesByUser(user);
            UsuarioPojo usuariop = (UsuarioPojo) model.get("usuariopojo");
            prepareList(usuariop, usuario.getNEscritorioId().getCUnidadRecep().getCunidadRecep());
            removList(usuariop, listado);
            usuariop.setAddTramite(listado);
            String pname = "";

//            PersonW personaLdap= (PersonW) personWRepository.findByPrimaryKey(user.replaceAll("-","."));
//            personaLdap = (PersonW) getName(getUsuario()); // Oscar Vides - Created for purpose proof
//            if(personaLdap!= null && personaLdap.getNit()!= null ) {
//                pname = personaLdap.getDisplayName();
//                if(pname==null)pname=personaLdap.getGivenName() +" "+personaLdap.getMiddleName()+" "+ personaLdap.getSn();
//            }
            GcSeguridadUsuario usr = secUsuarioRepository.fingByUsuario(user);
            pname = usr.getNombre();
            usuariop.setcUsuario(user);
            usuariop.setdUsuario(pname);
            usuariop.setEscritorio(usuario.getNEscritorioId());
            usuariop.getListadoEscritorio().add(usuariop.getEscritorio());
            model.addAttribute("usuariopojo", usuariop);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al momento de editar el usuario:" + user);
        }
        return "admin/form";
    }

    @RequestMapping(value = "{user}/show", method = RequestMethod.GET)
    public String mostrar(HttpServletRequest request,
            @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model,
            @PathVariable(value = "user") String user) {

        GcUsuario usuario = usuarioRepository.findOne(user);
        List<GcTramite> listado = tramiteRepository.getTramitesByUser(user);

        UsuarioPojo usuariop = (UsuarioPojo) model.get("usuariopojo");
        usuariop.setAddTramite(listado);
        usuariop.setcUsuario(user);
        usuariop.setEscritorio(usuario.getNEscritorioId());

        model.addAttribute("usuariopojo", usuariop);

        return "admin/verUsuario";
    }

    @RequestMapping(value = "/{user}/delete", method = RequestMethod.POST)
    public String destroy(HttpServletRequest request,
            @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model,
            @PathVariable(value = "user") String user) {

        GcUsuario usuario = usuarioRepository.findOne(user);
        List<GcTramite> listado = tramiteRepository.getTramitesByUser(user);
        usuario.setGcTramiteList(listado);
        try {
            System.out.println("Eliminado el usuario " + user);
            usuarioRepository.delete(usuario);
            usuarioRepository.delete(usuario.getCUsuario());
            model.addAttribute("mensaje", "USUARIO ELIMINADO CORRECTAMENTE!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al momento de eliminar el usuario: " + user);
        }
        index(request, usuariopojo, model);
        return "admin/confiUsuarios";
    }

    @RequestMapping(value = "/getuserldap", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getContribuyenteNombre(HttpServletRequest request, final ModelMap model,
            @RequestParam(value = "usuarioConsulta") String usuario) {
        System.out.println("USUARIO VIENE: " + usuario);
        //Boolean existe = usuarioRepository.exists(usuario.replaceAll("-","."));
        Boolean existe = usuarioRepository.exists(usuario);
        Long id = usuarioRepository.getEscritorioPorUsuario(usuario);
        String unidadRecep = secUsuarioRepository.encontrarUnidadByUsuario(usuario);
        GcSeguridadUsuario usr = secUsuarioRepository.fingByUsuario(usuario);
        //System.out.println("IDDDDDDDDDDDDDDDDDDDD: " + id);
        List<String> res = new ArrayList<String>();
        if (usr != null) {
            res.add("valido");
            res.add(unidadRecep);
            res.add(usr.getNombre());
            if (existe) {
                res.add("update");
            } else {
                res.add("insert");
            }
        }

        return res;
    }

    @RequestMapping(value = "/addTramite", method = RequestMethod.GET)
    public String addTramite(HttpServletRequest request,
            @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model,
            @RequestParam(value = "lista", required = false) String[] lista) {

        usuariopojo = (UsuarioPojo) model.get("usuariopojo");
        addList(usuariopojo, lista);
        model.addAttribute("usuariopojo", usuariopojo);

        return "admin/form";
    }

    @RequestMapping(value = "/delTramite", method = RequestMethod.GET)
    public String delTramite(HttpServletRequest request,
            @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model,
            @RequestParam(value = "lista", required = false) String[] lista) {

        usuariopojo = (UsuarioPojo) model.get("usuariopojo");
        delList(usuariopojo, lista);

        return "admin/form";
    }

    public void addList(UsuarioPojo user, String[] lista) {
        for (String l : lista) {
            for (Iterator<GcTramite> tramite = user.getListradoTramite().iterator(); tramite.hasNext();) {
                GcTramite tram = tramite.next();
                if (tram.getNTramiteId().toString().equalsIgnoreCase(l)) {
                    user.getAddTramite().add(tram);
                    tramite.remove();
                }
            }
        }
    }

    public void delList(UsuarioPojo user, String[] lista) {
        for (String l : lista) {
            for (Iterator<GcTramite> tramite = user.getAddTramite().iterator(); tramite.hasNext();) {
                GcTramite tram = tramite.next();
                if (tram.getNTramiteId().toString().equalsIgnoreCase(l)) {
                    user.getListradoTramite().add(tram);
                    tramite.remove();
                }
            }
        }
    }

    public void removList(UsuarioPojo user, List<GcTramite> listado) {
        for (GcTramite l : listado) {
            for (Iterator<GcTramite> tramite = user.getListradoTramite().iterator(); tramite.hasNext();) {
                GcTramite tram = tramite.next();
                if (tram.getNTramiteId().toString().equalsIgnoreCase(l.getNTramiteId().toString())) {
                    tramite.remove();
                }
            }
        }
    }

    public void prepareList(UsuarioPojo user, String unidad) {

        List<GcTramite> tramites = (List<GcTramite>) tramiteRepository.findAll();
        user.setListradoTramite(tramites);
        List<GcEscritorio> escritorios = (List<GcEscritorio>) escritorioRepository.getEscritoriosVacios(unidad);
        user.setListadoEscritorio(escritorios);
        user.setAddTramite(new ArrayList());
    }

    @RequestMapping(value = "/getEscritorios", method = RequestMethod.GET)
    public String getEscritorios(HttpServletRequest request,
            @ModelAttribute UsuarioPojo usuariopojo, final ModelMap model,
            @RequestParam(value = "cunidadRecepId", required = false) String unidad) {
        usuariopojo = (UsuarioPojo) model.get("usuariopojo");
        List<GcEscritorio> escritorios = (List<GcEscritorio>) escritorioRepository.getEscritoriosVacios(unidad);
        usuariopojo.setListadoEscritorio(escritorios);
        return "admin/form";
    }

    private String getUnidad() {
        return getUnidadRecep();
    }

    public PersonW getName(String username) {
        PersonW personaLdap = new PersonW();
        if (username.equals("oscar.vides")) {
            personaLdap.setNit("12345678912345");
            personaLdap.setDisplayName("Oscar Armando Vides");
            personaLdap.setGivenName("Oscar");
            personaLdap.setMiddleName("Armando");
            personaLdap.setSn("Vides");
            personaLdap.setUnidadRecep("80048");
        } else if (username.equals("ever.argueta")) {
            personaLdap.setNit("12345678912345");
            personaLdap.setDisplayName("Ever Ernaldo Argueta");
            personaLdap.setGivenName("Ever");
            personaLdap.setMiddleName("Ernaldo");
            personaLdap.setSn("Argueta");
            personaLdap.setUnidadRecep("80048");
        } else if (username.equals("florentin.hdz")) {
            personaLdap.setNit("12345678912345");
            personaLdap.setDisplayName("Florentin Lazo");
            personaLdap.setGivenName("Florentin");
            personaLdap.setMiddleName("");
            personaLdap.setSn("Lazo");
            personaLdap.setUnidadRecep("80048");
        } else if (username.equals("agustin.romero")) {
            personaLdap.setNit("12345678912345");
            personaLdap.setDisplayName("Carlos Agustin Romero");
            personaLdap.setGivenName("Carlos");
            personaLdap.setMiddleName("Agustin");
            personaLdap.setSn("Romero");
            personaLdap.setUnidadRecep("80048");
        }

        return personaLdap;
    }
}
