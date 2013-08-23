package com.bilouro.Alunos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bilouro.Alunos.R;
import com.bilouro.modelo.Prova;

public class DetalhesProvaFragment extends Fragment {

    private Prova prova;
    private TextView materia;
    private TextView data;
    private ListView topicos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.provas_detalhes, null);

        if (getArguments() != null){
            prova = (Prova) getArguments().getSerializable(getResources().getString(R.string.const_prova_selecionada));

            if (prova != null) {
                busca_campos(layout);
                preenche_campos();
            }
        }

        return layout;
    }

    private void preenche_campos() {
        materia.setText(prova.getMateria());
        data.setText(prova.getData());
        topicos.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopico_list()));
    }

    private void busca_campos(View layout) {
        materia = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
        data = (TextView) layout.findViewById(R.id.detalhe_prova_data);
        topicos = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
    }

}