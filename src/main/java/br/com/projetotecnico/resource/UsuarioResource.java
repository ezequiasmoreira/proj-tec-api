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

import br.com.projetotecnico.dto.UsuarioDTO;
import br.com.projetotecnico.models.Role;
import br.com.projetotecnico.models.Usuario;
import br.com.projetotecnico.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource  {
	
	@Autowired
	UsuarioService usuarioService;

	
	@Secured({"ROLE_USUARIO"})
	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> findRoles(@PathVariable Integer id) {
       Usuario usuario = usuarioService.obterPorId(id);
        return ResponseEntity.ok().body(usuario.getRoles());
    }
    
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = usuarioService.converterParaDTO(dto);
		return ResponseEntity.ok().body(new UsuarioDTO(usuarioService.salvar(usuario)));
	}

}
