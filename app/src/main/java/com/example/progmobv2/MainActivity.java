package com.example.progmobv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtNamaLengkap;
    TextView txtTglSewa;
    TextView txtUmur;
    TextView txtGender;
    TextView txtPenyewaan;
    String namaLengkap;
    String TglSewa;
    String Umur;
    String Gender;
    String Penyewaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaLengkap = getIntent().getExtras().getString("namaLengkap");
        TglSewa = getIntent().getExtras().getString("TglLahir");
        Umur = getIntent().getExtras().getString("Umur");
        Gender = getIntent().getExtras().getString("Gender");
        Penyewaan = getIntent().getExtras().getString("Service");

        txtNamaLengkap = findViewById(R.id.isi_namalengkap);
        txtTglSewa = findViewById(R.id.isi_tgl_sewa);
        txtUmur = findViewById(R.id.isi_umur);
        txtGender = findViewById(R.id.isi_gender);
        txtPenyewaan = findViewById(R.id.isi_penyewaan);

        txtNamaLengkap.setText(namaLengkap);
        txtTglSewa.setText(TglSewa);
        txtUmur.setText(Umur);
        txtGender.setText(Gender);
        txtPenyewaan.setText(Penyewaan);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Menampilkan Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"Menjeda Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this," Memulai Activity Kembali", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Menghancurkan activity", Toast.LENGTH_SHORT).show();
    }
}