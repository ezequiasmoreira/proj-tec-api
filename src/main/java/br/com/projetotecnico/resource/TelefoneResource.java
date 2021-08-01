package br.com.projetotecnico.resource;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetotecnico.dto.TelefoneDTO;
import br.com.projetotecnico.dto.UsuarioDTO;
import br.com.projetotecnico.models.Role;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.Usuario;
import br.com.projetotecnico.service.TelefoneService;
import br.com.projetotecnico.service.UsuarioService;

@RestController
@RequestMapping("/telefones")
public class TelefoneResource  {

	@Autowired
	TelefoneService telefoneService;
	
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Telefone> salvar(@RequestBody TelefoneDTO dto) {
		Telefone telefone = telefoneService.converterParaDTO(dto);
		telefone = telefoneService.salvar(telefone);
		return ResponseEntity.ok().body(telefone);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<TelefoneDTO> update(@PathVariable Integer id, @RequestBody TelefoneDTO telefoneDTO) {

        Telefone telefone = telefoneService.converterParaDTO(telefoneDTO);
        telefone.setId(id);
        return ResponseEntity.ok().body(new TelefoneDTO(telefoneService.atualizar(telefone)));
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Telefone telefone = telefoneService.obterPorId(id);
		telefoneService.excluir(telefone);
		return ResponseEntity.noContent().build();
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Telefone> obterPorId(@PathVariable Integer id) {
		Telefone telefone = telefoneService.obterPorId(id);
		return ResponseEntity.ok().body(telefone);
	}
}
