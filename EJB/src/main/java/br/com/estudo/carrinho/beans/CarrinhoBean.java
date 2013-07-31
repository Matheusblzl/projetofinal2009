package br.com.estudo.carrinho.beans;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;

import br.com.estudo.carrinho.ejb.Carrinho;

@Stateful
public class CarrinhoBean implements Carrinho {

    private Set<String> produtos = new HashSet<String>();

    @Override
    public void adiciona(String produto) {
        this.produtos.add(produto);
    }

    @Override
    public void remove(String produto) {
        this.produtos.remove(produto);
    }

    @Override
    public Set<String> getProdutos() {
        return produtos;
    }

}
