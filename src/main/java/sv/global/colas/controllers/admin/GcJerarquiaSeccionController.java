/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.math.BigInteger;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcEscritorio;
import sv.global.colas.entities.GcJerarquiaSeccion;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.GcJerarquiaSeccionPojo;
import sv.global.colas.pojos.entities.ZonaPojo;
import sv.global.colas.pojos.security.PersonW;
import sv.global.colas.repositories.GcEscritorioRepository;
import sv.global.colas.repositories.GcJerarquiaRepository;
import sv.global.colas.repositories.GcJerarquiaSeccionRepository;
import sv.global.colas.repositories.GcServiciosRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.repositories.PersonWRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/jerarquiasec")
@SessionAttributes("jrqSecPojo")
@EnableAsync
public class GcJerarquiaSeccionController extends MainController {
        
    @Autowired
    private GcJerarquiaSeccionRepository gcJerarquiaSeccionRepository;
    @Autowired
    private GcJerarquiaRepository gcJerarquiaRepository;
    @Autowired
    private GcServiciosRepository gcServiciosRepository;
    @Autowired
    private GcUnidadRecepRepository unidadRepository;
    @Autowired
    private GcUsuarioRepository usuarioRepository;
    @Autowired
    private PersonWRepository personWRepository;
    @Autowired
    private GcEscritorioRepository gcEscritorioRepository;
    
    Utils utilidad = new Utils();
    
