package com.joseph.nontonbareng.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joseph.nontonbareng.R;
import com.joseph.nontonbareng.config.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etUsername, etPassword, etRepassword;
    Button btnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etRepassword = findViewById(R.id.etRepassword);
        btnRegister = findViewById(R.id.btnRegister);

        TextView tvRegister = findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString().trim();
                String repassword = etRepassword.getText().toString().trim();

                if (!password.equals(repassword)) {
                    Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                } else if (etName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Error: Name cant blank!", Toast.LENGTH_SHORT).show();
                } else if (etUsername.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Error: Username cant blank!", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Error: Password cant blank!", Toast.LENGTH_SHORT).show();
                } else if (etRepassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Error: Password cant blank!", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.regisUser(etName.getText().toString().trim(), etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                    etName.setText("");
                    etUsername.setText("");
                    etPassword.setText("");
                    etRepassword.setText("");
                    Toast.makeText(RegisterActivity.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
