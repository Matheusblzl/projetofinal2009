package br.com.estudojavamagazine.beans;

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

@ManagedBean
@RequestScoped
@Controller
public class ProdutoBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    private Produto produto;
    
    private Long codigoCategoria;
    
    public ProdutoBean() {
    }
    
    public List<Produto> listar() {
    	return produtoService.findAllProdutos();
    }
    
    public void createInstance() {
        this.codigoCategoria = null;
    	setModoTela(ModoTela.Inserir);
    	this.produto = new Produto();
    }
    
    public void loadInstance() {
    	setModoTela(ModoTela.Exibir);
    	if (ObjectUtil.isNotNull(getCodigo())) {
    		this.produto = produtoService.findProduto(Long.parseLong(getCodigo()));
    	} else if (ObjectUtil.isNotNull(this.produto) && ObjectUtil.isNotNull(this.produto.getCodigo())) {
    		this.produto = produtoService.findProduto(Long.parseLong(getCodigo()));
    	}
    	if (ObjectUtil.isNotNull(this.produto.getCategoria())) {
    	    this.codigoCategoria = this.produto.getCategoria().getCodigo();
    	}
    }
    
    public void editInstance() {
    	loadInstance();
    	setModoTela(ModoTela.Editar);
    }
    
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
            produto.setCategoria(categoriaService.findCategoria(codigoCategoria));
            produto = produtoService.saveOrUpdate(produto);
        } catch (ProdutoException e) {
            e.printStackTrace();
            messageErro(e.getMessage());
            return null;
        }
        setCodigo(produto.getCodigo().toString());
        setModoTela(ModoTela.Exibir);
    	messageInfo("produto salva com sucesso!");
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

}
