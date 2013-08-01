package br.com.estudo.carrinho.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.estudo.carrinho.ejb.Carrinho;

@ManagedBean
@SessionScoped
public class CarrinhoMB {

    @EJB
    private Carrinho carrinho;

    private String produto;

    @PostConstruct
    public void inicializando() {
        System.out.println("Iniciando mais um carrinho...");
    }

    public List<String> getProdutos() {
        return new ArrayList<String>(this.carrinho.getProdutos());
    }

    public void adiciona() {
        this.carrinho.adiciona(this.produto);
        this.produto = "";
    }

    public void remove(String produto) {
        this.carrinho.remove(produto);
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getProduto() {
        return produto;
    }
}
