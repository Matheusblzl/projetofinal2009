package com.projetoboaviagem.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.projetoboaviagem.R;

public class GastoListActivity extends ListActivity implements OnItemClickListener {

	private List<Map<String, Object>> gastos;
	private String dataAnterior = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] de = { "data", "descricao", "valor", "categoria" };
		int[] para = { R.id.data, R.id.descricao, R.id.valor, R.id.categoria };
		SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(), R.layout.lista_gasto, de, para);
		adapter.setViewBinder(new GastoViewBinder());
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);

		// registramos aqui o novo menu de contexto
		registerForContextMenu(getListView());
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Map<String, Object> map = gastos.get(position);
		String descricao = (String) map.get("descricao");
		String mensagem = "Gasto selecionada: " + descricao;
		Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
	}

	private List<Map<String, Object>> listarGastos() {
		gastos = new ArrayList<Map<String, Object>>();

		Map<String, Object> item = new HashMap<String, Object>();
		item.put("data", "04/02/2012");
		item.put("descricao", "Diária Hotel");
		item.put("valor", "R$ 260,00");
		item.put("categoria", R.color.categoria_hospedagem);
		gastos.add(item);

		item = new HashMap<String, Object>();
		item.put("data", "12/06/2012");
		item.put("descricao", "Almoço");
		item.put("valor", "R$ 70,00");
		item.put("categoria", R.color.categoria_alimentacao);
		gastos.add(item);

		item = new HashMap<String, Object>();
		item.put("data", "14/06/2012");
		item.put("descricao", "taxi até aeroporto");
		item.put("valor", "R$ 40,00");
		item.put("categoria", R.color.categoria_transporte);
		gastos.add(item);

		item = new HashMap<String, Object>();
		item.put("data", "14/06/2012");
		item.put("descricao", "Lanche");
		item.put("valor", "R$ 12,00");
		item.put("categoria", R.color.categoria_alimentacao);
		gastos.add(item);

		return gastos;
	}

	private class GastoViewBinder implements ViewBinder {
		@Override
		public boolean setViewValue(View view, Object data, String textRepresentation) {
			if (view.getId() == R.id.data) {
				if (!dataAnterior.equals(data)) {
					TextView textView = (TextView) view;
					textView.setText(textRepresentation);
					dataAnterior = textRepresentation;
					view.setVisibility(View.VISIBLE);
				} else {
					view.setVisibility(View.GONE);
				}
				return true;
			}
			if (view.getId() == R.id.categoria) {
				Integer id = (Integer) data;
				view.setBackgroundColor(getResources().getColor(id));
				return true;
			}
			return false;
		}
	}

	// MENU CONTEXTO
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gasto_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.remover) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			gastos.remove(info.position);
			getListView().invalidateViews();
			dataAnterior = "";
			// remover do banco de dados
			return true;
		}
		return super.onContextItemSelected(item);
	}

}
