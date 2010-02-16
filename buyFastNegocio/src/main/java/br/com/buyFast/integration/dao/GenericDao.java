package br.com.buyFast.integration.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface que representa o modelo de acesso a dados.
 * @param <T> O tipo genérico do DAO.
 * @param <ID>
 */
public interface GenericDao <T, ID extends Serializable> {

	/**
	 * Obter a classe do objeto.
	 * @return a classe do objeto.
	 */
	public Class<T> getObjectClass();
	
	/**
	 * Persiste o objeto no banco de dados.
	 * @param object o objeto que será persistido.
	 * @return o objeto atualizado.
	 */
	public T save(T object);
	
	/**
	 * Pesquisa o objeto pelo código.
	 * @param id o código do objeto que será pesquisado.
	 * @return o modelo pesquisado no banco de dados.
	 */
	public T searchById(ID id);
	
	/**
	 * Atualiza o objeto no banco de dados.
	 * @param object o objeto que será atualizado.
	 * @return o objeto atualizado.
	 */
	public T update(T object);
	
	/**
	 * Remove o objeto no banco de dados.
	 * @param object o objeto a ser remove no banco de dados.
	 */
	public void delete(T object);
	
	/**
	 * Obtém todos os objetos persistidos.
	 * @return o conjunto de objetos no banco de dados.
	 */
	public List<T> all();
	
	/**
	 * Obtém um conjunto de objetos pesquisado pela query passada pelo parâmetro.<br />
	 * Deverão ser passados os parâmetros para a query através de um {@link Map}.
	 * @param query a query para a pesquisa no banco de dados.
	 * @param params os parâmetros para a query.
	 * @return o conjunto de objetos resultado da pesquisa.
	 */
	public List<T> listSearchParam(String query, Map<String, Object> params);
	
	/**
	 * Obtém um conjunto de objetos pesquisado pela query passada pelo parâmetro.<br />
	 * Deverão ser passados os parâmetros para a query através de um {@link Map}.<br />
	 * Neste método, há a possibilidade informar o máximo de resultados e o resultado corrente.
	 * @param query a query para a pesquisa no banco de dados.
	 * @param params os parâmetros para a query.
	 * @param maximum o máximo de resultados.
	 * @param current o resultado corrente.
	 * @return o conjunto de objetos resultado da pesquisa.
	 */
	public List<T> listSearchParam(String query, Map<String, Object> params, int maximum, int current);
	
	/**
	 * Obtém um conjunto de objetos pesquisado pela query passada pelo parâmetro.
	 * @param query a query para a pesquisa no banco de dados.
	 * @return o conjunto de objetos resultado da pesquisa.
	 */
	public List<T> listSearch(String query);
	
	/**
	 * Obtém o objeto resultante da query passada pelo parâmetro.
	 * @param query a query para a pesquisa no banco de dados.
	 * @param params os parâmetros para a query.
	 * @return o objeto resultado da pesquisa.
	 */
	public T searchParam(String query, Map<String, Object> params);
}