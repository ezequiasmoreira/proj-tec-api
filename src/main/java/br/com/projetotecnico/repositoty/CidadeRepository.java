package br.com.projetotecnico.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Cidade;

@Repository
public interface CidadeRepository  extends JpaRepository<Cidade, Integer>{
	
}