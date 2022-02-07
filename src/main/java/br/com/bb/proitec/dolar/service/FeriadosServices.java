package br.com.bb.proitec.dolar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bb.proitec.dolar.util.Feriados;

@Service
public class FeriadosServices {

	@Autowired
	private Feriados feriados;
	
	public boolean ehUmDiaUtil(LocalDate data) {
		if(data.getDayOfWeek().getValue() > 5)
			return false;
		
		return false;
	}
	
	public List<LocalDate> getFeriadosDoAno(int ano){
		List<LocalDate> listaDeFeriados = new ArrayList<LocalDate>();
		listaDeFeriados.addAll(getFeriadosFixos(ano));

		LocalDate pascoa = getDataDaPascoa(ano);
		listaDeFeriados.add(pascoa.minusDays(2)); //Sexta-feira da Paixão
		listaDeFeriados.add(pascoa.minusDays(48)); //Segunda-feira de Carnaval
		listaDeFeriados.add(pascoa.minusDays(47)); //Terça-feira de Carnaval
		listaDeFeriados.add(pascoa.minusDays(-60)); //Corpus Christi
		
		return listaDeFeriados;
	}
	
	private List<LocalDate> getFeriadosFixos(int ano){
		return feriados.getFeriadosFixos()
				.stream()
				.map(feriado -> LocalDate.of(ano, feriado.getMes(), feriado.getDia()))
				.collect(Collectors.toList());
	}
	
	private LocalDate getDataDaPascoa(int ano) {
		int a = ano % 19;
		int b = ano / 100;
		int c = ano % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int mes = (h + l - 7 * m + 114) / 31;
		int dia = ((h + l - 7 * m + 114) % 31) + 1;
		
		return LocalDate.of(ano, mes, dia);
	}
}
