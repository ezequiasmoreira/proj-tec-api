package br.com.projetotecnico.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotecnico.dto.ClienteDTO;
import br.com.projetotecnico.exception.ObjectNotFoundException;
import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.enums.TipoTelefone;
import br.com.projetotecnico.repositoty.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente converterParaDTO(ClienteDTO clienteDTO) {		
		return new Cliente(clienteDTO);
	}

	public Cliente salvar(Cliente cliente) {		
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(Cliente cliente) {		
		Optional<Cliente> clienteAtualizar = clienteRepository.findById(cliente.getId());
		 return clienteAtualizar.map(c -> clienteRepository.save(
				 new Cliente(c.getId(), cliente.getNome(), cliente.getSobrenome(), cliente.getCpfCnpj(),cliente.getTelefonePrincipal())))
	                .orElseThrow(() -> new ObjectNotFoundException("Telefone não encontrado!")); 
	}


	public void excluir(Cliente cliente){		
		clienteRepository.deleteById(cliente.getId());
	}

	public Cliente obterPorId(Integer clienteId) throws ObjectNotFoundException {		
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
	}

}