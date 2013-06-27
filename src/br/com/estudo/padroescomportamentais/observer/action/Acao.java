package br.com.estudo.padroescomportamentais.observer.action;

import java.util.HashSet;
import java.util.Set;

import br.com.estudo.padroescomportamentais.observer.inter.AcaoObserver;

public class Acao {

	private String codigo;
	private double valor;

	private Set<AcaoObserver> interessados = new HashSet<AcaoObserver>();

	public Acao(final String codigo, final double valor) {
		this.codigo = codigo;
		this.valor = valor;
	}

	public void registraInteressado(final AcaoObserver interessado) {
		this.interessados.add(interessado);
	}

	public void cancelaInteresse(final AcaoObserver interessado) {
		this.interessados.remove(interessado);
	}

	public double getValor() {
		return valor;
	}

	public void setValor(final double valor) {
		this.valor = valor;
		for (AcaoObserver interessado : this.interessados) {
			interessado.notificaAlteracao(this);
		}
	}

	public String getCodigo() {
		return codigo;
	}

}
