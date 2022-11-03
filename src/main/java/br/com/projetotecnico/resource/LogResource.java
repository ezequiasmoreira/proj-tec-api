package br.com.projetotecnico.resource;


import br.com.projetotecnico.dto.LogDTO;
import br.com.projetotecnico.dto.LogFilterDTO;
import br.com.projetotecnico.models.Log;
import br.com.projetotecnico.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogResource {
	@Autowired
	LogService logService;
	
	@RequestMapping(value="/filter",method=RequestMethod.POST)
	public ResponseEntity<List<Log>> getFilter(@RequestBody LogFilterDTO logFilter) {
		return ResponseEntity.ok().body(logService.getFilter(logFilter));
	}

	@RequestMapping(value="/classes",method=RequestMethod.GET)
	public ResponseEntity<List<Object>> getClasses() {
		return ResponseEntity.ok().body(logService.getClasses());
	}

	@RequestMapping(value="/propriedades",method=RequestMethod.GET)
	public ResponseEntity<List<LogDTO>> getPropriedades(@RequestParam(value="classe") String classe) throws ClassNotFoundException {
		return ResponseEntity.ok().body(logService.getPropriedades(classe));
	}
}
