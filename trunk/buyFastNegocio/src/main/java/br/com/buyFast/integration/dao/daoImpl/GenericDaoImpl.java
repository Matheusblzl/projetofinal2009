package br.com.buyFast.integration.dao.daoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.DaoException;
import br.com.buyFast.integration.dao.GenericDao;

/**
 * Classe DAO genérica que implementa a interface {@link GenericDao}.
 * @param <T>
 * @param <ID>
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class GenericDaoImpl<T, ID extends Serializable> extends HibernateDaoSupport
		implements GenericDao<T, ID> {

	/**
	 * Apresenta o log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog("GenericDaoImpl");
	
	/**
	 * A classe do objeto.
	 */
	private final Class<T> $Class;
	
	/**
	 * Instancia um novo {@link GenericDaoImpl}.
	 */
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
		this. $Class = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];
		} else  {
			$Class = (Class<T>) getClass().getGenericSuperclass();
		}
	}
	
	@Override
	public Class<T> getObjectClass() {
		return this.$Class;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public T save(T object) throws DaoException {
		try {
			logger.info("Salvando objeto " + object + "...");
			getHibernateTemplate().save(object);
			logger.info("Objeto " + getObjectClass().getSimpleName() + " salvo com sucesso.");
		} catch (Exception e) {
			String messageError = "Erro ao salvar objeto " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
		
		return object;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public T update(T object) throws DaoException {
		try {
			logger.info("Atualizando objeto " + object + "...");
			getHibernateTemplate().update(object);
			logger.info("Objeto " + getObjectClass().getSimpleName() + " atualizado com sucesso.");
		} catch (Exception e) {
			String messageError = "Erro ao atualizar objeto " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
		
		return object;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void delete(T object) throws DaoException {
		try {
			logger.info("Removendo objeto " + object + "...");
			getHibernateTemplate().delete(object);
			logger.info("Objeto " + getObjectClass().getSimpleName() + " removido com sucesso.");
		} catch (Exception e) {
			String messageError = "Erro ao remover objeto " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listSearch(String query) throws DaoException {
		try {
			logger.info("Executando query e retornando lista de resultados ...");
			return (List<T>) getHibernateTemplate().find(query);
		} catch (Exception e) {
			String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listSearchParam(String query, Map<String, Object> params) throws DaoException {
		try {
			logger.info("Executando query ...");
			Query q = getHibernateTemplate().getSessionFactory().openSession().createQuery(query);
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
			
			logger.info("Retornando lista de resultados...");
			return (List<T>) q.list();
		} catch (Exception e) {
			String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listSearchParam(String query, Map<String, Object> params, int maximum, int current) 
			throws DaoException {
		try {
			logger.info("Executando query ...");
			Query q = getHibernateTemplate().getSessionFactory().openSession().createQuery(query)
				.setMaxResults(maximum).setFirstResult(current);
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
			logger.info("Retornando lista de resultados...");
			return (List<T>) q.list();
		} catch (Exception e) {
			String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T searchById(ID id) throws DaoException {
		try {
			logger.info("Obtem objeto com ID = " + id);
			return (T) getHibernateTemplate().getSessionFactory().openSession().load($Class, id);
		}catch (Exception e) {
			String messageError = "Erro ao carregar objeto do tipo " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T searchParam(String query, Map<String, Object> params) throws DaoException {
		try {
			logger.info("Executando query ...");
			Query q = getHibernateTemplate().getSessionFactory().openSession().createQuery(query);
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
			logger.info("Retornando lista de resultados...");
			return (T) q.uniqueResult();
		} catch (Exception e) {
			String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> all() throws DaoException {
		try {
			logger.info("Obtendo todos os objetos do tipo " + getObjectClass().getSimpleName() + "...");
			return getHibernateTemplate().find("FROM " + $Class.getName());
		} catch (Exception e) {
			String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
			logger.error(messageError, e);
			throw new DaoException(messageError, e);
		}
	}

}
