package com.projetoboaviagem;

import java.text.ParseException;

import android.content.Intent;
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
import com.projetoboaviagem.dao.BoaViagemDAO;
import com.projetoboaviagem.domain.Viagem;
import com.projetoboaviagem.enumeradores.TipoViagem;
import com.projetoboaviagem.list.ViagemListActivity;
import com.projetoboaviagem.util.Constantes;
import com.projetoboaviagem.util.GlobalUtil;

public class ViagemActivity extends FragmentActivity {

	private Button buttonDataChegada;
	private Button buttonDataSaida;
	private EditText destino;
	private RadioGroup tipoViagem;
	private EditText orcamento;
	private EditText quantidadePessoas;

	private String id;

	private BoaViagemDAO dao = new BoaViagemDAO(this);

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

		id = getIntent().getStringExtra(Constantes.VIAGEM_ID);

		if (id != null) {
			prepararEdicao();
		}
	}

	private void prepararEdicao() {
		
		Viagem viagem = dao.buscarViagemPorId(Long.valueOf(id));

		if (viagem.getTipoViagem() == TipoViagem.VIAGEM_LAZER.ordinal()) {
			tipoViagem.check(R.id.lazer);
		} else {
			tipoViagem.check(R.id.negocios);
		}

		destino.setText(viagem.getDestino());
		buttonDataChegada.setText(GlobalUtil.getInstance().formatarDataMedio(viagem.getDataChegada()));
		buttonDataSaida.setText(GlobalUtil.getInstance().formatarDataMedio(viagem.getDataSaida()));
		quantidadePessoas.setText(viagem.getQuantidadePessoas().toString());
		orcamento.setText(viagem.getOrcamento().toString());
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

		int tipo = 0;

		if (tipoViagem.getCheckedRadioButtonId() == R.id.lazer) {
			tipo = TipoViagem.VIAGEM_LAZER.ordinal();
		} else {
			tipo = TipoViagem.VIAGEM_NEGOCIOS.ordinal();
		}

		Viagem viagem = dao.criarViagem(id != null ? Long.valueOf(id) : null,
				destino.getText().toString(),
				buttonDataChegada.getText().toString(),
				buttonDataSaida.getText().toString(),
				orcamento.getText().toString(),
				Integer.valueOf(quantidadePessoas.getText().toString()),
				tipo);

		try {
			if (id == null) {
				dao.inserir(viagem);
			} else {
				dao.atualizar(viagem);
			}
			Toast.makeText(this, getString(R.string.registro_salvo), Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, getString(R.string.erro_salvar), Toast.LENGTH_SHORT).show();
		} finally {
			dao.close();
		}

		startActivity(new Intent(this, ViagemListActivity.class));
		finish();
	}

	@Override
	protected void onDestroy() {
		dao.close();
		super.onDestroy();
	}

}
