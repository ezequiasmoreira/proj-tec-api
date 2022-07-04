package br.com.projetotecnico.repositoty;

import br.com.projetotecnico.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer>{
}