package com.bilouro.Alunos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.bilouro.modelo.Prova;

public class ProvaActivity extends FragmentActivity{
    private Prova prova_selecionada;
    private Prova[] prova_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provas);

        restore(savedInstanceState);
        popula_provas();

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frame_esquerda, getListaProvaFragmentActivity(prova_array), ListaProvaFragmentActivity.class.getCanonicalName());
        if (isTablet()) {

            DetalhesProvaFragment detalhesProvaFragment = getDetalhesProvaFragment(prova_selecionada);
            t.replace(R.id.frame_direita, detalhesProvaFragment, DetalhesProvaFragment.class.getCanonicalName());
        }
        t.commit();
    }

    private void popula_provas() {

        if (prova_array == null) {
            prova_array = new Prova[] {
                    new Prova("16/02/2013", "Matemática", new String[]{"Quanto é 2 + 2?", "Qual a raiz de 9?", "Qual o dobro de 10?", "Fatore 45"} ),
                    new Prova("20/12/2010", "Portugues",  new String[]{"Qual o sujeito da frase?", "Conserto ou Concerto?", "O a é crazeado?", } ),
                    new Prova("26/10/2012", "Inglês", new String[]{"What's your name?", "How are you?", "Loves me loves my dog", } ),
                    new Prova("01/07/2010", "História",  new String[]{"Quem descobriu o Brasil?", "Quando?", "Por que parte do litoral?", "Como se chamou a expedição?" } ),
            };
        }

        if (this.prova_selecionada == null && prova_array != null && prova_array.length > 0) {
            this.prova_selecionada = prova_array[0];
        }

    }

    private ListaProvaFragmentActivity getListaProvaFragmentActivity(Prova[] prova_array) {
        ListaProvaFragmentActivity listaProvaFragmentActivity = new ListaProvaFragmentActivity();

        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.const_prova_lista), prova_array);
        listaProvaFragmentActivity.setArguments( bundle );
        return listaProvaFragmentActivity;
    }

    public void seleciona_prova(Prova prova_selecionada) {

        this.prova_selecionada = prova_selecionada;

        DetalhesProvaFragment detalhesProvaFragment = getDetalhesProvaFragment(prova_selecionada);
        final FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        if (isTablet()) {
            t.replace(R.id.frame_direita, detalhesProvaFragment, DetalhesProvaFragment.class.getCanonicalName());
        } else {
            t.replace(R.id.frame_esquerda, detalhesProvaFragment, DetalhesProvaFragment.class.getCanonicalName());
            t.addToBackStack(null);
        }
        t.commit();
    }

    private DetalhesProvaFragment getDetalhesProvaFragment(Prova prova_selecionada) {
        DetalhesProvaFragment detalhesProvaFragment = new DetalhesProvaFragment();

        if (prova_selecionada != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(getString(R.string.const_prova_selecionada), prova_selecionada);

            detalhesProvaFragment.setArguments( bundle );
        }

        return detalhesProvaFragment;
    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(getResources().getString(R.string.const_prova_selecionada), this.prova_selecionada);
        outState.putSerializable(getResources().getString(R.string.const_prova_lista), this.prova_array);
    }

    private void restore(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.prova_selecionada = (Prova) savedInstanceState.getSerializable(getResources().getString(R.string.const_prova_selecionada));
            this.prova_array = (Prova[]) savedInstanceState.getSerializable(getResources().getString(R.string.const_prova_lista));
        }
    }
}
