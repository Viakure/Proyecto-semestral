package com.example.proyectocm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.proyectocm.models.NaturalezaRespuesta;
import com.example.proyectocm.models.Pokemon;
import com.example.proyectocm.models.PokemonRespuesta;
import com.example.proyectocm.models.naturaleza;
import com.example.proyectocm.pokeapi.NatuapiService;
import com.example.proyectocm.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Naturaleza extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaNaturalezaAdapter listaNaturalezaAdapter;
    private int offset;
    private boolean aptoParaCargar;
    private static final String TAG = "POKEDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naturaleza);
        //Up Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rvNaturaleza);
        listaNaturalezaAdapter = new ListaNaturalezaAdapter(this);
        recyclerView.setAdapter(listaNaturalezaAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItem = layoutManager.findFirstVisibleItemPosition();
                    if (aptoParaCargar) {
                        Log.i(TAG, "Llegamos al final.");
                        aptoParaCargar = false;
                        offset +=5;
                        obtenerDatos(offset);
                    }
                }
            }
        });
        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset) {
        NatuapiService service = retrofit.create(NatuapiService.class);
        Call<NaturalezaRespuesta> naturalezaRespuestaCall = service.obtenerListaNaturaleza(10, offset);
        naturalezaRespuestaCall.enqueue(new Callback<NaturalezaRespuesta>() {
            @Override
            public void onResponse(Call<NaturalezaRespuesta> call, Response<NaturalezaRespuesta> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {
                    NaturalezaRespuesta naturalezaRespuesta = response.body();
                    ArrayList<naturaleza> listaNaturaleza = naturalezaRespuesta.getResults();
                    listaNaturalezaAdapter.adicionarListaNaturaleza(listaNaturaleza);
                }
                else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<NaturalezaRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}