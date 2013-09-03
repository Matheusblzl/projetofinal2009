package com.projetoboaviagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

public class GastoActivity extends Activity {

	private int ano, mes, dia;
	private Button dataGasto;
	private Spinner categoria;
	
	private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gasto);
		
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
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria_gasto,
				android.R.layout.simple_spinner_item);
		
		categoria = (Spinner) findViewById(R.id.categoria);
		categoria.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.gasto, menu);
		return true;
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (R.id.data == id) {
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
			dataGasto.setText(dia + "/" + (mes + 1) + "/" + ano);
		}
	};
}
