package com.vit.codevar;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("auth/verifyUser")
    Call<Account> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("u/basic")
    Call<PersonalDetails> getInfo(@Header("Authorization") String authToken);
}
