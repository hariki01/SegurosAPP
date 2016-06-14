package com.eda.administrador.seguro.LibBusinessRules;

import com.eda.administrador.seguro.LibBusinessEntity.beColor;
import com.eda.administrador.seguro.LibDataAccess.DBConnection;
import com.eda.administrador.seguro.LibDataAccess.daColor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrador on 13/06/2016.
 */
public class brColor {

    public beColor Listado() {

        int Validar = 1;
        String Ref_Mensaje = "...";

        DBConnection dbConnection = null;
        daColor odaColor = null;
        beColor obeColor = null;
        Connection conn = null;

        dbConnection = new DBConnection();
        odaColor = new daColor();

        try {
            conn = dbConnection.conn();

            obeColor = odaColor.Listado(conn);

        } catch (Exception ex) {
            Validar = -1;
            Ref_Mensaje = "Exceptions / brColor : " + ex.getMessage();
            obeColor.setValidar(Validar);
            obeColor.setMensaje(Ref_Mensaje);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / brColor : " + se.getMessage();
                    obeColor.setValidar(Validar);
                    obeColor.setMensaje(Ref_Mensaje);
                }
            }
        }

        return obeColor;
    }
    
}
