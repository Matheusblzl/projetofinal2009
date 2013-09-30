package com.projetoboaviagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.projetoboaviagem.dao.BoaViagemDAO;
import com.projetoboaviagem.dao.DatabaseHelper;

public class GastoActivity extends Activity {

    private static final int DIALOG_SINGLE_CHOICE = 5;

    private BoaViagemDAO dao = new BoaViagemDAO(this);

    private int ano, mes, dia;
    private Button dataGasto;
    private Spinner categoria;
    private TextView destino;
    private EditText valor;
    private EditText descricao;
    private EditText local;

    private int viagemSelecionada = -1;
    
    private Cursor viagens = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto);

        viagens = dao.obterCursorListaDeViagens();

        if (viagens == null) {
            return;
        }

        Calendar calendar = Calendar.getInstance();

        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        dataGasto = (Button) findViewById(R.id.data);

        dataGasto.setText(dia + "/" + (mes + 1) + "/" + ano);

        dataGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v.getId());
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria_gasto, android.R.layout.simple_spinner_item);

        categoria = (Spinner) findViewById(R.id.categoria);
        categoria.setAdapter(adapter);

        destino = (TextView) findViewById(R.id.destino);

        valor = (EditText) findViewById(R.id.valor);

        descricao = (EditText) findViewById(R.id.descricao);

        local = (EditText) findViewById(R.id.local);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gasto, menu);
        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case R.id.data:
                return new DatePickerDialog(this, listener, ano, mes, dia);
            case DIALOG_SINGLE_CHOICE:
                return new AlertDialog.Builder(GastoActivity.this).setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_single_choice)
                        .setSingleChoiceItems(viagens, 0, DatabaseHelper.Table_Viagem.DESTINO, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                /* User clicked on a radio button do some stuff */
                                viagemSelecionada = whichButton;
                            }
                        }).setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                /* User clicked Yes so do some stuff */
                                persistir();
                            }
                        }).setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                /* User clicked No so do some stuff */
                            }
                        }).create();
            default:
                return null;
        }
    }

    private OnDateSetListener listener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataGasto.setText(dia + "/" + (mes + 1) + "/" + ano);
        }
    };

    public void registrarGasto(View view) {
        if (destino.getText().toString().isEmpty()) {
            showDialog(DIALOG_SINGLE_CHOICE);
        } else {
            persistir();
        }
    }

    private void persistir() {
        ContentValues values = new ContentValues();
        values.put("valor", valor.getText().toString());
        values.put("descricao", descricao.getText().toString());
        values.put("local", local.getText().toString());
        values.put("categoria", categoria.getSelectedItem().toString());
        
//        Gasto gasto = dao.criarGasto(null, viagens., categoria, data, descricao, local, valor);
        
        // dao.inserir(gasto)

        // SQLiteDatabase db = helper.getWritableDatabase();
        // db.insert("gasto", null, values);
        
        finish();
    }
    
    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }

}
