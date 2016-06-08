package com.eda.administrador.seguro.LibDataAccess;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrador on 19/05/2016.
 */
public class DBConnection {

    String SERVER = "172.19.133.8";
    String CLASS = "net.sourceforge.jtds.jdbc.Driver";
    String DATABASE = "SEGUROS";
    String USER = "sa";
    String PASS = "JORVIL";

    @SuppressLint("NewApi")
    public Connection conn(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection Conn = null;
        String ConnURL = null;

        try {
            Class.forName(CLASS);
            ConnURL = "jdbc:jtds:sqlserver://" + SERVER + ";" + "databaseName=" + DATABASE + ";user=" + USER + ";password=" + PASS + ";";
            Conn = DriverManager.getConnection(ConnURL);

        }  catch (SQLException se) {
            Log.e("ERROR", se.getMessage());

        } catch (ClassNotFoundException e) {
            Log.e("ERROR",e.getMessage());

        } catch (Exception e) {
            Log.e("ERROR",e.getMessage());

        }

        return  Conn;
    }

}
