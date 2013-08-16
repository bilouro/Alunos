package com.bilouro.Alunos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.bilouro.database.DatabaseHelper;
import com.bilouro.modelo.Aluno;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class ListaAlunosActivity extends OrmLiteBaseActivity<DatabaseHelper> {


    private Aluno aluno_selecionado;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_alunos);

        ListView lista_alunos = (ListView) findViewById(R.id.listView);

        //LISTVIEW choice mode
        //lista_alunos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //LISTVIEW  item click listener
        lista_alunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaAlunosActivity.this, CadastroAlunoActivity.class);
                intent.putExtra(getString(R.string.EXTRA_OBJECT), (Aluno) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        //LISTVIEW  item LONGclick listener
        lista_alunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //aluno_list.
                aluno_selecionado = (Aluno) parent.getItemAtPosition(position);
                return false;
            }
        });

        registerForContextMenu(lista_alunos);

    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        carrega_lista();
    }

    private void carrega_lista() {

        RuntimeExceptionDao<Aluno, Integer> alunoDao = getHelper().getRuntimeExceptionAlunoDao();
        List<Aluno> aluno_list = alunoDao.queryForAll();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>( this, android.R.layout.simple_list_item_1, aluno_list);

        ListView lista_alunos = (ListView) findViewById(R.id.listView);
        //LISTVIEW adapter
        lista_alunos.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_alunos_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_alunos_context_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idSelecionado = item.getItemId();
        if (idSelecionado == R.id.menu_novo) {
            Intent intent = new Intent(ListaAlunosActivity.this, CadastroAlunoActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.lista_alunos_context_remover_id:
                new AlertDialog.Builder(ListaAlunosActivity.this).
                        setIcon(android.R.drawable.ic_dialog_alert).
                        setTitle("Deletar").
                        setMessage("Deseja mesmo deletar").
                        setPositiveButton("Quero", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RuntimeExceptionDao<Aluno, Integer> alunoDao = getHelper().getRuntimeExceptionAlunoDao();
                                int i = alunoDao.delete(aluno_selecionado);
                                carrega_lista();
                            }
                        }).setNegativeButton("Não", null).show();
                return true;
        }
        return true;
    }

}
