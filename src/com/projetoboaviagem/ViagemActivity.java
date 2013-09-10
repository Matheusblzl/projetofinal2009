package com.projetoboaviagem;

import java.text.ParseException;
import java.util.Date;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.projetoboaviagem.components.DatePickerFragment;
import com.projetoboaviagem.dao.DatabaseHelper;
import com.projetoboaviagem.enumeradores.TipoViagem;
import com.projetoboaviagem.list.ViagemListActivity;
import com.projetoboaviagem.util.Constantes;
import com.projetoboaviagem.util.GlobalUtil;

public class ViagemActivity extends FragmentActivity {

    private DatabaseHelper helper;
    private Button buttonDataChegada;
    private Button buttonDataSaida;
    private EditText destino;
    private RadioGroup tipoViagem;
    private EditText orcamento;
    private EditText quantidadePessoas;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);

        buttonDataChegada = (Button) findViewById(R.id.dataChegada);
        buttonDataChegada.setText(R.string.labelSelecione);

        buttonDataSaida = (Button) findViewById(R.id.dataSaida);
        buttonDataSaida.setText(R.string.labelSelecione);

        destino = (EditText) findViewById(R.id.destino);

        orcamento = (EditText) findViewById(R.id.orcamento);

        quantidadePessoas = (EditText) findViewById(R.id.quantidadePessoas);

        tipoViagem = (RadioGroup) findViewById(R.id.tipoViagem);

        helper = new DatabaseHelper(this);

        id = getIntent().getStringExtra(Constantes.VIAGEM_ID);

        if (id != null) {
            prepararEdicao();
        }

    }

    private void prepararEdicao() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT tipo_viagem, destino, data_chegada, data_saida, quantidade_pessoas, orcamento FROM viagem WHERE _id = ?",
                new String[]{ id });
        cursor.moveToFirst();

        if (cursor.getInt(0) == TipoViagem.VIAGEM_LAZER.ordinal()) {
            tipoViagem.check(R.id.lazer);
        } else {
            tipoViagem.check(R.id.negocios);
        }

        destino.setText(cursor.getString(1));
        buttonDataChegada.setText(GlobalUtil.getInstance().formatarDataMedio(new Date(cursor.getLong(2))));
        buttonDataSaida.setText(GlobalUtil.getInstance().formatarDataMedio(new Date(cursor.getLong(3))));
        quantidadePessoas.setText(cursor.getString(4));
        orcamento.setText(cursor.getString(5));

        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.viagem, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo_gasto:
                startActivity(new Intent(this, GastoActivity.class));
                return true;
            case R.id.remover:
                return true;
            default:
                return super.onMenuItemSelected(featureId, item);
        }
    }

    public void selecionarData(View view) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        newFragment.setComponentId(view.getId());
    }

    // BANCO DE DADOS
    public void salvarViagem(View view) throws ParseException {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("destino", destino.getText().toString());
        values.put("data_chegada", GlobalUtil.getInstance().parseStringInDateMedio(buttonDataChegada.getText().toString()).toString());
        values.put("data_saida", GlobalUtil.getInstance().parseStringInDateMedio(buttonDataSaida.getText().toString()).toString());
        values.put("orcamento", orcamento.getText().toString());
        values.put("quantidade_pessoas", quantidadePessoas.getText().toString());

        int tipo = tipoViagem.getCheckedRadioButtonId();

        if (tipo == R.id.lazer) {
            values.put("tipo_viagem", TipoViagem.VIAGEM_LAZER.ordinal());
        } else {
            values.put("tipo_viagem", TipoViagem.VIAGEM_NEGOCIOS.ordinal());
        }

        long resultado = -1L;
        
        if (id == null) {
            resultado = db.insert("viagem", null, values);
        } else {
            resultado = db.update("viagem", values, "_id = ?", new String[]{ id });
        }

        if (resultado != -1) {
            Toast.makeText(this, getString(R.string.registro_salvo), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.erro_salvar), Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(this, ViagemListActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }

}
