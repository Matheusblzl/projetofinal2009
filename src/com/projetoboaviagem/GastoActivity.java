package com.projetoboaviagem;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.projetoboaviagem.dao.BoaViagemDAO;
import com.projetoboaviagem.dao.DatabaseHelper;
import com.projetoboaviagem.domain.Gasto;
import com.projetoboaviagem.domain.Viagem;
import com.projetoboaviagem.util.Constantes;
import com.projetoboaviagem.util.GlobalUtil;

public class GastoActivity extends Activity {

	private static final int DIALOG_YES_NO_MESSAGE = 1;
	private static final int DIALOG_SINGLE_CHOICE = 5;

	private String id = null;
	private Viagem viagem = null;

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

		if (!viagens.moveToNext()) {
			showDialog(DIALOG_YES_NO_MESSAGE);
			return;
		}

		destino = (TextView) findViewById(R.id.destino);

		if (getIntent().getStringExtra(Constantes.VIAGEM_ID) != null) {
			this.viagem = dao.buscarViagemPorId(Long.parseLong(getIntent().getStringExtra(Constantes.VIAGEM_ID)));
			destino.setText(this.viagem.getDestino());
		}

		Calendar calendar = Calendar.getInstance();

		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		dataGasto = (Button) findViewById(R.id.data);

		dataGasto.setText(GlobalUtil.getInstance().formatarDataMedio(calendar.getTime()));

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
				return new AlertDialog.Builder(GastoActivity.this, AlertDialog.THEME_HOLO_LIGHT)
						.setIconAttribute(android.R.attr.alertDialogIcon)
						.setTitle(R.string.alert_dialog_single_choice)
						.setSingleChoiceItems(viagens, 0, DatabaseHelper.Table_Viagem.DESTINO,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int whichButton) {
										/*
										 * User clicked on a radio button do
										 * some stuff
										 */
										viagemSelecionada = whichButton;
									}
								}).setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked Yes so do some stuff */
								viagem = obterViagemSelecionada(viagemSelecionada == -1 ? 0 : viagemSelecionada);
								persistir();
							}
						}).setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked No so do some stuff */
							}
						})
						.setCancelable(Boolean.FALSE)
						.create();
			case DIALOG_YES_NO_MESSAGE:
				return new AlertDialog.Builder(GastoActivity.this, AlertDialog.THEME_HOLO_LIGHT)
				        .setIconAttribute(android.R.attr.alertDialogIcon)
						.setTitle(R.string.dialog_title_text)
						.setMessage(R.string.custom_dialog_activity_text)
						.setPositiveButton(R.string.dialog_criar_viagem, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked OK so do some stuff */
								telaCriarViagem();
								finish();
							}
						}).setNegativeButton(R.string.dialog_cancelar, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked Cancel so do some stuff */
								finish();
							}
						})
						.setCancelable(Boolean.FALSE)
						.create();
			default:
				return null;
		}
	}

	private void telaCriarViagem() {
		Intent intent = new Intent(this, ViagemActivity.class);
		startActivity(intent);
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

	private Viagem obterViagemSelecionada(int index) {
		List<Viagem> list = dao.listarViagens();
		return list.get(index);
	}

	public void registrarGasto(View view) {
		if (destino.getText().toString().isEmpty()) {
			showDialog(DIALOG_SINGLE_CHOICE);
		} else {
			persistir();
		}
	}

	private void persistir() {
		Gasto gasto = null;
		try {
			gasto = dao.criarGasto(id != null ? Long.parseLong(id) : null,
					viagem.getId(),
					categoria.getSelectedItem().toString(), GlobalUtil.getInstance().parseStringInDateMedio(dataGasto.getText().toString()),
					descricao.getText().toString(),
					local.getText().toString(),
					Double.parseDouble(valor.getText().toString()));
		} catch (NumberFormatException e1) {
			Toast.makeText(this, getString(R.string.erro_formato_numero), Toast.LENGTH_SHORT).show();
			finish();
			return;
		} catch (ParseException e1) {
			Toast.makeText(this, getString(R.string.erro_parse_date), Toast.LENGTH_SHORT).show();
			finish();
			return;
		}

		try {
			if (id == null) {
				dao.inserir(gasto);
			} else {
				dao.atualizar(gasto);
			}
			Toast.makeText(this, getString(R.string.registro_salvo), Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, getString(R.string.erro_salvar), Toast.LENGTH_SHORT).show();
		} finally {
			dao.close();
		}

		finish();
	}

	@Override
	protected void onDestroy() {
		dao.close();
		super.onDestroy();
	}

}
