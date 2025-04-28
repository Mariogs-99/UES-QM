package sv.global.colas.controllers.me;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcTiquete;
import sv.global.colas.pojos.entities.ReasignacionTiquetePojo;
import sv.global.colas.repositories.GcTiqueteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.repositories.TbListasValorRepository;
import sv.global.colas.utils.Estados;
import sv.global.colas.utils.Utils;

@Controller
@EnableAsync
public class ReimprimirTiqueteController extends MainController {
	
    @Autowired
    GcTiqueteRepository gcTiqueteRepository;

    @Autowired
    TbListasValorRepository tbListasValorRepository;
        
    @Autowired
    GcUnidadRecepRepository gcUnidadRecepRepository;
	
    
    @RequestMapping(value="/reimTiq/data/{str}" , method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
    public @ResponseBody List<ReasignacionTiquetePojo> getData(@PathVariable(value="str") String str){
        List<ReasignacionTiquetePojo> listTiquete = null;
        List<String> lUnidadRecep = null;
        Utils util = null;
	try{
            util = new Utils();
            String cunidadRecep = getUnidad();
            lUnidadRecep = new ArrayList<String>();	
            lUnidadRecep.add(cunidadRecep);
            List<GcTiquete> tiquete = (List<GcTiquete>) gcTiqueteRepository.getListTiquetesEnEspera(lUnidadRecep);	
            listTiquete = new ArrayList<ReasignacionTiquetePojo>();
            for(GcTiquete t : tiquete){
                ReasignacionTiquetePojo reaTiq = new ReasignacionTiquetePojo();
                reaTiq.setEstado(Estados.getEstado(t.getMEstado()));
                reaTiq.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(t.getFhLlegada()));
                reaTiq.setHora(new SimpleDateFormat("hh:mm:ss").format(t.getFhLlegada()));
                reaTiq.setIdServicio(t.getNTramiteId().getNServiciosId().getNServiciosId());
                reaTiq.setIdTiquete(t.getNTiqueteId());
                reaTiq.setIdTramite(t.getNTramiteId().getNTramiteId());
                reaTiq.setNit(Utils.formatNit(t.getNit()));
                reaTiq.setServicio(t.getNTramiteId().getNServiciosId().getSNombre());
                reaTiq.setTiqueteNo(t.getSCorrelativo());
                reaTiq.setTramite(t.getNTramiteId().getSNombre());
                if(str.equals("Todos")){
                    listTiquete.add(reaTiq);
                }else if(util.contains(str, reaTiq)){
                    listTiquete.add(reaTiq);
                }
            }
        }catch(Exception ex){
                ex.printStackTrace();
        }
	return listTiquete;
    }
        
        
    public String getUnidad() {
        return getUnidadRecep();
    }

}
