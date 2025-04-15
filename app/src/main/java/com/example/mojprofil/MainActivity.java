package com.example.mojprofil;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    String newEmail;

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
            if(clicked == false) {
                firstAlert();
            }else{
                secondAlert();
            }
        });


    }
    public void firstAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Zmień Email");

        LinearLayout linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(50,50,50,50);

        TextView text1 = new TextView(MainActivity.this);
        text1.setText("Podaj nowy email:");
        linearLayout.addView(text1);


        EditText inputEmail = new EditText(MainActivity.this);
        inputEmail.setHint("Nowy email");
        linearLayout.addView(inputEmail);

        builder.setView(linearLayout);

        builder.setPositiveButton("Zapisz", (DialogInterface.OnClickListener) (dialog, which) -> {
            if(!inputEmail.getText().toString().contains("@")){
                dialog.dismiss();
                info.setText("Błąd: Nieprawidłowy format emaila.");
                info.setTextColor(Color.parseColor("#FF0000"));
            }else{
                newEmail = inputEmail.getText().toString();
                dialog.dismiss();
                secondAlert();
            }
        });

        builder.setNegativeButton("Anuluj", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        clicked = true;

        Button btnAnuluj = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        btnAnuluj.setBackgroundColor(Color.parseColor("#00BCD4"));
        btnAnuluj.setTextColor(Color.parseColor("#FFFFFF"));

        Button btnZapisz = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        btnZapisz.setBackgroundColor(Color.parseColor("#00BCD4"));
        btnZapisz.setTextColor(Color.parseColor("#FFFFFF"));
    }

    public void secondAlert(){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
        builder2.setTitle("Zmień Hasło");

        LinearLayout linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(50,50,50,50);

        TextView text1 = new TextView(MainActivity.this);
        text1.setText("Podaj nowe hasło:");
        linearLayout.addView(text1);

        EditText inputHaslo1 = new EditText(MainActivity.this);
        inputHaslo1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        linearLayout.addView(inputHaslo1);

        TextView text2 = new TextView(MainActivity.this);
        text2.setText("Powtórz hasło:");
        linearLayout.addView(text2);

        EditText inputHaslo2 = new EditText(MainActivity.this);
        inputHaslo2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        linearLayout.addView(inputHaslo2);

        builder2.setView(linearLayout);

        builder2.setPositiveButton("Zapisz", (DialogInterface.OnClickListener) (dialog, which) -> {
            if(!inputHaslo1.getText().toString().equals(inputHaslo2.getText().toString())){
                dialog.dismiss();
                info.setText("Błąd: Hasła nie pasują do siebie.");
                info.setTextColor(Color.parseColor("#FF0000"));
            }else if(inputHaslo1.getText().toString().isEmpty()){
                dialog.dismiss();
                info.setText("Błąd: Hasła są puste");
                info.setTextColor(Color.parseColor("#FF0000"));
            }else{
                dialog.dismiss();
                haslo.setText(inputHaslo1.getText().toString());
                haslo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                if(newEmail!=null){
                    email.setText(newEmail);
                    info.setText("Profil zaktualizowany! Nowy email: "+email.getText().toString());
                    newEmail = null;
                }else{
                    info.setText("Haslo zaktualizowane!");
                }
                info.setTextColor(Color.parseColor("#00FF00"));
            }
        });

        builder2.setNegativeButton("Anuluj", (DialogInterface.OnClickListener) (dialog, which) -> {
            info.setText("Edycja profilu anulowana.");
            info.setTextColor(Color.parseColor("#808080"));
            dialog.cancel();
        });

        AlertDialog alertDialog2 = builder2.create();
        alertDialog2.show();
        clicked = false;

        Button btnAnuluj = alertDialog2.getButton(DialogInterface.BUTTON_NEGATIVE);
        btnAnuluj.setBackgroundColor(Color.parseColor("#00BCD4"));
        btnAnuluj.setTextColor(Color.parseColor("#FFFFFF"));

        Button btnZapisz = alertDialog2.getButton(DialogInterface.BUTTON_POSITIVE);
        btnZapisz.setBackgroundColor(Color.parseColor("#00BCD4"));
        btnZapisz.setTextColor(Color.parseColor("#FFFFFF"));
    }

}