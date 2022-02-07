package br.com.bb.proitec.dolar.pojo;

import java.time.LocalDate;

public class Cotacao {

	private Double compra;
	private Double venda;
	private LocalDate data;

	public Double getCompra() {
		return compra;
	}

	public void setCompra(Double compra) {
		this.compra = compra;
	}

	public Double getVenda() {
		return venda;
	}

	public void setVenda(Double venda) {
		this.venda = venda;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Cotacao [compra=" + compra + ", venda=" + venda + ", data=" + data + "]";
	}

}
