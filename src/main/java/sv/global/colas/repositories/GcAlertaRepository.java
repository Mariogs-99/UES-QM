package sv.global.colas.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sv.global.colas.entities.GcAlerta;

public interface GcAlertaRepository extends CrudRepository<GcAlerta, Long> {

    @Query("SELECT a FROM GcAlerta a WHERE a.bActiva = 1 AND a.bPantalla = 1 AND a.cUsuarioNotifica in( ?1,'All') AND ?2 BETWEEN a.fiVigencia AND a.ffVigencia")
    public List<GcAlerta> getAlertasActivas(String tipo, Date fecha);
    
    @Query("SELECT alerta FROM GcAlerta alerta WHERE alerta.bActiva = 1 ORDER BY alerta.nAlertaId ASC ")
    @Override
    public Iterable<GcAlerta> findAll();
    
    @Query("SELECT alerta FROM GcAlerta alerta WHERE alerta.bActiva = 1 AND ?1 BETWEEN alerta.fiVigencia AND alerta.ffVigencia ORDER BY alerta.nAlertaId ASC")
    public Iterable<GcAlerta> findAllActivas(Date fecha);
    
    @Procedure(procedureName = "GC_SEND_MAIL")
    public void enviarCorreo(String dato1,String dato2,String dato3,String dato4,String dato5);
    
    
    
}
