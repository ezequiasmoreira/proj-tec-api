package br.com.projetotecnico.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetotecnico.dto.UsuarioDTO;
import br.com.projetotecnico.exception.ObjectNotFoundException;
import br.com.projetotecnico.models.Role;
import br.com.projetotecnico.models.Usuario;
import br.com.projetotecnico.repositoty.RoleRepository;
import br.com.projetotecnico.repositoty.UsuarioRepository;
import br.com.projetotecnico.spec.UsuarioSpec;

@Service
public class UsuarioService  {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UsuarioSpec usuarioSpec;
	
	@Autowired
    PasswordEncoder passwordEncoder;
		
	public Usuario obterPorId(Integer id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Usuario salvar(Usuario usuario) {
		usuarioSpec.validarEmailDoUsuario(usuario);
		
		usuario.setId(null);
		String senha = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senha);
		usuario.setAtivo(true);
		Role role = roleRepository.findByName("ROLE_USUARIO").orElseThrow(() -> new ObjectNotFoundException("ROLE_USUARIO não encontrado!"));
		usuario.setRoles(Arrays.asList(role)); 
		return usuarioRepository.save(usuario);
	}
	
	public Usuario converterParaDTO(UsuarioDTO usuarioDTO) {		
		return new Usuario(usuarioDTO);
	}

	public Usuario getUsuarioLogado() {
		return (Usuario) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
	}

}
