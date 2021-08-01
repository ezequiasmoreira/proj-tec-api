package br.com.projetotecnico.resource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetotecnico.dto.ClienteDTO;
import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource  {

	@Autowired
	ClienteService clienteService;
	
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Cliente> salvar(@RequestBody ClienteDTO dto) {
		Cliente cliente = clienteService.converterParaDTO(dto);
		cliente = clienteService.salvar(cliente);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {

        Cliente cliente = clienteService.converterParaDTO(clienteDTO);
        cliente.setId(id);
        return ResponseEntity.ok().body(new ClienteDTO(clienteService.atualizar(cliente)));
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Cliente cliente = clienteService.obterPorId(id);
		clienteService.excluir(cliente);
		return ResponseEntity.noContent().build();
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> obterPorId(@PathVariable Integer id) {
		Cliente cliente = clienteService.obterPorId(id);
		return ResponseEntity.ok().body(cliente);
	}
}
