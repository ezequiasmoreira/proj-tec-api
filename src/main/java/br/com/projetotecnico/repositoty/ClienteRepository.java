package br.com.projetotecnico.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetotecnico.models.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{
	@Transactional(readOnly=true)
	Cliente findByCpfCnpj(String cpfCnpj);
	
	@Transactional(readOnly=true)	
	@Query("SELECT obj FROM Cliente obj WHERE obj.cpfCnpj = :cpfCnpj AND obj.id <> :id")
	List<Cliente> obterPor(@Param("cpfCnpj") String cpfCnpj,Integer id);
}