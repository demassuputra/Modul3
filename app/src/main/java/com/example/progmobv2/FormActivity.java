package com.example.progmobv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FormActivity extends AppCompatActivity {
    TextView txtNamaLengkap;
    EditText inputtglsewa;
    DatePickerDialog.OnDateSetListener setListener;
    SeekBar seekBar;
    TextView lbl_umur;
    String nilaiUmur;
    Button btnDaftar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    CheckBox mPancingFullset, mTasPancing, m1SetKailPancing, mJoranPancing, mReelPancing, mLainnya;
    String mResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        //mengambil id di layout
        txtNamaLengkap = findViewById(R.id.input_namalengkap);
        inputtglsewa = findViewById(R.id.input_tgl_sewa);
        seekBar = findViewById(R.id.seekbar);
        lbl_umur = findViewById(R.id.lbl_umur);
        btnDaftar = findViewById(R.id.daftar);
        radioGroup = findViewById(R.id.radioGroupGender);
        mPancingFullset = findViewById(R.id.pancing_fullset);
        mTasPancing = findViewById(R.id.tas_pancing);
        m1SetKailPancing = findViewById(R.id.satu_set_kail_pancing);
        mJoranPancing = findViewById(R.id.joran_pancing);
        mReelPancing = findViewById(R.id.reel_pancing);
        mLainnya = findViewById(R.id.lainnya);





        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        inputtglsewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        inputtglsewa.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { //method listener untuk seekbar
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilaiUmur = String.valueOf(i);
                lbl_umur.setText("Umur : " + nilaiUmur);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() { //method button
            @Override
            public void onClick(View view) {

                mResult ="";
                //checkbox
                if(mPancingFullset.isChecked()){
                    mResult += mPancingFullset.getText().toString()+"\n";
                }
                if(mTasPancing.isChecked()){
                    mResult += mTasPancing.getText().toString()+"\n";
                }
                if(m1SetKailPancing.isChecked()){
                    mResult += m1SetKailPancing.getText().toString()+"\n";
                }
                if(mJoranPancing.isChecked()){
                    mResult += mJoranPancing.getText().toString()+"\n";
                }
                if(mReelPancing.isChecked()){
                    mResult += mReelPancing.getText().toString()+"\n";
                }
                if(mLainnya.isChecked()){
                    mResult += mLainnya.getText().toString()+"\n";
                }
                //radio button
                int radioId =radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
                builder.setIcon(R.drawable.warning);
                builder.setTitle("Daftarkan");
                builder.setMessage(
                        "Apakah anda sudah yakin dengan data anda ?\n\n" +
                                "Nama Lengkap : \n" + txtNamaLengkap.getText() + "\n\n" +
                                "Tanggal Sewa : \n" + inputtglsewa.getText() + "\n\n" +
                                "Umur : \n" + nilaiUmur + "\n\n" +
                                "Gender :\n" +radioButton.getText() + "\n\n" +
                                "Penyewaan Yang di Pilih :\n" +mResult + ""
                );

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() { //method button positive desicion
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Data anda berhasil terdaftarkan !", Toast.LENGTH_SHORT).show();

                        Intent layout2 = new Intent(FormActivity.this, MainActivity.class);

                        layout2.putExtra("namaLengkap", txtNamaLengkap.getText().toString());
                        layout2.putExtra("TglLahir", inputtglsewa.getText().toString());
                        layout2.putExtra("Umur", nilaiUmur);
                        layout2.putExtra("Gender", radioButton.getText().toString());
                        layout2.putExtra("Service", mResult);

                        startActivity(layout2);

                        finish();
                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() { //method button negative desicion
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create(); //method get alert dan create alert
                alertDialog.show(); //to show alert
            }
        });

    }
}