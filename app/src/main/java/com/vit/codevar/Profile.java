package com.vit.codevar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView fullNameTextView, userNameTextView, levelTextView, activenessTextView, helpfulnessTextView, intelligenceTextView;
    private SwipeRefreshLayout swipeLayout;
    private CoordinatorLayout view;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.codepark.in/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        profileImageView = findViewById(R.id.profileImageView);
        fullNameTextView = findViewById(R.id.fullNameTextView);
        userNameTextView = findViewById(R.id.usernameTextView);
        levelTextView = findViewById(R.id.levelTextView);
        activenessTextView = findViewById(R.id.activenessTextView);
        helpfulnessTextView = findViewById(R.id.helpfulnessTextView);
        intelligenceTextView = findViewById(R.id.intelligenceTextView);
        swipeLayout = findViewById(R.id.swipeLayout);
        view = findViewById(R.id.layout);

        Glide
                .with(getApplicationContext())
                .load(LoginActivity.profileImage)
                .into(profileImageView);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeSendFeedbackForm(LoginActivity.email,LoginActivity.password);
            }
        });

        fullNameTextView.setText(LoginActivity.fullName);
        userNameTextView.setText(String.format("@%s", LoginActivity.userName));
        levelTextView.setText(LoginActivity.level);
        activenessTextView.setText(LoginActivity.activeness);
        helpfulnessTextView.setText(LoginActivity.helpfulness);
        intelligenceTextView.setText(LoginActivity.intelligence);
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

                if(code.equals("0"))
                {
                    LoginActivity.cookie = account.getCookies().getCP();
                    Log.i("INFO",LoginActivity.cookie);
                    final SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
                    final Gson gson = new Gson();

                    String json = gson.toJson(email);
                    sharedPreferences.edit().putString("email",json).apply();
                    json = gson.toJson(password);
                    sharedPreferences.edit().putString("password",json).apply();

                    Call<PersonalDetails> call1 = api.getInfo("Bearer "+LoginActivity.cookie);

                    call1.enqueue(new Callback<PersonalDetails>() {
                        @Override
                        public void onResponse(Call<PersonalDetails> call, Response<PersonalDetails> response) {
                            PersonalDetails personalDetails = response.body();
                            int detailsCode = personalDetails.getCode();

                            Log.i("INFO",String.valueOf(detailsCode));

                            LoginActivity.fullName = personalDetails.getUserData().getName().getFullName();
                            LoginActivity.userName = personalDetails.getUserData().getUsername();
                            LoginActivity.profileImage = personalDetails.getUserData().getProfile_image();
                            LoginActivity.level = personalDetails.getUserData().getStats().getLevel();
                            LoginActivity.intelligence = personalDetails.getUserData().getStats().getIntelligence();
                            LoginActivity.helpfulness = personalDetails.getUserData().getStats().getHelpfulness();
                            LoginActivity.activeness = personalDetails.getUserData().getStats().getActiveness();

                            Glide
                                    .with(getApplicationContext())
                                    .load(LoginActivity.profileImage)
                                    .into(profileImageView);

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
                            json = gson.toJson(LoginActivity.logInStatus);
                            sharedPreferences.edit().putString("logInStatus",json).apply();
                            if (swipeLayout.isRefreshing())
                            {
                                swipeLayout.setRefreshing(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<PersonalDetails> call, Throwable t) {
                            Log.i("INFO",t.getMessage());
                            if (swipeLayout.isRefreshing())
                            {
                                swipeLayout.setRefreshing(false);
                            }
                        }
                    });
                }
                else
                {
                    Snackbar.make(view,message,Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.WHITE).show();
                    Log.i("INFO",message);
                    if (swipeLayout.isRefreshing())
                    {
                        swipeLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.i("INFO",t.getMessage());
                Snackbar.make(view,"Error occurred! Try again",Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.WHITE).show();
                if (swipeLayout.isRefreshing())
                {
                    swipeLayout.setRefreshing(false);
                }
            }

        });
    }
}
