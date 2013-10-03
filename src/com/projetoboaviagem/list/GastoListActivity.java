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
import com.projetoboaviagem.dao.BoaViagemDAO;
import com.projetoboaviagem.domain.Gasto;
import com.projetoboaviagem.domain.Viagem;
import com.projetoboaviagem.util.Constantes;
import com.projetoboaviagem.util.GlobalUtil;

public class GastoListActivity extends ListActivity implements OnItemClickListener {

	private List<Map<String, Object>> gastos;
	private String dataAnterior = "";
	private String viagem_id = null;
	
	private BoaViagemDAO dao = new BoaViagemDAO(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		viagem_id = getIntent().getStringExtra(Constantes.VIAGEM_ID);
		
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
		
		List<Gasto> list = dao.listarGastos(new Viagem(Long.parseLong(viagem_id)));

		for (Gasto gasto : list) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("data", GlobalUtil.getInstance().formatarDataMedio(gasto.getData()));
			item.put("descricao", gasto.getDescricao());
			item.put("valor", GlobalUtil.getInstance().formatarValor(gasto.getValor()));
			item.put("categoria", obterTipoGasto(gasto));
			gastos.add(item);
		}
		
		return gastos;
	}

	private int obterTipoGasto(Gasto gasto) {
		if (gasto.getCategoria().equals(Constantes.ALIMENTACAO)) {
			return R.color.categoria_alimentacao;
		} else if (gasto.getCategoria().equals(Constantes.COMBUSTIVEL)) {
			return R.color.categoria_outros;
		} else if (gasto.getCategoria().equals(Constantes.TRANSPORTE)) {
			return R.color.categoria_transporte;
		} else if (gasto.getCategoria().equals(Constantes.HOSPEDAGEM)) {
			return R.color.categoria_hospedagem;
		} else if (gasto.getCategoria().equals(Constantes.OUTROS)) {
			return R.color.categoria_outros;
		}
		return R.color.categoria_outros;
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
