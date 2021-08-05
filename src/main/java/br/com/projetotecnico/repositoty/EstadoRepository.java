package br.com.projetotecnico.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Estado;

@Repository
public interface EstadoRepository  extends JpaRepository<Estado, Integer>{
	
}