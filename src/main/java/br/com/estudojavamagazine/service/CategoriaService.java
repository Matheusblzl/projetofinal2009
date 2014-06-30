package br.com.estudojavamagazine.service;

import java.io.Serializable;

import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.service.lang.CategoriaException;

public interface CategoriaService extends Serializable {

	public abstract Categoria saveOrUpdate(Categoria categoria) throws CategoriaException;

	public abstract Categoria findCategoria(Long codigo);

}
