package com.projetoboaviagem.fragments;

import java.util.Arrays;
import java.util.List;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class ViagemListFragment extends ListFragment implements OnItemClickListener {

	@Override
	public void onStart() {
		super.onStart();
		List<String> viagens = Arrays.asList("Campo Grande", "São Paulo", "Miami");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, viagens);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}

}
