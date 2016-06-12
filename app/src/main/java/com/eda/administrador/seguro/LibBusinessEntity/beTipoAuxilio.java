package com.eda.administrador.seguro.LibBusinessEntity;

import java.util.ArrayList;

/**
 * Created by Administrador on 11/06/2016.
 */
public class beTipoAuxilio {

    String CodAuxilio;
    String Auxilio;
    ArrayList<beTipoAuxilio> aList_TipoAuxilio;

    int Validar;
    String Mensaje;
    int Encontrados;

    public String getCodAuxilio() {
        return CodAuxilio;
    }

    public void setCodAuxilio(String codAuxilio) {
        CodAuxilio = codAuxilio;
    }

    public String getAuxilio() {
        return Auxilio;
    }

    public void setAuxilio(String auxilio) {
        Auxilio = auxilio;
    }

    public ArrayList<beTipoAuxilio> getaList_TipoAuxilio() {
        return aList_TipoAuxilio;
    }

    public void setaList_TipoAuxilio(ArrayList<beTipoAuxilio> aList_TipoAuxilio) {
        this.aList_TipoAuxilio = aList_TipoAuxilio;
    }

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

    public int getEncontrados() {
        return Encontrados;
    }

    public void setEncontrados(int encontrados) {
        Encontrados = encontrados;
    }

    public String toString() {
        return Auxilio;
    }
}
