package com.example.lab13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LlamarActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference reference = database.getReference("message");
    private Button agregarButton,signOutBtn;
    private EditText numContactoEdit,nomContactoEdit;
    private ContactoAdapter adapter;
    private ListView usuarioList;
    private String idUsuario;
    private Usuario user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamar);

        if(auth.getCurrentUser() == null){
            goToLogin();
        }
        else{
            signOutBtn = findViewById(R.id.signoutBtn);
            recoverUser();
            signOutBtn.setOnClickListener(
                    (v)->{
                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("CerrarSession")
                                .setMessage("Estas seguro?")
                                .setNegativeButton("NO",(dialog,id)->{
                                    dialog.dismiss();
                                })
                                .setPositiveButton("si",(dialog,id)->{
                                    auth.signOut();
                                    finish();
                                });
                        builder.show();



                    }
            );
        }

        numContactoEdit=findViewById(R.id.numContactoEdit);
        nomContactoEdit=findViewById(R.id.nomContactoEdit);
        agregarButton=findViewById(R.id.agregarButton);
        adapter = new ContactoAdapter();
        usuarioList.setAdapter(adapter);
        usuarioList=findViewById(R.id.lista);
        idUsuario = getIntent().getExtras().getString("userid");




        loadDatabase();


        agregarButton.setOnClickListener(
                (v)->{
                    String id =  database.getReference().child("contactos").child(idUsuario).push().getKey(); //para escribirse como firebase
                    DatabaseReference reference = database.getReference().child("contactos").child(idUsuario).child(id);
                    Contacto contactos = new Contacto(
                            id,
                            idUsuario,
                            nomContactoEdit.getText().toString(),
                            numContactoEdit.getText().toString()
                    );
                    reference.setValue(contactos);

                }
        );





    }


private void goToLogin(){
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();

}

    private void recoverUser(){
        if(auth.getCurrentUser() !=null){
            String id = auth.getCurrentUser().getUid();
            database.getReference().child("user").child(id).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            user = snapshot.getValue(Usuario.class);

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    }
            );
        }

    }



    public void loadDatabase(){
        reference= database.getReference().child("user").child(idUsuario);
        reference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        adapter.clear();
                        for (DataSnapshot child :snapshot.getChildren() ) {
                            Contacto contacto =child.getValue(Contacto.class);
                            adapter.agregarUser(contacto);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }

        );
    }


}