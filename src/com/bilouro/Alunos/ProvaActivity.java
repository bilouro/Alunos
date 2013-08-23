package com.bilouro.Alunos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.bilouro.modelo.Prova;

public class ProvaActivity extends FragmentActivity{
    private Prova prova_selecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provas);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frame_esquerda, new ListaProvaFragmentActivity(), ListaProvaFragmentActivity.class.getCanonicalName());
        if (isTablet()) {

            DetalhesProvaFragment detalhesProvaFragment = getDetalhesProvaFragment(prova_selecionada);
            t.replace(R.id.frame_direita, detalhesProvaFragment, DetalhesProvaFragment.class.getCanonicalName());
        }
        t.commit();
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

}
