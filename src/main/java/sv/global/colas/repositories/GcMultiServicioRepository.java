/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import sv.global.colas.entities.GcConfLlamado;
import sv.global.colas.entities.GcMultimedServicio;
import sv.global.colas.entities.GcMultimedia;
import sv.global.colas.pojos.entities.MultimediaServicioDto;

/**
 *
 * @author Owner
 */
public interface GcMultiServicioRepository extends CrudRepository<GcMultimedia, Long> {


    @Query(value = "SELECT a.N_MULTIMEDIA_ID , b.c_unidad_recep,a.d_multimedia , a.s_tipo,a.s_ruta,a.s_multimedia \n" +
"            FROM gc_multimedia a JOIN gc_multimed_servicio b ON a.n_multimedia_id = b.n_multimedia_id \n" +
"              WHERE a.b_activa = 1 and (b.c_unidad_recep=?1) \n" +
"UNION \n" +
"SELECT  a.N_MULTIMEDIA_ID ,  \"\"  as c_unidad_recep,a.d_multimedia , a.s_tipo,a.s_ruta,a.s_multimedia \n" +
"            FROM gc_multimedia a \n" +
"                 where not exists (select 1 from gc_multimed_servicio b \n" +
"                 WHERE a.n_multimedia_id = b.n_multimedia_id and b.c_unidad_recep=?1 ) \n" +
"                   and a.b_activa = 1", nativeQuery = true)
    public List<MultimediaServicioDto> getLista(String cunidadRecep);

    @Query(value = "SELECT  a.N_MULTIMEDIA_ID , ''  as c_unidad_recep,a.d_multimedia , a.s_tipo,a.s_ruta,a.s_multimedia \n" +
"            FROM gc_multimedia a \n" +
"                   where a.b_activa = 1", nativeQuery = true)
    public List<MultimediaServicioDto> getListaVacia();

    @Modifying
    @Transactional
    @Query(value = "delete from GC_MULTIMED_SERVICIO where C_UNIDAD_RECEP=?1", nativeQuery = true)
    public void borrar(String cUnidadRecep);

    @Modifying
    @Transactional
    @Query(value = "delete from GC_MULTIMED_SERVICIO where C_UNIDAD_RECEP=?1 and N_MULTIMEDIA_ID=?2", nativeQuery = true)
    public void deleteOne(String cUnidadRecep,String multimedia);
    
    @Modifying
    @Transactional
    @Query(value = "insert into GC_MULTIMED_SERVICIO(C_UNIDAD_RECEP,N_MULTIMEDIA_ID,N_ORDEN) values(?1,?2,?3)", nativeQuery = true)
    public void insertar(String cUnidadRecep, int nmulti, int norden);
}
