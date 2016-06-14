package com.eda.administrador.seguro.LibDataAccess;

import com.eda.administrador.seguro.LibBusinessEntity.beColor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrador on 13/06/2016.
 */
public class daColor {

    public beColor Listado(Connection conn) {

        beColor obeColor = null;
        ArrayList<beColor> aList_Color = new ArrayList<>();

        int Validar = 1;
        int Filas = 0;
        String Ref_Mensaje = "...";

        String SqlSelect = "select codColor, Color from dhiColor";

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            if (conn == null) {
                obeColor = new beColor();
                Validar = -1;
                Ref_Mensaje = "Error in connection with SQL server";
                obeColor.setValidar(Validar);
                obeColor.setMensaje(Ref_Mensaje);
            } else {

                preparedStatement = conn.prepareStatement(SqlSelect);

                rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    beColor Color = new beColor();
                    Color.setCodColor(String.valueOf(rs.getInt("codColor")));
                    Color.setColor(rs.getString("color"));

                    aList_Color.add(Color);
                    Filas++;
                }
                obeColor = new beColor();
                obeColor.setValidar(Validar);
                obeColor.setEncontrados(Filas);

                if (Filas > 0) {
                    obeColor.setaList_Color(aList_Color);
                }
                if (Filas == 0) {
                    Ref_Mensaje = "No hay Color";
                    obeColor.setMensaje(Ref_Mensaje);
                }
                if (Filas > 1) {
                    Ref_Mensaje = "Existe mas de una Color";
                    obeColor.setMensaje(Ref_Mensaje);
                }

            }

        } catch (Exception ex) {
            if (obeColor == null) {
                obeColor = new beColor();
            }
            Validar = -1;
            Ref_Mensaje = "Exceptions / daColor : " + ex.getMessage();
            obeColor.setValidar(Validar);
            obeColor.setMensaje(Ref_Mensaje);

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / daColor : " + se.getMessage();
                    obeColor.setValidar(Validar);
                    obeColor.setMensaje(Ref_Mensaje);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / daColor : " + se.getMessage();
                    obeColor.setValidar(Validar);
                    obeColor.setMensaje(Ref_Mensaje);
                }
            }

        }

        return obeColor;

    }

}
