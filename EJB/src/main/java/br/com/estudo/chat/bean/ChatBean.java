package br.com.estudo.chat.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.estudo.chat.ejb.Chat;

@Singleton
@Startup
public class ChatBean implements Chat {

    private Set<String> salas = new HashSet<String>();

    @Override
    public void criaSala(String sala) {
        this.salas.add(sala);
    }

    @Override
    public List<String> listaSalas() {
        return new ArrayList<String>(this.salas);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(" Criando o ChatBean ... ");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println(" Destruindo o ChatBean ... ");
    }

}
