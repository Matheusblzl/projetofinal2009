package br.com.estudo;

import java.io.Serializable;
import java.util.List;

import br.com.estudo.lang.FacadeException;
import br.com.estudo.lang.ValidatedLoginSenhaException;
import br.com.estudo.lang.ValidatedTokenException;
import br.com.estudo.modelo.Ponto;
import br.com.estudo.modelo.Usuario;

public interface Facade extends Serializable {

    public String gerarToken(String ... values);

	Usuario getUsuarioByLogin(String login) throws FacadeException;

	void salvarUsuario(Usuario usuario) throws FacadeException;

	Usuario getUsuarioPorId(Long id) throws FacadeException;
    
	List<Ponto> getPontosUsuario(Usuario usuario) throws FacadeException;

    List<Ponto> getPontosUsuario(String login, String token) throws FacadeException, ValidatedLoginSenhaException, ValidatedTokenException;
}
