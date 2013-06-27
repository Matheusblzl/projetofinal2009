package br.com.estudo.padroescomportamentais.command.comando;

import br.com.estudo.padroescomportamentais.command.inter.Comando;
import br.com.estudo.padroescomportamentais.command.model.Player;

public class TocaMusicaComando implements Comando {

	private Player player;
	private String file;

	public TocaMusicaComando(final Player player, final String file) {
		this.player = player;
		this.file = file;
	}

	@Override
	public void executa() {
		try {
			this.player.play(this.file);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
