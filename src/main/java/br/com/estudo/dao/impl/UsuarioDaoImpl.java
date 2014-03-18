package br.com.estudo.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.estudo.dao.UsuarioDao;
import br.com.estudo.lang.DaoException;
import br.com.estudo.modelo.Usuario;

public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Long> implements UsuarioDao {

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog("UsuarioDaoImpl");
	
	@Override
	public Usuario searchByLogin(String login) throws DaoException {
		try {
			logger.info("Obtem objeto com login = " + login);
			return (Usuario) getSessionFactory().getCurrentSession().createQuery("select usuario from Usuario usuario where usuario.login = :loginValue")
					.setParameter("loginValue", login).uniqueResult();
		} catch (Exception e) {
			logger.error(e);
			throw new DaoException(e);
		}
	}
	
}
