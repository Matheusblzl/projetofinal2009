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
import javax.persistence.Transient;
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
    private Float longitude;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;
    @Enumerated(EnumType.ORDINAL)
    private StatusPesquisa statusPesquisa;
    private String mensagemApresentacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Transient
    private Boolean statusEnvio = Boolean.TRUE;
    @Transient
    private String msgEnvio;

    public Ponto() {
        super();
    }

    public Ponto(Usuario usuario,
            String nome,
            Float latitude,
            Float longitude,
            Endereco endereco,
            StatusPesquisa statusPesquisa,
            String mensagemApresentacao,
            Date dataCriacao,
            Date dataAtualizacao) {
        super();
        this.usuario = usuario;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longtude) {
        this.longitude = longtude;
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

    public Boolean getStatusEnvio() {
        return statusEnvio;
    }

    public void setStatusEnvio(Boolean statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

    public String getMsgEnvio() {
        return msgEnvio;
    }

    public void setMsgEnvio(String msgEnvio) {
        this.msgEnvio = msgEnvio;
    }

}
