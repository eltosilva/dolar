package br.com.bb.proitec.dolar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bb.proitec.dolar.pojo.Cotacao;

@Service
public class CotacaoService {

	@Autowired
	FeriadosServices feriadosServices;
	
	public List<Cotacao> getCotacoes(LocalDate data) {
		if(LocalDate.now().isBefore(data))
			throw new HttpClientErrorException("Não pode usar data futura", HttpStatus.BAD_REQUEST, "Requisição inválida", null, null, null);
		if(!ehDiaUtil(data))
			throw new HttpClientErrorException("Data informada é um final de semana ou feriado", HttpStatus.BAD_REQUEST, "Requisição inválida", null, null, null);
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("localhost:8090")
				.path("ptax")
				.queryParam("data", data)
				.build();
		
		List<Cotacao> cotacoes = new ArrayList<Cotacao>();
		
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Cotacao> entity = rt.getForEntity(uri.toUriString(), Cotacao.class);
		cotacoes.add(entity.getBody());
		
		int diaAnterior = 1;
		while(!ehDiaUtil(data.minusDays(diaAnterior))) {
			diaAnterior++;
		}
		
		data = data.minusDays(diaAnterior);

		uri = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("localhost:8090")
				.path("ptax")
				.queryParam("data", data)
				.build();
		
		entity = rt.getForEntity(uri.toUriString(), Cotacao.class);
		cotacoes.add(entity.getBody());
		
		return cotacoes;
	}
	
	private boolean ehDiaUtil(LocalDate data) {
		return data.getDayOfWeek().getValue() < 6 && !feriadosServices.getFeriadosDoAno(data.getYear()).contains(data);
	}
}
