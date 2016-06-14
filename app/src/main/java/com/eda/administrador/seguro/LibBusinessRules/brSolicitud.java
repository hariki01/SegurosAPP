package com.eda.administrador.seguro.LibBusinessRules;

import com.eda.administrador.seguro.LibBusinessEntity.beSolicitud;
import com.eda.administrador.seguro.LibDataAccess.DBConnection;
import com.eda.administrador.seguro.LibDataAccess.daSolicitud;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrador on 09/06/2016.
 */
public class brSolicitud {

    public beSolicitud Insertar(String Usuario, String Marca, String Color, String Auxilio, double Latitud, double Longitud, String Comentario, String Ruta) throws SQLException {

        int Validar = 1;
        String Ref_Mensaje = "...";

        DBConnection dbConnection = null;
        daSolicitud odaSolicitud = null;
        beSolicitud obeSolicitud = null;
        Connection conn = null;

        dbConnection = new DBConnection();
        odaSolicitud = new daSolicitud();

        try {
            conn = dbConnection.conn();
            conn.setAutoCommit(false);

            obeSolicitud = odaSolicitud.Insertar(conn, Usuario, Marca, Color, Auxilio, Latitud, Longitud, Comentario, Ruta);

            Validar = obeSolicitud.getValidar();

        } catch (Exception ex) {
            Validar = -1;
            Ref_Mensaje = "Exceptions / brSolicitud : " + ex.getMessage();
            obeSolicitud.setValidar(Validar);
            obeSolicitud.setMensaje(Ref_Mensaje);

        } finally {
            if (conn != null) {
                if (Validar == 1) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.close();
            }
        }

        return obeSolicitud;
    }
}


