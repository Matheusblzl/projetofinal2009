package br.com.estudo.persistenteLivroMB;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.estudo.models.Livro;
import br.com.estudo.sessionBenas.LivroRepositorio;

@ManagedBean
public class LivroMB {

    @EJB
    private LivroRepositorio repositorio;
    
    private Livro livro = new Livro();
    
    private Long codigoRemoverLivro = new Long(0);
    
    private List<Livro> livrosCache;

    public void removarLivro(Livro livro) {
        codigoRemoverLivro = livro.getId();
    }
    
    public void remover() {
        this.repositorio.remover(new Livro(codigoRemoverLivro));
        codigoRemoverLivro = null;
        this.livrosCache = null;
    }
    
    public void adiciona() {
        this.repositorio.adiciona(this.livro);
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
