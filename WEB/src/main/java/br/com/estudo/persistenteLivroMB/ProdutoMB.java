package br.com.estudo.persistenteLivroMB;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estudo.models.Produto;
import br.com.estudo.sessionBenas.ProdutoRepositorio;

@ManagedBean
@ViewScoped
public class ProdutoMB {

    @EJB
    private ProdutoRepositorio repositorio;
    private Produto produto = new Produto();
    private List<Produto> produtosCache;

    public void adiciona() {
        this.repositorio.adiciona(this.produto);
        limparDados();
    }

    public void remover() {
        this.repositorio.remover(produto);
        limparDados();
    }
    
    private void limparDados() {
        this.produto = new Produto();
        this.produtosCache = null;
    }
    
    public List<Produto> getProdutos() {
        if (this.produtosCache == null) {
            this.produtosCache = this.repositorio.getProdutos();
        }
        return this.produtosCache;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

}
