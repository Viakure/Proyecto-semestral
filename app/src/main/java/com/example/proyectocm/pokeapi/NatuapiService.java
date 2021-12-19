package com.example.proyectocm.pokeapi;

import com.example.proyectocm.models.NaturalezaRespuesta;
import com.example.proyectocm.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NatuapiService {

    @GET("nature")
    Call<NaturalezaRespuesta> obtenerListaNaturaleza(@Query("limit") int limit, @Query("offset") int offset);

}
