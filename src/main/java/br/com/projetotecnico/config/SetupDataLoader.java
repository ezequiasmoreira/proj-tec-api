package br.com.projetotecnico.config;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.projetotecnico.models.Role;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.Usuario;
import br.com.projetotecnico.models.enums.TipoTelefone;
import br.com.projetotecnico.repositoty.RoleRepository;
import br.com.projetotecnico.repositoty.TelefoneRepository;
import br.com.projetotecnico.repositoty.UsuarioRepository;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    TelefoneRepository telefoneRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    	usuarioRepository.deleteAll();
    	usuarioRepository.deleteAll();
        
        Role role = criarRoleSeNaoEncontrar("ROLE_USUARIO");        
        Usuario ezequias = new Usuario(null,"Ezequias","Prado","ezequias@hotmail.com",passwordEncoder.encode("123"),true);

        ezequias.setRoles(Arrays.asList(role)); 
        criarUsuarioSeNaoEncontrar(ezequias);
        
        Telefone telefone = new Telefone(null, "Telefone pessoal", TipoTelefone.CELULAR, "48998415269");
        telefoneRepository.save(telefone);
        
    }

    private Usuario criarUsuarioSeNaoEncontrar(final Usuario usuario) {
        Optional<Usuario> obj = usuarioRepository.findByEmail(usuario.getEmail());
        if(obj.isPresent()) {
            return obj.get();
        }
        return usuarioRepository.save(usuario);
    }
    
    private Role criarRoleSeNaoEncontrar(String name){
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()){
            return role.get();
        }
        return roleRepository.save(new Role(name));
    }

}