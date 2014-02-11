package br.com.estudo;

import br.com.estudo.service.Facade;
import br.com.estudo.service.serviceImpl.FacadeImpl;


public class Main {

    private Facade facade;
    
    public Main() {
        facade = new FacadeImpl();
    }

    public void usandoSpringSecurity() {
        System.out.println(facade.gerarToken("daniel@gmail.com", "123456"));
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.usandoSpringSecurity();
    }
}
