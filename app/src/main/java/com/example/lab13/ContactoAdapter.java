package com.example.lab13;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter {
    public ArrayList<Contacto> usuarioList;

    public ContactoAdapter(){
        usuarioList = new ArrayList<>();
    }

    public void agregarUser(Contacto contacto){
        usuarioList.add(contacto);
        notifyDataSetChanged();

    }



    @Override
    public int getCount() {
        return usuarioList.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View renglon, ViewGroup lista) {
        LayoutInflater inflater = LayoutInflater.from(lista.getContext());
        View renglonView = inflater.inflate(R.layout.row,null);
        Contacto contacto = usuarioList.get(pos);

        TextView nombreContactoRow = renglonView.findViewById(R.id.nombreContacto);
        TextView numeroContactoRow = renglonView.findViewById(R.id.numeroContacto);
        Button llamarBtn = renglonView.findViewById(R.id.llamarButton);

        nombreContactoRow.setText(contacto.getNombreContacto());
        numeroContactoRow.setText(contacto.getNumeroContacto());


        llamarBtn.setOnClickListener(
                (v)->{
                    ActivityCompat.requestPermissions((Activity) renglon.getContext(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                    String tele = "tel:" + usuarioList.get(pos).getNumeroContacto();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(tele));
                    renglon.getContext().startActivity(intent);


                }
        );



        return renglonView;
    }

    public void clear(){

        usuarioList.clear();
        notifyDataSetChanged();

    }
}
