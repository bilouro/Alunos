package com.bilouro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prova implements Serializable{
    private String data;
    private String materia;
    private String[] topico_list;

    @Override
    public String toString() {
        return getMateria() + " - " + getData();
    }

    public Prova(String data, String materia, String[] topico_list) {
        this.data = data;
        this.materia = materia;
        this.topico_list = topico_list;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String[] getTopico_list() {
        return topico_list;
    }

    public void setTopico_list(String[] topico_list) {
        this.topico_list = topico_list;
    }
}
