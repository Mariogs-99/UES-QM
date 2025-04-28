package sv.global.colas.controllers.me;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcJerarquiaSeccion;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.GcTiquete;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.repositories.GcConfTramiteRepository;
import sv.global.colas.repositories.GcJerarquiaSeccionRepository;
import sv.global.colas.repositories.GcPrioridadRepository;
import sv.global.colas.repositories.GcServiciosRepository;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.GcUsuarioRepository;
import sv.global.colas.repositories.PersonWRepository;
import sv.global.colas.repositories.RcRucRepository;
import sv.global.colas.repositories.TbListasValorRepository;

@Controller
@EnableAsync
public class EscalamientoTiqueteController extends MainController {

	
	@Autowired
	GcServiciosRepository gcServiciosRepository;
	
	@Autowired
	GcTramiteRepository gcTramiteRepository;
	
	@Autowired
	private PersonWRepository personWRepository;
		
	@Autowired
	GcPrioridadRepository gcPrioridadRepository;
	
	@Autowired
	GcTiqueteRepository gcTiqueteRepository;
	
	@Autowired
	GcUsuarioRepository gcUsuarioRepository;
	
	@Autowired
	RcRucRepository rcRucRepository;
	
	@Autowired
	GcUnidadRecepRepository gcUnidadRecepRepository;
	
	@Autowired
	GcConfTramiteRepository gcConfTramiteRepository;
	
	@Autowired
	TbListasValorRepository tbListasValorRepository;
        
        @Autowired
	GcJerarquiaSeccionRepository gcJerarquiaSeccionRepository;
	
	
    @RequestMapping(value="/escTiq/services" , method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
    public @ResponseBody List<GcServicios> getServices(){
        List<GcServicios> listServicios = null;
        try{
            listServicios = (List<GcServicios>) gcServiciosRepository.getAllServicios();
            for(GcServicios s : listServicios){
                s.setGcTramiteList(null);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listServicios;
    }
	
	
	
    @RequestMapping(value="/escTiq/tramitesByService" , method=RequestMethod.POST, headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<GcConfTramite> getTramitesByService(@RequestBody Map<String, String> map){
        List<GcConfTramite> listTramites = null;
        List<String> lUnidadRecep = null;
        try{	
            String cunidadRecep = getUnidad();
            lUnidadRecep = new ArrayList<String>();
            lUnidadRecep.add(cunidadRecep);	
            listTramites = (List<GcConfTramite>) gcConfTramiteRepository.listaTramitesEscByCS(lUnidadRecep,new Long(map.get("servicioId")));
            for(GcConfTramite t : listTramites){
                t.getNTramiteId().setGcUsuarioList(null);
                t.getNTramiteId().setGcReservaCitaList(null);
                t.getNTramiteId().setGcTiqueteList(null);
                t.getNTramiteId().getNServiciosId().setGcTramiteList(null);
                t.getNTramiteId().setGcConfTramiteList(null);
                t.getCUnidadRecep().setRcTramites(null);
                t.getCUnidadRecep().setEdDeclaracioneses(null);
                t.getCUnidadRecep().setEdNotaAbonos(null);
            }		
        }catch(Exception ex){
                ex.printStackTrace();
        }
            return listTramites;
    }
    
    
    
    @RequestMapping(value="/escTiq/getJerarquia" , method=RequestMethod.POST, headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<GcJerarquiaSeccion> getJerarqui(@RequestBody Map<String, String> map){
        List<GcJerarquiaSeccion> listJerarquia = null;
        try{	
            String cunidadRecep = getUnidad();
            listJerarquia = (List<GcJerarquiaSeccion>) gcJerarquiaSeccionRepository.getJerarquiaBySeccion(cunidadRecep,new Long(map.get("seccion")));
            for(GcJerarquiaSeccion j : listJerarquia){
                j.getNServiciosId().setGcTramiteList(null);
                j.getNJerarquiaId().setGcJerarquiaSeccionList(null);
            }		
        }catch(Exception ex){
                ex.printStackTrace();
        }
            return listJerarquia;
    }
	
	
	
    @RequestMapping(value="escTiq/insertData", method=RequestMethod.POST, headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody GcTiquete insertData(@RequestBody Map<String, Long> map){
	GcTiquete tiquete = null;
	try{	
            GcTiquete t = new GcTiquete();	
            String cunidadRecep = getUnidad();
            t.setNTramiteId(gcTramiteRepository.findOne(map.get("tramiteId")));
            TbUnidadRecep unidadRecep = (TbUnidadRecep) gcUnidadRecepRepository.getUnidadEnServicioE(cunidadRecep);
            unidadRecep.setRcTramites(null);
            unidadRecep.setEdDeclaracioneses(null);
            unidadRecep.setEdNotaAbonos(null);
	    t.setCUnidadRecep(unidadRecep);
            t.setNPrioridadId(gcPrioridadRepository.findOne(new Long(1)));
            t.setNTiqueteRea(2);
            t.setSCorrelativo(gcTiqueteRepository.getMaxCorrelativo(map.get("tramiteId"),cunidadRecep));
            t.setMEstado(1);
            t.setFhLlegada(new Date());	
            t.getNTramiteId().setGcUsuarioList(null);
            t.getNTramiteId().getNServiciosId().setGcTramiteList(null);
            t.getNTramiteId().setGcConfTramiteList(null);
            t.getNTramiteId().setGcReservaCitaList(null);
            t.getNTramiteId().setGcTiqueteList(null);
            t.getNPrioridadId().setGcTiqueteList(null);	
            t.setnJrqSecId(Integer.parseInt(map.get("jerarquiaId").toString()));
            int countTr = gcTiqueteRepository.verifyIfExistsTramiteAsignado(map.get("tramiteId"), cunidadRecep);	
            if(countTr!=0){
                tiquete = (GcTiquete) gcTiqueteRepository.save(t);
            }else{
                tiquete = new GcTiquete();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
	return tiquete;	
    }
        
        
    public String getUnidad() {
        return getUnidadRecep();
    }
	
	
	
}
