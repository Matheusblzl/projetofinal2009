package br.com.estudo.service.serviceImpl;

import org.springframework.security.crypto.codec.Base64;

import br.com.estudo.service.Facade;

public class FacadeImpl implements Facade {

    private static final long serialVersionUID = 1L;

    public String gerarToken(String... values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Parametros não podem ser nulos ou vazios!");
        }
        String keySource = null;
        
        for (String value : values) {
            keySource += value;
        }

        byte[] tokenByte = Base64.encode(keySource.getBytes());
        
        String token = new String(tokenByte);
        
        return token;
    }
}
