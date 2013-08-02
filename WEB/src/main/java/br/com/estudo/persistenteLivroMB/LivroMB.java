package br.com.estudo.persistenteLivroMB;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estudo.models.Livro;
import br.com.estudo.sessionBenas.LivroRepositorio;

@ManagedBean
@ViewScoped
public class LivroMB {

    @EJB
    private LivroRepositorio repositorio;
    
    private Livro livro = new Livro();
    
    private List<Livro> livrosCache;

    public void remover() {
        this.repositorio.remover(livro);
        limparDados();
    }
    
    public void adiciona() {
        this.repositorio.adiciona(this.livro);
        limparDados();
    }

    private void limparDados() {
        this.livro = new Livro();
        this.livrosCache = null;
    }
    
    public List<Livro> getLivros() {
        if (this.livrosCache == null) {
            this.livrosCache = this.repositorio.getLivros();
        }
        return this.livrosCache;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

}
