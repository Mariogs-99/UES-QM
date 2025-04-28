/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import sv.global.colas.entities.GcMultimedia;
import sv.global.colas.pojos.entities.MultimediaPojo;

/**
 *
 * @author Owner
 */
public interface GcMultimediaRepository extends CrudRepository<GcMultimedia, Long> {

    /* @Query(value="SELECT n_Multimedia_Id||';'||s_Tipo||';'||" +
"        n_Duracion||';'||TO_CHAR(fi_Multimedia,'DD/MM/YYYY')||';'||TO_CHAR(ff_Multimedia,'DD/MM/YYYY')||';'||" +
"        d_Multimedia||';'||c_Usuario_Crea||';'||c_Usuario_Modi||';'||" +
"        to_char(fi_Vigencia,'DD/MM/YYYY')||';'||to_char(f_Modifica,'DD/MM/YYYY')||';'||" +
"        b_Activa||';'||s_Ruta FROM gc_multimedia  order by n_Multimedia_Id ", nativeQuery = true)*/
    
    @Query(value = "SELECT CONCAT(n_Multimedia_Id, ';', s_Tipo, ';', n_Duracion, ';', \n"
            + "              date_format(fi_Multimedia, '%d/%c/%Y'), ';', \n"
            + "              date_format(ff_Multimedia, '%d/%c/%Y'), ';', \n"
            + "              d_Multimedia, ';', c_Usuario_Crea, ';', c_Usuario_Modi, ';', \n"
            + "              date_format(fi_vigencia, '%d/%c/%Y'), ';', \n"
            + "              date_format(f_modifica, '%d/%c/%Y'), ';', \n"
            + "              b_Activa, ';', s_Ruta) \n"
            + "FROM gc_multimedia \n"
            + "ORDER BY n_Multimedia_Id;", nativeQuery = true)
    public List<String> findA();

    /* @Query(value="SELECT n_Multimedia_Id||';'||s_Tipo||';'||" +
"        n_Duracion||';'||TO_CHAR(fi_Multimedia,'DD/MM/YYYY')||';'||TO_CHAR(ff_Multimedia,'DD/MM/YYYY')||';'||" +
"        d_Multimedia||';'||c_Usuario_Crea||';'||c_Usuario_Modi||';'||" +
"        to_char(fi_Vigencia,'DD/MM/YYYY')||';'||to_char(f_Modifica,'DD/MM/YYYY')||';'||" +
"        b_Activa||';'||s_Ruta||';'||s_multimedia||';'||TO_CHAR(fi_vigencia,'DD/MM/YYYY') FROM gc_multimedia  where n_Multimedia_Id=?1 ", nativeQuery = true)*/
    @Query(value = "SELECT CONCAT(n_Multimedia_Id, ';', s_Tipo, ';', n_Duracion, ';', "
            + "              date_format(fi_Multimedia, '%d/%c/%Y'), ';', "
            + "              date_format(ff_Multimedia, '%d/%c/%Y'), ';', "
            + "              d_Multimedia, ';', c_Usuario_Crea, ';', c_Usuario_Modi, ';', "
            + "              date_format(fi_vigencia, '%d/%c/%Y'), ';', "
            + "              date_format(f_modifica, '%d/%c/%Y'), ';', "
            + "              b_Activa, ';', s_Ruta, ';', s_multimedia, ';', "
            + "              date_format(fi_vigencia, '%d/%c/%Y')) "
            + "FROM gc_multimedia "
            + "WHERE n_Multimedia_Id = ?1", nativeQuery = true)
    public String findO(Long i);

    @Query(value = "select x_archivo FROM gc_multimedia  where n_Multimedia_Id=?1", nativeQuery = true)
    public Blob blobo(Long i);

    @Query("SELECT c FROM GcMultimedia c where nMultimediaId=?1")
    public GcMultimedia unreg(Long i);

    @Modifying
    @Transactional
    @Query(value = "delete from gc_multimedia where n_multimedia_id=?1", nativeQuery = true)
    public void borrarC(long i);

    @Modifying
    @Transactional
    @Query(value = "UPDATE GC_MULTIMEDIA "
            + "    SET N_DURACION        = ?2 ,"
            + "      C_USUARIO_MODI      = ?3 ,"
            + "      D_MULTIMEDIA        = ?4 ,"
            + "      F_MODIFICA          = ?5 ,"
            + "      B_ACTIVA            = ?6 ,"
            + "      S_TIPO              = ?7 , "
            + "      FF_MULTIMEDIA       = ?8 , "
            + "      FI_MULTIMEDIA       = ?9 , "
            + "      S_RUTA              = ?10 "
            + "    WHERE N_MULTIMEDIA_ID = ?1 ", nativeQuery = true)
    public void actualizar(long i, long ndur, String cumod, String dmult, Date fmodi, int bacti, String stipo, Date ffmul, Date fimul, String sruta);

}
