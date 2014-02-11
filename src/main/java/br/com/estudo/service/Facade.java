package br.com.estudo.service;

import java.io.Serializable;

public interface Facade extends Serializable {

    public String gerarToken(String ... values);
    
}
