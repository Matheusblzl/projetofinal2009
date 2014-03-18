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

@XmlRootElement
@Entity
public class Ponto implements Serializable {

    private static final long serialVersionUID = 6286561736740054180L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    private String nome;
    private Float latitude;
    private Float longtude;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;
    @Enumerated(EnumType.ORDINAL)
    private StatusPesquisa statusPesquisa;
    private String mensagemApresentacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public Ponto() {
        super();
    }

    public Ponto(Usuario usuario,
            String nome,
            Float latitude,
            Float longtude,
            Endereco endereco,
            StatusPesquisa statusPesquisa,
            String mensagemApresentacao,
            Date dataCriacao,
            Date dataAtualizacao) {
        super();
        this.usuario = usuario;
        this.nome = nome;
        this.latitude = latitude;
        this.longtude = longtude;
        this.endereco = endereco;
        this.statusPesquisa = statusPesquisa;
        this.mensagemApresentacao = mensagemApresentacao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongtude() {
        return longtude;
    }

    public void setLongtude(Float longtude) {
        this.longtude = longtude;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public StatusPesquisa getStatusPesquisa() {
        return statusPesquisa;
    }

    public void setStatusPesquisa(StatusPesquisa statusPesquisa) {
        this.statusPesquisa = statusPesquisa;
    }

    public String getMensagemApresentacao() {
        return mensagemApresentacao;
    }

    public void setMensagemApresentacao(String mensagemApresentacao) {
        this.mensagemApresentacao = mensagemApresentacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

}
