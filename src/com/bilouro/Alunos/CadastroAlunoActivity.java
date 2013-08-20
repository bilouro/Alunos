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

    private Aluno value_object;
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

        formularioHelper = new FormularioHelper(CadastroAlunoActivity.this);

        value_object = (Aluno) getIntent().getSerializableExtra(getString(R.string.EXTRA_OBJECT));
        Button btn = (Button) findViewById(R.id.ca_btn_salvar);

        //todo: levar para dentro do FormularioHelper
        if (value_object != null) {
            formularioHelper.setaAlunoNoFormulario(value_object);
            btn.setText(getString(R.string.ca_alterar_label));
        } else {
            value_object = new Aluno(null);
            formularioHelper.setaAlunoNoFormulario(value_object);
            btn.setText(getString(R.string.ca_incluir_label));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno = formularioHelper.pegaAlunoDoFormulario(value_object);

                int linhas_afetadas = 0;

                RuntimeExceptionDao<Aluno, Integer> alunoDao = getHelper().getRuntimeExceptionAlunoDao();
                if ( aluno.getId() != null ) {
                    linhas_afetadas = alunoDao.update(aluno);
                    Toast.makeText(CadastroAlunoActivity.this, "Aluno "+ aluno.getNome() +" alterado!", Toast.LENGTH_SHORT).show();
                } else {
                    linhas_afetadas = alunoDao.create(aluno);
                    Toast.makeText(CadastroAlunoActivity.this, "Aluno "+ aluno.getNome() +" incluido!", Toast.LENGTH_SHORT).show();
                }

                if (linhas_afetadas == 0 ) {
                    Toast.makeText(CadastroAlunoActivity.this, "Aluno "+ aluno.getNome() +"NÃO FOI salvo!", Toast.LENGTH_SHORT).show();
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


        final TextView tv = (TextView) findViewById(R.id.seek_progress);

        SeekBar nota = (SeekBar) findViewById(R.id.ca_seek_nota);
        nota.setMax(10);
        nota.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText("" + seekBar.getProgress());
                tv.refreshDrawableState();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ACTION_IMAGE_CAPTURE ) {
            if (resultCode == Activity.RESULT_OK){
                formularioHelper.carrega_imagem();
            } else {
                setCaminho_imagem_aluno(value_object.getFoto());
            }
        }
    }
}
