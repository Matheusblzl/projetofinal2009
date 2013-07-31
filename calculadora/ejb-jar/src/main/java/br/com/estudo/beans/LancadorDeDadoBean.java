package br.com.estudo.beans;

import java.util.Random;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local(LancadorDeDado.class)
public class LancadorDeDadoBean implements LancadorDeDado {

    private Random gerador = new Random();

    @Override
    public int lanca() {
        return this.gerador.nextInt(6) + 1;
    }

}
