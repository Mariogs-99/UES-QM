/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.controllers.reportes;

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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
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
 * @author CésarStanley
 */
@Controller
@RequestMapping("/impresion0705")
@EnableAsync
public class Impresion0705 {
    @PersistenceContext
     private EntityManager em;
    public Connection con = null;
  
  @Autowired
  Environment env;
  @Transactional
  @RequestMapping(value = "/pdf", method = RequestMethod.GET)
  @ResponseBody
  public void getRpt1(HttpServletResponse response, HttpServletRequest req) throws JRException, IOException, SQLException, ClassNotFoundException {
    String archivo=env.getProperty("dgii.jasper.reportes") + "0705.jasper";
    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
    Map parameters = new HashMap();
    parameters.put("Icono1", env.getProperty("dgii.jasper.reportes")+"esa.png");
    parameters.put("Icono2", env.getProperty("dgii.jasper.reportes")+"logoMh.png");
    parameters.put("FechaI", req.getParameter("FechaI"));
    parameters.put("FechaF", req.getParameter("FechaF"));
    parameters.put("Unidad", req.getParameter("Unidad"));
    parameters.put("PAsistio",req.getParameter("Asistio"));
    parameters.put("PServicio",new Integer(req.getParameter("Servicio")));
    parameters.put("PDiaSemana",new Integer(req.getParameter("DiaSemana")));
    parameters.put("terceros",new Integer(req.getParameter("terceros")));
    parameters.put("AreaAtencion", req.getParameter("AreaAtencion"));
    parameters.put("nunidad", req.getParameter("nunidad"));
    
    Conexion();
    
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters ,con);
    response.setContentType("application/x-pdf");
    response.setHeader("Content-disposition", "inline; filename=0705.pdf");
    final OutputStream outStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    outStream.flush();     
    outStream.close();
    con.close();
    }    
    
  
  @RequestMapping(value = "/html", method = RequestMethod.GET)
  @Transactional
  @ResponseBody
  public void getRpt2(HttpServletResponse response, HttpServletRequest req) throws JRException, IOException, SQLException, ClassNotFoundException {
    String archivo=env.getProperty("dgii.jasper.reportes") + "0705.jasper";
    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
    Map parameters = new HashMap();
    parameters.put("Icono1", env.getProperty("dgii.jasper.reportes")+"esa.png");
    parameters.put("Icono2", env.getProperty("dgii.jasper.reportes")+"logoMh.png");
    parameters.put("FechaI", req.getParameter("FechaI"));
    parameters.put("FechaF", req.getParameter("FechaF"));
    parameters.put("Unidad", req.getParameter("Unidad"));
    parameters.put("PAsistio",req.getParameter("Asistio"));
    parameters.put("PServicio",new Integer(req.getParameter("Servicio")));
    parameters.put("PDiaSemana",new Integer(req.getParameter("DiaSemana")));
    parameters.put("terceros",new Integer(req.getParameter("terceros")));
    parameters.put("AreaAtencion", req.getParameter("AreaAtencion"));
    parameters.put("nunidad", req.getParameter("nunidad"));
    
    Conexion();
    
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters ,con);
    response.setContentType("application/pdf");
    response.setHeader("Content-disposition", "inline; filename=0705.pdf");
    final OutputStream outStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    outStream.flush();     
    outStream.close();
    con.close();
    }
  
  
  @RequestMapping(value = "/print", method = RequestMethod.GET)
  @Transactional
  @ResponseBody
  public void getRpt3(HttpServletResponse response, HttpServletRequest req) throws JRException, IOException, SQLException, ClassNotFoundException 
  {
        String archivo=env.getProperty("dgii.jasper.reportes") + "0705.jasper";
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
        Map parameters = new HashMap();
        parameters.put("Icono1", env.getProperty("dgii.jasper.reportes")+"esa.png");
        parameters.put("Icono2", env.getProperty("dgii.jasper.reportes")+"logoMh.png");
    parameters.put("FechaI", req.getParameter("FechaI"));
    parameters.put("FechaF", req.getParameter("FechaF"));
    parameters.put("Unidad", req.getParameter("Unidad"));
    parameters.put("PAsistio",req.getParameter("Asistio"));
    parameters.put("PServicio",new Integer(req.getParameter("Servicio")));
    parameters.put("PDiaSemana",new Integer(req.getParameter("DiaSemana")));

        Conexion();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters ,con);
        JasperPrintManager.printReport(jasperPrint, false);
        con.close();
    }
    @Transactional
    public Connection Conexion() throws SQLException, ClassNotFoundException
    {
//        Class.forName(env.getProperty("dgii.database.driver"));
//        con = DriverManager.getConnection(env.getProperty("dgii.database.url"),env.getProperty("dgii.database.user"),env.getProperty("dgii.database.password"));
        org.hibernate.Session session = em.unwrap(Session.class);
        session.doWork(new Work(){
		    @Override public void execute(Connection conn) throws SQLException {
                       con=conn;
                    }
               });
        return con;
    }
}
