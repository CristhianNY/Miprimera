package com.cristhianbonilla.miprimera.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cristhianbonilla.miprimera.MainActivity;
import com.cristhianbonilla.miprimera.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Crear extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        showToolbar(getResources().getString(R.string.crear_una_aqui),false);

        firebaseAuth = FirebaseAuth.getInstance();

        final TextInputEditText tvEmail = (TextInputEditText) findViewById(R.id.email);
        final TextInputEditText tvNombre = (TextInputEditText) findViewById(R.id.nombre);
        final TextInputEditText tvUsuario = (TextInputEditText) findViewById(R.id.user);
        final TextInputEditText tvPassword = (TextInputEditText) findViewById(R.id.password_createaccount);
        final TextInputEditText tvConfirmPassword = (TextInputEditText) findViewById(R.id.confirm_password);

        Button joinUs = (Button) findViewById(R.id.joinUs);

        joinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = tvEmail.getText().toString().trim();
                String usuario= tvNombre.getText().toString().trim();
                String username= tvUsuario.getText().toString().trim();
                String password= tvPassword.getText().toString().trim();
                String confirmation = tvConfirmPassword.getText().toString().trim();

                if(email.equals("")){

                    Toast.makeText(getApplicationContext(), "Entrar email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(confirmation)){

                    if(password.equals("")){

                        Toast.makeText(getApplicationContext(), "contraseña invalidad", Toast.LENGTH_SHORT).show();

                    }
                    return;

                }

                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Crear.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // cuanto este correcto vamos a ir a login

                                if(!task.isSuccessful()){
                                    Toast.makeText(Crear.this, "Auth failed" + task.getException(), Toast.LENGTH_SHORT).show();


                                }else{

                                    startActivity(new Intent(Crear.this, MainActivity.class));

                                }
                            }
                        });
            }
        });

    }

    public void showToolbar (String title, boolean upButton){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle(title);
       getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);



    }
}
