package br.com.estudo.padroescomportamentais.command;

import br.com.estudo.padroescomportamentais.command.comando.AumentaVolumeComando;
import br.com.estudo.padroescomportamentais.command.comando.DiminuiVolumeComando;
import br.com.estudo.padroescomportamentais.command.comando.TocaMusicaComando;
import br.com.estudo.padroescomportamentais.command.model.Player;

/**
 * <p>
 * Objetivo:<br>
 * Controlar as chamadas a um determinado componente, modelando cada requisição como um objeto.
 * Permitir que as operações possam ser desfeitas, enfileiradas ou registradas.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Estamos desenvolvendo um aplicativo para gerenciar playlists de música. Os usuários poderão
 * selecionar as suas músicas favoritas e definir a ordem na qual elas devem ser reproduzidas. Um
 * playlist é basicamente uma seqüência de músicas. Contudo, o aplicativo pode adicionar, entre as
 * músicas de um playlist, comandos para aumentar ou diminuir o volume de reprodução.
 * Vamos utilizar uma biblioteca de audio para desenvolver esse aplicativo. Através dessa biblioteca,
 * podemos tocar músicas e controlar o volume das saídas de audio.
 * </p> 
 * @author daniel.oliveira
 */
public class TestaListaDeComandos {

	public static void main(final String[] args) {
		Player player = new Player();
		ListaDeComandos listaDeComandos = new ListaDeComandos();

		listaDeComandos.adiciona(new TocaMusicaComando(player, "musica1.mp3"));
		
		listaDeComandos.adiciona(new AumentaVolumeComando(player, 3));
		
		listaDeComandos.adiciona(new TocaMusicaComando(player, "musica2.mp3"));
		
		listaDeComandos.adiciona(new DiminuiVolumeComando(player, 3));
		
		listaDeComandos.adiciona(new TocaMusicaComando(player, "musica3.mp3"));

		listaDeComandos.executa();
	}

}
