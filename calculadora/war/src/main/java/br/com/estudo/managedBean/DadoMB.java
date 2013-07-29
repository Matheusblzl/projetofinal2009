package br.com.estudo.managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.estudo.beans.LancadorDeDadoBean;

@ManagedBean(name = "dadoMB")
public class DadoMB {
    
    @EJB
    private LancadorDeDadoBean lancadorDeDadoBean;

    private int resultado;

    public void lancaDado() {
        this.resultado = this.lancadorDeDadoBean.lanca();
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

}
