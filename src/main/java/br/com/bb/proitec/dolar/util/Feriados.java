package br.com.bb.proitec.dolar.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import br.com.bb.proitec.dolar.util.pojo.Feriado;

@ConfigurationProperties("b3")
@Configuration("feriados")
public class Feriados {

	private final List<Feriado> feriadosFixos = new ArrayList<Feriado>();
	
	public Feriados() {
		System.out.println("Carregado os Feriados");
	}

	public List<Feriado> getFeriadosFixos() {
		return feriadosFixos;
	}


}
