package br.com.projetotecnico.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projetotecnico.exception.ObjectNotFoundException;
import br.com.projetotecnico.models.Role;
import br.com.projetotecnico.models.Usuario;
import br.com.projetotecnico.repositoty.UsuarioRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

@Autowired
UsuarioRepository usuarioRepository;

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
    if (!usuario.isPresent()) {
        throw new UsernameNotFoundException(String.format("UserNotExist"));
    } else if (!usuario.get().isAtivo()) {
        throw new ObjectNotFoundException(String.format("UserNotEnabled"));
    }
    return new UserRepositoryUserDetails(usuario.get());
}

private final List<GrantedAuthority> getGrantedAuthorities(final Collection<Role> roles) {
    final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (Role role: roles) {
        authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
}

    public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles){
        return getGrantedAuthorities(roles);
    }

    private final static class UserRepositoryUserDetails extends Usuario implements UserDetails {

    public UserRepositoryUserDetails(Usuario usuario) {
        super(usuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
    

	@Override
	public String getPassword() {		
		return getSenha();
	}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

}
