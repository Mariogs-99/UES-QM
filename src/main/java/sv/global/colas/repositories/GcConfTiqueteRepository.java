/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import sv.global.colas.entities.GcConfTiquete;
import sv.global.colas.entities.TbListasValor;
import sv.global.colas.entities.TbUnidadRecep;


/**
 *
 * @author Owner
 */
public interface GcConfTiqueteRepository extends CrudRepository<GcConfTiquete, Long> {
    
    @Query("SELECT c FROM GcConfTiquete c where N_VERSION = ?1 and C_UNIDAD_RECEP=?2 order by nVersion desc")
    public GcConfTiquete maxVer(String nv,String unidad);
    //@Query(value="SELECT ifnull(max(nVersion)) FROM GcConfTiquete c where C_UNIDAD_RECEP=?1",nativeQuery = true)
    @Query(value="SELECT isnull(max(n_Version),0) FROM gc_conf_tiquete c where C_UNIDAD_RECEP=?1",nativeQuery = true)
    public int maximaVersion(String unidad);
    @Query("SELECT c FROM GcConfTiquete c where N_CONF_TIQ_ID = ?1 order by nVersion desc")
    public GcConfTiquete currTq(String id);
    @Query("SELECT c FROM GcConfTiquete c where C_UNIDAD_RECEP = ?1 order by nVersion desc")
    public List<GcConfTiquete> listaConfiguraciones(String cUnidadRecep);
    @Query("Select a from TbListasValor a where a.id.clista=(select b.id.clista from TbListasValorDet b where b.id.clistaDet= ?1 and  b.id.cmodulo='GC')")
    public TbListasValor n_unidad_tlv(String cUnidadRecep);
    @Query("Select c from TbUnidadRecep c where cunidadRecep=?1")
    public TbUnidadRecep n_unidad_tur(String cUnidadRecep);
    @Query(value="select ltrim(replace(replace(regexp_replace2(regexp_replace2(X_CONTENIDO,'%%'),'&&'),'&& ',''),'%%        ','')) from Gc_Conf_Tiquete a where C_UNIDAD_RECEP=?1 and N_VERSION=?2", nativeQuery = true)
    public String lineas_tiquete(String cUnidadRecep,String maxver);
    @Query(value = "SELECT visualizar(?1) ", nativeQuery = true)
    public String visualizar(String i);    
    @Query(value = "SELECT c FROM GcConfTiquete c where N_CONF_TIQ_ID = ?1")
    public GcConfTiquete getimg(String i);
    @Modifying
    @Transactional
    @Query("update GcConfTiquete set B_ACTIVA=0 where C_UNIDAD_RECEP=?1")
    public void noActivo(String cUnidadRecep);
    @Modifying
    @Transactional
    @Query("update GcConfTiquete set B_ACTIVA=1 where C_UNIDAD_RECEP=?1 and N_VERSION=?2" )
    public void setActivo(String cUnidadRecep, String ver);
    @Query(value="select plantillas(?1) ",nativeQuery = true)
    public String lineas_plantilla(String npla);
    @Query(value="select CONCAT(n_plantilla_tiq,';',s_nombre) from gc_plantilla_tiq",nativeQuery = true)
    //@Query(value="select n_plantilla_tiq||';'||s_nombre from gc_plantilla_tiq",nativeQuery = true)
    public List<String> lista_plantilla();
    @Query(value="Select count(N_CONF_TIQ_ID)+1 from gc_conf_tiquete",nativeQuery = true)
    public Long GetTotalID();
    //select trim(replace(replace(regexp_replace(regexp_replace(X_CONTENIDO, '</[^>]*>','%%'),'<[^>]*>','&&'),'&& ',''),'%%        ','')) from GC_CONF_TIQUETE
}
