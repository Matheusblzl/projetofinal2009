package br.com.estudo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.estudo.service.Facade;
import br.com.estudo.service.serviceImpl.FacadeImpl;


public class TestFacade {

    private Facade facade;
    
    @Before
    public void setUp() throws Exception {
        facade = new FacadeImpl();
    }

    @Test
    public void testGerarToken() {
        assertEquals(facade.gerarToken("daniel@gmail.com", "123456"), "bnVsbGRhbmllbEBnbWFpbC5jb20xMjM0NTY=");
    }
    
}
