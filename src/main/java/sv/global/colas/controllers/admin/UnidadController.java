/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcSeguridadRole;
import sv.global.colas.entities.GcUnidadRecep;
import sv.global.colas.pojos.entities.SeguriidadUsuarioPojo;
import sv.global.colas.repositories.GcSeguridadRoleRepository;
import sv.global.colas.repositories.UnidadRecepRepository;
import sv.global.colas.utils.Utils;


/**
 *
 * @author Ever Argueta
 */
@Controller
    @RequestMapping("/tbunidad")
//@SessionAttributes("seguriidadUsuarioPojo")
@SessionAttributes("tbunidadxpo")
@EnableAsync
public class UnidadController  extends MainController {
   
//    @Autowired
//    private GcSeguridadUsuarioRepository gcSeguridadUsuarioRepository;
//    @Autowired
//    private GcUnidadRecepRepository unidadRepository;
    @Autowired
    private GcSeguridadRoleRepository gcSeguridadRoleRepository;
     @Autowired
    private  UnidadRecepRepository gcUnidadRecepRepository; 
    Utils utilidad = new Utils();
    
//    @ModelAttribute("seguriidadUsuarioPojo")
//    public SeguriidadUsuarioPojo seguriidadUsuarioPojo() {
//        return new SeguriidadUsuarioPojo();
//    }

    @ModelAttribute("tbunidadxpo")
    public GcUnidadRecep tbunidad() {
        GcUnidadRecep tbUnidadRecep = new GcUnidadRecep();
        //..
        return tbUnidadRecep;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexUsuario(HttpServletRequest request, @ModelAttribute GcUnidadRecep tbunidadxpo, final ModelMap model) {
        Iterable<GcUnidadRecep> usuariosList = null;
        System.out.println("Entrando...");
        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            usuariosList = gcUnidadRecepRepository.findAll();
        }
        else
            usuariosList = gcUnidadRecepRepository.findAll();
        //else {
        //    usuariosList = gcUnidadRecepRepository.usuariosByCS(getUnidad());
        //}
        model.addAttribute("unidadList", usuariosList);
        return "unidad/unidades";
        //return "usuario/usuarios";
    }
    
    @RequestMapping("/newUsuario")
    public String newJerarquia(HttpServletRequest request, 
            @ModelAttribute SeguriidadUsuarioPojo seguriidadUsuarioPojo, final ModelMap model){
            seguriidadUsuarioPojo=(SeguriidadUsuarioPojo) model.get("seguriidadUsuarioPojo");
            seguriidadUsuarioPojo=new SeguriidadUsuarioPojo();
            seguriidadUsuarioPojo.setSeguridadRoleList((List<GcSeguridadRole>) gcSeguridadRoleRepository.findAll());
            seguriidadUsuarioPojo.setSeguridadRoleListAdded(new ArrayList());
            model.addAttribute("seguriidadUsuarioPojo",seguriidadUsuarioPojo);
            model.addAttribute("listaUnidades", getUnidadesList());
            model.addAttribute("listaRoles", gcSeguridadRoleRepository.findAll());
            return "usuario/newUsuario";
    }

