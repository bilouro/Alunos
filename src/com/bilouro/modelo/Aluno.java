package com.bilouro.modelo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.json.JSONException;
import org.json.JSONStringer;

import java.io.Serializable;
import java.util.List;

@DatabaseTable(tableName = "aluno")
public class Aluno implements Serializable{

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String telefone;
    @DatabaseField
    private double nota;
    @DatabaseField
    private String foto;

    Aluno() {
        // needed by ormlite
    }
    public Aluno(Long id) {
        this.id = id;
    }

    public Aluno(Long id, String nome, String telefone, double nota, String foto) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.nota = nota;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    private void appendJsonStringer(JSONStringer jsonStringer) {
        try {

            if ( jsonStringer == null ) jsonStringer = new JSONStringer() ;

            jsonStringer
                .object()
                .key("id").value(this.getId())
                .key("nome").value(this.getNome())
                .key("foto").value(this.getFoto())
                .key("nota").value(this.getNota())
                .key("telefone").value(this.getTelefone())
                .endObject();

        } catch (JSONException e) {

        }
    }

    public static String to_json(List<Aluno> aluno_List) {
        JSONStringer jsonStringer = null;
        try {
            jsonStringer = new JSONStringer();

            jsonStringer.object().key("list").array().
                         object().key("aluno").array();
            
            for ( Aluno aluno: aluno_List ) {
                aluno.appendJsonStringer(jsonStringer);
            }

            return jsonStringer.endArray().endObject().
                    endArray().endObject().toString();

        } catch (JSONException e) {
            return "";
        }


    }
}