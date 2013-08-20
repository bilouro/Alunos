package com.bilouro.Alunos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bilouro.modelo.Aluno;
import com.bilouro.utils.Utils;

import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter{

    private Activity activity;
    private List<Aluno> aluno_list;

    public ListaAlunosAdapter(Activity activity, List<Aluno> aluno_list) {
        this.activity = activity;
        this.aluno_list = aluno_list;
    }

    @Override
    public int getCount() {
        return aluno_list.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Aluno getItem(int position) {
        return aluno_list.get(position);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Aluno aluno = getItem(position);

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.cadastro_alunos_item_list, null);

        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(aluno.getNome());

        ImageView iv = (ImageView) view.findViewById(R.id.ca_img);
        Utils.carrega_imagem_reduzida(aluno.getFoto(), iv, R.drawable.ic_no_image);

        return view;
    }
}
