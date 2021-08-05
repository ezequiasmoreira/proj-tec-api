package br.com.projetotecnico.resource;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.projetotecnico.dto.ClienteDTO;
import br.com.projetotecnico.models.Cidade;
import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Estado;
import br.com.projetotecnico.repositoty.EstadoRepository;
import br.com.projetotecnico.service.ClienteService;
import br.com.projetotecnico.service.EnderecoService;
import br.com.projetotecnico.service.TelefoneService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource  {

	@Autowired
	EnderecoService enderecoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Estado>> findAll() {
		List<Estado> estados = enderecoService.obterTodosEstados();  
		return ResponseEntity.ok().body(estados);
	}
	
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<Cidade>> obterCidadesPorEstado(@PathVariable Integer estadoId) {
		List<Cidade> cidades = enderecoService.obterCidadesPorEstado(estadoId); 
		return ResponseEntity.ok().body(cidades);
	}
	
	@RequestMapping(value="/{cidadeId}/cidade", method=RequestMethod.GET)
	public ResponseEntity<Cidade> obterCidade(@PathVariable Integer cidadeId) {
		Cidade cidade = enderecoService.obterCidadePorId(cidadeId); 
		return ResponseEntity.ok().body(cidade);
	}
}
