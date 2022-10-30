package br.com.projetotecnico.repositoty;

import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer>{

    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Log obj WHERE obj.entity = :classe AND obj.acaoEntity IN :acao")
    List<Log> getFilter(@Param("classe") String classe, @Param("acao") List<Integer> acao);

    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Log obj WHERE obj.entity = :classe AND obj.identificador = :identificador AND obj.acaoEntity IN :acao")
    List<Log> getFilter(@Param("classe") String classe, @Param("identificador") String identificador,  @Param("acao") List<Integer> acao);

    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Log obj WHERE obj.entity = :classe AND dataCadastro >= :dataInicial AND dataCadastro <= :dataFinal AND obj.acaoEntity IN :acao")
    List<Log> getFilter(@Param("classe") String classe, @Param("dataInicial") LocalDateTime dataInicial, @Param("dataFinal") LocalDateTime dataFinal, @Param("acao") List<Integer> acao);

    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Log obj WHERE obj.entity = :classe AND obj.identificador = :identificador AND dataCadastro >= :dataInicial AND dataCadastro <= :dataFinal AND obj.acaoEntity IN :acao")
    List<Log> getFilter(@Param("classe") String classe, @Param("identificador") String identificador, @Param("dataInicial") LocalDateTime dataInicial, @Param("dataFinal") LocalDateTime dataFinal, @Param("acao") List<Integer> acao);

}