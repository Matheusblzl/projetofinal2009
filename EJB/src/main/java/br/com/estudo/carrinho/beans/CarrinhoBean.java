package br.com.estudo.carrinho.beans;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import br.com.estudo.carrinho.ejb.Carrinho;

@Stateful
public class CarrinhoBean implements Carrinho {

    private Set<String> produtos = new HashSet<String>();

    private static int contadorTotal;
    private static int contadorAtivos;
    private int id;

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

    @Override
    @Remove
    public void finalizaCompra() {
        System.out.println(" Finalizando a compra ");
    }

    @PostConstruct
    public void postContruct() {
        synchronized (CarrinhoBean.class) {
            CarrinhoBean.contadorTotal++;
            CarrinhoBean.contadorAtivos++;
            this.id = CarrinhoBean.contadorTotal;
            System.out.println(" PostConstruct ");
            System.out.println(" ID : " + this.id);
            System.out.println(" ContatorTotal : " + CarrinhoBean.contadorTotal);
            System.out.println(" ContatorAtivos : " + CarrinhoBean.contadorAtivos);
        }
    }

    @PrePassivate
    public void prePassivate() {
        synchronized (CarrinhoBean.class) {
            CarrinhoBean.contadorAtivos--;
            System.out.println(" PrePassivate ");
            System.out.println(" ID : " + this.id);
            System.out.println(" ContatorTotal : " + CarrinhoBean.contadorTotal);
            System.out.println(" ContatorAtivos : " + CarrinhoBean.contadorAtivos);
        }
    }

    @PostActivate
    public void postActivate() {
        synchronized (CarrinhoBean.class) {
            CarrinhoBean.contadorAtivos++;
            System.out.println(" PostActivate ");
            System.out.println(" ID : " + this.id);
            System.out.println(" ContatorTotal : " + CarrinhoBean.contadorTotal);
            System.out.println(" ContatorAtivos : " + CarrinhoBean.contadorAtivos);
        }
    }

    @PreDestroy
    public void preDestroy() {
        synchronized (CarrinhoBean.class) {
            CarrinhoBean.contadorAtivos--;
            System.out.println(" PreDestroy ");
            System.out.println(" ID : " + this.id);
            System.out.println(" ContatorTotal : " + CarrinhoBean.contadorTotal);
            System.out.println(" ContatorAtivos : " + CarrinhoBean.contadorAtivos);
        }
    }
}
