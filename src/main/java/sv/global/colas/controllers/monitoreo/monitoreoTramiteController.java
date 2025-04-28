package sv.global.colas.controllers.monitoreo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.pojos.entities.MonitoreoSeccionTramites;
import sv.global.colas.pojos.entities.MonitoreoSeccionUsuarios;
import sv.global.colas.pojos.entities.MonitoreoTramitePojo;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;

@Controller
@RequestMapping("/monitoreoT")
@SessionAttributes("monitoreoTramitePojo")
@EnableAsync
public class monitoreoTramiteController extends MainController {

    @Autowired
    private GcTramiteRepository gcTramiteRepository;
    @Autowired
    private GcUnidadRecepRepository gcUnidadRecepRepository;

    @ModelAttribute("monitoreoTramitePojo")
    public MonitoreoTramitePojo monitoreoTramite() {
        MonitoreoTramitePojo monitoreoTramitePojo = new MonitoreoTramitePojo();
        return monitoreoTramitePojo;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexEscritorio(HttpServletRequest request, @ModelAttribute MonitoreoTramitePojo monitoreoTramitePojo, final ModelMap model) {
        Iterable<GcTramite> tramiteList = gcTramiteRepository.getAllTramites();
        monitoreoTramitePojo.setTramiteList((List<GcTramite>) tramiteList);
        monitoreoTramitePojo.setUnidadesList(gcUnidadRecepRepository.getUnidadesEnServicio());
        model.addAttribute("monitoreoTramitePojo", monitoreoTramitePojo);
        model.addAttribute("gcTramiteList",gcTramiteRepository.getAllTramites()); 
        try{
            String nombre="";
            TbUnidadRecep unidad=gcUnidadRecepRepository.findOne(getUnidad());
            nombre=gcUnidadRecepRepository.getNombreUnidadCombinacion(unidad.getCunidadRecep());
            nombre=nombre==null?unidad.getDunidadRecep():nombre;
            model.addAttribute("unidadRecep",nombre);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "monitoreo/mTramites";
        
    }
    
    @RequestMapping(value = "/getMonAll", method = RequestMethod.GET,  headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public MonitoreoTramitePojo getall(HttpServletRequest request, @RequestParam(value = "idT", required = false) String tramite){
       
        MonitoreoTramitePojo monitoreoTramitePojo=new MonitoreoTramitePojo();
        if(tramite!=null && !"".equals(tramite)){
        Long tramiteId=Long.parseLong(tramite);
        MonitoreoSeccionTramites mst=new MonitoreoSeccionTramites();
        List serie=gcTramiteRepository.getTramitesByEstados(tramiteId, getUnidad());
        for(int i=0;i<serie.size();i++){
                Object[] obj=null;
                obj=(Object[]) serie.get(i);
                mst.setTramiteId(Long.parseLong(obj[0]+""));
                mst.setTramite((String)obj[1]);
                mst.setEsperando(Long.parseLong(obj[2]+""));
                mst.setProcesados(Long.parseLong(obj[3]+""));
        }
        serie=gcTramiteRepository.getSeriesTramiteEsperando(tramiteId, getUnidad());
        if(serie!=null){
                Object[] obj2=null;
                obj2=(Object[]) serie.get(0);
                mst.setHora(Long.parseLong(obj2[0]+""));
                mst.setMin45(Long.parseLong(obj2[1]+""));
                mst.setMin30(Long.parseLong(obj2[2]+""));
                mst.setMin15(Long.parseLong(obj2[3]+""));
                mst.setActual(Long.parseLong(obj2[4]+""));
                }else{
                    mst.setHora(Long.MIN_VALUE);
                    mst.setMin45(Long.MIN_VALUE);
                    mst.setMin30(Long.MIN_VALUE);
                    mst.setMin15(Long.MIN_VALUE);
                    mst.setActual(Long.MIN_VALUE);
                }
        
        monitoreoTramitePojo.setMst(mst);
        try{
            List dato1=gcTramiteRepository.getTramiteEspera(tramiteId,getUnidad());
            if(dato1!=null){
                for(int i=0;i<dato1.size();i++){
                    Object[] obj=(Object[]) dato1.get(i);
                    monitoreoTramitePojo.setMaxEspera((String)obj[0]);
                    monitoreoTramitePojo.setMinEspera((String)obj[0]);
                    monitoreoTramitePojo.setPromEspera((String)obj[0]);
                    monitoreoTramitePojo.setModaEspera((String)obj[0]);
                }
            }else{
                monitoreoTramitePojo.setMaxEspera("0:00:00");
                monitoreoTramitePojo.setMinEspera("0:00:00");
                monitoreoTramitePojo.setPromEspera("0:00:00");
                monitoreoTramitePojo.setModaEspera("0:00:00");
            }
            List dato2=gcTramiteRepository.getTramiteLlamado(tramiteId,getUnidad());
            if(dato2!=null){
                for(int i=0;i<dato2.size();i++){
                    Object[] obj=(Object[]) dato2.get(i);
                    monitoreoTramitePojo.setMaxLlamado((String)obj[0]);
                    monitoreoTramitePojo.setMinLlamado((String)obj[0]);
                    monitoreoTramitePojo.setPromLlamado((String)obj[0]);
                    monitoreoTramitePojo.setModaLlamado((String)obj[0]);
                }
            }else{
                monitoreoTramitePojo.setMaxLlamado("0:00:00");
                monitoreoTramitePojo.setMinLlamado("0:00:00");
                monitoreoTramitePojo.setPromLlamado("0:00:00");
                monitoreoTramitePojo.setModaLlamado("0:00:00");
            }
            List dato3=gcTramiteRepository.getTramiteProceso(tramiteId,getUnidad());
            if(dato3!=null){
                for(int i=0;i<dato3.size();i++){
                    Object[] obj=(Object[]) dato3.get(i);
                    monitoreoTramitePojo.setMaxProceso((String)obj[0]);
                    monitoreoTramitePojo.setMinProceso((String)obj[0]);
                    monitoreoTramitePojo.setPromProceso((String)obj[0]);
                    monitoreoTramitePojo.setModaProceso((String)obj[0]);
                }
            }else{
                monitoreoTramitePojo.setMaxProceso("0:00:00");
                monitoreoTramitePojo.setMinProceso("0:00:00");
                monitoreoTramitePojo.setPromProceso("0:00:00");
                monitoreoTramitePojo.setModaProceso("0:00:00");
            }
            List<MonitoreoSeccionUsuarios> usuarios=new ArrayList<MonitoreoSeccionUsuarios>();
            List usuariosList=gcTramiteRepository.getUsuarioByTramite(tramiteId,getUnidad());
            for(int i=0;i<usuariosList.size();i++){
                MonitoreoSeccionUsuarios users=new MonitoreoSeccionUsuarios();
                Object[] obj=(Object[]) usuariosList.get(i);
                users.setUsuario((String)obj[0]);
                users.setEstado((String)obj[1]);
                usuarios.add(users);
            }
            monitoreoTramitePojo.setUsuarios(usuarios);
            
        }catch(Exception e){
            e.printStackTrace();
        }
       }
        return monitoreoTramitePojo;
    }
    

        private String getUnidad() {
            return getUnidadRecep();
        }
}
