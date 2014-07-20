package br.com.estudojavamagazine.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries(value = { 
		@NamedQuery(name = Produto.FIND_ALL_PRODUTO, query = "Select prod from Produto prod order by prod.nome"),
		@NamedQuery(name = Produto.FIND_PRODUTO_BY_CATEGORIA_PRODUTO, query = "Select prod from Produto prod inner join prod.categoria categ where lower(categ.nome) = lower(:nomeCat) and lower(prod.nome) = lower(:nomeProd) order by prod.nome"),
		@NamedQuery(name = Produto.FIND_PRODUTO_BY_CATEGORIA, query = "Select prod from Produto prod inner join prod.categoria categ where lower(categ.nome) = lower(:nomeCat) order by prod.nome")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 4254771097647384430L;

    public static final String FIND_ALL_PRODUTO = "find_all_produto";
    public static final String FIND_PRODUTO_BY_CATEGORIA_PRODUTO = "findProdutoByCategriaAndProdutoName";
    public static final String FIND_PRODUTO_BY_CATEGORIA = "findProdutoByCategria";

    @Id
    @Column(name = "codigo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "valor", nullable = false, precision = 2)
    private Double valor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_codigo", nullable = false)
    private Categoria categoria;

    public Produto() {
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
            if (descricao.length() > 30) {
                return descricao.substring(0, 30) + " ...";
            }
        }
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Produto other = (Produto) obj;
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
        if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", valor=" + valor + "]";
    }

}
