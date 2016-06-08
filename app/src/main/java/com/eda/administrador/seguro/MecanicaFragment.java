package com.eda.administrador.seguro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransitionCompat21;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class MecanicaFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_CODIGO_USUARIO = "0000";
    private String CodigoUsuario = "0000";

    private Uri fileUri;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private Uri lastOutputMediaFileUri = null;
    Button btnCamara;

    private static final int RESULT_OK = -1;
    private static final int RESULT_CANCELED = 0;

    public static MecanicaFragment newInstance(String Usuario) {
        MecanicaFragment fragment = new MecanicaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CODIGO_USUARIO, Usuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            CodigoUsuario = getArguments().getString(ARG_CODIGO_USUARIO);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mecanica, container, false);
        View view = inflater.inflate(R.layout.fragment_mecanica, container, false);

        btnCamara = (Button) view.findViewById(R.id.btnCamara);

        btnCamara.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCamara) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            lastOutputMediaFileUri = fileUri;
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("Camerify", "ResultCode: RESULT_OK");
                String fileName = data != null ? data.getData().getPath() : lastOutputMediaFileUri.getPath();
                Toast.makeText(getActivity(), "Image saved to: " + fileName, Toast.LENGTH_LONG).show();

            } else if (resultCode == RESULT_CANCELED) {
                // Intent canceled;
                Log.d("Camerify", "ResultCode: RESULT_CANCELED");
            } else {
                // Intent error
                Log.d("Camerify", "ResultCode: " + Integer.toString(resultCode));
            }
        }
    }

    private Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
        File mediaStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
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
}
