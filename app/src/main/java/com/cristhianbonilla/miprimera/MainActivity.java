package com.cristhianbonilla.miprimera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cristhianbonilla.miprimera.view.Crear;
import com.cristhianbonilla.miprimera.view.LoginFacebook;
import com.cristhianbonilla.miprimera.view.fragment.ContenedorActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        final TextInputEditText editEmail = (TextInputEditText) findViewById(R.id.nombreUsuario);
        final TextInputEditText editPassword = (TextInputEditText) findViewById(R.id.password_login);
        Button btnLogin = (Button) findViewById(R.id.boton_login);




        // boton login email y password

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Log.d(TAG, "funciona por aca?");


                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email  = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();



                if(email.equals("")){

                    Toast.makeText(MainActivity.this, "El correo es obligatorio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals("")){
                    Toast.makeText(MainActivity.this, "EL password no puede estar vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "falla al login" + ((FirebaseAuthException)task.getException()).getErrorCode(), Toast.LENGTH_LONG).show();
                        }else {
                            FirebaseUser userFirebase = task.getResult().getUser();
                            SharedPreferences.Editor sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE).edit();
                            sharedPreferences.putString("email",userFirebase.getEmail());
                            sharedPreferences.commit();
                            goHome();
                        }
                    }
                });



            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }



    public void goHome(){

        Intent intencion = new Intent(this, ContenedorActivity.class);
        startActivity(intencion);



    }

    public void goHome2(View view){

        Intent intencion = new Intent(this, LoginFacebook.class);
        startActivity(intencion);



    }
    public void goCreateAccount (View view){

        Intent intencion = new Intent(this, Crear.class);
        startActivity(intencion);

    }

    public void  goToCristhianWeb (View view){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cristhianbonilla.com/"));
        startActivity(intent);
    }

}
