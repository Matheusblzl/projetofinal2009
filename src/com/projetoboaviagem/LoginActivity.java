package com.projetoboaviagem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText usuario;
	private EditText senha;
	private CheckBox manterConectado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		usuario = (EditText) findViewById(R.id.inputUsuario);
		senha = (EditText) findViewById(R.id.inputSenha);
		manterConectado = (CheckBox) findViewById(R.id.manterConectado);

		SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);

		boolean conectado = preferencias.getBoolean(ConfiguracoesActivity.MANTER_CONECTADO, false);

		if (conectado) {
			startActivity(new Intent(this, DashboardActivity.class));
			finish();
		}
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		finish();
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void entrarOnClick(View v) {
		String usuarioInfo = usuario.getText().toString().toLowerCase().trim();
		String senhaInfo = senha.getText().toString().trim();

		if ("".equals(usuarioInfo) && "".equals(senhaInfo)) {
			startActivity(new Intent(this, DashboardActivity.class));
		}

		if ("daniel".equals(usuarioInfo) && "123".equals(senhaInfo)) {
			
			SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
			
			Editor editor = preferencias.edit();
			editor.putBoolean(ConfiguracoesActivity.MANTER_CONECTADO, manterConectado.isChecked());
			editor.commit();

			startActivity(new Intent(this, DashboardActivity.class));
		} else {
			String mensagemErro = getString(R.string.erro_autenticacao);
			Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
