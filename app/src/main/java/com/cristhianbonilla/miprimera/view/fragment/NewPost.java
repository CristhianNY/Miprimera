package com.cristhianbonilla.miprimera.view.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cristhianbonilla.miprimera.MiPrimeraAplication;
import com.cristhianbonilla.miprimera.R;
import com.cristhianbonilla.miprimera.model.Post;
import com.cristhianbonilla.miprimera.utils.Constantes;
import com.cristhianbonilla.miprimera.view.LoginFacebook;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPost extends Fragment {

    ImageView ivPicture;
    Button btnTakePicture;

    static final int  REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentAsolutePhotoPath;
    StorageReference storaReference;
    MiPrimeraAplication app;
    DatabaseReference postReferece;
    public NewPost() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_new_post, container, false);
        showToolbar("ne post",true,view);
        ivPicture = (ImageView) view.findViewById(R.id.ivPicture);
        btnTakePicture= (Button) view.findViewById(R.id.btnTakePicture);


        app = (MiPrimeraAplication) getActivity().getApplicationContext();
        storaReference = app.getStorageReference();
        postReferece = app.getPostReference();
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });


        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK){


            Picasso.with(getActivity()).load(mCurrentPhotoPath).into(ivPicture);
            addPictureToGalery();
          //  Toast.makeText(getActivity(),mCurrentPhotoPath,Toast.LENGTH_SHORT);

            uploadFile();
        }
    }

    private void uploadFile() {
        File newFile = new File(mCurrentAsolutePhotoPath);
        //obtenemos la referencia
        Uri contentUri = Uri.fromFile(newFile);

        StorageReference imagenReference = storaReference.child(Constantes.FIREBASE_STORAGE_IMAGES + contentUri.getLastPathSegment());
        UploadTask uploadTask = imagenReference.putFile(contentUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "falla al subir la imagen" , Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
             //   Toast.makeText(getActivity(), taskSnapshot.getDownloadUrl().toString() , Toast.LENGTH_LONG).show();

                String imageUrl = taskSnapshot.getDownloadUrl().toString();
                
                CreateNewPost(imageUrl);

            }
        });
    }

    private void CreateNewPost(String imageUrl) {

        SharedPreferences preferencia = getActivity().getSharedPreferences("USER", getActivity().MODE_PRIVATE);
        String email = preferencia.getString("email","");
        String enCodeEmail = email.replace(".",",");


        Post post = new Post(enCodeEmail, imageUrl, (double) new Date().getTime());
        postReferece.push().setValue(post);
        getFragmentManager().popBackStackImmediate();
    }

    public void showToolbar (String title, boolean upButton, View view){

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);



    }

    private void takePicture(){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {

                Log.d(TAG, getString(R.string.error_al_crear_el_archivo));
            }
            if(photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(getActivity(),
                        "com.cristhianbonilla.miprimera",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }else{
            Log.d(TAG, getString(R.string.error_al_crear_el_archivo2));


        }

    }
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directorio en el que será almacenado*/
        );


        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        mCurrentAsolutePhotoPath = image.getAbsolutePath();
        return image;
    }

    private void addPictureToGalery(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        File newFiile = new File(mCurrentPhotoPath);

        Uri contentUri = Uri.fromFile(newFiile);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);

    }




}
