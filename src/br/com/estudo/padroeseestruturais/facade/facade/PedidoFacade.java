package br.com.estudo.padroeseestruturais.facade.facade;

import br.com.estudo.padroeseestruturais.facade.model.Pedido;
import br.com.estudo.padroeseestruturais.facade.process.Estoque;
import br.com.estudo.padroeseestruturais.facade.process.Financeiro;
import br.com.estudo.padroeseestruturais.facade.process.PosVenda;

public class PedidoFacade {

	private Estoque estoque;
	private Financeiro financeiro;
	private PosVenda posVenda;

	public PedidoFacade(final Estoque estoque, final Financeiro financeiro, final PosVenda posVenda) {
		this.estoque = estoque;
		this.financeiro = financeiro;
		this.posVenda = posVenda;
	}

	public void registraPedido(final Pedido p) {
		this.estoque.enviaProduto(p.getProduto(), p.getEnderecoDeEntrega());
		this.financeiro.fatura(p.getCliente(), p.getProduto());
		this.posVenda.agendaContato(p.getCliente(), p.getProduto());
	}

}
