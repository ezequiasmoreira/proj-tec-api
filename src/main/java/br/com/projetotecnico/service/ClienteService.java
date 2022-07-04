package br.com.projetotecnico.service;

import java.util.List;
import java.util.Optional;

import br.com.projetotecnico.repositoty.ClienteRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetotecnico.dto.ClienteDTO;
import br.com.projetotecnico.exception.ObjectNotFoundException;
import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Endereco;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.repositoty.ClienteRepository;
import br.com.projetotecnico.repositoty.ClienteRepositoryLog;
import br.com.projetotecnico.spec.ClienteSpec;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TelefoneService telefoneService;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private ClienteSpec clienteSpec;
	@Autowired
	private ClienteRepositoryCustom clienteRepositoryLog;

	public Cliente converterParaDTO(ClienteDTO clienteDTO) {		
		return new Cliente(clienteDTO);
	}

	public Cliente salvar(Cliente cliente, ClienteDTO clienteDTO) {		
		clienteSpec.validarDisponibilidadeCfpCnpj(clienteDTO);
		cliente.setId(null); 
		List<Telefone> telefones = telefoneService.salvarTelefones(clienteDTO.getTelefones());		
		cliente.setTelefones(telefones);
		cliente = clienteRepositoryLog.save(cliente);
		List<Endereco> enderecos = enderecoService.salvarEnderecos(clienteDTO.getEnderecos(),cliente);
		cliente.setEnderecos(enderecos);
		return clienteRepositoryLog.save(cliente); 
	}
	
	public Cliente atualizar(Cliente cliente) {	
		return clienteRepository.save(cliente); 
	}

	public Cliente atualizar(Cliente cliente, ClienteDTO clienteDTO) throws ObjectNotFoundException {	
		clienteSpec.validarDisponibilidadeCfpCnpj(clienteDTO,cliente);
		Optional<Cliente> clienteAtualizar = clienteRepository.findById(cliente.getId());
		Cliente clienteAtualizado = clienteAtualizar.map(c -> clienteRepository.save(new Cliente(c.getId(), cliente.getNome(), cliente.getSobrenome(), cliente.getCpfCnpj())))
	                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!")); 
		
		List<Telefone> telefones = telefoneService.atualizarTelefones(clienteDTO.getTelefones());
		List<Endereco> enderecos = enderecoService.atualizarEnderecos(clienteDTO.getEnderecos(),clienteAtualizado);
		clienteAtualizado.setTelefones(telefones);
		clienteAtualizado.setEnderecos(enderecos);
		return clienteRepositoryLog.save(clienteAtualizado);
	}


	public void excluir(Cliente cliente){		
		clienteRepositoryLog.delete(cliente);
	}

	public Cliente obterPorId(Integer clienteId) throws ObjectNotFoundException {		
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
	}
	
	public List<Cliente> obterTodos() throws ObjectNotFoundException {		
		return clienteRepository.findAll();
	}
}