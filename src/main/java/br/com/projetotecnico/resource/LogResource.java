package br.com.projetotecnico.resource;


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
}
