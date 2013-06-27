package br.com.estudo.padroescomportamentais.command;

import java.util.ArrayList;
import java.util.List;

import br.com.estudo.padroescomportamentais.command.inter.Comando;

public class ListaDeComandos {

	private List<Comando> comandos = new ArrayList<Comando>();

	public void adiciona(final Comando comando) {
		this.comandos.add(comando);
	}

	public void executa() {
		for (Comando comando : this.comandos) {
			comando.executa();
		}
	}

}
