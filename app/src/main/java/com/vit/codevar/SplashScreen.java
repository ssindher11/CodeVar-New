package com.vit.codevar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen extends AppCompatActivity
{
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.codepark.in/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(getApplicationContext().getColor(R.color.background));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<Boolean>(){}.getType();
        String json = sharedPreferences.getString("logInStatus", "false");
        LoginActivity.logInStatus = gson.fromJson(json, type);



        if (LoginActivity.logInStatus) {

            Type type1 = new TypeToken<String>(){}.getType();
            json = sharedPreferences.getString("email"," ");
            LoginActivity.email = gson.fromJson(json,type1);
            json = sharedPreferences.getString("password"," ");
            LoginActivity.password = gson.fromJson(json,type1);

            executeSendFeedbackForm(LoginActivity.email, LoginActivity.password);
        }
        else
        {
            CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    //if ()
                    //{
                    //Intent sIntent = new Intent(SplashScreen.this, Login.class);
                    //startActivity(sIntent);
                    //SplashScreen.this.finish();
                    //}
                    //else
                    //{
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    //}
                }
            };
            countDownTimer.start();
        }
    }
    private void executeSendFeedbackForm(final String email, final String password){
        final API api = retrofit.create(API.class);

        Call<Account> call = api.userLogin(
                email,
                password
        );

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Log.i("INFO","Response Recvd");
                Account account = response.body();
                String code = account.getCode();
                String message = account.getMessage();
                Log.i("INFO",code);
                Log.i("INFO",message);

                if(code.equals("0"))
                {
                    LoginActivity.cookie = account.getCookies().getCP();
                    Log.i("INFO",LoginActivity.cookie);
                    final SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
                    final Gson gson = new Gson();

                    Call<PersonalDetails> call1 = api.getInfo("Bearer "+LoginActivity.cookie);

                    call1.enqueue(new Callback<PersonalDetails>() {
                        @Override
                        public void onResponse(Call<PersonalDetails> call, Response<PersonalDetails> response) {
                            PersonalDetails personalDetails = response.body();

                            LoginActivity.fullName = personalDetails.getUserData().getName().getFullName();
                            LoginActivity.userName = personalDetails.getUserData().getUsername();
                            LoginActivity.profileImage = personalDetails.getUserData().getProfile_image();
                            LoginActivity.level = personalDetails.getUserData().getStats().getLevel();
                            LoginActivity.intelligence = personalDetails.getUserData().getStats().getIntelligence();
                            LoginActivity.helpfulness = personalDetails.getUserData().getStats().getHelpfulness();
                            LoginActivity.activeness = personalDetails.getUserData().getStats().getActiveness();

                            String json = gson.toJson(LoginActivity.profileImage);
                            sharedPreferences.edit().putString("profileImage",json).apply();
                            json = gson.toJson(LoginActivity.userName);
                            sharedPreferences.edit().putString("userName",json).apply();
                            json = gson.toJson(LoginActivity.fullName);
                            sharedPreferences.edit().putString("fullName",json).apply();
                            json = gson.toJson(LoginActivity.level);
                            sharedPreferences.edit().putString("level",json).apply();
                            json = gson.toJson(LoginActivity.intelligence);
                            sharedPreferences.edit().putString("intelligence",json).apply();
                            json = gson.toJson(LoginActivity.helpfulness);
                            sharedPreferences.edit().putString("helpfulness",json).apply();
                            json = gson.toJson(LoginActivity.activeness);
                            sharedPreferences.edit().putString("activeness",json).apply();
                            Log.i("INFO",LoginActivity.profileImage);
                            Log.i("INFO",LoginActivity.userName);
                            Log.i("INFO",LoginActivity.fullName);
                            Log.i("INFO",String.valueOf(LoginActivity.level));
                            Log.i("INFO",String.valueOf(LoginActivity.intelligence));
                            Log.i("INFO",String.valueOf(LoginActivity.activeness));
                            Log.i("INFO",String.valueOf(LoginActivity.helpfulness));

                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<PersonalDetails> call, Throwable t) {
                            Log.i("INFO",t.getMessage());
                            fail();
                        }
                    });
                }
                else
                {
                    fail();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.i("INFO",t.getMessage());
                fail();
            }
        });
    }

    void fail()
    {
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() {
                Log.i("INFO","FAIL");
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
                Gson gson = new Gson();
                Type type1 = new TypeToken<String>(){}.getType();
                String json = sharedPreferences.getString("userName"," ");
                LoginActivity.userName = gson.fromJson(json,type1);
                json = sharedPreferences.getString("fullName"," ");
                LoginActivity.fullName = gson.fromJson(json,type1);
                json = sharedPreferences.getString("profileImage"," ");
                LoginActivity.profileImage = gson.fromJson(json,type1);
                json = sharedPreferences.getString("level"," ");
                LoginActivity.level = gson.fromJson(json,type1);
                json = sharedPreferences.getString("helpfulness"," ");
                LoginActivity.helpfulness = gson.fromJson(json,type1);
                json = sharedPreferences.getString("activeness"," ");
                LoginActivity.activeness = gson.fromJson(json,type1);
                json = sharedPreferences.getString("intelligence"," ");
                LoginActivity.intelligence = gson.fromJson(json,type1);

                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }.start();
    }
}
