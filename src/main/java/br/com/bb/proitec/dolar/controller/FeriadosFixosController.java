package br.com.bb.proitec.dolar.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bb.proitec.dolar.service.FeriadosServices;

@RestController
@RequestMapping("/dias-nao-uteis")
public class FeriadosFixosController {

	@Autowired
	private FeriadosServices feriadosServices;

	@GetMapping("/feriados")
	public ResponseEntity<List<LocalDate>> consultarFeriados(Integer ano) {

		return ResponseEntity.ok(feriadosServices.getFeriadosDoAno(ano.intValue()));
	}
	
	@GetMapping("/dia-semana")
	public ResponseEntity<Integer> diaDaSemana(@RequestParam LocalDate data){
		
		return ResponseEntity.ok(data.getDayOfWeek().getValue());
	}
}
