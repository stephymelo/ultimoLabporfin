package com.example.lab13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private EditText passwordEdit,correoEditLogin;
    private TextView signupLink;
    private Button ingresarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passwordEdit = findViewById(R.id.passwordEdit);
        correoEditLogin = findViewById(R.id.correoEditLogin);
        signupLink = findViewById(R.id.signupLink);
        ingresarBtn = findViewById(R.id.ingresarBtn);
        ingresarBtn.setOnClickListener(this);
        signupLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ingresarBtn:
                auth.signInWithEmailAndPassword(correoEditLogin.getText().toString(),passwordEdit.getText().toString()).addOnCompleteListener(
                  task -> {
                      if(task.isSuccessful()){
                          Intent i = new Intent(this,LlamarActivity.class);
                          startActivity(i);
                          finish();


                      }else{
                          Toast.makeText(this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                      }
                  }
                );

                break;

            case R.id.signupLink:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                break;
        }

    }
}