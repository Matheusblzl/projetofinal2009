package br.com.estudo.sessionBenas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.estudo.models.Livro;

@Stateless
public class LivroRepositorio {

    @PersistenceContext
    private EntityManager manager;

    public void adiciona(Livro livro) {
        this.manager.persist(livro);
    }

    public void remover(Livro livro) {
        this.manager.remove(this.manager.find(Livro.class, livro.getId()));
    }
    
    public List<Livro> getLivros() {
        TypedQuery<Livro> query = this.manager.createQuery("select x from Livro x", Livro.class);
        return query.getResultList();
    }

}
