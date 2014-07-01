package br.com.estudojavamagazine.service;

import java.io.Serializable;
import java.util.List;

import br.com.estudojavamagazine.domain.Produto;
import br.com.estudojavamagazine.service.lang.ProdutoException;

public interface ProdutoService extends Serializable {

	public abstract Produto saveOrUpdate(Produto produto) throws ProdutoException;

	public abstract Produto findProduto(Long codigo);

	public abstract List<Produto> findAllProdutos();

	public abstract void removerProduto(Long codigo) throws ProdutoException;

	public void removerProduto(Produto produto) throws ProdutoException;
	
}
