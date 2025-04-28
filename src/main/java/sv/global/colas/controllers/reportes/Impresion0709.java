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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
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
@RequestMapping("/impresion0709")
@EnableAsync
public class Impresion0709 {

    @PersistenceContext
    private EntityManager em;
    public Connection con = null;
    public String jasperReportDir;

    @Autowired
    Environment env;
    @Autowired
    ServletContext srvContext;

    @Transactional
    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse response, HttpServletRequest req) throws JRException, IOException, SQLException, ClassNotFoundException {
        String path = env.getProperty("dgii.jasper.reportes");

        String archivo = path + "0709.jasper";

        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
        Map parameters = new HashMap();
        parameters.put("Icono1", path + "colas.PNG");
        parameters.put("Icono2", path + "qtiquete.png");
        parameters.put("FechaI", req.getParameter("FechaI"));
        parameters.put("FechaF", req.getParameter("FechaF"));
        parameters.put("Unidad", req.getParameter("Unidad"));
        parameters.put("Servicio", new Integer(req.getParameter("Servicio")));
        parameters.put("DiaSemana", new Integer(req.getParameter("DiaSemana")));
        parameters.put("AreaAtencion", req.getParameter("AreaAtencion"));
        parameters.put("nunidad", req.getParameter("nunidad"));
        parameters.put("Ver", req.getParameter("Ver"));
        parameters.put("Meses", req.getParameter("MM"));

        Conexion();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=0709.pdf");
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
        String archivo = env.getProperty("dgii.jasper.reportes") + "0709.jasper";
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
        Map parameters = new HashMap();
        parameters.put("Icono1", env.getProperty("dgii.jasper.reportes") + "colas.PNG");
        parameters.put("Icono2", env.getProperty("dgii.jasper.reportes") + "qtiquete.png");
        parameters.put("FechaI", req.getParameter("FechaI"));
        parameters.put("FechaF", req.getParameter("FechaF"));
        parameters.put("Unidad", req.getParameter("Unidad"));
        parameters.put("Servicio", new Integer(req.getParameter("Servicio")));
        parameters.put("DiaSemana", new Integer(req.getParameter("DiaSemana")));
        parameters.put("AreaAtencion", req.getParameter("AreaAtencion"));
        parameters.put("nunidad", req.getParameter("nunidad"));
        parameters.put("Ver", req.getParameter("Ver"));
        parameters.put("Meses", req.getParameter("MM"));

        Conexion();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=0709.pdf");
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        outStream.flush();
        outStream.close();
        con.close();
    }

    @Transactional
    @RequestMapping(value = "/generarReporte", method = RequestMethod.GET)
    public void generarReporte(HttpServletResponse response, HttpServletRequest req) throws JRException, SQLException, ClassNotFoundException, IOException {

        // Ruta del archivo .jrxml
        String path = srvContext.getRealPath("/WEB-INF/classes/reports/");
        String archivoJrxml = path + "ReportexOficina.jrxml";
        // Compilar el archivo .jrxml a .jasper
        JasperReport jasperReport = JasperCompileManager.compileReport(archivoJrxml);

        // Obtener parámetros del HttpServletRequest
        String unidad = req.getParameter("unidad");
        String fechaInicio = req.getParameter("fechaInicio");
        String fechaFin = req.getParameter("fechaFin");

        // Ruta de la imagen
        String imagePath = srvContext.getRealPath("/WEB-INF/classes/static/images/ues-logo.png");

        // Mapa de parámetros
        Map parameters = new HashMap();
        parameters.put("oficina", unidad);
        parameters.put("fechaInicio", fechaInicio);
        parameters.put("fechaFin", fechaFin);
        parameters.put("image", imagePath);

        // Obtener conexión
        Conexion();

        // Llenar el reporte con la conexión y parámetros
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);

        // Configurar la respuesta HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=reporte.pdf");
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        // Cerrar streams
        outStream.flush();
        outStream.close();
        con.close();
    }

    @Transactional
    @RequestMapping(value = "/print", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt3(HttpServletResponse response, HttpServletRequest req) throws JRException, IOException, SQLException, ClassNotFoundException {
        String archivo = env.getProperty("dgii.jasper.reportes") + "0709.jasper";
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(archivo);
        Map parameters = new HashMap();
        parameters.put("Icono1", env.getProperty("dgii.jasper.reportes") + "colas.PNG");
        parameters.put("Icono2", env.getProperty("dgii.jasper.reportes") + "qtiquete.png");
        parameters.put("FechaI", req.getParameter("FechaI"));
        parameters.put("FechaF", req.getParameter("FechaF"));
        parameters.put("Unidad", req.getParameter("Unidad"));
        parameters.put("Servicio", new Integer(req.getParameter("Servicio")));
        parameters.put("DiaSemana", new Integer(req.getParameter("DiaSemana")));
        parameters.put("Ver", req.getParameter("Ver"));
        parameters.put("Meses", req.getParameter("MM"));

        Conexion();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
        JasperPrintManager.printReport(jasperPrint, false);
        con.close();
    }

    @Transactional
    public Connection Conexion() throws SQLException, ClassNotFoundException {
        //Class.forName(env.getProperty("dgii.database.driver"));
        //con = DriverManager.getConnection(env.getProperty("dgii.database.url"),env.getProperty("dgii.database.user"),env.getProperty("dgii.database.password"));
        org.hibernate.Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection conn) throws SQLException {
                con = conn;
            }
        });
        return con;
    }
}
