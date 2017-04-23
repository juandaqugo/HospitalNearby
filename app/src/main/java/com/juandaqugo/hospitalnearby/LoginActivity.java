package com.juandaqugo.hospitalnearby;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText usuario, contrasena;
    Button iniciar, registrar, emergencia;
    String susuario, aux, nombre, documento, correo, scontrasena, alergia, enfermedad, t_acudiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor = prefs.edit();

        susuario = prefs.getString("usuario", "nousuario");
        aux = prefs.getString("aux", "noaux");
        nombre = prefs.getString("nombre", "nonombre");
        documento = prefs.getString("documento", "nodocumento");
        correo = prefs.getString("correo", "nocorreo");
        scontrasena = prefs.getString("contrasena", "nocontrasena");
        alergia = prefs.getString("alergias", "noalergias");
        enfermedad = prefs.getString("enfermedades", "noenfermedades");
        t_acudiente = prefs.getString("tacudiente", "notacudiente");

        if(prefs.getInt("login", -1) == 1) {
            intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
            intent.putExtra("aux", aux);
            intent.putExtra("nombre", nombre);
            intent.putExtra("usuario", susuario);
            intent.putExtra("documento", documento);
            intent.putExtra("correo", correo);
            intent.putExtra("contrasena", scontrasena);
            intent.putExtra("alergias", alergia);
            intent.putExtra("enfermedades", enfermedad);
            intent.putExtra("tacudiente", t_acudiente);
            startActivity(intent);
            finish();
        }

        usuario = (EditText) findViewById(R.id.eusuario);
        contrasena = (EditText) findViewById(R.id.econtrasena);
        iniciar = (Button) findViewById(R.id.biniciar);
        registrar = (Button) findViewById(R.id.bregistrese);

        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this ,RegistroActivity.class);
                startActivityForResult(intent, 1234);
            }
        });
        iniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(usuario.getText().toString().equals("") || contrasena.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Llene los campos requeridos",Toast.LENGTH_SHORT).show();
                }else if(!(usuario.getText().toString().equals(susuario) && contrasena.getText().toString().equals(scontrasena))){
                    Toast.makeText(getApplicationContext(),"Usuario no registrado",Toast.LENGTH_SHORT).show();
                }else if(usuario.getText().toString().equals(susuario) && contrasena.getText().toString().equals(scontrasena)){
                    editor.putInt("login",1);
                    editor.commit();
                    intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
                    intent.putExtra("aux", aux);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("usuario", susuario);
                    intent.putExtra("documento", documento);
                    intent.putExtra("correo", correo);
                    intent.putExtra("contrasena", scontrasena);
                    intent.putExtra("alergias", alergia);
                    intent.putExtra("enfermedades", enfermedad);
                    intent.putExtra("tacudiente", t_acudiente);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode==RESULT_OK){
            susuario = data.getExtras().getString("usuario");
            aux = data.getExtras().getString("aux");
            nombre = data.getExtras().getString("nombre");
            documento = data.getExtras().getString("documento");
            correo = data.getExtras().getString("correo");
            scontrasena = data.getExtras().getString("contrasena");
            alergia = data.getExtras().getString("alergias");
            enfermedad = data.getExtras().getString("enfermedades");
            t_acudiente = data.getExtras().getString("tacudiente");

            editor.putString("usuario", susuario);
            editor.putString("aux", aux);
            editor.putString("nombre", nombre);
            editor.putString("documento", documento);
            editor.putString("correo", correo);
            editor.putString("contrasena", scontrasena);
            editor.putString("alergias", alergia);
            editor.putString("enfermedades", enfermedad);
            editor.putString("tacudiente", t_acudiente);
        }else{
            if(requestCode==1234 && resultCode==RESULT_CANCELED){
                Toast.makeText(this, "ERROR en Registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
