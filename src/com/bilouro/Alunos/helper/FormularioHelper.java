package com.bilouro.Alunos.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.*;
import com.bilouro.Alunos.CadastroAlunoActivity;
import com.bilouro.Alunos.R;
import com.bilouro.modelo.Aluno;
import com.bilouro.utils.Utils;

public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private SeekBar nota;
    private ImageView imageView;
    private final TextView tv_progress;
    private CadastroAlunoActivity activity;
    private Aluno aluno_selecionado;
    private final Button btn;
    private final TextView tv_seek;

    public FormularioHelper(CadastroAlunoActivity activity, Aluno aluno_selecionado) {
        this.aluno_selecionado = aluno_selecionado;
        nome = (EditText) activity.findViewById(R.id.ca_nome);
        telefone = (EditText) activity.findViewById(R.id.ca_telefone);
        nota = (SeekBar) activity.findViewById(R.id.ca_seek_nota);
        imageView = (ImageView) activity.findViewById(R.id.ca_img);
        tv_progress = (TextView) activity.findViewById(R.id.seek_progress);
        btn = (Button) activity.findViewById(R.id.ca_btn_salvar);
        tv_seek = (TextView) activity.findViewById(R.id.seek_progress);
        this.activity = activity;
    }

    public void setaAlunoNoFormulario(Aluno aluno){
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        nota.setProgress((int) aluno.getNota());
        tv_progress.setText("" + (int) aluno.getNota());
        activity.setCaminho_imagem_aluno(aluno.getFoto());
        carrega_imagem();
    }
    public Aluno pegaAlunoDoFormulario(Aluno _aluno) {
        _aluno.setNome(nome.getEditableText().toString());
        _aluno.setTelefone(telefone.getEditableText().toString());
        _aluno.setNota(nota.getProgress() );
        _aluno.setFoto(activity.getCaminho_imagem_aluno());
        return _aluno;
    }

    public void carrega_imagem() {
        Utils.carrega_imagem_reduzida(activity.getCaminho_imagem_aluno(), imageView, R.drawable.ic_no_image);
    }


    public void configura_formulario() {
        nota.setMax(10);
        nota.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_seek.setText("" + seekBar.getProgress());
                tv_seek.refreshDrawableState();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        if (aluno_selecionado.getId() != null) {
            btn.setText(activity.getString(R.string.ca_alterar_label));
        } else {
            btn.setText(activity.getString(R.string.ca_incluir_label));
        }
        setaAlunoNoFormulario(aluno_selecionado);
    }
}

