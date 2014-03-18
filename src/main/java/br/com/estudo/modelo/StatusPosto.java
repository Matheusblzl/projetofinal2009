package br.com.estudo.modelo;

public enum StatusPosto {
    POSTOABERTO(0, "Posto Aberto"),
    POSTOFECHADO(1, "Posto Fechado"),
    END_NAO_ENCONTRADO(2, "Endereço Não Encontrado"),
    OUTRO_EST_END_INFORM(3, "Outro Estabelecimento no Endereço Informado");

    private int codigo;
    private String descricao;

    private StatusPosto(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
