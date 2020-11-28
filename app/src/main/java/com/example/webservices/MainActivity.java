package com.example.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity {

    private Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.Btn_logueo);
        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        EditText txtpass = (EditText) findViewById(R.id.txtPass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SaludoActivity.class);
                b = new Bundle();
                b.putString("usr",   txtNombre.getText().toString());
                b.putString("pass",   txtpass.getText().toString());
                intent.putExtras(b);
                startActivityForResult(intent, 0);
            }
        });
    }
}