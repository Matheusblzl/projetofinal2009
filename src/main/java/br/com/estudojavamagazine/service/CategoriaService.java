package br.com.estudojavamagazine.service;

import java.io.Serializable;
import java.util.List;

import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.service.lang.CategoriaException;

public interface CategoriaService extends Serializable {

	public abstract Categoria saveOrUpdate(Categoria categoria) throws CategoriaException;

	public abstract Categoria findCategoria(Long codigo);

	public abstract List<Categoria> findAllCategorias();

	public abstract void removerCategoria(Long codigo) throws CategoriaException;

	public void removerCategoria(Categoria categoria) throws CategoriaException;

}
