/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.TbUnidadRecep;
import sv.global.colas.utils.Parametros;

/**
 *
 * @author Owner
 */
public interface GcUnidadRecepRepository extends CrudRepository<TbUnidadRecep, String> {

    @Query("SELECT t FROM TbUnidadRecep t WHERE t.mservicio IN('M','C') ")
    public List<TbUnidadRecep> getUnidadesEnServicio();

    @Query("SELECT t FROM TbUnidadRecep t WHERE t.mservicio IN('M','C') AND c_unidad_Recep IN(SELECT MIN(d.id.clistaDet) FROM TbListasValorDet d where c_modulo='GC' group by c_lista)")
    public List<TbUnidadRecep> getUnidadesCombinaciones();

    @Query("SELECT t FROM TbUnidadRecep t where t.mservicio IN('C','M') and C_UNIDAD_RECEP = ?1")
    public List<TbUnidadRecep> getUnidadesEnServicio(String cunidadRecep);

    @Query("SELECT 1 FROM TbUnidadRecep t where t.mservicio IN('C','M') and C_UNIDAD_RECEP =  CENTRO_SERVICIO(?1) ")
    public Long getUnidadDuplicado(String cunidadRecep);

    @Query("SELECT t.dunidadRecep FROM TbUnidadRecep t where t.mservicio IN('C','M') and t.cunidadRecep.cunidadRecep = ?1 ")
    public String getUnidadEnServicio(String cunidadRecep);

    @Query("SELECT t.cunidadRecep FROM TbUnidadRecep t where t.cunidadRecep IN(select u.cUnidadRecep.cunidadRecep from GcSeguridadUsuario u where u.usuario=?1 )  ")
    public String getUnidadByUsuario(String usuario);

    @Query("SELECT t FROM TbUnidadRecep t WHERE t.mservicio IN('C','M') and t.cunidadRecep = ?1 ")
    public TbUnidadRecep getUnidadEnServicioE(String cunidadRecep);

    @Query(value = Parametros.SQL_ZONA_USUARIOS, nativeQuery = true)
    public List getUserMonitoreo(String unidadRecep);

    @Query(value = "SELECT UNIDAD_SATURACION(?1)", nativeQuery = true)
    public Long getCsSaturacion(String unidadRecep);

    @Query(value = "SELECT CENTRO_SERVICIO(?1) ", nativeQuery = true)
    public String getCsCombinacion(String unidadRecep);

    @Query(value = "select D_LISTA from TB_LISTAS_VALOR where c_lista =(select c_lista from TB_LISTAS_VALOR_DET where c_modulo='GC' and c_lista_det=?1 )", nativeQuery = true)
    public String getNombreUnidadCombinacion(String unidadRecep);

    @Query(value = "SELECT PROMEDIO_ESPERA_UNIDAD(?1)", nativeQuery = true)
    public Integer getPromedioEsperaUnidad(String unidadRecep);

}
