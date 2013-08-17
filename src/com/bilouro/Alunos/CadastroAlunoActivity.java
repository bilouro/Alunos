package com.bilouro.Alunos;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.bilouro.Alunos.helper.FormularioHelper;
import com.bilouro.database.DatabaseHelper;
import com.bilouro.modelo.Aluno;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class CadastroAlunoActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    private Aluno value_object;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_alunos);

        value_object = (Aluno) getIntent().getSerializableExtra(getString(R.string.EXTRA_OBJECT));
        Button btn = (Button) findViewById(R.id.ca_btn_salvar);

        if (value_object != null) {
              new FormularioHelper(CadastroAlunoActivity.this).setaAlunoNoFormulario(value_object);
              btn.setText(getString(R.string.ca_alterar_label));
        } else {
            value_object = new Aluno(null);
            btn.setText(getString(R.string.ca_incluir_label));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormularioHelper fh = new FormularioHelper(CadastroAlunoActivity.this);

                Aluno aluno = fh.pegaAlunoDoFormulario(value_object);

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
        imageView.setImageResource(R.drawable.ic_no_image);


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


}
