package com.example.zidan11rpl012019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
   EditText etUsername,etPass,etName;
   TextView tvLogin;
   Button btnReg;
   String username, password, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etRegUsername);
        etPass = findViewById(R.id.etRegPass);
        etName = findViewById(R.id.etRegName);

        tvLogin = findViewById(R.id.tvLoginHere);
        tvLogin.setOnClickListener(this);

        btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReg:
                username = etUsername.getText().toString();
                password = etPass.getText().toString();
                name = etName.getText().toString();
                register(username, password,name);
                break;

            case  R.id.tvLoginHere:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void register(String username, String password, String name) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
