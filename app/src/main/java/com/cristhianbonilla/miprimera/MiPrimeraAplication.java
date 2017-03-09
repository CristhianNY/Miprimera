package com.cristhianbonilla.miprimera;

import android.app.Application;

import com.cristhianbonilla.miprimera.utils.Constantes;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by ASUS on 2/03/2017.
 */

public class MiPrimeraAplication extends Application{


    StorageReference storageReference;
    DatabaseReference postReference;
    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        // agregamos referencia 1

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl(Constantes.FIREBASE_STORAGE_URL);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);

        postReference = firebaseDatabase.getReference(Constantes.FIREBASE_DATABASE_LOCATION_POST);

    }

    public StorageReference getStorageReference(){

        return storageReference;

    }

    public DatabaseReference getPostReference(){

        return  postReference;
    }
}
