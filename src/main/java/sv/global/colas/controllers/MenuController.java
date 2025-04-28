package sv.global.colas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.global.colas.pojos.security.PersonW;
import sv.global.colas.repositories.PersonWRepository;

@Controller
public class MenuController extends MainController {
	
	@Autowired
	PersonWRepository personWRepository;
	
	@RequestMapping("/me/home")
	public String irMesaEntrada(Model model){
		PersonW personaLdap= (PersonW) personWRepository.findByPrimaryKey(getUsuario()); 

        String name=personaLdap.getDisplayName();
        if(name==null)name=personaLdap.getGivenName() +" "+personaLdap.getMiddleName()+" "+ personaLdap.getSn();
		model.addAttribute("user", getUsuario());
                String unidad = getUnidadEnServicio();
                model.addAttribute("dunidad",unidad );
		return "me/home";
	}
	
	
	@RequestMapping("/me/reservaCita")
	public String irReservaCita(Model model){
		PersonW personaLdap= (PersonW) personWRepository.findByPrimaryKey(getUsuario()); 

        String name=personaLdap.getDisplayName();
        if(name==null)name=personaLdap.getGivenName() +" "+personaLdap.getMiddleName()+" "+ personaLdap.getSn();
		model.addAttribute("user", getUsuario());
		return "me/reservaCita";
	}
	
	
	@RequestMapping("/me/reasignacionTiquete")
	public String irReasignacionTiquete(Model model){
		PersonW personaLdap= (PersonW) personWRepository.findByPrimaryKey(getUsuario()); 

        String name=personaLdap.getDisplayName();
        if(name==null)name=personaLdap.getGivenName() +" "+personaLdap.getMiddleName()+" "+ personaLdap.getSn();
		model.addAttribute("user", getUsuario());
		return "me/reasignacionTiquete";
	}
	
	
	@RequestMapping("/me/escalamientoTiquete")
	public String irEscalamientoTiquete(Model model){
		PersonW personaLdap= (PersonW) personWRepository.findByPrimaryKey(getUsuario()); 

        String name=personaLdap.getDisplayName();
        if(name==null)name=personaLdap.getGivenName() +" "+personaLdap.getMiddleName()+" "+ personaLdap.getSn();
		model.addAttribute("user", getUsuario());
		return "me/escalamientoTiquete";
	}
	
	
	@RequestMapping("/me/reimpresionTiquete")
	public String irReimpresionTiquete(Model model){
		PersonW personaLdap= (PersonW) personWRepository.findByPrimaryKey(getUsuario()); 

        String name=personaLdap.getDisplayName();
        if(name==null)name=personaLdap.getGivenName() +" "+personaLdap.getMiddleName()+" "+ personaLdap.getSn();
		model.addAttribute("user", getUsuario());
		return "me/reimpresionTiquete";
	}
	
	
	@RequestMapping("/admin/home")
	public String irAdministracion(){
		return "admin/index";
	}
        
       
}
