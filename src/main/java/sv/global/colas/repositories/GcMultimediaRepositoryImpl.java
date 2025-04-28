package sv.global.colas.repositories;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public class GcMultimediaRepositoryImpl implements GcMultimediaInfoRepository {
    // @PersistenceContext
    // private EntityManager em;
    @Autowired
    private GcUsuarioRepository usuarioRepository;
    @Autowired
    private Environment env;

    @Override
    @Transactional
    public String getArchivo(String id,EntityManager em) {
        final String[] tfile=new String[1];
        String nfile="";
        final Blob[] blobo= new Blob[1];
        final String vid=id;        
        org.hibernate.Session session = em.unwrap(Session.class);
        File f;
        FileOutputStream fw = null;
          try{               
               session.doWork(new Work(){
		    @Override 
                    public void execute(Connection conn) throws SQLException {
                        Statement stmt = conn.createStatement();
                        ResultSet rset = stmt.executeQuery("select x_archivo,s_multimedia FROM gc_multimedia  where n_Multimedia_Id="+Long.parseLong(vid));
                        if(rset.next()){
                            blobo[0]=rset.getBlob(1);   // Print col 1  
                            tfile[0]=rset.getString(2);
                        }
                        stmt.close();
                        stmt=null;
                    }

                  
               });
                nfile=env.getProperty("dgii.dir.tmp")+"/"+tfile[0];
                System.out.println(nfile);
               f=new File(nfile);
               fw = new FileOutputStream(f);
               IOUtils.copy(blobo[0].getBinaryStream(), fw);
               fw.flush();
               fw.close();
//               
            } catch(Exception e){
                e.printStackTrace();                
            }
        
        
        return nfile;
    }
    
     
     
    
}
