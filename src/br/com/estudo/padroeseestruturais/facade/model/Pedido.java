package br.com.estudo.padroeseestruturais.facade.model;

public class Pedido {

	private String produto;
	private String cliente;
	private String enderecoDeEntrega;

	public Pedido(final String produto, final String cliente, final String enderecoDeEntrega) {
		this.produto = produto;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public String getProduto() {
		return produto;
	}

	public String getCliente() {
		return cliente;
	}

	public String getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((enderecoDeEntrega == null) ? 0 : enderecoDeEntrega.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (enderecoDeEntrega == null) {
			if (other.enderecoDeEntrega != null)
				return false;
		} else if (!enderecoDeEntrega.equals(other.enderecoDeEntrega))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [produto=" + produto + ", cliente=" + cliente + ", enderecoDeEntrega=" + enderecoDeEntrega + "]";
	}

	public String getNotaFiscal() {
		return null;
	}
	
}
