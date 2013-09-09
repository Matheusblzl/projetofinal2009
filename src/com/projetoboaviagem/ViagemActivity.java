package com.projetoboaviagem;

import java.text.ParseException;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.projetoboaviagem.dao.DatabaseHelper;
import com.projetoboaviagem.util.Constantes;
import com.projetoboaviagem.util.GlobalUtil;

public class ViagemActivity extends Activity {

    private DatabaseHelper helper;
    private int ano, mes, dia;
    private Button buttonDataChegada;
    private Button buttonDataSaida;
    private EditText destino;
    private RadioGroup tipoViagem;
    private EditText orcamento;
    private EditText quantidadePessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);

        Calendar calendar = Calendar.getInstance();

        buttonDataChegada = (Button) findViewById(R.id.dataChegada);
        buttonDataChegada.setText(GlobalUtil.getInstance().formatarDataMedio(calendar.getTime()));

        buttonDataSaida = (Button) findViewById(R.id.dataSaida);
        buttonDataSaida.setText(GlobalUtil.getInstance().formatarDataMedio(calendar.getTime()));

        destino = (EditText) findViewById(R.id.destino);

        orcamento = (EditText) findViewById(R.id.orcamento);

        quantidadePessoas = (EditText) findViewById(R.id.quantidadePessoas);

        tipoViagem = (RadioGroup) findViewById(R.id.tipoViagem);

        helper = new DatabaseHelper(this);
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
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (R.id.dataChegada == id) {
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        if (R.id.dataSaida == id) {
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    private OnDateSetListener listener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = GlobalUtil.getInstance().converterEmCalendar(dayOfMonth, monthOfYear, year);
            buttonDataChegada.setText(GlobalUtil.getInstance().formatarDataMedio(calendar.getTime()));
        }
    };

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
            values.put("tipo_viagem", Constantes.VIAGEM_LAZER);
        } else {
            values.put("tipo_viagem", Constantes.VIAGEM_NEGOCIOS);
        }

        long resultado = db.insert("viagem", null, values);
        if (resultado != -1) {
            Toast.makeText(this, getString(R.string.registro_salvo), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.erro_salvar), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }

}
