package br.com.projetotecnico.repositoty;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Cidade;

@Repository
public interface CidadeRepository  extends JpaRepository<Cidade, Integer>{
	
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
	public List<Cidade> obterCidadesPorEstado(@Param("estadoId") Integer estado_id);
	
	Optional<Cidade> findById(Integer id);
}