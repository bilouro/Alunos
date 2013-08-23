package com.bilouro.Alunos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bilouro.modelo.Prova;

public class ListaProvaFragmentActivity extends Fragment{

    private Prova[] prova_array;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.provas_list_fragment, container, false);

        if (getArguments() != null){
            prova_array = (Prova[]) getArguments().getSerializable(getResources().getString(R.string.const_prova_lista));

            if ( prova_array == null ) prova_array = new Prova[0];
        }

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, prova_array);

        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                final Prova prova_selecionada = (Prova) adapter.getItemAtPosition(position);

                ProvaActivity provaActivity = (ProvaActivity) getActivity();
                provaActivity.seleciona_prova(prova_selecionada);
            }
        });

        return view;
    }

}
