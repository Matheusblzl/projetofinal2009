package br.com.estudojavamagazine.service.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.service.CategoriaService;
import br.com.estudojavamagazine.service.lang.CategoriaException;
import br.com.estudojavamagazine.service.util.ObjectUtil;

@Service("categoriaService")
public class CategoriaServiceImpl implements Serializable, CategoriaService {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Categoria saveOrUpdate(Categoria categoria) throws CategoriaException {
		try {
			if (ObjectUtil.isNotNull(categoria)) {
				categoria = entityManager.merge(categoria);
			} else {
				entityManager.persist(categoria);
			}
		} catch (Exception e) {
			throw new CategoriaException(e);
		}
		return categoria;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Categoria findCategoria(Long codigo) {
		if (ObjectUtil.isNotNull(codigo)) {
			return entityManager.find(Categoria.class, codigo);
		} else {
			return null;
		}
	}
	
}
