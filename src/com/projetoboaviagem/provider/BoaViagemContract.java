package com.projetoboaviagem.provider;

import android.net.Uri;

public final class BoaViagemContract {

    public static final class Viagem {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + "vnd.br.com.casadocodigo.boaviagem.provider/viagem";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + "vnd.br.com.casadocodigo.boaviagem.provider/viagem";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BoaViagemProvider.AUTHORITY_URI, BoaViagemProvider.VIAGEM_PATH);
        public static final String _ID = "_id";
        public static final String DESTINO = "destino";
        public static final String DATA_CHEGADA = "data_chegada";
        public static final String DATA_SAIDA = "data_saida";
        public static final String ORCAMENTO = "orcamento";
        public static final String QUANTIDADE_PESSOAS = "quantidade_pessoas";
    }

    public static final class Gasto {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + "vnd.br.com.casadocodigo.boaviagem.provider/gasto";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + "vnd.br.com.casadocodigo.boaviagem.provider/gasto";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BoaViagemProvider.AUTHORITY_URI, BoaViagemProvider.GASTO_PATH);
        public static final String _ID = "_id";
        public static final String VIAGEM_ID = "viagem_id";
        public static final String CATEGORIA = "categoria";
        public static final String DATA = "data";
        public static final String DESCRICAO = "descricao";
        public static final String LOCAL = "local";
    }

}
