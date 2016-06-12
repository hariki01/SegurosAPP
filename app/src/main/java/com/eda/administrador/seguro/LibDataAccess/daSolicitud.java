package com.eda.administrador.seguro.LibDataAccess;

import com.eda.administrador.seguro.LibBusinessEntity.beSolicitud;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Administrador on 09/06/2016.
 */
public class daSolicitud {

    public beSolicitud Insertar(Connection conn, String Usuario, String Marca, String Color, String Auxilio, int Latitud, int Longitud, String Ruta) {

        beSolicitud obeSolicitud = null;

        int Validar = 1;
        String Ref_Mensaje = "...";

        int isSuccess = 0;

        String SqlInsert;

        SqlInsert = "insert into dhiSolicitud(codCliente,codMarca,codColor,codAuxilio,latitud,longitud,foto) values(?,?,?,?,?,?,?)";
        FileInputStream fis = null;
        PreparedStatement preparedStatement = null;

        try {
            if (conn == null) {
                obeSolicitud = new beSolicitud();
                Validar = -1;
                Ref_Mensaje = "Error in connection with SQL server";
                obeSolicitud.setValidar(Validar);
                obeSolicitud.setMensaje(Ref_Mensaje);
            } else {

                File file = new File(Ruta);
                fis = new FileInputStream(file);
                preparedStatement = conn.prepareStatement(SqlInsert);

                preparedStatement.setInt(1,Integer.parseInt(Usuario));
                preparedStatement.setInt(2,Integer.parseInt(Marca));
                preparedStatement.setInt(3,Integer.parseInt(Color));
                preparedStatement.setInt(4,Integer.parseInt(Auxilio));
                preparedStatement.setInt(5,Latitud);
                preparedStatement.setInt(6,Longitud);
                preparedStatement.setBinaryStream(7,fis,(int)file.length());

                isSuccess = preparedStatement.executeUpdate();

                if (isSuccess > 0) {

                    obeSolicitud = new beSolicitud();
                    obeSolicitud.setValidar(Validar);

                } else {
                    if (obeSolicitud == null) {
                        obeSolicitud = new beSolicitud();
                    }
                    Validar = -1;
                    Ref_Mensaje = "No grabo la informacion";
                    obeSolicitud.setValidar(Validar);
                    obeSolicitud.setMensaje(Ref_Mensaje);
                }

            }

        } catch (Exception ex) {
            if (obeSolicitud == null) {
                obeSolicitud = new beSolicitud();
            }
            Validar = -1;
            Ref_Mensaje = "Exceptions / daSolicitud " + ex.getMessage();
            obeSolicitud.setValidar(Validar);
            obeSolicitud.setMensaje(Ref_Mensaje);

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    fis.close();
                } catch (Exception se) {
                    Validar = -1;
                    Ref_Mensaje = "Exception / daSolicitud : " + se.getMessage();
                    obeSolicitud.setValidar(Validar);
                    obeSolicitud.setMensaje(Ref_Mensaje);
                }
            }

        }

        return obeSolicitud;
    }

}