    @ModelAttribute("jrqSecPojo")
    public GcJerarquiaSeccionPojo gcJerarquiasecsList(){
        return new GcJerarquiaSeccionPojo();
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexJerarquiaSec(HttpServletRequest request, @ModelAttribute GcJerarquiaSeccionPojo gcJerarquiaSeccionPojo, final ModelMap model){
        
        if ("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            Iterable<GcJerarquiaSeccion> listaGcJerarquiasec=gcJerarquiaSeccionRepository.findAll();
            model.addAttribute("listaJerarquiasec", listaGcJerarquiasec);
        } else {
            Iterable<GcJerarquiaSeccion> listaGcJerarquiasec=gcJerarquiaSeccionRepository.findAllByUnidad(getUnidad());
            model.addAttribute("listaJerarquiasec", listaGcJerarquiasec);
        }
            return "jerarquiasec/jerarquiasecs";
    }
    
    @RequestMapping("/newJerarquiasec")
    public String newJerarquiaSec(HttpServletRequest request, @ModelAttribute GcJerarquiaSeccionPojo gcJerarquiaSeccionPojo, final ModelMap model){
            model.addAttribute("jrqSecPojo",gcJerarquiaSeccionPojo);
            model.addAttribute("listaJerarquias", gcJerarquiaRepository.findAll());
            model.addAttribute("listaUnidades", getUnidadesList());
            List<GcEscritorio> escritorio2=gcEscritorioRepository.getEscritoriosVacios(getUnidad());
            escritorio2.addAll(gcEscritorioRepository.getEscritoriosJefes(getUnidad()));
            model.addAttribute("listaEscritorios", escritorio2);
            model.addAttribute("listaSecciones", gcServiciosRepository.getAllServicios());
            return "jerarquiasec/newJerarquiaseccion";
    }
    
    @RequestMapping(value = "/getEscritorios", method = RequestMethod.GET)
    public String getZonasXunidad(HttpServletRequest request, 
            @ModelAttribute GcJerarquiaSeccionPojo gcJerarquiaSeccionPojo, final ModelMap model,
            @RequestParam(value = "cunidadRecepId", required = false) String unidad){
            List<GcEscritorio> escritorio2=gcEscritorioRepository.getEscritoriosVacios(unidad);
            escritorio2.addAll(gcEscritorioRepository.getEscritoriosJefes(unidad));
            model.addAttribute("listaEscritorios", escritorio2);
            return "jerarquiasec/newJerarquiaseccion";
    }
    
   @RequestMapping(value="{jerarquiasec}/editJerarquiaSec", method = RequestMethod.GET)
    public String editJerarquiaSec(HttpServletRequest request, @ModelAttribute GcJerarquiaSeccionPojo gcJerarquiaSeccionPojo, final ModelMap model, @PathVariable(value = "jerarquiasec") Long jerarquiasec){
            GcJerarquiaSeccion gcJerarquiaSeccion = gcJerarquiaSeccionRepository.findOne(jerarquiasec);
            gcJerarquiaSeccionPojo.setUsuario(gcJerarquiaSeccion.getSUsuario());
            gcJerarquiaSeccionPojo.setJrqsecId(gcJerarquiaSeccion.getNJrqSecId());
            gcJerarquiaSeccionPojo.setEscritorio(usuarioRepository.findOne(gcJerarquiaSeccion.getSUsuario()).getNEscritorioId().getNEscritorioId());
            model.addAttribute("seccionId", gcJerarquiaSeccion.getNServiciosId().getNServiciosId());
            model.addAttribute("unidad", gcJerarquiaSeccion.getCUnidadRecep());
            model.addAttribute("jerarquiaId", gcJerarquiaSeccion.getNJerarquiaId().getNJerarquiaId());
            model.addAttribute("jrqSecPojo", gcJerarquiaSeccionPojo);
            model.addAttribute("listaJerarquias", gcJerarquiaRepository.findAll());
            model.addAttribute("listaUnidades", getUnidadesList());
            List<GcEscritorio> escritorio2=gcEscritorioRepository.getEscritoriosVacios(gcJerarquiaSeccion.getCUnidadRecep());
            escritorio2.addAll(gcEscritorioRepository.getEscritoriosJefes(gcJerarquiaSeccion.getCUnidadRecep()));
            model.addAttribute("listaEscritorios", escritorio2);
            model.addAttribute("listaSecciones", gcServiciosRepository.getAllServicios());
            return "jerarquiasec/newJerarquiaseccion";
    }
      
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String guardarJerarquiaSec(HttpServletRequest request, @ModelAttribute GcJerarquiaSeccionPojo gcJerarquiaSeccionPojo, final ModelMap model) {
        try{
            if(gcJerarquiaSeccionPojo.getJrqsecId()!=null){
                String existe=gcJerarquiaSeccionRepository.getJerarquiaSecByNameId(gcJerarquiaSeccionPojo.getUnidad(),gcJerarquiaSeccionPojo.getSeccion(),gcJerarquiaSeccionPojo.getJerarquia(),gcJerarquiaSeccionPojo.getJrqsecId());
                if(existe==null || "".equals(existe)){
                    GcJerarquiaSeccion grqSec=gcJerarquiaSeccionRepository.findOne(gcJerarquiaSeccionPojo.getJrqsecId());
                    grqSec.setNJerarquiaId(gcJerarquiaRepository.findOne( gcJerarquiaSeccionPojo.getJerarquia()));
                    grqSec.setNServiciosId(gcServiciosRepository.findOne(gcJerarquiaSeccionPojo.getSeccion()));
                    grqSec.setSUsuario(gcJerarquiaSeccionPojo.getUsuario());
                    grqSec.setCUsuarioModifica(getUsuario());
                    grqSec.setFfVigencia(new Date());
                    grqSec.setDJerarquiasSeccion(gcJerarquiaSeccionPojo.getDescripcion());
                    if(usuarioRepository.exists(grqSec.getSUsuario())){
                        GcUsuario usuario=usuarioRepository.findOne(grqSec.getSUsuario());
                        usuario.setNEscritorioId(gcEscritorioRepository.findOne(gcJerarquiaSeccionPojo.getEscritorio()));
                        usuarioRepository.save(usuario);
                    }else{
                        GcUsuario usuario=new GcUsuario();
                        usuario.setCUsuario(grqSec.getSUsuario());
                        usuario.setNEscritorioId(gcEscritorioRepository.findOne(gcJerarquiaSeccionPojo.getEscritorio()));

                        usuarioRepository.save(usuario);
                    }
                    gcJerarquiaSeccionRepository.save(grqSec);
                    model.addAttribute("mensaje", "CONFIGURACION DE SECCION GUARDADA CORRECTAMENTE");
                }else{
                    model.addAttribute("mensajeError", "YA EXISTE UNA CONFIGURACION IGUAL!");
                }
            }else{
                GcJerarquiaSeccion grqSec=new GcJerarquiaSeccion();
                String existe=gcJerarquiaSeccionRepository.getJerarquiaSecByName(gcJerarquiaSeccionPojo.getUnidad(),gcJerarquiaSeccionPojo.getSeccion(),gcJerarquiaSeccionPojo.getJerarquia());
                if(existe==null || "".equals(existe)){
                    grqSec.setCUnidadRecep(gcJerarquiaSeccionPojo.getUnidad());
                    grqSec.setNJerarquiaId(gcJerarquiaRepository.findOne( gcJerarquiaSeccionPojo.getJerarquia()));
                    grqSec.setNServiciosId(gcServiciosRepository.findOne(gcJerarquiaSeccionPojo.getSeccion()));
                    grqSec.setSUsuario(gcJerarquiaSeccionPojo.getUsuario());
                    grqSec.setCUsuarioModifica(getUsuario());
                    grqSec.setCUsuarioCrea(getUsuario());
                    grqSec.setDJerarquiasSeccion(gcJerarquiaSeccionPojo.getDescripcion());
                    grqSec.setFiVigencia(new Date());
                    grqSec.setFfVigencia(new Date());
                    grqSec.setDUnidadRecep(unidadRepository.findOne(gcJerarquiaSeccionPojo.getUnidad()).getDunidadRecep());
                    if(usuarioRepository.exists(grqSec.getSUsuario())){
                        GcUsuario usuario=usuarioRepository.findOne(grqSec.getSUsuario());
                        usuario.setNEscritorioId(gcEscritorioRepository.findOne(gcJerarquiaSeccionPojo.getEscritorio()));
                        usuarioRepository.save(usuario);
                    }else{
                        GcUsuario usuario=new GcUsuario();
                        usuario.setCUsuario(grqSec.getSUsuario());
                        usuario.setNEscritorioId(gcEscritorioRepository.findOne(gcJerarquiaSeccionPojo.getEscritorio()));
                        usuarioRepository.save(usuario);
                    }
                        grqSec.setNJrqSecId(grqSec.getNJrqSecId());
                        if(grqSec.getNJrqSecId()==null){
                            grqSec.setNJrqSecId(gcJerarquiaSeccionRepository.GetTotalId());
                        }
                    gcJerarquiaSeccionRepository.save(grqSec);
                    model.addAttribute("mensaje", "CONFIGURACION DE SECCION GUARDADA CORRECTAMENTE");
                }else{
                    model.addAttribute("mensajeError", "YA EXISTE UNA CONFIGURACION IGUAL!");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Error: "+e.getMessage());
        } 
        indexJerarquiaSec(request, null, model);
        return "jerarquiasec/jerarquiasecs";
    }
        
    @RequestMapping(value = "{jerarquiasec}/delete", method = RequestMethod.POST)
    public String eliminarJerarquiaSec(HttpServletRequest request, @ModelAttribute GcJerarquiaSeccionPojo gcJerarquiaSeccionPojo, final ModelMap model,@PathVariable(value = "jerarquiasec") Long jerarquiasec) {
        GcJerarquiaSeccion gcJerarquiaSeccion2 = gcJerarquiaSeccionRepository.findOne(jerarquiasec);
        try{
            if(gcJerarquiaSeccion2!=null){
                gcJerarquiaSeccionRepository.delete(gcJerarquiaSeccion2);
                //gcJerarquiaSeccionRepository.delete(gcJerarquiaSeccion2.getNJrqSecId());
                model.addAttribute("mensaje", "SE ELIMINO LA CONFIGURACION CORRECTAMENTE!");
            }else{
                model.addAttribute("mensaje", "NINGUNA CONFIGURACION POR ELIMINAR!");
            }
        }catch(Exception e){
            model.addAttribute("mensajeError", "ERROR: "+e.getMessage());
        }
        indexJerarquiaSec( request, null, model);
        return "jerarquiasec/jerarquiasecs";
    }
     
   @RequestMapping(value="{jerarquiasec}/verJerarquiaSec", method = RequestMethod.GET)
    public String verJerarquia(HttpServletRequest request, @ModelAttribute GcJerarquiaSeccionPojo gcJerarquiaSeccionPojo, final ModelMap model, @PathVariable(value = "jerarquiasec") Long jerarquiasec){
            GcJerarquiaSeccion gcJerarquiaSeccion2 = gcJerarquiaSeccionRepository.findOne(jerarquiasec);
            model.addAttribute("gcJerarquiaSeccion", gcJerarquiaSeccion2);
            model.addAttribute("dunidadRecep", unidadRepository.findOne(gcJerarquiaSeccion2.getCUnidadRecep()).getDunidadRecep());
            GcEscritorio esc=gcEscritorioRepository.findOne(usuarioRepository.findOne(gcJerarquiaSeccion2.getSUsuario()).getNEscritorioId().getNEscritorioId());
            model.addAttribute("escritorio",esc.getCIdentificador()+esc.getNNumEscritorio() );
            return "jerarquiasec/verJerarquiaseccion";
    }
    
    public List<TbUnidadRecep> getUnidadesList(){
        List<TbUnidadRecep> unidades = null;
        if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
            unidades = (List<TbUnidadRecep>) unidadRepository.getUnidadesEnServicio();
            List<TbUnidadRecep> combinacionUnidades = unidadRepository.getUnidadesCombinaciones();
            for(TbUnidadRecep unidaddes:combinacionUnidades){
                String nombre=unidadRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
                unidaddes.setDunidadRecep(nombre==null?unidaddes.getCunidadRecep():nombre);
            }
            unidades.addAll(combinacionUnidades);
        }else{
            unidades = (List<TbUnidadRecep>) unidadRepository.getUnidadesEnServicio(getUnidad());
            for(TbUnidadRecep unidaddes:unidades){
                String nombre=unidadRepository.getNombreUnidadCombinacion(unidaddes.getCunidadRecep());
                unidaddes.setDunidadRecep(nombre==null?unidaddes.getDunidadRecep():nombre);
            }
        }
        return unidades;
    }

        private String getUnidad() {
//       String unidad = getPrincipal().getUnidadRecep();
        String unidad = "80048";
        if (unidad == null) {
            unidad = getPrincipal().getUbicacionFisica();
        }
        return unidad;

        }
          
    @RequestMapping(value = "/getuserldap/{usuarioConsulta}", method = RequestMethod.GET)
    public @ResponseBody String getContribuyenteNombre(HttpServletRequest request, final ModelMap model, @PathVariable(value = "usuarioConsulta") String usuario) {
        
        PersonW personaLdap= (PersonW) personWRepository.findByPrimaryKey(usuario.replaceAll("-",".")); 
        personaLdap = (PersonW) getName(getUsuario()); // Oscar Vides - Created for purpose proof
        if(personaLdap!= null && personaLdap.getNit()!= null ) {
            String name=personaLdap.getDisplayName();
            if(name==null)name=personaLdap.getGivenName() +" "+personaLdap.getMiddleName()+" "+ personaLdap.getSn();
            String unidad=personaLdap.getUnidadRecep();
            String tipoError="";
            if(unidad==null)unidad=personaLdap.getUbicacionFisica();
            if(unidad==null || "01".equals(unidad)){
                tipoError="Error";
            }else {
                if("ROLE_GC_R1".equals(utilidad.getAdminUsuario(getRoles()))) {
                    tipoError=unidad;
                }else{
                    if(unidad.equals(getUnidad())) tipoError=unidad;
                    else tipoError="ErrorRole";
                }
            }
            if(unidad!=null && !unidad.equals("")){
                TbUnidadRecep unn=unidadRepository.findOne(unidad);
                unidad=unn.getDunidadRecep();
            }
            return name+","+tipoError+","+unidad;
        } else {
            System.out.println("Este usuario:"+usuario+", no existe en el ldap");
            return "";
        }
    }
    
    
    public PersonW getName(String username){
        PersonW personaLdap = new PersonW();
        if(username.equals("oscar.vides")){
            personaLdap.setNit("12345678912345");
            personaLdap.setDisplayName("Oscar Armando Vides");
            personaLdap.setGivenName("oscar");
            personaLdap.setMiddleName("Armando");
            personaLdap.setSn("Vides");
            personaLdap.setUnidadRecep("80048");
        }else if(username.equals("ever.argueta")){
            personaLdap.setNit("12345678912345");
            personaLdap.setDisplayName("Ever Ernaldo Argueta");
            personaLdap.setGivenName("Ever");
            personaLdap.setMiddleName("Ernaldo");
            personaLdap.setSn("Argueta");
            personaLdap.setUnidadRecep("80048");
        }else if(username.equals("florentin.hdz")){
            personaLdap.setNit("12345678912345");
            personaLdap.setDisplayName("Florentin Lazo");
            personaLdap.setGivenName("Florentin");
            personaLdap.setMiddleName("");
            personaLdap.setSn("Lazo"); 
            personaLdap.setUnidadRecep("80048");
        }else if(username.equals("agustin.romero")){
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
