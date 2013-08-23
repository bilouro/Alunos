package com.bilouro.Alunos;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bilouro.modelo.Aluno;
import com.bilouro.modelo.Prova;

import java.util.Arrays;

public class ListaProvaFragmentActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.provas_list_fragment, container, false);

        Prova[] prova_array = new Prova[] {
                new Prova("16/02/2013", "Matemática", new String[]{"Quanto é 2 + 2?", "Qual a raiz de 9?", "Qual o dobro de 10?", "Fatore 45"} ),
                new Prova("20/12/2010", "Portugues",  new String[]{"Qual o sujeito da frase?", "Conserto ou Concerto?", "O a é crazeado?", } ),
                new Prova("26/10/2012", "Inglês", new String[]{"What's your name?", "How are you?", "Loves me loves my dog", } ),
                new Prova("01/07/2010", "História",  new String[]{"Quem descobriu o Brasil?", "Quando?", "Por que parte do litoral?", "Como se chamou a expedição?" } ),
        };
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
