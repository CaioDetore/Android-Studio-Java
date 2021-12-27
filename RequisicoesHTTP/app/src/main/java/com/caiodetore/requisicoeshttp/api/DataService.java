package com.caiodetore.requisicoeshttp.api;

import com.caiodetore.requisicoeshttp.model.Foto;
import com.caiodetore.requisicoeshttp.model.Postagem;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagem();

    @POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);

    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem> salvarPostagem(
            @Field("userId") String userId,
            @Field("title") String title,
            @Field("body") String body
    );

    //substitui a postagem anterior
    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id") int id, @Body Postagem postagem );

    //atualiza a postagem com os campos que são passado aqui
    @PATCH("/posts/{id}")
    Call<Postagem> atualizarPostagemPatch(@Path("id") int id, @Body Postagem postagem );

    @DELETE("/posts/{id}")
    Call<Void> removerPostagem(@Path("id") int id);

}
