package com.projetoboaviagem.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "BoaViagem";
    private static int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    public static class Table_Viagem {
        public static final String TABELA = "viagem";
        public static final String _ID = "_id";
        public static final String DESTINO = "destino";
        public static final String DATA_CHEGADA = "data_chegada";
        public static final String DATA_SAIDA = "data_saida";
        public static final String ORCAMENTO = "orcamento";
        public static final String QUANTIDADE_PESSOAS = "quantidade_pessoas";
        public static final String TIPO_VIAGEM = "tipo_viagem";
        public static final String[] COLUNAS = new String[]{ _ID, DESTINO, DATA_CHEGADA, DATA_SAIDA, TIPO_VIAGEM, ORCAMENTO, QUANTIDADE_PESSOAS };
    }

    public static class Table_Gasto {
        public static final String TABELA = "gasto";
        public static final String _ID = "_id";
        public static final String VIAGEM_ID = "viagem_id";
        public static final String CATEGORIA = "categoria";
        public static final String DATA = "data";
        public static final String DESCRICAO = "descricao";
        public static final String VALOR = "valor";
        public static final String LOCAL = "local";
        public static final String[] COLUNAS = new String[]{ _ID, VIAGEM_ID, CATEGORIA, DATA, DESCRICAO, VALOR, LOCAL };
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE viagem (_id INTEGER PRIMARY KEY,");
        builder.append(" destino TEXT, tipo_viagem INTEGER, data_chegada DATE,");
        builder.append(" data_saida DATE, orcamento DOUBLE,");
        builder.append(" quantidade_pessoas INTEGER);");

        db.execSQL(builder.toString());

        builder = new StringBuilder();
        builder.append("CREATE TABLE gasto (_id INTEGER PRIMARY KEY,");
        builder.append(" categoria TEXT, data DATE, valor DOUBLE,");
        builder.append(" descricao TEXT, local TEXT, viagem_id INTEGER,");
        builder.append(" FOREIGN KEY(viagem_id) REFERENCES viagem(_id));");

        db.execSQL(builder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("ALTER TABLE gasto ADD COLUMN pessoa TEXT");
    }

}
