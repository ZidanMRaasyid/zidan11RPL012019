package com.example.zidan11rpl012019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zidan11rpl012019.api.ApiClient;
import com.example.zidan11rpl012019.api.ApiInterface;
import com.example.zidan11rpl012019.model.Login.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    EditText etUsername,etPass;
    Button btnLogin;
    TextView tvRegister;
    String username,password;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("login", MODE_PRIVATE);

        etUsername = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPass);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                username = etUsername.getText().toString();
                password = etPass.getText().toString();
                login(username, password);

                editor = pref.edit();
                editor.putString("username", etUsername.getText().toString());
                editor.putString("status", "login");
                editor.apply();
                break;
        case R.id.tvRegister:
            Intent intent= new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void login(String username, String password) {
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Login> loginCall =apiInterface.loginResponse(username,password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
                    Toast.makeText(LoginActivity.this, response.body().getLoginData().getName(), Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(LoginActivity.this,Adapterrv.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
