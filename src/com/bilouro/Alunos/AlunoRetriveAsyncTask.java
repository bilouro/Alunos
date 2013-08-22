package com.bilouro.Alunos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.bilouro.utils.WebClient;
import org.apache.http.HttpResponse;

/**
 * AsyncTask<String, Void, Long>
 *     String eh o parametro do -> new AlunoRetriveAsyncTask().execute( json_object_list );
 *                              -> será enviado para o doInBackground
 *     Void eh o parametro que tem haver com progresso pouco usado
 *     Long eh o parametro de retorno do doInBackground e a entrada do onPostExecute
 */
public class AlunoRetriveAsyncTask extends AsyncTask<String, Void, Boolean> {

    private Context context_chamador;

    public AlunoRetriveAsyncTask(Context context) {
        this.context_chamador = context;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        WebClient webClient = new WebClient("http://www.caelum.com.br/mobile");
        final HttpResponse response = webClient.post(params[0]);

        return (response != null && response.getStatusLine().getStatusCode() == 200);
    }

    @Override
    protected void onPostExecute(Boolean executado_com_sucesso) {
        String mensagem = "Erro de transmissão";
        if (executado_com_sucesso) {
            mensagem = "Transmitido com sucesso";
        }
        Toast.makeText(this.context_chamador, mensagem, Toast.LENGTH_LONG).show();
    }
}
