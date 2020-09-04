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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    EditText etUsername,etPass;
    Button btnLogin;
    TextView tvRegister;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("login", MODE_PRIVATE);
        etUsername =(EditText)findViewById(R.id.etUsername);
        etPass = (EditText)findViewById(R.id.etPass);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().equalsIgnoreCase("Zidan") &&
                etPass.getText().toString().equalsIgnoreCase("123")){
                    editor = pref.edit();
                editor.putString("username", etUsername.getText().toString());
                editor.putString("status", "login");
                editor.apply();

                startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }
            }
        });
    }

}
