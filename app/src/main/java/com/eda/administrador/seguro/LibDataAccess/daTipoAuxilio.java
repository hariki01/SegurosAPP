package com.eda.administrador.seguro.LibDataAccess;

import com.eda.administrador.seguro.LibBusinessEntity.beTipoAuxilio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrador on 11/06/2016.
 */
public class daTipoAuxilio {

    public beTipoAuxilio Listado(Connection conn, String Tipo) {

        beTipoAuxilio obeTipoAuxilio = null;
        ArrayList<beTipoAuxilio> aList_TipoAuxilio = new ArrayList<>();

        int Validar = 1;
        int Filas = 0;
        String Ref_Mensaje = "...";

        String SqlSelect = "select codAuxilio, auxilio from dhiTipoAuxilio where codTipo = ?";

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            if (conn == null) {
                obeTipoAuxilio = new beTipoAuxilio();
                Validar = -1;
                Ref_Mensaje = "Error in connection with SQL server";
                obeTipoAuxilio.setValidar(Validar);
                obeTipoAuxilio.setMensaje(Ref_Mensaje);
            } else {

                preparedStatement = conn.prepareStatement(SqlSelect);
                preparedStatement.setString(1,Tipo);

                rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    beTipoAuxilio TipoAuxilio = new beTipoAuxilio();
                    TipoAuxilio.setCodAuxilio(String.valueOf(rs.getInt("codAuxilio")));
                    TipoAuxilio.setAuxilio(rs.getString("auxilio"));

                    aList_TipoAuxilio.add(TipoAuxilio);
                    Filas++;
                }
                obeTipoAuxilio = new beTipoAuxilio();
                obeTipoAuxilio.setValidar(Validar);
                obeTipoAuxilio.setEncontrados(Filas);

                if (Filas > 0) {
                    obeTipoAuxilio.setaList_TipoAuxilio(aList_TipoAuxilio);
                }
                if (Filas == 0) {
                    Ref_Mensaje = "No hay Tipo Auxilio";
                    obeTipoAuxilio.setMensaje(Ref_Mensaje);
                }
                if (Filas > 1) {
                    Ref_Mensaje = "Existe mas de una Tipo Auxilio";
                    obeTipoAuxilio.setMensaje(Ref_Mensaje);
                }

            }

        } catch (Exception ex) {
            if (obeTipoAuxilio == null) {
                obeTipoAuxilio = new beTipoAuxilio();
            }
            Validar = -1;
            Ref_Mensaje = "Exceptions / daTipoAuxilio : " + ex.getMessage();
            obeTipoAuxilio.setValidar(Validar);
            obeTipoAuxilio.setMensaje(Ref_Mensaje);

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / daTipoAuxilio : " + se.getMessage();
                    obeTipoAuxilio.setValidar(Validar);
                    obeTipoAuxilio.setMensaje(Ref_Mensaje);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / daTipoAuxilio : " + se.getMessage();
                    obeTipoAuxilio.setValidar(Validar);
                    obeTipoAuxilio.setMensaje(Ref_Mensaje);
                }
            }

        }

        return obeTipoAuxilio;

    }

}
