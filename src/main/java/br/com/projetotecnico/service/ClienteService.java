package br.com.projetotecnico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetotecnico.dto.ClienteDTO;
import br.com.projetotecnico.exception.ObjectNotFoundException;
import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.repositoty.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TelefoneService telefoneService;

	public Cliente converterParaDTO(ClienteDTO clienteDTO) {		
		return new Cliente(clienteDTO);
	}

	public Cliente salvar(Cliente cliente, ClienteDTO clienteDTO) {		
		cliente.setId(null); 
		List<Telefone> telefones = telefoneService.salvarTelefones(clienteDTO.getTelefones());
		cliente.setTelefones(telefones);
		return clienteRepository.save(cliente); 
	}

	public Cliente atualizar(Cliente cliente, ClienteDTO clienteDTO) throws ObjectNotFoundException {		
		Optional<Cliente> clienteAtualizar = clienteRepository.findById(cliente.getId());
		Cliente clienteAtualizado = clienteAtualizar.map(c -> clienteRepository.save(new Cliente(c.getId(), cliente.getNome(), cliente.getSobrenome(), cliente.getCpfCnpj())))
	                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!")); 
		
		List<Telefone> telefones = telefoneService.atualizarTelefones(clienteDTO.getTelefones());
		clienteAtualizado.setTelefones(telefones);
		return clienteRepository.save(clienteAtualizado);
	}


	public void excluir(Cliente cliente){		
		clienteRepository.deleteById(cliente.getId());
	}

	public Cliente obterPorId(Integer clienteId) throws ObjectNotFoundException {		
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
	}
	
	public List<Cliente> obterTodos() throws ObjectNotFoundException {		
		return clienteRepository.findAll();
	}
}