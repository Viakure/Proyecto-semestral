package com.example.proyectocm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Registro extends AppCompatActivity {
    EditText correo, clave;
    Button registrar;

    FirebaseAuth firebaseAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.etRegistrarCorreo);
        clave = findViewById(R.id.etRegistrarClave);
        registrar = findViewById(R.id.btRegistrarCuenta);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = correo.getText().toString();
                String pass = clave.getText().toString();

                if (mail != null & pass != null) {
                    firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registro.this, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                Toast.makeText(Registro.this, "Ocurrio un problema al registrar el usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(Registro.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//oncreate
}