package br.com.projetotecnico.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Endereco;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Integer>{
	
}