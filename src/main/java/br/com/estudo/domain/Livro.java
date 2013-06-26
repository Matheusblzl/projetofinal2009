package br.com.estudo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({@NamedQuery(name = "livro.all", query = "FROM Livro l ORDER BY l.titulo")})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private int paginas;
    private String editora;
    private String isbn;
    private int avaliacao;

    public Livro() {
        super();
    }

    public Livro(Long id) {
        this.id = id;
    }

    public Livro(String titulo, String autor, int paginas, String editora, String isbn, int avaliacao) {
        super();
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.editora = editora;
        this.isbn = isbn;
        this.avaliacao = avaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        final int value = 32;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> value));
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) obj;
        if (id != other.id) {
            return false;
        }
        if (isbn == null) {
            if (other.isbn != null) {
                return false;
            }
        } else if (!isbn.equals(other.isbn)) {
            return false;
        }
        if (titulo == null) {
            if (other.titulo != null) {
                return false;
            }
        } else if (!titulo.equals(other.titulo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", isbn=" + isbn + "]";
    }

}
