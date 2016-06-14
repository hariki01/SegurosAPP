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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eda.administrador.seguro.LibBusinessEntity.beSeguridad;
import com.eda.administrador.seguro.LibBusinessRules.brSeguridad;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private brSeguridad obrSeguridad;
    private beSeguridad obeSeguridad;

    private Button btnAceptar;
    private EditText edtUsuario;
    private EditText edtClave;
    private String msg="";
    private String status="";

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

                searchRequest();

                /*
                int login = -1;
                login = Login();

                if (login == -1 || login == 0) {
                    return;
                } */

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



        searchRequest();

        if (!status.equals("1")) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }



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

    private void OpenTecnicoActivity() {
        Intent intent = new Intent(MainActivity.this, MainTecnicoActivity.class);
        intent.putExtra(Main2Activity.EXTRA_CODIGO_USUARIO, "Tecnico");
        startActivity(intent);
    }

    private void OpenPrincipalActivity() {
         /*
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        */
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra(Main2Activity.EXTRA_CODIGO_USUARIO, "Cliente");
        startActivity(intent);

        /*
        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
        intent.putExtra(PrincipalActivity.EXTRA_CODIGO_USUARIO, obeSeguridad.getUsuaCodigo().toString());
        //startActivityForResult(intent,REQUEST_CODE);
        startActivity(intent);
        */
    }

    public void searchRequest() {


        if (ValidarCampos() == -1) return;

        String url = "http://devservice.virtualspree.com/user/login?login="+edtUsuario.getText().toString()+"&clave="+edtClave.getText().toString();

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {

                            status = response.getString("status");
                            msg = response.getString("msg").toString();
                            String tipo = response.getString("tipo");

                            if ( status.equals("1") ) {

                                if (tipo.equals("1")) {
                                    OpenPrincipalActivity();
                                } else if (tipo.equals("2")) {
                                    OpenTecnicoActivity();
                                }

                            }

                            System.out.println("status: "+status+"\nmsg: "+msg+"\ntipo: "+tipo);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);
    }

}
