package com.eda.administrador.seguro.LibDataAccess;

import com.eda.administrador.seguro.LibBusinessEntity.beSeguridad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Administrador on 19/05/2016.
 */
public class daSeguridad {

    public beSeguridad Login(Connection conn, String usuario, String clave) {

        beSeguridad obeSeguridad = null;

        int Existe = 0;

        int Validar = 1;
        String Ref_Mensaje = "...";

        boolean isSuccess = false;

        String StoreProcedure = "{call SP_ValidarUsuario (?, ?, ?)}";

        CallableStatement callableStatement = null;

        try {
            if (conn == null) {
                obeSeguridad = new beSeguridad();
                Validar = -1;
                Ref_Mensaje = "Error in connection with SQL server";
                obeSeguridad.setValidar(Validar);
                obeSeguridad.setMensaje(Ref_Mensaje);
            } else {

                callableStatement = conn.prepareCall(StoreProcedure);
                callableStatement.setString("@user", usuario);
                callableStatement.setString("@clave", clave);
                callableStatement.registerOutParameter("@existe", Types.INTEGER);

                isSuccess = callableStatement.execute();

                if (isSuccess = true) {

                    Existe = callableStatement.getInt("@existe");

                    obeSeguridad = new beSeguridad();
                    obeSeguridad.setUsuaCodigo("0000");
                    obeSeguridad.setExiste(Existe);

                    if (Existe == 1) {
                        obeSeguridad.setMensaje("Usuario conforme");
                    } else {
                        obeSeguridad.setMensaje("Usuario no encontrado");
                    }

                    obeSeguridad.setValidar(Validar);

                } else {
                    if (obeSeguridad == null) {
                        obeSeguridad = new beSeguridad();
                    }
                    Validar = -1;
                    Ref_Mensaje = "Usuario no encontrado";
                    obeSeguridad.setValidar(Validar);
                    obeSeguridad.setMensaje(Ref_Mensaje);
                }

            }

        } catch (Exception ex) {
            if (obeSeguridad == null) {
                obeSeguridad = new beSeguridad();
            }
            Validar = -1;
            Ref_Mensaje = "Exceptions / daSeguridad " + ex.getMessage();
            obeSeguridad.setValidar(Validar);
            obeSeguridad.setMensaje(Ref_Mensaje);

        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException se) {
                    Validar = -1;
                    Ref_Mensaje = "SQLException / daSeguridad : " + se.getMessage();
                    obeSeguridad.setValidar(Validar);
                    obeSeguridad.setMensaje(Ref_Mensaje);
                }
            }

        }

        return obeSeguridad;
    }


}
