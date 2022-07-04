package br.com.projetotecnico.repositoty;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Cliente;

@Repository
public interface ClienteRepositoryLog  extends CrudRepository<Cliente, Integer>{
}