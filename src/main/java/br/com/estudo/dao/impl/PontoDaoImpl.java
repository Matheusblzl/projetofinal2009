package br.com.estudo.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.estudo.dao.PontoDao;
import br.com.estudo.lang.DaoException;
import br.com.estudo.modelo.Ponto;
import br.com.estudo.modelo.Usuario;

public class PontoDaoImpl extends GenericDaoImpl<Ponto, Long> implements PontoDao {

    private static final Log logger = LogFactory.getLog("PontoDaoImpl");
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Ponto> findAllPontosByIdUsuario(Usuario usuario) throws DaoException {
        try {
            logger.info("Obtem pontos do usuário id = " + usuario.getId());
            
            return getSessionFactory().getCurrentSession().createQuery("select ponto from Ponto ponto where ponto.usuario.id = :idValue")
                    .setParameter("idValue", usuario.getId()).list();
        } catch (Exception e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ponto> findAllPontosByLoginUsuario(String login) throws DaoException {
        try {
            logger.info("Obtem pontos do login = " + login);
            
            return getSessionFactory().getCurrentSession().createQuery("select ponto from Ponto ponto where ponto.usuario.login = :loginValue")
                    .setParameter("loginValue", login).list();
        } catch (Exception e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

}
