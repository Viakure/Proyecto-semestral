package com.example.proyectocm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void irPokemon(View view){
        Intent intent = new Intent(this, pokemones.class);
        startActivity(intent);
        Toast.makeText(this, "Ir a pokemones", Toast.LENGTH_SHORT).show();
    }
    public void irNaturaleza(View view){
        Intent intent = new Intent(this, Naturaleza.class);
        startActivity(intent);
        Toast.makeText(this, "Ir a Naturalezas", Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.cerrarSesion) {
            Toast.makeText(this, "Cerrando sesion", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            irLogin();
        }
        return super.onOptionsItemSelected(item);
    }

    private void irLogin() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}