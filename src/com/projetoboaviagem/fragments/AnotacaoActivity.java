package com.projetoboaviagem.fragments;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.projetoboaviagem.R;

public class AnotacaoActivity extends FragmentActivity {

	private boolean tablet = true;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setContentView(R.layout.anotacoes);
		View view = findViewById(R.id.fragment_unico);
		
		if (view != null) {
			tablet = false;
			
			ViagemListFragment fragment = new ViagemListFragment();
			
			fragment.setArguments(bundle);
			FragmentManager manager = getSupportFragmentManager();
			
			FragmentTransaction transaction = manager.beginTransaction();
			
			transaction.replace(R.id.fragment_unico, fragment);
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}
}
