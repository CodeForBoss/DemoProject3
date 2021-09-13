package com.example.demoproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
     private static final String clientId = "admin-cli";
     private static final String clientSecret = "0406a491-3e16-46a5-8108-cbe73fa16c11";
     private static final String grantType = "password";

     Button login;
     EditText username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usernameId);
        pass = findViewById(R.id.passwordId);
        login = findViewById(R.id.loginId);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString();
                String password = pass.getText().toString();
                if(!userName.isEmpty() && !password.isEmpty()){
                    executeRequest(userName,password,clientId,clientSecret,grantType);
                } else {
                    Toast.makeText(MainActivity.this, "Enter username and password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                   Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
               } else if(response.code() == 401){
                   Toast.makeText(MainActivity.this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "connection failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}