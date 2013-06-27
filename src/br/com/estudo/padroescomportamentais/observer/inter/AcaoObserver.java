package br.com.estudo.padroescomportamentais.observer.inter;

import br.com.estudo.padroescomportamentais.observer.action.Acao;

/**
 * Para notificar os interessados sobre as alterações nos valores da ação, devemos registrar os interessados
 * e notificá-los. Para padronizar a notificação dos interessados, criemos a interface AcaoObserver.
 * @author daniel.oliveira
 */
public interface AcaoObserver {
	void notificaAlteracao (Acao acao);
}
