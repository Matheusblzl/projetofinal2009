package br.com.estudojavamagazine.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.estudojavamagazine.enumerate.ModoTela;


public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    
    private ModoTela modoTela = ModoTela.Exibir;
    
    public BaseBean() {
        
    }
    
    public void message(String msg, Severity severity) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage msgFaces = new FacesMessage(msg);
        msgFaces.setSeverity(severity);
        fc.addMessage("", msgFaces);
        fc.getExternalContext().getFlash().setKeepMessages(true);
        fc.renderResponse();
    }
    
    public void messageErro(String msg) {
        message(msg, FacesMessage.SEVERITY_ERROR);
    }
    
    public void messageInfo(String msg) {
        message(msg, FacesMessage.SEVERITY_INFO);
    }
    
    public void messageWarn(String msg) {
        message(msg, FacesMessage.SEVERITY_WARN);
    }

    // ==== GETTERS AND SETTERS ====
    
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public ModoTela getModoTela() {
		return modoTela;
	}

	public void setModoTela(ModoTela modoTela) {
		this.modoTela = modoTela;
	}

}
