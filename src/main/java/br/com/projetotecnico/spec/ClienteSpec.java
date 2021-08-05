package br.com.projetotecnico.spec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotecnico.dto.ClienteDTO;
import br.com.projetotecnico.exception.ValidateException;
import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.repositoty.ClienteRepository;

@Service
public class ClienteSpec  {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Boolean validarDisponibilidadeCfpCnpj(ClienteDTO dto) {
		
		if ((dto.getId() != null) || (dto.getCpfCnpj() == "")) {
			return true;
		}			
		
		Cliente cliente = clienteRepository.findByCpfCnpj(dto.getCpfCnpj()); 
		
		if(cliente == null) {
			return true;
		}		
		Throwable throwable = new Throwable("cpfCnpj");
		
		throw new ValidateException(
				"CPF: " + cliente.getCpfCnpj() + " Já está em uso.",throwable);
	}
	
	public Boolean validarDisponibilidadeCfpCnpj(ClienteDTO dto,Cliente cliente) {
		
		Cliente clienteCpfCnpj = clienteRepository.findByCpfCnpj(dto.getCpfCnpj()); 
		if (clienteCpfCnpj == null) {
			return true;
		}
		
		if (clienteCpfCnpj.equals(cliente)) {
			return true;
		}
		
		Throwable throwable = new Throwable("cpfCnpj");
		
		throw new ValidateException(
				"CPF: " + cliente.getCpfCnpj() + " Já está em uso.",throwable);
	}
}
