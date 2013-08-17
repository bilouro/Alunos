package com.bilouro.Alunos.helper;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bilouro.Alunos.CadastroAlunoActivity;
import com.bilouro.Alunos.R;
import com.bilouro.modelo.Aluno;

public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private SeekBar nota;
    private ImageView botaoImagem;
    private final TextView tv_progress;

    public FormularioHelper(CadastroAlunoActivity activity) {
        nome = (EditText) activity.findViewById(R.id.ca_nome);
        telefone = (EditText) activity.findViewById(R.id.ca_telefone);
        nota = (SeekBar) activity.findViewById(R.id.ca_seek_nota);
        botaoImagem = (ImageView) activity.findViewById(R.id.ca_img);
        tv_progress = (TextView) activity.findViewById(R.id.seek_progress);
    }

    public void setaAlunoNoFormulario(Aluno aluno){
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        nota.setProgress((int) aluno.getNota());
        tv_progress.setText("" + (int) aluno.getNota());
    }
    public Aluno pegaAlunoDoFormulario(Aluno _aluno) {
        _aluno.setNome(nome.getEditableText().toString());
        _aluno.setTelefone(telefone.getEditableText().toString());
        _aluno.setNota( nota.getProgress() );
        return _aluno;
    }

}