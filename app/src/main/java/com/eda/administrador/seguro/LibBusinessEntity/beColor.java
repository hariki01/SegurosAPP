package com.eda.administrador.seguro.LibBusinessEntity;

import java.util.ArrayList;

/**
 * Created by Administrador on 13/06/2016.
 */
public class beColor {

    String CodColor;
    String Color;
    ArrayList<beColor> aList_Color;

    int Validar;
    String Mensaje;
    int Encontrados;

    public String getCodColor() {
        return CodColor;
    }

    public void setCodColor(String codColor) {
        CodColor = codColor;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public ArrayList<beColor> getaList_Color() {
        return aList_Color;
    }

    public void setaList_Color(ArrayList<beColor> aList_Color) {
        this.aList_Color = aList_Color;
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
        return Color;
    }

}
