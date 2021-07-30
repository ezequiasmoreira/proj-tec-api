package br.com.projetotecnico.spec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotecnico.exception.ValidateException;
import br.com.projetotecnico.models.Usuario;
import br.com.projetotecnico.repositoty.UsuarioRepository;

@Service
public class UsuarioSpec  {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Boolean validarEmailDoUsuario(Usuario usuario) {	
		
		Usuario usuarioEmail = usuarioRepository.obterPor(usuario.getEmail());
		if (usuarioEmail != null) {
			Throwable throwable = new Throwable("email");
			
			throw new ValidateException(
					"E-mail: " + usuario.getEmail() + " Já está em uso.",throwable);
		}
		return true;
	}
}
