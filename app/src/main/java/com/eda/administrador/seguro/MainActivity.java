package com.eda.administrador.seguro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eda.administrador.seguro.LibBusinessEntity.beSeguridad;
import com.eda.administrador.seguro.LibBusinessRules.brSeguridad;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private brSeguridad obrSeguridad;
    private beSeguridad obeSeguridad;

    private Button btnAceptar;
    private EditText edtUsuario;
    private EditText edtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obrSeguridad = new brSeguridad();

        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtClave = (EditText) findViewById(R.id.edtClave);

        btnAceptar.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAceptar:

                int login = -1;
                login = Login();

                if (login == -1 || login == 0) {
                    return;
                }
                break;
        }
    }

    private int Login() {

        if (ValidarCampos() == -1) return -1;

        String usuario = edtUsuario.getText().toString();
        String clave = edtClave.getText().toString();
        int Existe = 0;

        int Validar = 1;
        String Mensaje = "...";

            /*
        try {
            obeSeguridad = obrSeguridad.Login(usuario.trim(), clave.trim());
            if (obeSeguridad == null) {
                Validar = -1;
                Mensaje = "Error in connection with obrSeguridad.Login()";
                Toast.makeText(this, Mensaje, Toast.LENGTH_LONG).show();
            } else {

                Validar = obeSeguridad.getValidar();

                if (Validar == 1) {

                    Existe = obeSeguridad.getExiste();

                    if (Existe == 0) {
                        Validar = 0;
                        Mensaje = obeSeguridad.getMensaje();
                        Toast.makeText(this, Mensaje, Toast.LENGTH_LONG).show();
                    } else {
                        OpenPrincipalActivity();
                    }
                } else {
                    Validar = -1;
                }

            }

        } catch (Exception ex) {
            Validar = -1;
            Mensaje = "Exceptions / MainActivity : " + ex.getMessage();
            Toast.makeText(this, Mensaje, Toast.LENGTH_LONG).show();
        } */

        OpenPrincipalActivity();
        return Validar;
    }

    private int ValidarCampos() {
        int validar = 1;

        if (edtUsuario.getText().toString().equals("")) {
            Toast.makeText(this, "Ingrese su Usuario", Toast.LENGTH_LONG).show();
            edtUsuario.requestFocus();
            validar = -1;
            return validar;
        }

        if (edtClave.getText().toString().equals("")) {
            Toast.makeText(this, "Ingrese su Clave", Toast.LENGTH_LONG).show();
            edtClave.requestFocus();
            validar = -1;
            return validar;
        }
        return validar;
    }

    private void OpenPrincipalActivity() {
        Intent intent = new Intent(MainActivity.this, MainTecnicoActivity.class);
        startActivity(intent);

        /*
        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
        intent.putExtra(PrincipalActivity.EXTRA_CODIGO_USUARIO, obeSeguridad.getUsuaCodigo().toString());
        //startActivityForResult(intent,REQUEST_CODE);
        startActivity(intent);
        */
    }

}
