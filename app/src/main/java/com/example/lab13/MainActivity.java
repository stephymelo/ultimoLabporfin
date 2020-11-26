package com.example.lab13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button userButton;
    private EditText userName;

    private FirebaseAuth auth;
    private FirebaseDatabase db;

    private EditText nombreEdit,passwordEditSign,correoEdit;
    private TextView irLoginLink;
    private Button signupBtn;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userButton=findViewById(R.id.userButton);
        userName=findViewById(R.id.userTextEdit);
        userButton.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        passwordEditSign = findViewById(R.id.passwordEdit);
        correoEdit = findViewById(R.id.correoEdit);
        irLoginLink = findViewById(R.id.irLoginLink);




        loadDatabase();


    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.userButton:

                auth.createUserWithEmailAndPassword(correoEdit.getText().toString(),passwordEditSign.getText().toString())
                        .addOnCompleteListener(
                                task -> {
                                    if(task.isSuccessful()){
                                        String id= auth.getCurrentUser().getUid();
                                        Usuario user = new Usuario(
                                                id,
                                                userName.getText().toString(),
                                                passwordEditSign.getText().toString(),
                                                correoEdit.getText().toString()


                                        );
                                        db.getReference().child("user").child(id).setValue(user).addOnCompleteListener(
                                                taskdb->{
                                                    if(taskdb.isSuccessful()){
                                                        Intent i = new Intent(this,LlamarActivity.class);
                                                        startActivity(i);
                                                        finish();

                                                    }
                                                }
                                        );

                                    }else{
                                        Toast.makeText(this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }

                                }
                        );

                break;

            case R.id.irLoginLink:
                finish();
                break;
        }
    }




    public void loadDatabase(){

    }

}