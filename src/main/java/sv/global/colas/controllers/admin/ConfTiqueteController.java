/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfTiquete;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.TbListasValor;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.repositories.GcConfTiqueteRepository;
import sv.global.colas.repositories.GcConfTramiteRepository;
import sv.global.colas.repositories.GcTramiteRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;
import sv.global.colas.utils.Utils;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/conftiquete")
@SessionAttributes("gcctiquete")
@EnableAsync
public class ConfTiqueteController  extends MainController{
    
    @Autowired
    private GcConfTiqueteRepository gcConfTiqueteRepository;
    
    @Autowired
	private GcUnidadRecepRepository gcUnidadRecepRepository;
    
    @Autowired
    private Environment env;
    
    Utils utilidad = new Utils();
         
    @ModelAttribute("gcctiquete")
    public GcConfTiquete gcctramite(){
        return new GcConfTiquete();
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexConfTiquete(HttpServletRequest request, @ModelAttribute GcConfTiquete gcConfTiquete, final ModelMap model){
            model.addAttribute("unidadesList", getUnidadesList());
            return "conftiquete/conftiq";
    }    
    
        @SuppressWarnings("unchecked")
	@RequestMapping(value="/getTq", method=RequestMethod.POST, headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getTiquete(@RequestBody Map<String, String> map){
		String r="";
                String jso="";
                String nid="";
                String bactivo="";
                GcConfTiquete cTq=null;
                String cusuario = getUsuario();
                //String unidad=getPrincipal().getUnidadRecep();
                String unidad=getUnidad();
                String nUnidad="";
                JSONArray list;
                String maxVer;
                unidad=map.get("unidad");
//                if(unidad==null)unidad=getPrincipal().getUbicacionFisica();                
                TbListasValor tlv=gcConfTiqueteRepository.n_unidad_tlv(unidad);
                if (tlv==null){
                    TbUnidadRecep tur=gcConfTiqueteRepository.n_unidad_tur(unidad);
                    nUnidad=tur.getDunidadRecep();
                }else{
                    nUnidad=tlv.getDlista();
                }                         
                
                nUnidad=nUnidad.trim();
                List<GcConfTiquete> listaVer=gcConfTiqueteRepository.listaConfiguraciones(unidad);
                list = new JSONArray();    
                JSONArray lineas;
                for (int i=0;i<listaVer.size();i++){                    
                    list.add(listaVer.get(i).getNVersion());
                    if (i==0){
                        //maximo valor de version
                        nid=listaVer.get(0).getNConfTiqId()+"";
                        bactivo=listaVer.get(0).getBActiva()+"";
                    }
                }
                
                List<String> listaPla=gcConfTiqueteRepository.lista_plantilla();
                JSONArray listNpla = new JSONArray();
                lineas = new JSONArray();
                for (int i=0;i<listaPla.size();i++){                                           
                    listNpla.add(listaPla.get(i));                                       
                }
                 if (listaVer.size()==0){
                     maxVer=0+"";
                     bactivo="0";
                     nid="0";
                 }    
                 else {   
                        maxVer=listaVer.get(0).getNVersion()+"";
                        cTq=gcConfTiqueteRepository.maxVer(maxVer, unidad);
                        String Slineas=gcConfTiqueteRepository.lineas_tiquete(unidad, maxVer);
                        StringTokenizer st;
                        st = new StringTokenizer(Slineas,"%%");          
                        while (st.hasMoreElements()){
                            String s=(String) st.nextToken();
                            s=s.trim();
                            s=s.replaceAll("&&","");
                            s=s.replaceAll("á","&aacute;");
                            s=s.replaceAll("é","&eacute;");
                            s=s.replaceAll("í","&iacute;");
                            s=s.replaceAll("ó","&oacute;");
                            s=s.replaceAll("ú","&uacute;");

                            if (s.length()!=0)
                                lineas.add(s);
                        }
                 }
               JSONObject jo=new JSONObject();
               JSONArray jsonArray=new JSONArray();
               jo.put("titulo", nUnidad);
               jo.put("versiones",list);
               jo.put("lineas",lineas);
               jo.put("maxver",maxVer);
               jo.put("nid",nid);
               jo.put("unidad",unidad);
               jo.put("bactivo",bactivo);
               if (maxVer.equals("0"))
                   jo.put("img","#");
               else    
                    jo.put("img",cTq.getXImagen());
               jo.put("Nplan",listNpla);
               jso=jo.toJSONString();
               //jso=Charset.forName("UTF-8").encode(jso).toString();                    
		return jso;
	}
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/saveTq", method=RequestMethod.POST, headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String saveTiquete(@RequestBody Map<String, String> map){
            GcConfTiquete ct=new GcConfTiquete();
            String cusuario = getUsuario();
            GcConfTiquete cTq=null;
            short bactiva=Short.parseShort(map.get("bactiva"));
            String unidad=map.get("unidad");
           SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            int nversion=Integer.parseInt(map.get("nversion"));
            int nueva=Integer.parseInt(map.get("nueva"));
            if (nueva==1){
                nversion=gcConfTiqueteRepository.maximaVersion(unidad);
                nversion++;
                ct.setNVersion(nversion);
            }
            else{
               ct.setNConfTiqId(Long.parseLong(map.get("id")));
               cTq=gcConfTiqueteRepository.currTq(map.get("id"));
               ct.setNVersion(nversion);
            }
            
            ct.setBActiva(bactiva);
            ct.setCUnidadRecep(unidad);
            ct.setXImagen(map.get("img"));
            ct.setXContenido(map.get("xcontenido")); 
            Date d=new Date();
            if (nueva==1){
                ct.setFiVigencia(d);
                ct.setCUsuarioCrea(cusuario);
            }else{
                ct.setFiVigencia(cTq.getFiVigencia());
                ct.setCUsuarioCrea(cTq.getCUsuarioCrea());
                ct.setFModifica(d);
            }
            ct.setCUsuarioModi(cusuario);                      
            
            gcConfTiqueteRepository.save(ct);
            if (bactiva==1){
                gcConfTiqueteRepository.noActivo(unidad);
                gcConfTiqueteRepository.setActivo(unidad, nversion+"");
            }
            List<GcConfTiquete> listaVer=gcConfTiqueteRepository.listaConfiguraciones(unidad);
                JSONArray list = new JSONArray();
                for (int i=0;i<listaVer.size();i++){                    
                    list.add(listaVer.get(i).getNVersion());                   
                }
            JSONObject jo=new JSONObject();
            jo.put("resultado", "ok");
            jo.put("id",ct.getNConfTiqId());
            jo.put("nversion",ct.getNVersion());
            jo.put("bactivo",ct.getBActiva());
            jo.put("versiones",list);
            String jso=jo.toJSONString();
            return jso;
        }    
        
        
        
        @SuppressWarnings("unchecked")
	@RequestMapping(value="/chgTq", method=RequestMethod.POST, headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String chgTiquete(@RequestBody Map<String, String> map){
		String r="";
                String jso="";
                long nid=0;
                short bactivo=0;
                String nversion="";
                String unidad="";
                GcConfTiquete cTq=null;
                String cusuario = getUsuario();
                String nUnidad="";
                //unidad="80052";
                nversion=map.get("nversion");
                unidad=map.get("unidad");
                
                cTq=gcConfTiqueteRepository.maxVer(nversion, unidad);
                String Slineas=gcConfTiqueteRepository.lineas_tiquete(unidad, nversion);
                if (cTq !=null){
                    bactivo=cTq.getBActiva();
                    nid=cTq.getNConfTiqId();
                }else{
                    bactivo=1;
                    nid=1001;
                }
                    
                StringTokenizer st;
                st = new StringTokenizer(Slineas,"%%");                
                JSONArray lineas = new JSONArray();
                while (st.hasMoreElements()){
                    String s=(String) st.nextToken();
                    s=s.trim();
                    s=s.replaceAll("&&","");
                    s=s.replaceAll("á","&aacute;");
                    s=s.replaceAll("é","&eacute;");
                    s=s.replaceAll("í","&iacute;");
                    s=s.replaceAll("ó","&oacute;");
                    s=s.replaceAll("ú","&uacute;");
                    
                    if (s.length()!=0)
                        lineas.add(s);
                }
                
              
               JSONObject jo=new JSONObject();
               //JSONArray jsonArray=new JSONArray();
               jo.put("lineas",lineas);
               jo.put("bactivo",bactivo);
               jo.put("nid",nid);
               jo.put("img",cTq.getXImagen());
               jso=jo.toJSONString();
               //jso=Charset.forName("UTF-8").encode(jso).toString();                    
		return jso;
	}
        
        @SuppressWarnings("unchecked")
	@RequestMapping(value="/reaPla", method=RequestMethod.POST, headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String readPlantilla(@RequestBody Map<String, String> map){
		String r="";
                String jso="";
                String nPlantilla;
                nPlantilla=map.get("nplantilla"); 
                //System.out.println(nPlantilla);
                String Slineas=gcConfTiqueteRepository.lineas_plantilla(nPlantilla);
                //System.out.println(Slineas);
                StringTokenizer st;
                st = new StringTokenizer(Slineas,"%%");                
                JSONArray lineas = new JSONArray();
                while (st.hasMoreElements()){
                    String s=(String) st.nextToken();
                    s=s.trim();
                    s=s.replaceAll("&&","");
                    s=s.replaceAll("á","&aacute;");
                    s=s.replaceAll("é","&eacute;");
                    s=s.replaceAll("í","&iacute;");
                    s=s.replaceAll("ó","&oacute;");
                    s=s.replaceAll("ú","&uacute;");
                    
                    if (s.length()!=0)
                        lineas.add(s);
                }               
               JSONObject jo=new JSONObject();
               //JSONArray jsonArray=new JSONArray();
               jo.put("lineas",lineas);
               jso=jo.toJSONString();
               //jso=Charset.forName("UTF-8").encode(jso).toString();                    
		return jso;
	}
        
        
        
        @SuppressWarnings("unchecked")
	@RequestMapping(value="/mirarTq", method=RequestMethod.POST, headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String seeTiquete(@RequestBody Map<String, String> map){
		String r="";
                GcConfTiquete rimg;
                String jso="";
                String vid=map.get("id");
                JSONObject jo=new JSONObject();
                
               //JSONArray jsonArray=new JSONArray();
                r=gcConfTiqueteRepository.visualizar(vid);
                rimg=gcConfTiqueteRepository.getimg(vid);
                //h3 {font-size:14px;} .marco {padding: 1px;border: 2px solid black;margin: 1px;}.inn {padding: 1px;border: 1px dashed blue;margin: 5px;background-color: FFFFFF;text-align:center;}
                if (r.equals("-1")){
                    jo.put("lineas","<center>Ningun Registro a mostrar <br>(puede que no haya salvado el registro)</center>");
                   jo.put("img","#");
                }else {
                   jo.put("lineas",r);
                   jo.put("img",rimg.getXImagen());
                }
               jso=jo.toJSONString();
                return jso;
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
