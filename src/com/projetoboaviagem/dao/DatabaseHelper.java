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
//        db.execSQL("ALTER TABLE gasto ADD COLUMN pessoa TEXT");
    }

}
