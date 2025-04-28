package sv.global.colas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sv.global.colas.entities.TbListasValor;
import sv.global.colas.entities.TbListasValorId;

public interface TbListasValorRepository extends CrudRepository<TbListasValor, TbListasValorId> {
	
	/*@Query("SELECT tb FROM TbListasValorDet det "
			+ "INNER JOIN det.tbListasValor tb "
			+ "WHERE det.clistaDet = ?1 AND det.cmodulo = 'GC' )  ")*/
	@Query("SELECT tb FROM TbListasValor tb WHERE ROWNUM < 10 ")
	public List<TbListasValor> getListCentroServicios();
	
	//TbListasValorDet
	
	@Query("SELECT det FROM TbListasValorDet det WHERE "
			+ "det.id.clista = (SELECT lt.id.clista FROM TbListasValor lt WHERE lt.id.clista = ( "
			+ "SELECT vdet.cvalor FROM TbListasValorDet vdet WHERE vdet.id.clistaDet = ?1 AND vdet.id.cmodulo = 'GC')) ")
	public List<TbListasValor> getUnidadesReceptoras(String unidadRecep);
	

}
