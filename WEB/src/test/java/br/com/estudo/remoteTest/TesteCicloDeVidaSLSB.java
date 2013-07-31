package br.com.estudo.remoteTest;

import javax.naming.InitialContext;

import br.com.estudo.ejb.LancadorDeDado;

public class TesteCicloDeVidaSLSB {

    public static void main(String[] args) throws Exception {

        InitialContext ic = new InitialContext();
        LancadorDeDado lancadorDeDado = (LancadorDeDado) ic.lookup("lancadorDeDadoBean");

//        for (int i = 0; i < 100; i++) {
//            final LancadorDeDado lancadorDeDado = (LancadorDeDado) ctx.lookup(LancadorDeDado.class.getName());
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < 100; i++) {
//                        System.out.println(lancadorDeDado.lanca());
//                    }
//                }
//            });
//            thread.start();
//        }

    }
}
