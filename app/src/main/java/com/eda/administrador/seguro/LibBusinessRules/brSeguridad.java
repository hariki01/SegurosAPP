package com.eda.administrador.seguro.LibBusinessRules;

import com.eda.administrador.seguro.LibBusinessEntity.beSeguridad;
import com.eda.administrador.seguro.LibDataAccess.DBConnection;
import com.eda.administrador.seguro.LibDataAccess.daSeguridad;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrador on 19/05/2016.
 */
public class brSeguridad {

    public beSeguridad Login(String usuario, String clave) {

        int Validar = 1;
        String Ref_Mensaje = "...";

        DBConnection dbConnection = null;
        daSeguridad odaSeguridad = null;
        beSeguridad obeSeguridad = null;
        Connection conn = null;

        dbConnection = new DBConnection();
        odaSeguridad = new daSeguridad();

        try {
            conn = dbConnection.conn();

            obeSeguridad = odaSeguridad.Login(conn, usuario, clave);

        } catch (Exception ex) {
            Validar = -1;
            Ref_Mensaje = "Exceptions / brSeguridad : " + ex.getMessage();
            obeSeguridad.setValidar(Validar);
            obeSeguridad.setMensaje(Ref_Mensaje);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / brSeguridad : " + se.getMessage();
                    obeSeguridad.setValidar(Validar);
                    obeSeguridad.setMensaje(Ref_Mensaje);
                }
            }
        }

        return obeSeguridad;

    }
}
