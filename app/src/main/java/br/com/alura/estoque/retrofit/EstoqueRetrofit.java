package br.com.alura.estoque.retrofit;

import retrofit2.Retrofit;

public class EstoqueRetrofit {

    public EstoqueRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.251:8080/")
                .build();
    }
}
