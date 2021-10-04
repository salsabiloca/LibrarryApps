package com.example.e_perpus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_perpus.adapter.AlertDialogManager;
import com.example.e_perpus.adapter.SessionManager;

public class Home extends AppCompatActivity {
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        btnLogout = findViewById(R.id.out);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog dialog = new AlertDialog.Builder(Home.this)
                        .setTitle("Anda yakin ingin keluar?").setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();

            }
        });
    }
    public void PinjamBuku(View v) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void ContactUs(View v) {
        Intent i = new Intent(this, ContactActivity.class);
        startActivity(i);
    }
}