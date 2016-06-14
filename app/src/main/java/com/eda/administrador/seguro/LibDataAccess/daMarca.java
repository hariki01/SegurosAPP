package com.eda.administrador.seguro.LibDataAccess;

import com.eda.administrador.seguro.LibBusinessEntity.beMarca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrador on 11/06/2016.
 */
public class daMarca {

    public beMarca Listado(Connection conn) {

        beMarca obeMarca = null;
        ArrayList<beMarca> aList_Marca = new ArrayList<>();

        int Validar = 1;
        int Filas = 0;
        String Ref_Mensaje = "...";

        String SqlSelect = "select codMarca, marca from dhimarca";

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            if (conn == null) {
                obeMarca = new beMarca();
                Validar = -1;
                Ref_Mensaje = "Error in connection with SQL server";
                obeMarca.setValidar(Validar);
                obeMarca.setMensaje(Ref_Mensaje);
            } else {

                preparedStatement = conn.prepareStatement(SqlSelect);

                rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    beMarca marca = new beMarca();
                    marca.setMarcaId(String.valueOf(rs.getInt("codMarca")));
                    marca.setMarcaNombre(rs.getString("marca"));

                    aList_Marca.add(marca);
                    Filas++;
                }
                obeMarca = new beMarca();
                obeMarca.setValidar(Validar);
                obeMarca.setEncontrados(Filas);

                if (Filas > 0) {
                    obeMarca.setaList_Marca(aList_Marca);
                }
                if (Filas == 0) {
                    Ref_Mensaje = "No hay Marca";
                    obeMarca.setMensaje(Ref_Mensaje);
                }
                if (Filas > 1) {
                    Ref_Mensaje = "Existe mas de una Marca";
                    obeMarca.setMensaje(Ref_Mensaje);
                }

            }

        } catch (Exception ex) {
            if (obeMarca == null) {
                obeMarca = new beMarca();
            }
            Validar = -1;
            Ref_Mensaje = "Exceptions / daMarca : " + ex.getMessage();
            obeMarca.setValidar(Validar);
            obeMarca.setMensaje(Ref_Mensaje);

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / daMarca : " + se.getMessage();
                    obeMarca.setValidar(Validar);
                    obeMarca.setMensaje(Ref_Mensaje);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / daMarca : " + se.getMessage();
                    obeMarca.setValidar(Validar);
                    obeMarca.setMensaje(Ref_Mensaje);
                }
            }

        }

        return obeMarca;

    }

}
