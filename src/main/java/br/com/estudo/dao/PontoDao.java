package br.com.estudo.dao;

import java.util.List;

import br.com.estudo.lang.DaoException;
import br.com.estudo.modelo.Ponto;
import br.com.estudo.modelo.Usuario;

public interface PontoDao extends GenericDao<Ponto, Long> {

    public List<Ponto> findAllPontosByIdUsuario(Usuario usuario) throws DaoException;
    public List<Ponto> findAllPontosByLoginUsuario(String login) throws DaoException;
    
}
