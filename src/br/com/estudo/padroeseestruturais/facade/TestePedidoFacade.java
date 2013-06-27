package br.com.estudo.padroeseestruturais.facade;

import br.com.estudo.padroeseestruturais.facade.facade.PedidoFacade;
import br.com.estudo.padroeseestruturais.facade.model.Pedido;
import br.com.estudo.padroeseestruturais.facade.process.Estoque;
import br.com.estudo.padroeseestruturais.facade.process.Financeiro;
import br.com.estudo.padroeseestruturais.facade.process.PosVenda;

/**
 * <p>
 * Objetivo:<br>
 * Prover uma interface simplificada para a utilização de várias interfaces de um subsistema.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Estamos melhorando um sistema que realiza todos os procedimentos que devem ser realizados
 * após o registro de um pedido. Quando um pedido é realizado, o módulo que gerencia o estoque deve
 * ser avisado para que o produto seja encaminhado ao endereço de entrega. O módulo financeiro deve
 * ser avisado para que o processo de faturamento seja realizado. O módulo de pós venda também
 * deve ser avisado para que contatos futuros sejam realizados com o cliente com o intuito de verificar
 * a satisfação do mesmo com o produto obtido.
 * O sistema já está funcionando e realiza todos os processos decorrentes da realização de um novo
 * pedido. Mas, queremos simplificar essa lógica encapsulando as chamadas aos módulos de estoque,
 * financeiro e de pós venda.
 * </p>
 * @author daniel.oliveira
 *
 */
public class TestePedidoFacade {

	public static void main(final String[] args) {
		Estoque estoque = new Estoque();
		Financeiro financeiro = new Financeiro();
		PosVenda posVenda = new PosVenda();
		PedidoFacade facade = new PedidoFacade(estoque, financeiro, posVenda);
		Pedido pedido = new Pedido(" Notebook ", " Rafael Cosentino ", "Av Brigadeiro Faria Lima , 1571 , São Paulo , SP");
		facade.registraPedido(pedido);
	}

}
