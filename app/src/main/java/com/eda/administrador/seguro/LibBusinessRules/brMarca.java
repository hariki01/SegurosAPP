package com.eda.administrador.seguro.LibBusinessRules;

import com.eda.administrador.seguro.LibBusinessEntity.beMarca;
import com.eda.administrador.seguro.LibDataAccess.DBConnection;
import com.eda.administrador.seguro.LibDataAccess.daMarca;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrador on 11/06/2016.
 */
public class brMarca {

    public beMarca Listado() {

        int Validar = 1;
        String Ref_Mensaje = "...";

        DBConnection dbConnection = null;
        daMarca odaMarca = null;
        beMarca obeMarca = null;
        Connection conn = null;

        dbConnection = new DBConnection();
        odaMarca = new daMarca();

        try {
            conn = dbConnection.conn();

            obeMarca = odaMarca.Listado(conn);

        } catch (Exception ex) {
            Validar = -1;
            Ref_Mensaje = "Exceptions / brMarca : " + ex.getMessage();
            obeMarca.setValidar(Validar);
            obeMarca.setMensaje(Ref_Mensaje);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / brMarca : " + se.getMessage();
                    obeMarca.setValidar(Validar);
                    obeMarca.setMensaje(Ref_Mensaje);
                }
            }
        }

        return obeMarca;
    }

}
