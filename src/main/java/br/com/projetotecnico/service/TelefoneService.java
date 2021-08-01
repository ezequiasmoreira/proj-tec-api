package br.com.projetotecnico.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotecnico.dto.TelefoneDTO;
import br.com.projetotecnico.exception.ObjectNotFoundException;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.enums.TipoTelefone;
import br.com.projetotecnico.repositoty.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;

	public Telefone converterParaDTO(TelefoneDTO telefoneDTO) {		
		return new Telefone(telefoneDTO);
	}

	public Telefone salvar(Telefone telefone) {		
		telefone.setId(null);
		return telefoneRepository.save(telefone);
	}

	public Telefone atualizar(Telefone telefone) {		
		Optional<Telefone> telefoneAtualizar = telefoneRepository.findById(telefone.getId());
        return telefoneAtualizar.map(t -> telefoneRepository.save(new Telefone(t.getId(), telefone.getDescricao(), TipoTelefone.toEnum(telefone.getTipo()), telefone.getNumero())))
                .orElseThrow(() -> new ObjectNotFoundException("Telefone não encontrado!"));
	}


	public void excluir(Telefone telefone){		
		telefoneRepository.deleteById(telefone.getId());
	}

	public Telefone obterPorId(Integer telefoneId) throws ObjectNotFoundException {		
		Optional<Telefone> telefone = telefoneRepository.findById(telefoneId);
		return telefone.orElseThrow(() -> new ObjectNotFoundException("Telefone não encontrado."));
	}

}