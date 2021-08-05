package br.com.projetotecnico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetotecnico.exception.ObjectNotFoundException;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.enums.TipoTelefone;
import br.com.projetotecnico.repositoty.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;

	public Telefone salvar(Telefone telefone) {		
		telefone.setId(null);
		return telefoneRepository.save(telefone);
	}

	public Telefone atualizar(Telefone telefone) {		
		Optional<Telefone> telefoneAtualizar = telefoneRepository.findById(telefone.getId());
        return telefoneAtualizar.map(t -> telefoneRepository.save(new Telefone(t.getId(), telefone.getDescricao(), 
        		TipoTelefone.toEnum(telefone.getTipo()), telefone.getNumero())))
                .orElseThrow(() -> new ObjectNotFoundException("Telefone não encontrado!"));
	}
	
	public List<Telefone> salvarTelefones(List<Telefone> telefones) {
		List<Telefone> listaDeTelefones = new ArrayList<>();
		for (Telefone telefone : telefones) {
			listaDeTelefones.add(telefoneRepository.save(telefone));
		}
		return listaDeTelefones;
	}
	
	public List<Telefone> atualizarTelefones(List<Telefone> telefones) {
		List<Telefone> listaDeTelefones = new ArrayList<>();		
		for (Telefone telefone : telefones) {
			if (telefone.getId() == null) {
				listaDeTelefones.add(salvar(telefone));
			}else {
				listaDeTelefones.add(atualizar(telefone));
			}
		}
		return listaDeTelefones;
	}
	

}