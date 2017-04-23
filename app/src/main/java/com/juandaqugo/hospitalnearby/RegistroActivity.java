package com.juandaqugo.hospitalnearby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    EditText nombre, documento, telefono, contrasena, r_contrasena, alergia, enfermedad, correo;
    EditText acudiente, t_acudiente, usuario;
    Button enviar, cancelar;
    RadioButton masculino, femenino, apos, aneg, bpos, bneg, opos, oneg, abpos, abneg;
    String aux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = (EditText) findViewById(R.id.enombre);
        usuario = (EditText) findViewById(R.id.enombre_usuario);
        documento = (EditText) findViewById(R.id.edocumento);
        telefono = (EditText) findViewById(R.id.etelefono);
        correo = (EditText) findViewById(R.id.ecorreo);
        contrasena = (EditText) findViewById(R.id.econtrasenar);
        r_contrasena = (EditText) findViewById(R.id.econtrasenarep);
        alergia = (EditText) findViewById(R.id.ealergias);
        enfermedad = (EditText) findViewById(R.id.eenfermedades);
        acudiente = (EditText) findViewById(R.id.enombre_acudiente);
        t_acudiente = (EditText) findViewById(R.id.etelefono_acudiente);
        enviar = (Button) findViewById(R.id.benviar);
        cancelar  = (Button) findViewById(R.id.bcancelar);
        masculino = (RadioButton) findViewById(R.id.rmasculino);
        femenino = (RadioButton) findViewById(R.id.rfemenino);
        apos = (RadioButton) findViewById(R.id.r_apos);
        aneg = (RadioButton) findViewById(R.id.r_aneg);
        bpos = (RadioButton) findViewById(R.id.r_bpos);
        bneg = (RadioButton) findViewById(R.id.r_bneg);
        opos = (RadioButton) findViewById(R.id.r_opos);
        oneg = (RadioButton) findViewById(R.id.r_oneg);
        abpos = (RadioButton) findViewById(R.id.r_abpos);
        abneg = (RadioButton) findViewById(R.id.r_abneg);
        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                Intent intent = new Intent();
                if(nombre.getText().toString().equals("") || documento.getText().toString().equals("") ||
                        telefono.getText().toString().equals("") || contrasena.getText().toString().equals("") ||
                        r_contrasena.getText().toString().equals("") || correo.getText().toString().equals("") ||
                        usuario.getText().toString().equals("") || acudiente.getText().toString().equals("") ||
                        t_acudiente.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(),"Llene los campos requeridos",Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, intent);
                } else if(!(contrasena.getText().toString().equals(r_contrasena.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"Contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, intent);
                }else {
                    if(apos.isChecked()){
                        aux="A+";
                    }else if(aneg.isChecked()){
                        aux="A-";
                    }else if(bpos.isChecked()){
                        aux="B+";
                    }else if(bneg.isChecked()){
                        aux="B-";
                    }else if(opos.isChecked()){
                        aux="O+";
                    }else if(oneg.isChecked()){
                        aux="O-";
                    }else if(abpos.isChecked()){
                        aux="AB+";
                    }else if(abneg.isChecked()){
                        aux="AB-";
                    }
                    intent.putExtra("aux", aux);
                    intent.putExtra("nombre", nombre.getText().toString());
                    intent.putExtra("usuario", usuario.getText().toString());
                    intent.putExtra("documento", documento.getText().toString());
                    intent.putExtra("correo", correo.getText().toString());
                    intent.putExtra("contrasena", contrasena.getText().toString());
                    intent.putExtra("alergias", alergia.getText().toString());
                    intent.putExtra("enfermedades", enfermedad.getText().toString());
                    intent.putExtra("tacudiente", t_acudiente.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}