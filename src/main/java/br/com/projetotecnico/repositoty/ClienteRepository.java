package br.com.projetotecnico.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{
	
}