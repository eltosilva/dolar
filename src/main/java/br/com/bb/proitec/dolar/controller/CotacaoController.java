package br.com.bb.proitec.dolar.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bb.proitec.dolar.pojo.Cotacao;
import br.com.bb.proitec.dolar.service.CotacaoService;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {

	@Autowired
	CotacaoService cotacaoService;
	
	@GetMapping
	public ResponseEntity<List<Cotacao>> consultarCotacoes(@RequestParam LocalDate data){
		
		return ResponseEntity.ok(cotacaoService.getCotacoes(data));
	}
}
