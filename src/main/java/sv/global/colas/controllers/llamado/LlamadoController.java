package sv.global.colas.controllers.llamado;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcConfLlamado;
import sv.global.colas.entities.GcMultimedia;
import sv.global.colas.repositories.GcConfLlamadoRepository;
import sv.global.colas.repositories.GcUnidadRecepRepository;

@Controller
@SessionAttributes({ "usuario" })
@EnableAsync
public class LlamadoController extends MainController {

	@Autowired
	private GcUnidadRecepRepository gcUnidadRecepRepository;
	
	@Autowired 
	private GcConfLlamadoRepository gcConfLlamadoRepository;
        
    @Autowired
    private Environment env;
        
    @PersistenceContext
    private EntityManager em;

	@RequestMapping("/llamado/home")
	public String home(ModelMap map) {
		String unidad = getUnidad();
		map.put("centro", unidad);
		List<GcConfLlamado> confsByCS = gcConfLlamadoRepository.getConfsByCS(getUnidad());
		
		if(confsByCS.isEmpty()){
			map.put("mensaje", "Ministerio de Hacienda Gobierno de El Salvador");
		}
		else{
			map.put("mensaje", confsByCS.get(0).getSMensaje());
		}
                //cargarArchivos(unidad);
		return "llamado/llamado";
	}
	
    public String getUnidad() {
        return getUnidadRecep();
    }
	
	
//       @Transactional 
//       private String cargarArchivos(String unidad){
//           String ret="";
//           String directorio=env.getProperty("dgii.dir.contenido");
//           File folder=new File(directorio);
//           File[] files=folder.listFiles();
//           int nfile=files.length;
//           String[][] lista = new String[nfile][2];
//           
//           for (int i=0;i<nfile;i++){
//               lista[i][0]="B";
//               lista[i][1]=files[i].getName();               
//           }
//                      
//           List<GcMultimedia> contenido=gcConfLlamadoRepository.listrepro(unidad);
//           boolean nuevo=false;
//           String[][] Nuevo = new String[contenido.size()][2];
//           int indNu=0;
//           String
//           for (int i=0;i<contenido.size();i++){
//               //System.out.println(contenido.get(i));
//               String[] s=contenido.get(i).split(";");
//               if (ret.length()>0){
//                   ret=ret+";";
//               }
//               ret=ret+s[2];
//               boolean buscar=true;
//               int j=0;
//               nuevo=true;
//               while (buscar && j<nfile){
//                   if (lista[j][1].equals(s[2])){
//                       buscar=false;
//                       lista[j][0]="M";
//                       nuevo=false;
//                   }else{
//                       j++;
//                   }
//               }
//               if (nuevo){
//                   Nuevo[indNu][0]=s[2];
//                   Nuevo[indNu][1]=s[0]+"";
//                   indNu++;
//               }
//           }
//           //borrar los que ya no estan en la lista
//           for (int i=0;i<nfile;i++){
//               //System.out.println(lista[i][0]+" es "+lista[i][1]);
//               if (lista[i][0].equals("B")){
//                   //files[i].delete();
//               }
//           }
//           for (int i=0;i<indNu;i++){
//               //System.out.println("Nuevo "+Nuevo[i][0]+" con "+Nuevo[i][1]);
//               //bajarContenido(Nuevo[i][1]);
//           }     
//           //System.out.println("el ret "+ret);
//           return ret;
//       }
       @Transactional
       private void bajarContenido(String id){
            final String[] thefile=new String[1];
            final String vid=id;          
            final Blob[] blobo= new Blob[1];
            String nfile="";
            org.hibernate.Session session = em.unwrap(Session.class);
            File f;                         
            FileOutputStream fw = null;
            try{                   
                      
               
               session.doWork(new Work(){
		    @Override public void execute(Connection conn) throws SQLException {
                        Statement stmt = conn.createStatement();
                        ResultSet rset = stmt.executeQuery("select x_archivo,s_multimedia FROM gc_multimedia  where n_Multimedia_Id="+Long.parseLong(vid));
                        if(rset.next()){
                            blobo[0]=rset.getBlob(1);   // Print col 1  
                            thefile[0]=rset.getString(2);
                        }
                        stmt.close();
                        stmt=null;
                    }
               });
                nfile=env.getProperty("dgii.dir.contenido")+"/"+thefile[0];
                //System.out.println(nfile);
               f=new File(nfile);
               fw = new FileOutputStream(f);
               IOUtils.copy(blobo[0].getBinaryStream(), fw);
               fw.flush();
               fw.close();
//               
            } catch(Exception e){
                e.printStackTrace();                
            }
           
       }
       
    @Transactional
    @RequestMapping(value="/llamado/getContenido", method = RequestMethod.POST)
    public @ResponseBody String getBlobo (HttpServletRequest request, 
            @RequestBody Map<String, String> map){
            String jso="";
            String unidad = getUnidad();
            
            List<GcMultimedia> contenido=gcConfLlamadoRepository.listrepro(unidad);
            Gson gson = new Gson();
            jso = gson.toJson(contenido);
            return jso;
    }

}