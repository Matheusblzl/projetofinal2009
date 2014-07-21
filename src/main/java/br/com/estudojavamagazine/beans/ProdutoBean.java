package br.com.estudojavamagazine.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.domain.Produto;
import br.com.estudojavamagazine.enumerate.ModoTela;
import br.com.estudojavamagazine.service.CategoriaService;
import br.com.estudojavamagazine.service.ProdutoService;
import br.com.estudojavamagazine.service.lang.ProdutoException;
import br.com.estudojavamagazine.service.util.ObjectUtil;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@RequestScoped
@Controller
@URLMappings(mappings = {
        @URLMapping(id = "url-produto", pattern = "/produto", viewId = "/pages/listarProdutos.jsf"),
        @URLMapping(id = "url-lista-produtos", pattern = "/produtos", viewId = "/pages/listarProdutos.jsf"),
        @URLMapping(id = "url-new-produto", parentId = "url-produto", pattern = "/new", viewId = "/pages/produto.jsf", onPostback = false),
        @URLMapping(id = "url-exibir-produto", parentId = "url-produto", pattern = "/exibir/#{codigo : produtoBean.codigo}", viewId = "/pages/produto.jsf", onPostback = false),
        @URLMapping(id = "url-excluir-produto", parentId = "url-produto", pattern = "/excluir/#{codigo : produtoBean.codigo}", viewId = "/pages/listarProdutos.jsf", onPostback = false),
        @URLMapping(id = "url-editar-produto", parentId = "url-produto", pattern = "/editar/#{codigo : produtoBean.codigo}", viewId = "/pages/produto.jsf", onPostback = false),
        @URLMapping(id = "url-listar-categoria-produto", pattern = "/listar/#{nomeCategoria : produtoBean.nomeCategoria}/#{nomeProduto : produtoBean.nomeProduto}", viewId = "/pages/listarProdutos.jsf")
        })
public class ProdutoBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;

	private Produto produto;

	private Long codigoCategoria;

	private List<Produto> listarProduto = new ArrayList<Produto>();

	private String nomeCategoria;
	private String nomeProduto;

	public ProdutoBean() {
	}

	public List<Produto> listar() {
		listarProduto = new ArrayList<Produto>();
		if (ObjectUtil.isNotNull(nomeProduto)
				&& ObjectUtil.isNotNull(nomeCategoria)) {
			listarProduto = produtoService
					.findProdutoByNomeCategoriaAndNomeProduto(nomeCategoria,
							nomeProduto);
		} else if (ObjectUtil.isNotNull(nomeCategoria)) {
			listarProduto = produtoService
					.findProdutoByNomeCategoria(nomeCategoria);
		} else {
			listarProduto = produtoService.findAllProdutos();
		}

		nomeCategoria = null;
		nomeProduto = null;

		return listarProduto;
	}

	@URLAction(mappingId = "url-new-produto")
	public void createInstance() {
		this.codigoCategoria = null;
		setModoTela(ModoTela.Inserir);
		this.produto = new Produto();
	}

	@URLAction(mappingId = "url-exibir-produto")
	public void loadInstance() {
		setModoTela(ModoTela.Exibir);
		if (ObjectUtil.isNotNull(getCodigo())) {
			this.produto = produtoService.findProduto(Long
					.parseLong(getCodigo()));
		} else if (ObjectUtil.isNotNull(this.produto)
				&& ObjectUtil.isNotNull(this.produto.getCodigo())) {
			this.produto = produtoService.findProduto(Long
					.parseLong(getCodigo()));
		}
		if (ObjectUtil.isNotNull(this.produto.getCategoria())) {
			this.codigoCategoria = this.produto.getCategoria().getCodigo();
		}
	}

	@URLAction(mappingId = "url-editar-produto")
	public void editInstance() {
		loadInstance();
		setModoTela(ModoTela.Editar);
	}

	@URLAction(mappingId = "url-excluir-produto")
	public void excluirInstance() {
		if (ObjectUtil.isNotNull(getCodigo())) {
			try {
				produtoService.removerProduto(Long.parseLong(getCodigo()));
				messageInfo("produto removida com sucesso!");
			} catch (NumberFormatException e) {
				messageErro("Erro ao obter código -- " + this.getCodigo());
			} catch (ProdutoException e) {
				messageErro(e.getMessage());
			}
		}
		setModoTela(ModoTela.Excluir);
		this.codigoCategoria = null;
	}

	public String persist() {
		try {
			produto.setCategoria(categoriaService
					.findCategoria(codigoCategoria));
			produto = produtoService.saveOrUpdate(produto);
		} catch (ProdutoException e) {
			e.printStackTrace();
			messageErro(e.getMessage());
			return null;
		}
		setCodigo(produto.getCodigo().toString());
		setModoTela(ModoTela.Exibir);
		messageInfo("Produto salva com sucesso!");
		return "pretty:url-exibir-produto";
	}

	public List<Categoria> allCategorias() {
		return categoriaService.findAllCategorias();
	}

	// ==== GETTERS AND SETTERS ====
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Long codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public List<Produto> getListarProduto() {
		return listarProduto;
	}

	public void setListarProduto(List<Produto> listarProduto) {
		this.listarProduto = listarProduto;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

}
