package br.com.projetotecnico.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetotecnico.models.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
	
	@Transactional(readOnly=true)	
	Optional<Usuario> findByEmail(String email);
	
	@Transactional(readOnly=true)	
	@Query("SELECT obj FROM Usuario obj WHERE obj.email = :email ")
	Usuario obterPor(@Param("email") String email);
}