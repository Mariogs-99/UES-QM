/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.reportes;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.SimpleFileResolver;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author LiliHaydee
 */
@Controller
@RequestMapping("/impresion0712")
@EnableAsync
public class Impresion0712 {
    @PersistenceContext
     private EntityManager em;
    public Connection con = null;
    public String jasperReportDir;
    
  @Autowired
  Environment env;
  @Transactional 
  @RequestMapping(value = "/pdf", method = RequestMethod.GET)
  @ResponseBody
  public void getRpt1(HttpServletResponse response, HttpServletRequest req) throws JRException, IOException, SQLException, ClassNotFoundException {
     String archivo=""; //env.getProperty("dgii.jasper.reportes") + "0712.jasper";
    String losmeses=req.getParameter("MM");
    if (losmeses.equals("MON-YYYY"))
        archivo=env.getProperty("dgii.jasper.reportes") + "0712A.jasper";
    else
        archivo=env.getProperty("dgii.jasper.reportes") + "0712.jasper";
    
    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
    Map parameters = new HashMap();
    parameters.put("Icono1", env.getProperty("dgii.jasper.reportes")+"esa.png");
    parameters.put("Icono2", env.getProperty("dgii.jasper.reportes")+"logoMh.png");
    parameters.put("FechaI", req.getParameter("FechaI"));
    parameters.put("FechaF", req.getParameter("FechaF"));
    parameters.put("Unidad", req.getParameter("Unidad"));
//    parameters.put("Tramite", new Integer(req.getParameter("Tramite")));
    parameters.put("Tramite", req.getParameter("Tramite"));
    parameters.put("Servicio",new Integer(req.getParameter("Servicio")));
    parameters.put("DiaSemana",new Integer(req.getParameter("DiaSemana")));
    parameters.put("AreaAtencion", req.getParameter("AreaAtencion"));
    parameters.put("nunidad", req.getParameter("nunidad"));
    parameters.put("Ver",req.getParameter("Ver"));
    parameters.put("Meses",req.getParameter("MM"));
    
    Conexion();
    
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters ,con);
    response.setContentType("application/x-pdf");
    response.setHeader("Content-disposition", "inline; filename=0708.pdf");
    final OutputStream outStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    outStream.flush();     
    outStream.close();
    con.close();
    }    
    
  @Transactional
  @RequestMapping(value = "/html", method = RequestMethod.GET)
  @ResponseBody
  public void getRpt2(HttpServletResponse response, HttpServletRequest req) throws JRException, IOException, SQLException, ClassNotFoundException {
    String archivo=""; //env.getProperty("dgii.jasper.reportes") + "0712.jasper";
    String losmeses=req.getParameter("MM");
    if (losmeses.equals("MON-YYYY"))
        archivo=env.getProperty("dgii.jasper.reportes") + "0712A.jasper";
    else
        archivo=env.getProperty("dgii.jasper.reportes") + "0712.jasper";
    
    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
    Map parameters = new HashMap();
    parameters.put("Icono1", env.getProperty("dgii.jasper.reportes")+"esa.png");
    parameters.put("Icono2", env.getProperty("dgii.jasper.reportes")+"logoMh.png");
    parameters.put("FechaI", req.getParameter("FechaI"));
    parameters.put("FechaF", req.getParameter("FechaF"));
    parameters.put("Unidad", req.getParameter("Unidad"));
//    parameters.put("Tramite", new Integer(req.getParameter("Tramite")));
    parameters.put("Tramite", req.getParameter("Tramite"));
    parameters.put("Servicio",new Integer(req.getParameter("Servicio")));
    parameters.put("DiaSemana",new Integer(req.getParameter("DiaSemana")));
    parameters.put("AreaAtencion", req.getParameter("AreaAtencion"));
    parameters.put("nunidad", req.getParameter("nunidad"));
    parameters.put("Ver",req.getParameter("Ver"));
    parameters.put("Meses",req.getParameter("MM"));
    
    Conexion();
    
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters ,con);
    response.setContentType("application/pdf");
    response.setHeader("Content-disposition", "inline; filename=0712.pdf");
    final OutputStream outStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    outStream.flush();     
    outStream.close();
    con.close();
    }
  
  @Transactional
  @RequestMapping(value = "/print", method = RequestMethod.GET)
  @ResponseBody
  public void getRpt3(HttpServletResponse response, HttpServletRequest req) throws JRException, IOException, SQLException, ClassNotFoundException 
  {
        String archivo=env.getProperty("dgii.jasper.reportes") + "0712.jasper";
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
        Map parameters = new HashMap();
        parameters.put("Icono1", env.getProperty("dgii.jasper.reportes")+"esa.png");
        parameters.put("Icono2", env.getProperty("dgii.jasper.reportes")+"logoMh.png");
    parameters.put("FechaI", req.getParameter("FechaI"));
    parameters.put("FechaF", req.getParameter("FechaF"));
    parameters.put("Unidad", req.getParameter("Unidad"));
//    parameters.put("Tramite", new Integer(req.getParameter("Tramite")));
    parameters.put("Tramite", req.getParameter("Tramite"));
    parameters.put("Servicio",req.getParameter("Servicio"));
    parameters.put("DiaSemana",new Integer(req.getParameter("DiaSemana")));
    parameters.put("Ver",req.getParameter("Ver"));
    parameters.put("Meses",req.getParameter("MM"));

        Conexion();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters ,con);
        JasperPrintManager.printReport(jasperPrint, false);
        con.close();
    }
    @Transactional
    public Connection Conexion() throws SQLException, ClassNotFoundException
    {
        //Class.forName(env.getProperty("dgii.database.driver"));
        //con = DriverManager.getConnection(env.getProperty("dgii.database.url"),env.getProperty("dgii.database.user"),env.getProperty("dgii.database.password"));
        org.hibernate.Session session = em.unwrap(Session.class);
        session.doWork(new Work(){
		    @Override public void execute(Connection conn) throws SQLException {
                       con=conn;
                    }
               });
        return con;
    }
}
