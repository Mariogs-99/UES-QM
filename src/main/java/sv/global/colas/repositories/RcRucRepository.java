package sv.global.colas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sv.global.colas.entities.RcRuc;


public interface RcRucRepository extends CrudRepository<RcRuc, String> {

	@Query("SELECT ruc FROM RcRuc ruc LEFT JOIN FETCH ruc.tbTipoContrib WHERE ruc.nit = ?1 ")
	public RcRuc findByNit(String nit);
	
	//@Query("SELECT ruc.tbTipoContrib.CUsuario FROM RcRuc ruc LEFT JOIN FETCH ruc.tbTipoContrib WHERE ruc.nit = ?1 ")
	//public String getTipoPersona(String nit);
	
	@Query("SELECT case when (count(det) > 0) then true else false end FROM TbListasValorDet det WHERE det.id.clista = 'P_NITS_BIENESTAR' AND det.cvalor = ?1 ")
	public boolean isBienestar(String nit);
	
	@Query("SELECT case when (count(det) > 0) then true else false end FROM TbListasValorDet det WHERE det.id.clista = 'P_NITS_MIN_DEFENSA' AND det.cvalor = ?1 ")
	public boolean isIpsfa(String nit);
	
	@Query("SELECT case when (ruc.FTermino is not null) then true else false end FROM RcRuc ruc WHERE ruc.nit = ?1 ")
	public boolean isTermino(String nit);
}
