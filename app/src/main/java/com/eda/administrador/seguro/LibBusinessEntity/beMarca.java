package com.eda.administrador.seguro.LibBusinessEntity;

import java.util.ArrayList;

/**
 * Created by Administrador on 11/06/2016.
 */
public class beMarca {

    String MarcaId;
    String MarcaNombre;
    ArrayList<beMarca> aList_Marca;

    int Validar;
    String Mensaje;
    int Encontrados;

    public int getValidar() {
        return Validar;
    }

    public void setValidar(int validar) {
        Validar = validar;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getMarcaId() {
        return MarcaId;
    }

    public void setMarcaId(String marcaId) {
        MarcaId = marcaId;
    }

    public String getMarcaNombre() {
        return MarcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        MarcaNombre = marcaNombre;
    }

    public int getEncontrados() {
        return Encontrados;
    }

    public void setEncontrados(int encontrados) {
        Encontrados = encontrados;
    }

    public ArrayList<beMarca> getaList_Marca() {
        return aList_Marca;
    }

    public void setaList_Marca(ArrayList<beMarca> aList_Marca) {
        this.aList_Marca = aList_Marca;
    }

    public String toString() {
        return MarcaNombre;
    }

}
