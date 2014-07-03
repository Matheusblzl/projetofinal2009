package br.com.estudojavamagazine.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries(value = { @NamedQuery(name = Categoria.FIND_ALL_CATEGORIAS, query = "Select cat from Categoria cat order by cat.nome"),
        @NamedQuery(name = Categoria.FIND_CATEGORIA_BY_NAME, query = "Select cat from Categoria cat where cat.nome like :nome order by cat.nome") })
public class Categoria implements Serializable {

    private static final long serialVersionUID = 2996804009178324875L;

    public static final String FIND_ALL_CATEGORIAS = "findAllCategorias";
    public static final String FIND_CATEGORIA_BY_NAME = "findCategoriaByName";

    @Id
    @Column(name = "codigo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "descricao", nullable = true)
    private String descricao;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Produto> produtos = new TreeSet<Produto>();

    public Categoria() {
        super();
    }

    public Categoria(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Transient
    public String getDescricaoResumida() {
        if (descricao != null) {
            if (descricao.length() > 50) {
                return descricao.substring(0, 50) + " ...";
            }
        }
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Categoria other = (Categoria) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categoria [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + "]";
    }

}
