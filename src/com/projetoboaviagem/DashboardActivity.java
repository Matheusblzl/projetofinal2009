package com.projetoboaviagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.projetoboaviagem.fragments.AnotacaoActivity;
import com.projetoboaviagem.list.ViagemListActivity;

public class DashboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_teste_frag_01:
				startActivity(new Intent(this, AnotacaoActivity.class));
				break;
			case R.id.action_settings_dash_board:
				finish();
				break;
			default:
				break;
		}
		return true;
	}

	public void selecionarOpcao(View view) {
		switch (view.getId()) {
			case R.id.nova_viagem:
				startActivity(new Intent(this, ViagemActivity.class));
				overridePendingTransition(R.anim.zoom_enter, R.anim.fade);
				break;
			case R.id.novo_gasto:
				startActivity(new Intent(this, GastoActivity.class));
				overridePendingTransition(R.anim.zoom_enter, R.anim.fade);
				break;
			case R.id.minhas_viagens:
				startActivity(new Intent(this, ViagemListActivity.class));
				overridePendingTransition(R.anim.zoom_enter, R.anim.fade);
				break;
			case R.id.configuracoes:
				startActivity(new Intent(this, ConfiguracoesActivity.class));
				overridePendingTransition(R.anim.zoom_enter, R.anim.fade);
				break;
		}
	}

}
