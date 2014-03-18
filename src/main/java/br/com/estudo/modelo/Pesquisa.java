package br.com.estudo.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pesquisa implements Serializable {

    private static final long serialVersionUID = -6785475108461296463L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Ponto ponto;
    @Enumerated(EnumType.ORDINAL)
    private StatusPosto statusPosto;
    private String fotoPosto;
    private String fotoPlacar;
    private String precos;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSincronismo;

    public Pesquisa() {
        super();
    }

    public Pesquisa(String nome,
            Usuario usuario,
            Ponto ponto,
            StatusPosto statusPosto,
            String fotoPosto,
            String fotoPlacar,
            String precos,
            Date dataCriacao,
            Date dataSincronismo) {
        super();
        this.nome = nome;
        this.usuario = usuario;
        this.ponto = ponto;
        this.statusPosto = statusPosto;
        this.fotoPosto = fotoPosto;
        this.fotoPlacar = fotoPlacar;
        this.precos = precos;
        this.dataCriacao = dataCriacao;
        this.dataSincronismo = dataSincronismo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ponto getPonto() {
        return ponto;
    }

    public void setPonto(Ponto ponto) {
        this.ponto = ponto;
    }

    public StatusPosto getStatusPosto() {
        return statusPosto;
    }

    public void setStatusPosto(StatusPosto statusPosto) {
        this.statusPosto = statusPosto;
    }

    public String getFotoPosto() {
        return fotoPosto;
    }

    public void setFotoPosto(String fotoPosto) {
        this.fotoPosto = fotoPosto;
    }

    public String getFotoPlacar() {
        return fotoPlacar;
    }

    public void setFotoPlacar(String fotoPlacar) {
        this.fotoPlacar = fotoPlacar;
    }

    public String getPrecos() {
        return precos;
    }

    public void setPrecos(String precos) {
        this.precos = precos;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataSincronismo() {
        return dataSincronismo;
    }

    public void setDataSincronismo(Date dataSincronismo) {
        this.dataSincronismo = dataSincronismo;
    }

}
