package com.projetoboaviagem.domain;

import java.io.Serializable;
import java.util.Date;

public class Gasto implements Serializable {

	private static final long serialVersionUID = 2478762742880446800L;

	private Long id;
	private Date data;
	private String categoria;
	private String descricao;
	private Double valor;
	private String local;
	private Long viagemId;

	public Gasto() {
		super();
	}

	public Gasto(Long id, Date data, String categoria, String descricao, Double valor, String local, Long viagemId) {
		super();
		this.id = id;
		this.data = data;
		this.categoria = categoria;
		this.descricao = descricao;
		this.valor = valor;
		this.local = local;
		this.viagemId = viagemId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Long getViagemId() {
		return viagemId;
	}

	public void setViagemId(Long viagemId) {
		this.viagemId = viagemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Gasto other = (Gasto) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Gasto [id=" + id + ", data=" + data + ", categoria=" + categoria + ", descricao=" + descricao + ", valor="
				+ valor + ", local=" + local + ", viagemId=" + viagemId + "]";
	}

}
