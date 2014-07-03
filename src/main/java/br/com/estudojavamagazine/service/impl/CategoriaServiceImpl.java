package br.com.estudojavamagazine.service.impl;

import java.io.Serializable;
import java.util.List;

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
@Transactional(propagation = Propagation.REQUIRED)
public class CategoriaServiceImpl implements Serializable, CategoriaService {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
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
	public Categoria findCategoria(Long codigo) {
		if (ObjectUtil.isNotNull(codigo)) {
			return entityManager.find(Categoria.class, codigo);
		} else {
			return null;
		}
	}

	@Override
	public List<Categoria> findAllCategorias() {
		return entityManager.createNamedQuery(Categoria.FIND_ALL_CATEGORIAS, Categoria.class).getResultList();
	}

	@Override
	public void removerCategoria(Categoria categoria) throws CategoriaException {
		if (ObjectUtil.isNotNull(categoria) && ObjectUtil.isNotNull(categoria.getCodigo())) {
			removerCategoria(categoria.getCodigo());
		}
	}
	
	@Override
	public void removerCategoria(Long codigo) throws CategoriaException {
		if (ObjectUtil.isNotNull(codigo)) {
			Categoria categoria = entityManager.find(Categoria.class, codigo);
			if (ObjectUtil.isNotNull(categoria)) {
				entityManager.remove(categoria);
			} else {
				throw new CategoriaException("Categoria não existe ou já foi removida!");
			}
		}
	}

	@Override
	public Categoria findByName(String nome) {
		List<Categoria> list = entityManager.createNamedQuery(Categoria.FIND_CATEGORIA_BY_NAME, Categoria.class)
				.setParameter("nome", nome)
				.getResultList();
		if (ObjectUtil.isNotNullAndNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
}
