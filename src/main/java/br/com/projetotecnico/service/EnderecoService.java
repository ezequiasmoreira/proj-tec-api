package br.com.projetotecnico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetotecnico.exception.ObjectNotFoundException;
import br.com.projetotecnico.models.Cidade;
import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Endereco;
import br.com.projetotecnico.models.Estado;
import br.com.projetotecnico.repositoty.CidadeRepository;
import br.com.projetotecnico.repositoty.ClienteRepository;
import br.com.projetotecnico.repositoty.EnderecoRepository;
import br.com.projetotecnico.repositoty.EstadoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteService clienteService;

	public Endereco salvar(Endereco endereco) {		
		endereco.setId(null);
		return enderecoRepository.save(endereco);
	}

	public Endereco atualizar(Endereco endereco) {		
		Optional<Endereco> enderecoAtualizar = enderecoRepository.findById(endereco.getId());
        return enderecoAtualizar.map(e -> enderecoRepository.save(new Endereco(e.getId(), endereco.getDescricao(), endereco.getLogradouro(),
        		endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCep(), endereco.getPrincipal(), endereco.getCidade())))
                .orElseThrow(() -> new ObjectNotFoundException("Endereco não encontrado!"));
	}
	
	public List<Endereco> salvarEnderecos(List<Endereco> enderecos,Cliente cliente) {
		boolean possuiEnderecoPrincipal = false;
		List<Endereco> listaDeEnderecos = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			listaDeEnderecos.add(enderecoRepository.save(endereco));
			if (endereco.getPrincipal()==true) {
				possuiEnderecoPrincipal = true;
				cliente.setEndereco(endereco);
			}
		}
		if(!possuiEnderecoPrincipal) {
			new ObjectNotFoundException("Endereço principal não definido.");
		}
		clienteService.atualizar(cliente);
		return listaDeEnderecos;
	}
	
	public List<Endereco> atualizarEnderecos(List<Endereco> enderecos,Cliente cliente) {
		boolean possuiEnderecoPrincipal = false;
		List<Endereco> listaDeEnderecos = new ArrayList<>();		
		for (Endereco endereco : enderecos) {
			if (endereco.getId() == null) {
				listaDeEnderecos.add(salvar(endereco));
			}else {
				listaDeEnderecos.add(atualizar(endereco));
			}
			if (endereco.getPrincipal()==true) {
				possuiEnderecoPrincipal = true;
				cliente.setEndereco(endereco);
			}
		}
		if(!possuiEnderecoPrincipal) {
			new ObjectNotFoundException("Endereço principal não definido.");
		}
		clienteService.atualizar(cliente);
		return listaDeEnderecos;
	}
	
	public List<Estado> obterTodosEstados() {		
		return estadoRepository.findAll();
	}
	
	public List<Cidade> obterCidadesPorEstado(Integer estadoId) {		
		return cidadeRepository.obterCidadesPorEstado(estadoId);
	}
	
	public Cidade obterCidadePorId(Integer cidadeId) {		
		return cidadeRepository.findById(cidadeId).orElseThrow(()-> new ObjectNotFoundException("Cidade não encontrada com ID: " + cidadeId));
	}

}