package com.eda.administrador.seguro;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.eda.administrador.seguro.LibBusinessEntity.beColor;
import com.eda.administrador.seguro.LibBusinessEntity.beMarca;
import com.eda.administrador.seguro.LibBusinessEntity.beSolicitud;
import com.eda.administrador.seguro.LibBusinessEntity.beTipoAuxilio;
import com.eda.administrador.seguro.LibBusinessRules.brColor;
import com.eda.administrador.seguro.LibBusinessRules.brMarca;
import com.eda.administrador.seguro.LibBusinessRules.brSolicitud;
import com.eda.administrador.seguro.LibBusinessRules.brTipoAuxilio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MecanicaFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String ARG_CODIGO_USUARIO = "0";
    private String CodigoUsuario = "0";
    private static final String ARG_CODIGO_TIPO = "0";
    private String CodigoTipo = "0";

    private brSolicitud obrSolicitud;
    private beSolicitud obeSolicitud;
    private brMarca obrMarca;
    private beMarca obeMarca;
    private brTipoAuxilio obrTipoAuxilio;
    private beTipoAuxilio obeTipoAuxilio;
    private brColor obrColor;
    private beColor obeColor;

    ArrayList<beMarca> aList_Marca;
    ArrayList<beTipoAuxilio> aList_TipoAuxilio;
    ArrayList<beColor> aList_Color;

    //private Uri fileUri;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    //private Uri lastOutputMediaFileUri = null;

    private static final int MAP_ACTIVITY_REQUEST_CODE = 300;
    private String sDireccion;
    private double dLatitud;
    private double dLongitud;

    ImageView imgCamara;
    Bitmap bmpCamara;

    EditText edtUbicacion;

    Spinner spiMotivo;
    Spinner spiVehiculo;
    Spinner spiColor;

    EditText edtComentario;

    Button btnUbicacion;
    Button btnCamara;
    Button btnGrabar;

    File finalFile;

    private static final int RESULT_OK = -1;
    private static final int RESULT_CANCELED = 0;

    ArrayAdapter<beTipoAuxilio> spinnerAdapterMotivo;
    ArrayAdapter<beMarca> spinnerAdapterMarca;
    ArrayAdapter<beColor> spinnerAdapterColor;

    private String sMotivo;
    private String sVehiculo;
    private String sColor;

    public static MecanicaFragment newInstance(String Usuario, String Tipo) {
        MecanicaFragment fragment = new MecanicaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CODIGO_USUARIO, Usuario);
        args.putString(ARG_CODIGO_TIPO, Tipo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            CodigoUsuario = getArguments().getString(ARG_CODIGO_USUARIO);
            CodigoTipo = getArguments().getString(ARG_CODIGO_TIPO);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mecanica, container, false);

        View view = inflater.inflate(R.layout.fragment_mecanica, container, false);

        imgCamara = (ImageView) view.findViewById(R.id.imgCamara);
        btnCamara = (Button) view.findViewById(R.id.btnCamara);
        btnUbicacion = (Button) view.findViewById(R.id.btnUbicacion);
        edtUbicacion = (EditText) view.findViewById(R.id.edtUbicacion);
        edtComentario = (EditText) view.findViewById(R.id.edtComentario);
        btnGrabar = (Button) view.findViewById(R.id.btnGrabar);

        btnCamara.setOnClickListener(this);
        btnUbicacion.setOnClickListener(this);
        btnGrabar.setOnClickListener(this);

        spiMotivo = (Spinner) view.findViewById(R.id.spiMotivo);
        spiVehiculo = (Spinner) view.findViewById(R.id.spiVehiculo);
        spiColor = (Spinner) view.findViewById(R.id.spiColor);

        spiMotivo.setOnItemSelectedListener(this);
        spiVehiculo.setOnItemSelectedListener(this);
        spiColor.setOnItemSelectedListener(this);

        Motivo_Listado(CodigoTipo);
        Marca_Listado();
        Color_Listado();

        spinnerAdapterMotivo = new ArrayAdapter<beTipoAuxilio>(getActivity(),
                android.R.layout.simple_spinner_item, aList_TipoAuxilio);
        spinnerAdapterMotivo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiMotivo.setAdapter(spinnerAdapterMotivo);

        spinnerAdapterMarca = new ArrayAdapter<beMarca>(getActivity(),
                android.R.layout.simple_spinner_item, aList_Marca);
        spinnerAdapterMarca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiVehiculo.setAdapter(spinnerAdapterMarca);

        spinnerAdapterColor = new ArrayAdapter<beColor>(getActivity(),
                android.R.layout.simple_spinner_item, aList_Color);
        spinnerAdapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiColor.setAdapter(spinnerAdapterColor);

        return view;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCamara) {
            /*
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            lastOutputMediaFileUri = fileUri;
            */
            /*
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "picture.jpg");
            fileUri = Uri.fromFile(mediaStorageDir);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            */
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        if (v.getId() == R.id.btnUbicacion) {
            Intent intent = new Intent(getActivity(), MapActivity.class);
            startActivityForResult(intent, MAP_ACTIVITY_REQUEST_CODE);
        }

        if (v.getId() == R.id.btnGrabar) {

            Grabar(CodigoUsuario,sVehiculo,sColor,sMotivo,
                    dLatitud, dLongitud,edtComentario.getText().toString(), finalFile.toString());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Bundle ext = data.getExtras();
                bmpCamara = (Bitmap) ext.get("data");
                imgCamara.setImageBitmap(bmpCamara);


                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getContext(), bmpCamara);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                finalFile = new File(getRealPathFromURI(tempUri));
            }
        }

        if (requestCode == MAP_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                sDireccion = data.getStringExtra("DIRECCION");
                dLatitud = data.getDoubleExtra("LATITUD", 0);
                dLongitud = data.getDoubleExtra("LONGITUD", 0);
                edtUbicacion.setText(sDireccion.toString());
            }

        }


        /*
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Bundle ext = data.getExtras();
                bmpCamara = (Bitmap)ext.get("data");
                imgCamara.setImageBitmap(bmpCamara);

                String pathToImage = "";

                Toast.makeText(getActivity(), "Image saved to: " + pathToImage, Toast.LENGTH_LONG).show();
            }
        }
        */


    }

    /*
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Seguro");
        if (! mediaStorageDir.exists()) {
            if(! mediaStorageDir.mkdirs()) {
                Log.d("Camerify", "failed to create directory");
                return null;
            }
        } else {
            Log.d("Camerify", "Directory found");
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if(type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp+".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        try {
            Log.d("Camerify", mediaFile.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mediaFile;
    }
    */

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private void Grabar(String Usuario, String Marca, String Color, String Auxilio, double Latitud, double Longitud, String Comentario, String Ruta) {

        int Validar = -1;
        String Mensaje = "...";

        obeSolicitud = null;
        obrSolicitud = new brSolicitud();

        try {
            obeSolicitud = obrSolicitud.Insertar(Usuario, Marca, Color, Auxilio, Latitud, Longitud, Comentario, Ruta);

            Validar = obeSolicitud.getValidar();

            if (Validar == -1) {
                Mensaje = obeSolicitud.getMensaje();
                Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
            } else {
                Mensaje = obeSolicitud.getMensaje();
                Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {

            Mensaje = "Exceptions / Grabar : " + ex.getMessage();
            Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
        }

    }

    private ArrayList<beMarca> Marca_Listado() {

        int Validar = -1;
        String Mensaje = "...";

        obeMarca = null;
        obrMarca = new brMarca();
        aList_Marca = new ArrayList<beMarca>();

        try {
            obeMarca = obrMarca.Listado();
            aList_Marca = obeMarca.getaList_Marca();

            Validar = obeMarca.getValidar();

            if (Validar == -1) {
                Mensaje = obeMarca.getMensaje();
                Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
            } else {
                //Mensaje = obeMarca.getMensaje();
                //Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {

            Mensaje = "Exceptions / Marca_Listado : " + ex.getMessage();
            Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
        }

        return aList_Marca;

    }

    private ArrayList<beColor> Color_Listado() {

        int Validar = -1;
        String Mensaje = "...";

        obeColor = null;
        obrColor = new brColor();
        aList_Color = new ArrayList<beColor>();

        try {
            obeColor = obrColor.Listado();
            aList_Color = obeColor.getaList_Color();

            Validar = obeColor.getValidar();

            if (Validar == -1) {
                Mensaje = obeColor.getMensaje();
                Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
            } else {
                //Mensaje = obeMarca.getMensaje();
                //Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {

            Mensaje = "Exceptions / Color_Listado : " + ex.getMessage();
            Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
        }

        return aList_Color;

    }

    private ArrayList<beTipoAuxilio> Motivo_Listado(String Tipo) {

        int Validar = -1;
        String Mensaje = "...";

        obeTipoAuxilio = null;
        obrTipoAuxilio = new brTipoAuxilio();
        aList_TipoAuxilio = new ArrayList<beTipoAuxilio>();

        try {
            obeTipoAuxilio = obrTipoAuxilio.Listado(Tipo);
            aList_TipoAuxilio = obeTipoAuxilio.getaList_TipoAuxilio();

            Validar = obeTipoAuxilio.getValidar();

            if (Validar == -1) {
                Mensaje = obeTipoAuxilio.getMensaje();
                Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
            } else {
                //Mensaje = obeTipoAuxilio.getMensaje();
                //Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {

            Mensaje = "Exceptions / Motivo_Listado : " + ex.getMessage();
            Toast.makeText(getActivity(), Mensaje, Toast.LENGTH_LONG).show();
        }

        return aList_TipoAuxilio;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;

        if (spinner.getId() == R.id.spiMotivo) {

            beTipoAuxilio motivo = spinnerAdapterMotivo.getItem(position);
            sMotivo =  motivo.getCodAuxilio().toString();

        }

        if (spinner.getId() == R.id.spiVehiculo) {
            beMarca vehiculo = spinnerAdapterMarca.getItem(position);
            sVehiculo =  vehiculo.getMarcaId().toString();
        }

        if (spinner.getId() == R.id.spiColor) {
            beColor color = spinnerAdapterColor.getItem(position);
            sColor = color.getCodColor().toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
