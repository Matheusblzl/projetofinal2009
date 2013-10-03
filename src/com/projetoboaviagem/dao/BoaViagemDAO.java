package com.projetoboaviagem.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projetoboaviagem.dao.DatabaseHelper.Table_Viagem;
import com.projetoboaviagem.domain.Gasto;
import com.projetoboaviagem.domain.Viagem;
import com.projetoboaviagem.util.GlobalUtil;

public class BoaViagemDAO {

	private DatabaseHelper helper;

	private SQLiteDatabase db;

	public BoaViagemDAO(Context context) {
		helper = new DatabaseHelper(context);
	}

	private SQLiteDatabase getDb() {
		if (db == null) {
			db = helper.getWritableDatabase();
		}
		return db;
	}

	public void close() {
		helper.close();
		db = null;
	}

	public Cursor obterCursorListaDeViagens() {
        Cursor cursor = getDb().query(DatabaseHelper.Table_Viagem.TABELA, DatabaseHelper.Table_Viagem.COLUNAS, null, null, null, null, Table_Viagem.DESTINO);
        return cursor;
	}
	
	public List<Viagem> listarViagens() {
		Cursor cursor = obterCursorListaDeViagens();
		List<Viagem> viagens = new ArrayList<Viagem>();
		while (cursor.moveToNext()) {
			Viagem viagem = criarViagem(cursor);
			viagens.add(viagem);
		}
		cursor.close();
		return viagens;
	}

	public Viagem buscarViagemPorId(Long id) {
		Cursor cursor = getDb().query(DatabaseHelper.Table_Viagem.TABELA, DatabaseHelper.Table_Viagem.COLUNAS,
				DatabaseHelper.Table_Viagem._ID + " = ?", new String[]{ id.toString() }, null, null, null);
		if (cursor.moveToNext()) {
			Viagem viagem = criarViagem(cursor);
			cursor.close();
			return viagem;
		}

		return null;
	}

