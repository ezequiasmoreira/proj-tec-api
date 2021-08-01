package br.com.projetotecnico.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Telefone;

@Repository
public interface TelefoneRepository  extends JpaRepository<Telefone, Integer>{
	
}