    @RequestMapping(value = "{usuario}/editUsuario", method = RequestMethod.GET)
    public String editUsuario(HttpServletRequest request,
            @ModelAttribute SeguriidadUsuarioPojo seguriidadUsuarioPojo, final ModelMap model,
            @PathVariable(value = "usuario") String usuario) {
            GcUnidadRecep gcSeguridadUsuario=gcUnidadRecepRepository.findOne(usuario);
            
            
//            seguriidadUsuarioPojo=(SeguriidadUsuarioPojo) model.get("seguriidadUsuarioPojo");
//            seguriidadUsuarioPojo.setSeguridadRoleList((List<GcSeguridadRole>) gcSeguridadRoleRepository.findAll());
//            seguriidadUsuarioPojo.setSeguridadRoleListAdded(gcSeguridadRoleRepository.rolesByUsuario(usuario));
//            seguriidadUsuarioPojo.setIdUsuario(usuario);
//            seguriidadUsuarioPojo.setNombre(gcSeguridadUsuario.getNombre());
//            seguriidadUsuarioPojo.setCorreo(gcSeguridadUsuario.getCorreo());
//            seguriidadUsuarioPojo.setUnidadRecep(gcSeguridadUsuario.getCUnidadRecep().getCunidadRecep());
//            seguriidadUsuarioPojo.setUsuario(gcSeguridadUsuario.getUsuario());
//            seguriidadUsuarioPojo.setbActiva(gcSeguridadUsuario.getbActiva());
//            removList( seguriidadUsuarioPojo, seguriidadUsuarioPojo.getSeguridadRoleListAdded()) ;
//            gcSeguridadUsuario.setUsuarioModifica(gcSeguridadUsuario.getCUnidadRecep().getCunidadRecep());
//            model.addAttribute("unidadrecep", gcSeguridadUsuario.getCUnidadRecep().getCunidadRecep());
//            model.addAttribute("listaUnidades", getUnidadesList());
//            model.addAttribute("seguriidadUsuarioPojo", seguriidadUsuarioPojo);
        return "usuario/newUsuario"; 
    }
    
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardarJerarquiaSec(HttpServletRequest request, @ModelAttribute SeguriidadUsuarioPojo seguriidadUsuarioPojo, final ModelMap model) {
        try{
//            GcSeguridadUsuario gcSeguridadUsuario=new GcSeguridadUsuario();
//            if(seguriidadUsuarioPojo.getIdUsuario()!=null){
//                gcSeguridadUsuario=gcSeguridadUsuarioRepository.findOne(seguriidadUsuarioPojo.getIdUsuario());
//                gcSeguridadUsuario.setFechaModifica(new Date());
//                gcSeguridadUsuario.setUsuarioModifica(getUsuario());
//                gcSeguridadUsuario.setIdUsuario(seguriidadUsuarioPojo.getIdUsuario());
//            }else{
//                gcSeguridadUsuario.setFechaIngreso(new Date());
//                gcSeguridadUsuario.setUsuarioIngreso(getUsuario());
//            }
//            if(seguriidadUsuarioPojo.getCambiaPass()==1){
//                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//                gcSeguridadUsuario.setContraseña(passwordEncoder.encode(seguriidadUsuarioPojo.getContraseña()));
//            }else{
//                gcSeguridadUsuario.setContraseña(gcSeguridadUsuarioRepository.passById(gcSeguridadUsuario.getIdUsuario()));
//            }
//            gcSeguridadUsuario.setCUnidadRecep(unidadRepository.getUnidadEnServicioE(seguriidadUsuarioPojo.getUnidadRecep()));
//            gcSeguridadUsuario.setGcSeguridadRoleList(seguriidadUsuarioPojo.getSeguridadRoleListAdded());
//            gcSeguridadUsuario.setNombre(seguriidadUsuarioPojo.getNombre());
//            gcSeguridadUsuario.setCorreo(seguriidadUsuarioPojo.getCorreo());
//            gcSeguridadUsuario.setUsuario(seguriidadUsuarioPojo.getUsuario());
//            gcSeguridadUsuario.setbActiva(seguriidadUsuarioPojo.getbActiva());
//            gcSeguridadUsuarioRepository.save(gcSeguridadUsuario);
//            model.addAttribute("mensaje", "CONFIGURACION DE USUARIO GUARDADA CORRECTAMENTE");
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "ALGO SALIO MAL");
        }
        indexUsuario(request, null, model);
        return "usuario/usuarios";
    }

    @RequestMapping(value = "{usuario}/verUsuario", method = RequestMethod.GET)
    public String verUsuario(HttpServletRequest request,
            @ModelAttribute SeguriidadUsuarioPojo seguriidadUsuarioPojo, final ModelMap model,
            @PathVariable(value = "usuario") Long usuario) {
//            GcSeguridadUsuario gcSeguridadUsuario=gcSeguridadUsuarioRepository.findOne(usuario);
//            seguriidadUsuarioPojo=(SeguriidadUsuarioPojo) model.get("seguriidadUsuarioPojo");
//            seguriidadUsuarioPojo.setSeguridadRoleListAdded(gcSeguridadRoleRepository.rolesByUsuario(usuario));
//            seguriidadUsuarioPojo.setIdUsuario(usuario);
//            seguriidadUsuarioPojo.setNombre(gcSeguridadUsuario.getNombre());
//            seguriidadUsuarioPojo.setCorreo(gcSeguridadUsuario.getCorreo());
//            seguriidadUsuarioPojo.setTbUnidadRecep(gcSeguridadUsuario.getCUnidadRecep());
//            seguriidadUsuarioPojo.setUsuario(gcSeguridadUsuario.getUsuario());
//            seguriidadUsuarioPojo.setbActiva(gcSeguridadUsuario.getbActiva());
//            gcSeguridadUsuario.setUsuarioModifica(gcSeguridadUsuario.getCUnidadRecep().getCunidadRecep());
//            model.addAttribute("seguriidadUsuarioPojo",seguriidadUsuarioPojo);
        return "usuario/verUsuario"; 
    }
    
    public String getUnidad() {
        //String unidad = getPrincipal().getUnidadRecep();
        String unidad = "80048";
        if (unidad == null) {
            unidad = getPrincipal().getUbicacionFisica();
        }
        return unidad;
    }
    
    public List<GcUnidadRecep> getUnidadesList(){
        List<GcUnidadRecep> unidades = null;
//        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
//            unidades = (List<TbUnidadRecep>) unidadRepository.getUnidadesEnServicio();
//        }else{
//            unidades = (List<TbUnidadRecep>) unidadRepository.getUnidadesEnServicio(getUnidad());
//        }
        return unidades;
    }
    
    
    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public String addTramite(HttpServletRequest request, 
            @ModelAttribute SeguriidadUsuarioPojo seguriidadUsuarioPojo, final ModelMap model,
            @RequestParam(value = "lista", required = false) String[] lista) {
        seguriidadUsuarioPojo = (SeguriidadUsuarioPojo) model.get("seguriidadUsuarioPojo");
        addList(seguriidadUsuarioPojo, lista);
        model.addAttribute("seguriidadUsuarioPojo", seguriidadUsuarioPojo);
        return "usuario/newUsuario";
    }
    
    @RequestMapping(value = "/delRole", method = RequestMethod.GET)
    public String delTramite(HttpServletRequest request, 
            @ModelAttribute SeguriidadUsuarioPojo seguriidadUsuarioPojo, final ModelMap model,
            @RequestParam(value = "lista", required = false) String[] lista) {
        seguriidadUsuarioPojo = (SeguriidadUsuarioPojo) model.get("seguriidadUsuarioPojo");
        delList(seguriidadUsuarioPojo, lista);
        return "usuario/newUsuario";
    }
    
    @RequestMapping(value = "/{user}/delete", method = RequestMethod.POST)
    public String destroy(HttpServletRequest request, 
            @ModelAttribute SeguriidadUsuarioPojo seguriidadUsuarioPojo, final ModelMap model,
            @PathVariable(value = "user") Long user){
        try {
            //GcSeguridadUsuario gcSeguridadUsuario=gcSeguridadUsuarioRepository.findOne(user);
            //gcSeguridadUsuario.setGcSeguridadRoleList(null);
            //gcSeguridadUsuarioRepository.save(gcSeguridadUsuario);
            System.out.println("Eliminando el usuario " + user );
            //gcSeguridadUsuarioRepository.delete(gcSeguridadUsuario);
            model.addAttribute("mensaje", "USUARIO ELIMINADO CORRECTAMENTE!");
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al momento de eliminar el usuario: "+user);
        }
        indexUsuario(request, null, model);
        return "admin/confiUsuarios";
    }
    
    public void addList(SeguriidadUsuarioPojo user, String[] lista) {
        for (String l : lista) {
            for (Iterator<GcSeguridadRole> role = user.getSeguridadRoleList().iterator(); role.hasNext(); ) {
                GcSeguridadRole rol = role.next();
                if(rol.getIdRole().toString().equalsIgnoreCase(l)) {
                    user.getSeguridadRoleListAdded().add(rol);
                    role.remove();
                }
            }
        }
    }
    
    public void delList(SeguriidadUsuarioPojo user, String[] lista) {
        for (String l : lista) {
            for (Iterator<GcSeguridadRole> role = user.getSeguridadRoleListAdded().iterator(); role.hasNext(); ) {
                GcSeguridadRole rol = role.next();
                if(rol.getIdRole().toString().equalsIgnoreCase(l)) {
                    user.getSeguridadRoleList().add(rol);
                    role.remove();
                }
            }
        }
    }
    
    public void removList(SeguriidadUsuarioPojo user, List<GcSeguridadRole> listado) {
        for (GcSeguridadRole l : listado) {
            for (Iterator<GcSeguridadRole> role = user.getSeguridadRoleList().iterator(); role.hasNext(); ) {
                GcSeguridadRole rol = role.next();
                if(rol.getIdRole().toString().equalsIgnoreCase(l.getIdRole().toString())){
                    role.remove();
                }
            }
        }
    }
}
