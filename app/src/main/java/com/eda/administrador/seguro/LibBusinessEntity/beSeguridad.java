package com.eda.administrador.seguro.LibBusinessEntity;

/**
 * Created by Administrador on 19/05/2016.
 */
public class beSeguridad {

    private String UsuaCodigo;
    private int Existe;

    private int Validar;
    private String Mensaje;

    public String getUsuaCodigo() {
        return UsuaCodigo;
    }

    public void setUsuaCodigo(String usuaCodigo) {
        UsuaCodigo = usuaCodigo;
    }

    public int getExiste() {
        return Existe;
    }

    public void setExiste(int existe) {
        Existe = existe;
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
}
