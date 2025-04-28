/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sv.global.colas.controllers.MainController;
import sv.global.colas.entities.GcMultimedia;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.pojos.entities.MultimediaPojo;
import sv.global.colas.pojos.entities.ServicioPojo;
import sv.global.colas.pojos.entities.ZonaPojo;
import sv.global.colas.repositories.GcMultimediaRepository;
import sv.global.colas.repositories.GcMultimediaRepositoryImpl;
import sv.global.colas.repositories.GcServicioRepository;

/**
 *
 * @author Owner
 */
@Controller
@RequestMapping("/multimedia")
@SessionAttributes("gcmultimedia")
@EnableAsync
public class MultimediaController extends MainController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private GcMultimediaRepository multimediaRepository;

    @Autowired
    private Environment env;

    @Autowired
    ServletContext srvContext;

    private GcMultimediaRepositoryImpl gcmultimediarepositoryiml = new GcMultimediaRepositoryImpl();

    /* default constructor for compatibility java 11 */
    public MultimediaController() {
    }

    @ModelAttribute(value = "gcmultimedia")
    public MultimediaPojo serviciomulti() {
        MultimediaPojo servicio = new MultimediaPojo();

        List<String> multimediaList = multimediaRepository.findA();

        List<GcMultimedia> lista = new ArrayList<GcMultimedia>();
        String s = "";
        StringTokenizer st;
        for (int i = 0; i < multimediaList.size(); i++) {
            try {
                GcMultimedia lst = new GcMultimedia();
                s = multimediaList.get(i);
                st = new StringTokenizer(s, ";");
                DateFormat format = new SimpleDateFormat("dd/M/yyyy");
                int j = 0;
                while (st.hasMoreElements()) {
                    String ss = (String) st.nextToken();
                    switch (j) {
                        case 0:
                            lst.setNMultimediaId(Long.parseLong(ss));
                            break;
                        case 1:
                            lst.setSTipo(ss);
                            break;
                        case 2:
                            lst.setNDuracion(Long.parseLong(ss));
                            break;
                        case 3:
                            lst.setFiMultimedia(format.parse(ss));
                            break;
                        case 4:
                            lst.setFfMultimedia(format.parse(ss));
                            break;
                        case 5:
                            lst.setDMultimedia(ss);
                            break;
                        case 6:
                            lst.setCUsuarioCrea(ss);
                            break;
                        case 7:
                            lst.setCUsuarioModi(ss);
                            break;
                        case 8:
                            lst.setFiVigencia(format.parse(ss));
                            break;
                        case 9:
                            lst.setFModifica(format.parse(ss));
                            break;
                        case 10:
                            lst.setBActiva(Short.parseShort(ss));
                            break;
                        case 11:
                            lst.setSRuta(ss);
                            break;

                    }
                    j++;
                }
                lst.setXArchivo(null);
                lista.add(lst);
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        servicio.setGcMultimediaList(lista);
        return servicio;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexServicio(HttpServletRequest request, @ModelAttribute MultimediaPojo servicioP, final ModelMap model) {
        System.out.println("antes....");
        List<String> multimediaList = multimediaRepository.findA();
        System.out.println("despues...." + multimediaList.size());
        List<GcMultimedia> lista = new ArrayList<GcMultimedia>();
        String s = "";
        StringTokenizer st;
        for (int i = 0; i < multimediaList.size(); i++) {
            try {
                GcMultimedia lst = new GcMultimedia();
                s = multimediaList.get(i);
                // System.out.println("procesando "+i+" y contenido "+s);
                DateFormat format = new SimpleDateFormat("dd/M/yyyy");
                st = new StringTokenizer(s, ";");
                int j = 0;
                while (st.hasMoreElements()) {
                    String ss = (String) st.nextToken();
                    //System.out.println(j+"-"+ss);
                    switch (j) {
                        case 0:
                            lst.setNMultimediaId(Long.parseLong(ss));
                            break;
                        case 1:
                            lst.setSTipo(ss);
                            break;
                        case 2:
                            lst.setNDuracion(Long.parseLong(ss));
                            break;
                        case 3:
                            lst.setFiMultimedia(format.parse(ss));
                            break;
                        case 4:
                            lst.setFfMultimedia(format.parse(ss));
                            break;
                        case 5:
                            lst.setDMultimedia(ss);
                            break;
                        case 6:
                            lst.setCUsuarioCrea(ss);
                            break;
                        case 7:
                            lst.setCUsuarioModi(ss);
                            break;
                        case 8:
                            lst.setFiVigencia(format.parse(ss));
                            break;
                        case 9:
                            lst.setFModifica(format.parse(ss));
                            break;
                        case 10:
                            lst.setBActiva(Short.parseShort(ss));
                            break;
                        case 11:
                            lst.setSRuta(ss);
                            break;

                    }
                    j++;
                }
                lst.setXArchivo(null);
                lista.add(lst);
            } catch (Exception e) {
                System.out.println("mensaje: " + e.getMessage());
            }
        }
        // System.out.println("resultado:"+lista.size());
//            System.out.println(multimediaList.get(0).getDMultimedia());
//            System.out.println(multimediaList.get(0).getCUsuarioCrea());
        MultimediaPojo multimediapojo;// = (MultimediaPojo) model.get("multimediapojo");
        //if(multimediapojo==null) 
        multimediapojo = new MultimediaPojo();
        multimediapojo.setGcMultimediaList((List<GcMultimedia>) lista);
        model.addAttribute("multimediapojo", multimediapojo);
        return "multimedia/multimedia";
    }

    //desde aca
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(HttpServletRequest request, @ModelAttribute GcMultimedia gcMultimedia, final ModelMap model,
            @RequestParam("contenido") MultipartFile file) {
        System.out.println("ENTRA A GUARDAR EN MULTIMEDIACONTROLLER");
        //GcMultimedia multimedia = (GcMultimedia) model.get("gcmultimedia");
        byte[] bytes;
        Blob bb = null;
        boolean conBlog = true;
        try {
            bytes = file.getBytes();
            System.out.println(bytes.length);
            if (gcMultimedia.getNMultimediaId() == null) {
                //nuevo                      
                bb = new SerialBlob(bytes);
                gcMultimedia.setsMultimedia(file.getOriginalFilename());
                gcMultimedia.setXArchivo(bb);
                gcMultimedia.setCUsuarioCrea(getUsuario());
                gcMultimedia.setCUsuarioModi(getUsuario());
                gcMultimedia.setFModifica(new Date());
                gcMultimedia.setFfVigencia(null);
                gcMultimedia.setFiVigencia(new Date());
            } else {
                gcMultimedia.setFModifica(new Date());
                gcMultimedia.setCUsuarioModi(getUsuario());
                if (!file.getOriginalFilename().trim().equals("")) {
                    gcMultimedia.setsMultimedia(file.getOriginalFilename());
                    bb = new SerialBlob(bytes);
                    gcMultimedia.setXArchivo(bb);
                } else {
                    conBlog = false;
                }
            }
            gcMultimedia = multimediaRepository.findOne(gcMultimedia.getNMultimediaId());
            gcMultimedia.setCUsuarioModi(getUsuario());
            gcMultimedia.setFModifica(new Date());
            if (conBlog) {
                multimediaRepository.save(gcMultimedia);
            } else {
                multimediaRepository.actualizar(gcMultimedia.getNMultimediaId(),
                        gcMultimedia.getNDuracion().longValue(),
                        gcMultimedia.getCUsuarioModi(),
                        gcMultimedia.getDMultimedia(),
                        gcMultimedia.getFModifica(),
                        gcMultimedia.getBActiva(),
                        gcMultimedia.getSTipo(),
                        gcMultimedia.getFfMultimedia(),
                        gcMultimedia.getFiMultimedia(),
                        gcMultimedia.getSRuta());
            }
            model.addAttribute("mensaje", "Datos guardados correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error al momento de Guardar los datos!");
        }
        System.out.println("PRUEBA...");
        //indexServicio(request, null, model);
        return "multimedia";
    }

    //hasta aca   
    @RequestMapping("/newMultimedia")
    public String newServicio(HttpServletRequest request,
            @ModelAttribute GcMultimedia gcMultimedia, final ModelMap model) {
        model.addAttribute("gcmultimedia", gcMultimedia);
        return "multimedia/newMultimedia";
    }

    @RequestMapping(value = "{servicio}/editMultimedia", method = RequestMethod.GET)
    public String editMultimedia(HttpServletRequest request,
            @ModelAttribute GcMultimedia gcServicios_2, final ModelMap model,
            @PathVariable(value = "servicio") Long servicioId) {
        SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");
        String s = multimediaRepository.findO(servicioId);
        StringTokenizer st;
        MultimediaPojo gcServicios = new MultimediaPojo();
        try {
            st = new StringTokenizer(s, ";");
            int j = 0;
            while (st.hasMoreElements()) {
                String ss = (String) st.nextToken();
                //System.out.println(j+"-"+ss);
                switch (j) {
                    case 0:
                        gcServicios.setnMultimediaId(Long.parseLong(ss));
                        break;
                    case 1:
                        gcServicios.setsTipo(ss);
                        break;
                    case 2:
                        gcServicios.setnDuracion(Long.parseLong(ss));
                        break;
                    case 3:
                        gcServicios.setFiMultimedia(ss);
                        break;
                    case 4:
                        gcServicios.setFfMultimedia(ss);
                        break;
                    case 5:
                        gcServicios.setdMultimedia(ss);
                        break;
                    case 6:
                        gcServicios.setcUsuarioCrea(ss);
                        break;
                    case 7:
                        gcServicios.setcUsuarioModi(ss);
                        break;
                    case 8:
                        gcServicios.setFiVigencia(ss);
                        break;
                    case 9:
                        gcServicios.setfModifica(ss);
                        break;
                    case 10:
                        gcServicios.setbActiva(Short.parseShort(ss));
                        break;
                    case 11:
                        gcServicios.setsRuta(ss);
                        break;
                    case 12:
                        gcServicios.setsMultimedia(ss);
                        break;
                    case 13:
                        gcServicios.setFiVigencia(ss);

                }
                j++;
            }
        } catch (Exception e) {
            System.out.println("mensaje: " + e.getMessage());
        }
        //gcServicios = multimediaRepository.findOne(servicioId);
        //gcServicios.setBActiva(gcServicios.getBAtiva()==0?2:gcServicios.getBActiva());
        model.addAttribute("gcmultimedia", gcServicios);
        return "multimedia/editMultimedia";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, @ModelAttribute GcMultimedia gcMultimedia, final ModelMap model, @RequestParam("contenido") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();

        // Configurar la ruta externa para guardar archivos
        //String uploadsDir = "C:\\uploads"; // Ruta absoluta en el sistema de archivos
        String uploadsDir = "/var/uploads"; 
        File directory = new File(uploadsDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path fileNameAndPath = null;

        if (file != null && !file.isEmpty()) {
            // Verificar el tipo de archivo si es necesario
            if (!file.getContentType().startsWith("image/") && !file.getContentType().startsWith("video/")) {
                model.addAttribute("mensajeError", "El archivo debe ser una imagen o un video válido.");
                return "multimedia/multimedia";
            }

            fileNameAndPath = Paths.get(uploadsDir, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            gcMultimedia.setsMultimedia(file.getOriginalFilename());
            gcMultimedia.setSRuta("/uploads/" + file.getOriginalFilename());
        }

        try {
            if (gcMultimedia.getNMultimediaId() == null) {
                gcMultimedia.setNDuracion(0L);
                gcMultimedia.setFiMultimedia(new Date());
                gcMultimedia.setFfMultimedia(new Date());
                gcMultimedia.setXArchivo(null);
                gcMultimedia.setCUsuarioCrea(getUsuario());
                gcMultimedia.setCUsuarioModi(getUsuario());
                gcMultimedia.setFModifica(new Date());
                gcMultimedia.setFfVigencia(new Date());
                gcMultimedia.setFiVigencia(new Date());
            } else {
                gcMultimedia.setFModifica(new Date());
                gcMultimedia.setCUsuarioModi(getUsuario());
                gcMultimedia.setNDuracion(0L);
                gcMultimedia.setFiMultimedia(new Date());
                gcMultimedia.setFfMultimedia(new Date());
                gcMultimedia.setFiVigencia(new Date());
            }

            multimediaRepository.save(gcMultimedia);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrió un error al momento de guardar los datos: " + e.getMessage());
        }

        indexServicio(request, null, model);
        return "multimedia/multimedia";
    }

    //
    @RequestMapping(value = "/saveMulti", method = RequestMethod.POST)
    public @ResponseBody
    String guardar_js(HttpServletRequest request, @RequestBody Map<String, String> map, final ModelMap model) {
        SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");
        GcMultimedia gcMultimedia = new GcMultimedia();
        Date d = null;
        String s = "";
        try {
            System.out.println("AQUI ENTRA A GUARDAR...");
            String id = "";
            id = map.get("id");
            if (id.equals("*")) {
                gcMultimedia.setCUsuarioCrea(getUsuario());
                gcMultimedia.setCUsuarioModi(getUsuario());
                gcMultimedia.setFModifica(new Date());
                gcMultimedia.setFfVigencia(null);
                gcMultimedia.setFiVigencia(new Date());
            } else {
                gcMultimedia = multimediaRepository.findOne(Long.parseLong(id));
                gcMultimedia.setCUsuarioModi(getUsuario());
                gcMultimedia.setFModifica(new Date());
            }
            gcMultimedia.setSTipo(map.get("stipo"));
            s = map.get("archi");
            int index = 0;
            if (!s.equals("*")) {
                index = s.lastIndexOf("\\");
                s = s.substring(index + 1);
                gcMultimedia.setsMultimedia(s);
                //  gcMultimedia.setXArchivo(map.get("video"));
            }
            //System.out.println(s);
            gcMultimedia.setNDuracion(Long.parseLong(map.get("duracion")));
            d = format.parse(map.get("fi"));
            gcMultimedia.setFiMultimedia(d);
            d = format.parse(map.get("ff"));
            gcMultimedia.setFfMultimedia(d);
            gcMultimedia.setBActiva(Short.parseShort(map.get("acti")));
            gcMultimedia.setSRuta(map.get("ruta"));
            gcMultimedia.setDMultimedia(map.get("descripcion"));
            multimediaRepository.save(gcMultimedia);
            model.addAttribute("mensaje", "Datos guardados correctamente");
            System.out.println("Guardados....");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrio un Error!" + " Error: " + e.getMessage());
            System.out.println("neles..." + e);
        }
        //indexServicio(request, null, model);
        multimediaRepository.save(gcMultimedia);
        JSONObject jo = new JSONObject();
        jo.put("resultado", "ok");
        String jso = jo.toJSONString();
        return jso;
    }

    @RequestMapping(value = "{multimedia}/verMultimedia", method = RequestMethod.GET)
    public String verMultimedia(HttpServletRequest request,
            @ModelAttribute GcMultimedia gcMultimedia, final ModelMap model,
            @PathVariable(value = "multimedia") Long multimediaId) {
        gcMultimedia = multimediaRepository.findOne(multimediaId);
        model.addAttribute("gcmultimedia", gcMultimedia);
        return "multimedia/verMultimedia";
    }

    @RequestMapping(value = "{multimedia}/eliminar", method = RequestMethod.POST)
    public String eliminar(HttpServletRequest request,
            @ModelAttribute GcMultimedia gcMultimedia, final ModelMap model,
            @PathVariable(value = "multimedia") Long multimediaId) {
        //gcMultimedia = multimediaRepository.findOne(multimediaId);
        try {
            multimediaRepository.borrarC(multimediaId.longValue());
            model.addAttribute("mensaje", "Se elimino el registro correctamente!");
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Ocurrio un Error!" + " Error: " + e.getMessage());
        }
        indexServicio(request, null, model);
        return "multimedia/multimedia";
    }

    @Transactional
    @RequestMapping(value = "/getBlob", method = RequestMethod.POST)
    public @ResponseBody
    String getBlobo(HttpServletRequest request,
            @RequestBody Map<String, String> map) {
        String jso = "";
        final String[] thefile = new String[1];
        String nfile = "";
        final String vid = map.get("id");
        JSONObject jo = new JSONObject();
        org.hibernate.Session session = em.unwrap(Session.class);
        //thefile[0]=gcmultimediarepositoryiml.getArchivo(vid,em);
        final Blob[] blobo = new Blob[1];
        //org.hibernate.Session session = em.unwrap(Session.class);

        File f;

        FileOutputStream fw = null;
        try {

            session.doWork(new Work() {
                @Override
                public void execute(Connection conn) throws SQLException {
                    Statement stmt = conn.createStatement();
                    ResultSet rset = stmt.executeQuery("select x_archivo,s_multimedia FROM gc_multimedia  where n_Multimedia_Id=" + Long.parseLong(vid));
                    if (rset.next()) {
                        blobo[0] = rset.getBlob(1);   // Print col 1  
                        thefile[0] = rset.getString(2);
                    }
                    stmt.close();
                    stmt = null;
                }
            });
            nfile = env.getProperty("dgii.dir.tmp") + "/" + thefile[0];
            System.out.println(nfile);
            f = new File(nfile);
            fw = new FileOutputStream(f);
            IOUtils.copy(blobo[0].getBinaryStream(), fw);
            fw.flush();
            fw.close();
//               
        } catch (Exception e) {
            e.printStackTrace();
        }

        jo.put("titulo", "La mera");
        jo.put("elBlob", "/colas/tmp/" + thefile[0]);
        jso = jo.toJSONString();

        return jso;
    }

    //@RequestMapping(value = "/saveAlLoco", method = RequestMethod.POST)
    @RequestMapping(value = "/guardarAll", method = RequestMethod.POST)
    public @ResponseBody
    String guardarloco(HttpServletRequest request, @RequestBody Map<String, String> map) {

        System.out.println("REQUEST: " + request);
        Long uno = (long) 16;//ew Long(1);
        GcMultimedia gcMultimedia = multimediaRepository.findOne(uno);

//            gcMultimedia.setXArchivo(map.get("video"));
        //multimediaRepository.save(gcMultimedia);
        JSONObject jo = new JSONObject();
        jo.put("resultado", "ok");
        String jso = jo.toJSONString();
        return jso;
    }

}
