package com.vit.codevar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity
{
    private TextInputEditText emailEditText, passwordEditText;
    public static String email, password, cookie, profileImage, userName, fullName, level, intelligence, helpfulness, activeness;
    private MaterialButton logInButton;
    private CoordinatorLayout view;
    public static Boolean logInStatus;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private TextView textView2;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.codepark.in/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setStatusBarColor(getApplicationContext().getColor(R.color.background));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        logInButton = findViewById(R.id.logInButton);
        view = findViewById(R.id.layout);
        progressBar = findViewById(R.id.progressBar);
        linearLayout = findViewById(R.id.linearLayout);
        textView2 = findViewById(R.id.textView2);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEditText.getText().toString().toLowerCase();
                password = passwordEditText.getText().toString();

                hideUI();
                executeSendFeedbackForm(email,password);
            }
        });
    }

    private void hideUI()
    {
        float x = 0.3f;
        progressBar.setVisibility(View.VISIBLE);
        emailEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
        logInButton.setEnabled(false);
        logInButton.setAlpha(x);
        linearLayout.setAlpha(x);
        textView2.setAlpha(x);
    }

    private void showUI()
    {
        float x = 1.0f;
        progressBar.setVisibility(View.INVISIBLE);
        emailEditText.setEnabled(true);
        passwordEditText.setEnabled(true);
        logInButton.setEnabled(true);
        logInButton.setAlpha(x);
        linearLayout.setAlpha(x);
        textView2.setAlpha(x);
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
                    logInStatus = true;
                    cookie = account.getCookies().getCP();
                    Log.i("INFO",cookie);
                    final SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
                    final Gson gson = new Gson();

                    String json = gson.toJson(email);
                    sharedPreferences.edit().putString("email",json).apply();
                    json = gson.toJson(password);
                    sharedPreferences.edit().putString("password",json).apply();

                    Call<PersonalDetails> call1 = api.getInfo("Bearer "+cookie);

                    call1.enqueue(new Callback<PersonalDetails>() {
                        @Override
                        public void onResponse(Call<PersonalDetails> call, Response<PersonalDetails> response) {
                            PersonalDetails personalDetails = response.body();
                            int detailsCode = personalDetails.getCode();

                            Log.i("INFO",String.valueOf(detailsCode));

                            fullName = personalDetails.getUserData().getName().getFullName();
                            userName = personalDetails.getUserData().getUsername();
                            profileImage = personalDetails.getUserData().getProfile_image();
                            level = personalDetails.getUserData().getStats().getLevel();
                            intelligence = personalDetails.getUserData().getStats().getIntelligence();
                            helpfulness = personalDetails.getUserData().getStats().getHelpfulness();
                            activeness = personalDetails.getUserData().getStats().getActiveness();

                            String json = gson.toJson(profileImage);
                            sharedPreferences.edit().putString("profileImage",json).apply();
                            json = gson.toJson(userName);
                            sharedPreferences.edit().putString("userName",json).apply();
                            json = gson.toJson(fullName);
                            sharedPreferences.edit().putString("fullName",json).apply();
                            json = gson.toJson(level);
                            sharedPreferences.edit().putString("level",json).apply();
                            json = gson.toJson(intelligence);
                            sharedPreferences.edit().putString("intelligence",json).apply();
                            json = gson.toJson(helpfulness);
                            sharedPreferences.edit().putString("helpfulness",json).apply();
                            json = gson.toJson(activeness);
                            sharedPreferences.edit().putString("activeness",json).apply();
                            Log.i("INFO",profileImage);
                            Log.i("INFO",userName);
                            Log.i("INFO",fullName);
                            Log.i("INFO",String.valueOf(level));
                            Log.i("INFO",String.valueOf(intelligence));
                            Log.i("INFO",String.valueOf(activeness));
                            Log.i("INFO",String.valueOf(helpfulness));
                            json = gson.toJson(logInStatus);
                            sharedPreferences.edit().putString("logInStatus",json).apply();

                            showUI();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<PersonalDetails> call, Throwable t) {
                            Log.i("INFO",t.getMessage());
                            showUI();
                        }
                    });
                }
                else
                {
                    Snackbar.make(view,message,Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.WHITE).show();
                    Log.i("INFO",message);
                    showUI();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.i("INFO",t.getMessage());
                Snackbar.make(view,"Error occurred! Try again",Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.WHITE).show();
                showUI();
            }
        });
    }
}