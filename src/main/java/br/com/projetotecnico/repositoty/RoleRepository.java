package br.com.projetotecnico.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	 Optional<Role> findByName(String name);
}

