package sv.global.colas.controllers.monitoreo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.TbListasValor;
import sv.global.colas.repositories.TbListasValorRepository;

@Controller
@EnableAsync
public class TbListasValorController extends MainController {
	
	
	@Autowired TbListasValorRepository tbListasValorRepository;
	
	
	
	@RequestMapping("monitoreo/home")
	public String ircentrosdeServicios(){
		return "monitoreo/chome";
	}
	
	@RequestMapping("/centrodeservicio")//mismo nombre del action y en el controlador
	public String ircentros(){
		return "monitoreo/centrodeservicio";//nombre carpeta+nombre archivo html()
	}
	
	@RequestMapping("/tramite")
	public String irtramite(){
		return "monitoreo/tramit";
	}
	
	
	

	@RequestMapping(value="/monitoreo/getListCentroServicios" , method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public @ResponseBody List<TbListasValor> getListCentroServicios(){
		
		List<TbListasValor> list = null;
		try{
			
			list = (List<TbListasValor>) tbListasValorRepository.getListCentroServicios();
			
			for(TbListasValor tb : list){
				tb.setTbListasValorDets(null);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return list;
		
	}
	
}
