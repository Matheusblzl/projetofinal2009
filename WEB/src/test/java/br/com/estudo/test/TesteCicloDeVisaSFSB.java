package br.com.estudo.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.estudo.carrinho.ejb.Carrinho;

public class TesteCicloDeVisaSFSB {

    public static void main(String[] args) throws NamingException, InterruptedException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
        props.put(Context.SECURITY_PRINCIPAL, "danieloliveira");
        props.put(Context.SECURITY_CREDENTIALS, "123");
        InitialContext ic = new InitialContext(props);

        Carrinho[] carrinhos = new Carrinho[6];

        for (int i = 0; i < carrinhos.length; i++) {
            carrinhos[i] = (Carrinho) ic.lookup("estudoEjbMavenWEB/CarrinhoBean!br.com.estudo.carrinho.ejb.Carrinho");
            carrinhos[i].adiciona("Chaveiro-K19");
            carrinhos[i].adiciona("Caneta-K19");
            Thread.sleep(1000);
        }
        carrinhos[0].adiciona("Borracha-K19");
        Thread.sleep(5000);
        carrinhos[0].finalizaCompra();
    }
}
