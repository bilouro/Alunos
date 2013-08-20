package com.bilouro.Alunos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import com.bilouro.Alunos.helper.FormularioHelper;
import com.bilouro.database.DatabaseHelper;
import com.bilouro.modelo.Aluno;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.io.File;

public class CadastroAlunoActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    private Aluno aluno_selecionado;
    final int REQUEST_CODE_ACTION_IMAGE_CAPTURE = 0;
    private String caminho_imagem_aluno;
    private FormularioHelper formularioHelper;

    public void setCaminho_imagem_aluno(String caminho_imagem_aluno) {
        this.caminho_imagem_aluno = caminho_imagem_aluno;
    }

    public String getCaminho_imagem_aluno() {
        return caminho_imagem_aluno;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_alunos);

        aluno_selecionado = (Aluno) getIntent().getSerializableExtra(getString(R.string.EXTRA_OBJECT));
        if ( aluno_selecionado == null)aluno_selecionado = new Aluno(null);

        formularioHelper = new FormularioHelper(CadastroAlunoActivity.this, aluno_selecionado);
        formularioHelper.configura_formulario();

        Button btn = (Button) findViewById(R.id.ca_btn_salvar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno = formularioHelper.pegaAlunoDoFormulario(aluno_selecionado);

                RuntimeExceptionDao<Aluno, Integer> alunoDao = getHelper().getRuntimeExceptionAlunoDao();
                if ( aluno.getId() != null ) {
                    alunoDao.update(aluno);
                } else {
                    alunoDao.create(aluno);
                }
                finish();
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.ca_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCaminho_imagem_aluno(System.currentTimeMillis() + ".jpg");

                Uri caminho_arquivo = Uri.fromFile(new File(Environment.getExternalStorageDirectory()+ "/" +getCaminho_imagem_aluno()));
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                i.putExtra(MediaStore.EXTRA_OUTPUT, caminho_arquivo);

                startActivityForResult(i, REQUEST_CODE_ACTION_IMAGE_CAPTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ACTION_IMAGE_CAPTURE ) {
            if (resultCode == Activity.RESULT_OK){
                formularioHelper.carrega_imagem();
            } else {
                setCaminho_imagem_aluno(aluno_selecionado.getFoto());
            }
        }
    }
}
