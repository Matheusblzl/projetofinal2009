package br.com.estudo.carrinho.ejb;

import java.util.Set;

import javax.ejb.Local;

@Local
public interface Carrinho {

    void adiciona(String produto);

    void remove(String produto);

    Set<String> getProdutos();

}
