package br.com.estudo.beans;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import br.com.estudo.ejb.LancadorDeDado;

@Stateless(mappedName = "lancadorDeDadoBean")
public class LancadorDeDadoBean implements LancadorDeDado {

    private Random gerador = new Random();
    private static int contador;

    @PostConstruct
    public void inicializando() {
        synchronized (LancadorDeDadoBean.class) {
            LancadorDeDadoBean.contador++;
            System.out.println(" Criando um lançador de dados ... ");
            System.out.println(" Total : " + +LancadorDeDadoBean.contador);
        }
    }

    @PreDestroy
    public void destruindo() {
        synchronized (LancadorDeDadoBean.class) {
            LancadorDeDadoBean.contador--;
            System.out.println(" Destruindo um lançador de dados ... ");
            System.out.println(" Total : " + +LancadorDeDadoBean.contador);
        }
    }

    @Override
    public int lanca() {
        return this.gerador.nextInt(6) + 1;
    }

}
