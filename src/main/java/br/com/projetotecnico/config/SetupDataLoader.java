package br.com.projetotecnico.config;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Role;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.Usuario;
import br.com.projetotecnico.models.enums.TipoTelefone;
import br.com.projetotecnico.repositoty.ClienteRepository;
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
    
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    	usuarioRepository.deleteAll();
    	usuarioRepository.deleteAll();
        
        Role role = criarRoleSeNaoEncontrar("ROLE_USUARIO");        
        Usuario ezequias = new Usuario(null,"Ezequias","Prado","ezequias@hotmail.com",passwordEncoder.encode("123"),true);

        ezequias.setRoles(Arrays.asList(role)); 
        criarUsuarioSeNaoEncontrar(ezequias);
        
        Telefone telefone = new Telefone(null, "Telefone pessoal", TipoTelefone.CELULAR, "48998415269");
        Telefone telefone1 = new Telefone(null, "Telefone residencial", TipoTelefone.RESIDENCIAL, "48998418050");
        Telefone telefone2 = new Telefone(null, "Telefone comercial", TipoTelefone.COMERCIAL, "48998418796");
        telefoneRepository.save(telefone);
        telefoneRepository.save(telefone1);
        telefoneRepository.save(telefone2); 
        
        Cliente cliente = new Cliente(null, "joão", "silva", "10801593688");
        clienteRepository.save(cliente);
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