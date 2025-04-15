package com.example.mojprofil;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView email, haslo, info;
    Button btn;
    boolean clicked = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        haslo = findViewById(R.id.haslo);
        info = findViewById(R.id.info);
        btn = findViewById(R.id.btn);
        info.setText("Witaj! Aplikacja wykonana przez: Renni");

        btn.setOnClickListener(v->{
            firstAlert();
        });

        if(clicked){
          secondAlert();
        }

    }
    public void firstAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Podaj nowy email:")
                .setTitle("Zmień Email");
        final EditText input = new EditText(this);
        input.setHint("Nowy email");
        builder.setView(input);

        builder.setPositiveButton("Zapisz", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
            secondAlert();
        });

        builder.setNegativeButton("Anuluj", (DialogInterface.OnClickListener) (dialog, which) -> {
            clicked = true;
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void secondAlert(){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
        builder2.setMessage("Podaj nowe hasło:")
                .setTitle("Zmień Hasło");
        final EditText inputHaslo1 = new EditText(this);
        builder2.setView(inputHaslo1);
        final EditText inputHaslo2 = new EditText(this);
        builder2.setView(inputHaslo2);

        builder2.setPositiveButton("Zapisz", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });

        builder2.setNegativeButton("Anuluj", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog2 = builder2.create();
        alertDialog2.show();
    }

}