package br.com.projetotecnico.config;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.projetotecnico.models.Cidade;
import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Endereco;
import br.com.projetotecnico.models.Estado;
import br.com.projetotecnico.models.Role;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.Usuario;
import br.com.projetotecnico.models.enums.TipoTelefone;
import br.com.projetotecnico.repositoty.CidadeRepository;
import br.com.projetotecnico.repositoty.ClienteRepository;
import br.com.projetotecnico.repositoty.EnderecoRepository;
import br.com.projetotecnico.repositoty.EstadoRepository;
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
    
    @Autowired
    EstadoRepository estadoRepository;
    
    @Autowired
    CidadeRepository cidadeRepository;
    
    @Autowired
    EnderecoRepository enderecoRepository;

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
        telefoneRepository.saveAll(Arrays.asList(telefone,telefone1,telefone2));
        
        Cliente cliente = new Cliente(null, "joão", "silva", "10801593688");
        clienteRepository.save(cliente);
        
        Estado santaCatarina = new Estado(null, "SANTA CATARINA", 42);
        Estado rioDeJaneiro = new Estado(null, "RIO DE JANEIRO", 33);
        Estado saoPaulo = new Estado(null, "SÃO PAULO", 35);
        estadoRepository.saveAll(Arrays.asList(santaCatarina,rioDeJaneiro,saoPaulo));
        
        Cidade criciuma = new Cidade(null, "CRICIÚMA", santaCatarina);
        Cidade icara = new Cidade(null, "IÇARA", santaCatarina);
        Cidade saojose = new Cidade(null, "SÃO JOSE", santaCatarina);
        
        Cidade voltaRedonda = new Cidade(null, "VOLTA REDONDA", rioDeJaneiro);
        Cidade resende = new Cidade(null, "RESENDE", rioDeJaneiro);
        Cidade paracambi = new Cidade(null, "PARACAMBI", rioDeJaneiro);
        
        Cidade saoCaetano = new Cidade(null, "SÃO CAETANO", saoPaulo);
        Cidade atibaia = new Cidade(null, "ATIBAIA", saoPaulo);
        Cidade sorocaba = new Cidade(null, "SOROCABA", saoPaulo);
        cidadeRepository.saveAll(Arrays.asList(criciuma,icara,saojose,voltaRedonda,resende,paracambi,saoCaetano,atibaia,sorocaba));
        
        Endereco endereco1 = new Endereco(null, "CASA", "LBANO JOSE GOMES", "784", "RES. SANTA LUZIA", "SANTA LUZIA", "88860-220", true, criciuma);
        Endereco endereco2 = new Endereco(null, "TRABALHO", "CENTRO", "400", "GETULIO VARGAS", "VILA NOVA", "88860-333", false, criciuma);
        enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2));
        cliente.setEndereco(endereco1);
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