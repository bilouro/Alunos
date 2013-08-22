package com.bilouro.Alunos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bilouro.modelo.Prova;

public class ListaProvaFragmentActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.provas_list_fragment, null);

        Prova[] prova_array = new Prova[] {
                new Prova("16/10/1980", "Matemática", new String[]{"Topic 1", "Topic 2", "Topic 3", } ),
                new Prova("20/12/2010", "Portugues", new String[]{"Topic 1", "Topic 2", "Topic 3", } ),
                new Prova("26/10/1980", "Matemática", new String[]{"Topic 1", "Topic 2", "Topic 3", } ),
                new Prova("01/12/2010", "Portugues", new String[]{"Topic 1", "Topic 2", "Topic 3", } ),
        };
        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, prova_array);

        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(adapter);

        return view;
    }

}
