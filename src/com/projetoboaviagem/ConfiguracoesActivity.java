package com.projetoboaviagem;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ConfiguracoesActivity extends PreferenceActivity {

	public static final String MANTER_CONECTADO = "manter_conectado";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferencias);
	}

}