	public long inserir(Viagem viagem) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Table_Viagem.DESTINO, viagem.getDestino());
		values.put(DatabaseHelper.Table_Viagem.TIPO_VIAGEM, viagem.getTipoViagem());
		values.put(DatabaseHelper.Table_Viagem.DATA_CHEGADA, viagem.getDataChegada().getTime());
		values.put(DatabaseHelper.Table_Viagem.DATA_SAIDA, viagem.getDataSaida().getTime());
		values.put(DatabaseHelper.Table_Viagem.ORCAMENTO, viagem.getOrcamento());
		values.put(DatabaseHelper.Table_Viagem.QUANTIDADE_PESSOAS, viagem.getQuantidadePessoas());

		return getDb().insert(DatabaseHelper.Table_Viagem.TABELA, null, values);
	}

	public long atualizar(Viagem viagem) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Table_Viagem.DESTINO, viagem.getDestino());
		values.put(DatabaseHelper.Table_Viagem.TIPO_VIAGEM, viagem.getTipoViagem());
		values.put(DatabaseHelper.Table_Viagem.DATA_CHEGADA, viagem.getDataChegada().getTime());
		values.put(DatabaseHelper.Table_Viagem.DATA_SAIDA, viagem.getDataSaida().getTime());
		values.put(DatabaseHelper.Table_Viagem.ORCAMENTO, viagem.getOrcamento());
		values.put(DatabaseHelper.Table_Viagem.QUANTIDADE_PESSOAS, viagem.getQuantidadePessoas());

		return getDb().update(DatabaseHelper.Table_Viagem.TABELA, values, DatabaseHelper.Table_Viagem._ID + " = ?",
				new String[]{ viagem.getId().toString() });
	}

	public boolean removerViagem(Long id) {
		String whereClause = DatabaseHelper.Table_Viagem._ID + " = ?";
		String[] whereArgs = new String[]{ id.toString() };
		int removidos = getDb().delete(DatabaseHelper.Table_Viagem.TABELA, whereClause, whereArgs);
		return removidos > 0;
	}

	public List<Gasto> listarGastos(Viagem viagem) {
		String selection = DatabaseHelper.Table_Gasto.VIAGEM_ID + " = ?";
		String[] selectionArgs = new String[]{ viagem.getId().toString() };

		Cursor cursor = getDb().query(DatabaseHelper.Table_Gasto.TABELA, DatabaseHelper.Table_Gasto.COLUNAS, selection, selectionArgs, null,
				null, null);
		List<Gasto> gastos = new ArrayList<Gasto>();
		while (cursor.moveToNext()) {
			Gasto gasto = criarGasto(cursor);
			gastos.add(gasto);
		}
		cursor.close();
		return gastos;
	}

	public Gasto buscarGastoPorId(Integer id) {
		Cursor cursor = getDb().query(DatabaseHelper.Table_Gasto.TABELA, DatabaseHelper.Table_Gasto.COLUNAS,
				DatabaseHelper.Table_Gasto._ID + " = ?", new String[]{ id.toString() }, null, null, null);
		if (cursor.moveToNext()) {
			Gasto gasto = criarGasto(cursor);
			cursor.close();
			return gasto;
		}
		return null;
	}

	public long inserir(Gasto gasto) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Table_Gasto.CATEGORIA, gasto.getCategoria());
		values.put(DatabaseHelper.Table_Gasto.DATA, gasto.getData().getTime());
		values.put(DatabaseHelper.Table_Gasto.DESCRICAO, gasto.getDescricao());
		values.put(DatabaseHelper.Table_Gasto.LOCAL, gasto.getLocal());
		values.put(DatabaseHelper.Table_Gasto.VALOR, gasto.getValor());
		values.put(DatabaseHelper.Table_Gasto.VIAGEM_ID, gasto.getViagemId());
		
		return getDb().insert(DatabaseHelper.Table_Gasto.TABELA, null, values);
	}

	public long atualizar(Gasto gasto) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Table_Gasto.CATEGORIA, gasto.getCategoria());
		values.put(DatabaseHelper.Table_Gasto.DATA, gasto.getData().getTime());
		values.put(DatabaseHelper.Table_Gasto.DESCRICAO, gasto.getDescricao());
		values.put(DatabaseHelper.Table_Gasto.LOCAL, gasto.getLocal());
		values.put(DatabaseHelper.Table_Gasto.VALOR, gasto.getValor());
		values.put(DatabaseHelper.Table_Gasto.VIAGEM_ID, gasto.getViagemId());

		return getDb().update(DatabaseHelper.Table_Gasto.TABELA, values, DatabaseHelper.Table_Gasto._ID + " = ?",
				new String[]{ gasto.getId().toString() });
	}

	public boolean removerGasto(Long id) {
		String whereClause = DatabaseHelper.Table_Gasto._ID + " = ?";
		String[] whereArgs = new String[]{ id.toString() };
		int removidos = getDb().delete(DatabaseHelper.Table_Gasto.TABELA, whereClause, whereArgs);
		return removidos > 0;
	}

	public boolean removerGastosViagem(Long id) {
		String whereClause = DatabaseHelper.Table_Gasto.VIAGEM_ID + " = ?";
		String[] whereArgs = new String[]{ id.toString() };
		int removidos = getDb().delete(DatabaseHelper.Table_Gasto.TABELA, whereClause, whereArgs);
		return removidos > 0;
	}

	public double calcularTotalGasto(Viagem viagem) {
		Cursor cursor = getDb().rawQuery(
				"SELECT SUM(" + DatabaseHelper.Table_Gasto.VALOR + ") FROM " + DatabaseHelper.Table_Gasto.TABELA + " WHERE "
						+ DatabaseHelper.Table_Gasto.VIAGEM_ID + " = ?", new String[]{ viagem.getId().toString() });
		cursor.moveToFirst();
		double total = cursor.getDouble(0);
		cursor.close();
		return total;
	}

	private Viagem criarViagem(Cursor cursor) {
		Viagem viagem = new Viagem(

		cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Table_Viagem._ID)),
		cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Viagem.DESTINO)),
		cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Table_Viagem.TIPO_VIAGEM)),
		new Date(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Table_Viagem.DATA_CHEGADA))),
		new Date(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Table_Viagem.DATA_SAIDA))),
		cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Table_Viagem.ORCAMENTO)),
		cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Table_Viagem.QUANTIDADE_PESSOAS)));
		
		return viagem;
	}

	public Viagem criarViagem(Long id, String destino, String dataChegada, String dataSaida, String orcamento,
			Integer quantidadePessoas, int tipoViagem) throws ParseException {
		Viagem viagem = new Viagem();
		viagem.setId(id != null ? id : null);
		viagem.setDestino(destino);
		viagem.setDataChegada(GlobalUtil.getInstance().parseStringInDateMedio(dataChegada));
		viagem.setDataSaida(GlobalUtil.getInstance().parseStringInDateMedio(dataSaida));
		viagem.setOrcamento(Double.valueOf(orcamento));
		viagem.setQuantidadePessoas(quantidadePessoas);
		viagem.setTipoViagem(tipoViagem);

		return viagem;
	}
	
	private Gasto criarGasto(Cursor cursor) {
		Gasto gasto = new Gasto(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Table_Gasto._ID)),

		new Date(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Table_Gasto.DATA))),
		cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Gasto.CATEGORIA)),
		cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Gasto.DESCRICAO)),
		cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Table_Gasto.VALOR)),
		cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Gasto.LOCAL)),
		cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Table_Gasto.VIAGEM_ID)));
		
		return gasto;
	}
	
	public Gasto criarGasto(Long id, Long idViagem, String categoria, Date data, String descricao, String local, Double valor) {
	    Gasto gasto = new Gasto();
	    
	    gasto.setCategoria(categoria);
	    gasto.setData(data);
	    gasto.setDescricao(descricao);
	    gasto.setId(id != null ? id : null);
	    gasto.setLocal(local);
	    gasto.setValor(valor);
	    gasto.setViagemId(idViagem);
	    
	    return gasto;
	}

}
