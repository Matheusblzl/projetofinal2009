package br.com.estudo.chat.ejb;

import java.util.List;

public interface Chat {

    void criaSala(String sala);

    List<String> listaSalas();

}
