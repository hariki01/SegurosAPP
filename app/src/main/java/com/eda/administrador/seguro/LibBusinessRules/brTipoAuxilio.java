package com.eda.administrador.seguro.LibBusinessRules;

import com.eda.administrador.seguro.LibBusinessEntity.beTipoAuxilio;
import com.eda.administrador.seguro.LibDataAccess.DBConnection;
import com.eda.administrador.seguro.LibDataAccess.daTipoAuxilio;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrador on 11/06/2016.
 */
public class brTipoAuxilio {

    public beTipoAuxilio Listado(String Tipo) {

        int Validar = 1;
        String Ref_Mensaje = "...";

        DBConnection dbConnection = null;
        daTipoAuxilio odaTipoAuxilio = null;
        beTipoAuxilio obeTipoAuxilio = null;
        Connection conn = null;

        dbConnection = new DBConnection();
        odaTipoAuxilio = new daTipoAuxilio();

        try {
            conn = dbConnection.conn();

            obeTipoAuxilio = odaTipoAuxilio.Listado(conn,Tipo);

        } catch (Exception ex) {
            Validar = -1;
            Ref_Mensaje = "Exceptions / brTipoAuxilio : " + ex.getMessage();
            obeTipoAuxilio.setValidar(Validar);
            obeTipoAuxilio.setMensaje(Ref_Mensaje);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / brTipoAuxilio : " + se.getMessage();
                    obeTipoAuxilio.setValidar(Validar);
                    obeTipoAuxilio.setMensaje(Ref_Mensaje);
                }
            }
        }

        return obeTipoAuxilio;
    }

}
