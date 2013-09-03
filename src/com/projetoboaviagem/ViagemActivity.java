package com.projetoboaviagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ViagemActivity extends Activity {

	private int ano, mes, dia;
	private Button dataChegada;
	private Button dataSaida;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viagem);

		Calendar calendar = Calendar.getInstance();

		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);

		dataChegada = (Button) findViewById(R.id.dataChegada);
		dataChegada.setText(dia + "/" + (mes + 1) + "/" + ano);

		dataSaida = (Button) findViewById(R.id.dataSaida);
		dataSaida.setText(dia + "/" + (mes + 1) + "/" + ano);
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
			ano = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			dataChegada.setText(dia + "/" + (mes + 1) + "/" + ano);
		}
	};

}
