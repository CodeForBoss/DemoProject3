package com.example.demoproject3;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIClient {

    @FormUrlEncoded
    @POST("auth/realms/res/protocol/openid-connect/token")
    Call<ResponseBody> sendData(
      @Field("username") String userName,
      @Field("password") String password,
      @Field("client_id") String clientId,
      @Field("client_secret") String clientSecret,
      @Field("grant_type") String grantType
    );
}
