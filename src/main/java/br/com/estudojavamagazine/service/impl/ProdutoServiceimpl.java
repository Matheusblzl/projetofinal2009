package br.com.estudojavamagazine.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudojavamagazine.domain.Produto;
import br.com.estudojavamagazine.service.ProdutoService;
import br.com.estudojavamagazine.service.lang.ProdutoException;
import br.com.estudojavamagazine.service.util.ObjectUtil;

@Service("produtoService")
@Transactional(propagation = Propagation.REQUIRED)
public class ProdutoServiceimpl implements ProdutoService {

	private static final long serialVersionUID = 3095786918632665898L;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Produto saveOrUpdate(Produto produto) throws ProdutoException {
		try {
			if (ObjectUtil.isNotNull(produto.getCodigo())) {
				produto = entityManager.merge(produto);
			} else {
				entityManager.persist(produto);
			}
		} catch (Exception e) {
			throw new ProdutoException(e);
		}
		return produto;
	}

	@Override
	public Produto findProduto(Long codigo) {
		if (ObjectUtil.isNotNull(codigo)) {
			return entityManager.find(Produto.class, codigo);
		} else {
			return null;
		}
	}

	@Override
	public List<Produto> findAllProdutos() {
		return entityManager.createNamedQuery(Produto.FIND_ALL_PRODUTO, Produto.class).getResultList();
	}

	@Override
	public void removerProduto(Produto produto) throws ProdutoException {
		if (ObjectUtil.isNotNull(produto) && ObjectUtil.isNotNull(produto.getCodigo())) {
			removerProduto(produto.getCodigo());
		}
	}
	
	@Override
	public void removerProduto(Long codigo) throws ProdutoException {
		if (ObjectUtil.isNotNull(codigo)) {
			Produto produto = entityManager.find(Produto.class, codigo);
			if (ObjectUtil.isNotNull(produto)) {
				entityManager.remove(produto);
			} else {
				throw new ProdutoException("Produto não existe ou já foi removida!");
			}
		}
	}

}
