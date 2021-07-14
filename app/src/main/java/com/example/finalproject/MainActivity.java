package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText eml;
    EditText pass;
    EditText nam;
    //membuat Variabel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //untuk menampilkkan tampilan id
        findAllViewsId();
        button.setOnClickListener(this);
    }

    private void findAllViewsId() {
        button = (Button) findViewById(R.id.input);
        eml = (EditText) findViewById(R.id.ETemail);
        pass = (EditText) findViewById(R.id.ETpass);
        nam = (EditText) findViewById(R.id.ETnam);
        //membuat method data
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);//untuk membuat obyek bundle dan menyisipkan tipe data string ke obyek
        Bundle bundle = new Bundle();
        bundle.putString("Nama", nam.getText().toString());
        bundle.putString("pasword", pass.getText().toString());
        bundle.putString("Email", eml.getText().toString());
        intent.putExtras(bundle); //untuk menambahkan bundle intent
        startActivity(intent); //untu melakukkan proses menghubungkkan halaman selanjutnya
        {
            //method data peringatan yang akan digunakkan
            nam.setError("Nama Mohon Diisi");
            pass.setError("Password Mohon Diisi");
            eml.setError("Email Mohon Diisi");
        }
        Toast.makeText(getApplicationContext(),"Masuk Berhasil", Toast.LENGTH_SHORT).show(); //menampilkkan toast peringatan dari method yang digunakan
    }
}