package br.com.estudo.padroescomportamentais.command.comando;

import br.com.estudo.padroescomportamentais.command.inter.Comando;
import br.com.estudo.padroescomportamentais.command.model.Player;

public class DiminuiVolumeComando implements Comando {

	private Player player;
	private int levels;

	public DiminuiVolumeComando(final Player player, final int levels) {
		this.player = player;
		this.levels = levels;
	}

	@Override
	public void executa() {
		this.player.decreaseVolume(this.levels);
	}

}
