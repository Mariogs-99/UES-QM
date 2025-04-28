package sv.global.colas.controllers.monitoreo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import sv.global.colas.controllers.MainController;
import sv.global.colas.controllers.operacion.TiqueteDao;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.GcTiquete;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.repositories.GcServiciosRepository;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;

@Controller
@SessionAttributes({ "usuario", "tramiteform" })
@EnableAsync
public class TiquetesEnEsperaController extends MainController {

	@Autowired
	private GcTiqueteRepository gcTiqueteRepository;

	@Autowired
	GcUnidadRecepRepository gcUnidadRecepRepository;
	
	@Autowired
	private GcServiciosRepository gcServiciosRepository;
	
	@Autowired
	private GcTramiteRepository gcTramiteRepository;

	public TiquetesEnEsperaController() {

	}

	@RequestMapping("/monitoreo/tiquetesEnEspera")
	public String pruebas(Model model) {

		model.addAttribute("user", getUsuario());
		return "monitoreo/tiquetesEnEsperaPorSeccion";
	}

	@RequestMapping(value = "/operacion/enEspera/{seccion}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public @ResponseBody List<TiqueteDao> getData(@PathVariable(value = "seccion") String seccion) {
		return tiquetesEnEspera(seccion);
	}

	@SuppressWarnings("deprecation")
	private List<TiqueteDao> tiquetesEnEspera(String seccion) {
		List<GcTiquete> allTiquetes;
		if (seccion.equals("todos")) {
			allTiquetes = gcTiqueteRepository.getPruevasEnEsperaList(getUnidad());
		}else {
			List<GcTramite> todosTramitesByServiceId = gcTramiteRepository.getTodosTramitesByServiceId(Long.valueOf(seccion));
			List<Long> nTramiteId = new ArrayList<Long>();
			for (GcTramite gcTramite : todosTramitesByServiceId) {
				nTramiteId.add(gcTramite.getNTramiteId());
			}
			allTiquetes = gcTiqueteRepository.getNext(getUnidad(), nTramiteId);			
		}
		
		List<TiqueteDao> tiquetesList = new ArrayList<TiqueteDao>();
		Date nowDate = new Date(gcTiqueteRepository.getServerDateTime().getTime());
		if (allTiquetes.isEmpty()) {
			return tiquetesList;
		}
		
		int hoursNow = nowDate.getHours();
		int minutesNow = nowDate.getMinutes();
		int secondsNow = nowDate.getSeconds();
		int segundosNow = ((hoursNow * 3600) + (minutesNow * 60) + secondsNow);
		for (GcTiquete gcTiquete : allTiquetes) {
				int hours = gcTiquete.getFhLlegada().getHours();
				int minutes = gcTiquete.getFhLlegada().getMinutes();
				int seconds = gcTiquete.getFhLlegada().getSeconds();
				int time = ((hours * 3600) + (minutes * 60) + (seconds));
				int tiempoEspera = segundosNow - time;
				Integer prioridad = gcTiquete.getNPrioridadId().getNPeso();
				if (gcTiquete.getNTiqueteRea() == 1) {
					prioridad = +1;// / Ejemplo
				}
				tiempoEspera = tiempoEspera * prioridad;
				TiqueteDao tiqueteDao = new TiqueteDao();
				tiqueteDao.setcUnidadRecep(gcTiquete.getCUnidadRecep().getDunidadRecep());
				tiqueteDao.setmEstado("En Espera");
				tiqueteDao.setnTramiteId(gcTiquete.getNTramiteId().getSNombre());
				tiqueteDao.setsCorrelativo(gcTiquete.getSCorrelativo());
				tiqueteDao.setnTiqueteId(gcTiquete.getNTiqueteId());
				tiqueteDao.setFhLlegada(gcTiquete.getFhLlegada());
				tiqueteDao.setdPrioridad(gcTiquete.getNPrioridadId().getDPrioridad());
		        switch (gcTiquete.getNTiqueteRea()) {
			        case 0:  tiqueteDao.setnTiqueteRea("NO");
			        		 tiqueteDao.setnTiqueteEsc("NO");
		            		 break;
		            case 1:  tiqueteDao.setnTiqueteRea("SI");
	        		 		 tiqueteDao.setnTiqueteEsc("NO");
		                     break;
		            case 2:  tiqueteDao.setnTiqueteRea("NO");
	        		 		 tiqueteDao.setnTiqueteEsc("SI");
		                     break;
		        }
				if(gcTiquete.getnTiempoHolgura()==null){
					tiqueteDao.setnTiempoHolgura(0);
				}else{
					tiqueteDao.setnTiempoHolgura(gcTiquete.getnTiempoHolgura());					
				}
				tiqueteDao.setPuntuacion(tiempoEspera);
				tiquetesList.add(tiqueteDao);
		}			
		return tiquetesList;
	}

        private String getUnidad() {
            return getUnidadRecep();
        }
	
	@RequestMapping(value = "/operacion/getServicios", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public @ResponseBody List<GcServicios> getServicios() {
		List<GcServicios> allServiciosActivo = gcServiciosRepository.getAllServiciosActivo();
		for (GcServicios gcServicios : allServiciosActivo) {
			gcServicios.setGcTramiteList(null);
			gcServicios.setCUsuarioCrea(null);
			gcServicios.setDServicios(null);
			gcServicios.setCUsuarioModi(null);
			gcServicios.setFfVigencia(null);
			gcServicios.setFiVigencia(null);
			gcServicios.setFModifica(null);		
		}
		return allServiciosActivo;
	}

}