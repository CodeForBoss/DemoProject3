package com.example.demoproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
     private static final String clientId = "admin-cli";
     private static final String clientSecret = "0406a491-3e16-46a5-8108-cbe73fa16c11";
     private static final String grantType = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userName = "res";
        String password = "res";
        executeRequest(userName,password,clientId,clientSecret,grantType);
    }
    public void executeRequest(String userName,String password, String clientId, String clientSecret, String grantType){
        APIClient apiClient = RetrofitRest.getRetroFit().create(APIClient.class);

        Call<ResponseBody> call = apiClient.sendData(
                userName,password,clientId,clientSecret,grantType
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               if(response.code()==200){
                   Log.d("anisur","success");
               } else if(response.code() == 401){
                   Log.d("anisur","unauthorized");
               }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}