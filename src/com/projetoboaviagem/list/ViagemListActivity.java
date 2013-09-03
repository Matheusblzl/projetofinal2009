package com.projetoboaviagem.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import com.projetoboaviagem.GastoActivity;
import com.projetoboaviagem.R;
import com.projetoboaviagem.ViagemActivity;

public class ViagemListActivity extends ListActivity implements OnItemClickListener, OnClickListener, ViewBinder {

	private List<Map<String, Object>> viagens;

	private AlertDialog alertDialog;

	private AlertDialog dialogConfirmacao;

	private int viagemSelecionada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] de = { "imagem", "destino", "data", "total", "barraProgresso" };

		int[] para = { R.id.tipoViagem, R.id.destino, R.id.data, R.id.valor, R.id.barraProgresso };

		SimpleAdapter adapter = new SimpleAdapter(this, listarViagens(), R.layout.lista_viagem, de, para);

		adapter.setViewBinder(this);
		
		setListAdapter(adapter);
		
		getListView().setOnItemClickListener(this);

		this.alertDialog = criaAlertDialog();
		this.dialogConfirmacao = criaDialogConfirmacao();
	}

	private List<Map<String, Object>> listarViagens() {
		viagens = new ArrayList<Map<String, Object>>();

		Map<String, Object> item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.unemployedicon48x48);
		item.put("destino", "São Paulo");
		item.put("data", "02/02/2012 a 04/02/2012");
		item.put("total", "Gasto total R$ 314,98");
		item.put("barraProgresso", new Double[]{ 500.0, 450.0, 314.98 });
		viagens.add(item);

		item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.beachchairicon);
		item.put("destino", "Maceió");
		item.put("data", "14/05/2012 a 22/05/2012");
		item.put("total", "Gasto total R$ 234,67");
		item.put("barraProgresso", new Double[]{ 500.0, 300.0, 234.67 });
		viagens.add(item);

		item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.beachchairicon);
		item.put("destino", "Rio de Janeiro");
		item.put("data", "16/06/2012 a 22/06/2012");
		item.put("total", "Gasto total R$ 490,67");
		item.put("barraProgresso", new Double[]{ 500.0, 495.0, 490.67 });
		viagens.add(item);

		item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.unemployedicon48x48);
		item.put("destino", "Rio de Janeiro");
		item.put("data", "17/07/2012 a 27/07/2012");
		item.put("total", "Gasto total R$ 150,67");
		item.put("barraProgresso", new Double[]{ 500.0, 450.0, 150.67 });
		viagens.add(item);

		item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.unemployedicon48x48);
		item.put("destino", "Bahia");
		item.put("data", "18/10/2012 a 24/10/2012");
		item.put("total", "Gasto total R$ 347,67");
		item.put("barraProgresso", new Double[]{ 500.0, 450.0, 347.67 });
		viagens.add(item);

		return viagens;
	}

	@Override
	public boolean setViewValue(View view, Object data, String textRepresentation) {
		if (view.getId() == R.id.barraProgresso) {
			Double valores[] = (Double[]) data;
			ProgressBar progressBar = (ProgressBar) view;
			progressBar.setMax(valores[0].intValue());
			progressBar.setSecondaryProgress(valores[1].intValue());
			progressBar.setProgress(valores[2].intValue());
			return true;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// Map<String, Object> map = viagens.get(position);
		// String destino = (String) map.get("destino");
		// String mensagem = "Viagem selecionada: " + destino;
		// Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
		// startActivity(new Intent(this, GastoListActivity.class));

		this.viagemSelecionada = position;
		alertDialog.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int item) {
		switch (item) {
			case 0:
				startActivity(new Intent(this, ViagemActivity.class));
				break;
			case 1:
				startActivity(new Intent(this, GastoActivity.class));
				break;
			case 2:
				startActivity(new Intent(this, GastoListActivity.class));
				break;
			case 3:
				dialogConfirmacao.show();
				break;
			case DialogInterface.BUTTON_POSITIVE:
				viagens.remove(viagemSelecionada);
				getListView().invalidateViews();
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				dialogConfirmacao.dismiss();
				break;
		}
	}

	private AlertDialog criaAlertDialog() {
		final CharSequence[] items = { getString(R.string.alert_editar), getString(R.string.alert_NovoGasto),
				getString(R.string.alert_gastos_realizados), getString(R.string.alert_remover) };

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(R.string.alert_opcoes);
		builder.setItems(items, this);

		return builder.create();
	}

	private AlertDialog criaDialogConfirmacao() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(R.string.confirmacao_exclusao_viagem);
		builder.setPositiveButton(getString(R.string.sim), this);
		builder.setNegativeButton(getString(R.string.nao), this);

		return builder.create();
	}

}
