package br.com.projetotecnico.repositoty;
import java.util.Optional;

import br.com.projetotecnico.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.projetotecnico.models.Cliente;

@Repository("clienteRepositoryCustom")
public class ClienteRepositoryCustom implements ClienteRepositoryLog {

    @Autowired
    @Qualifier("clienteRepositoryLog")
    private ClienteRepositoryLog clienteRepositoryLog;

	@Autowired
	private LogService logService;

    public Cliente save(Cliente cliente) {
		logService.setIdentificador(cliente.getId());
    	Cliente entity = clienteRepositoryLog.save(cliente);
		logService.salvar(entity);
        return entity;
    }

	@Override
	public <S extends Cliente> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cliente> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Cliente> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		clienteRepositoryLog.deleteById(id);
		logService.logDeleteById(id, new Cliente());
	}

	@Override
	public void delete(Cliente entity) {
		clienteRepositoryLog.delete(entity);
		logService.logDelete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Cliente> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}