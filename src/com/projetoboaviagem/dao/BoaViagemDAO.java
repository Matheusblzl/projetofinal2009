package com.projetoboaviagem.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BoaViagemDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public BoaViagemDAO(Context context) {
        helper = new DatabaseHelper(context);
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close() {
        helper.close();
    }

}
