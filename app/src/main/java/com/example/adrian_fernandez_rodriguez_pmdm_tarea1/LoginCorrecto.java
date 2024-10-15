package com.example.adrian_fernandez_rodriguez_pmdm_tarea1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginCorrecto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_correcto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String username = getIntent().getStringExtra("USERNAME");

        // Referencias a los elementos del layout
        TextView welcomeText = findViewById(R.id.textViewWelcome);
        ImageButton imageButtonWeb = findViewById(R.id.imageBtnWeb);
        Button btnPonerAlarma = findViewById(R.id.btnAlarma);

        // Mostrar el mensaje de bienvenida con el nombre de usuario
        String welcome = getString(R.string.welcome_activity_login_correcto) + " " + username;
        welcomeText.setText(welcome);

        // Acción del ImageButton para abrir la página web
        imageButtonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent implícito para abrir el navegador con la URL
                String url = "https://www.tutorialspoint.com/android/android_intents_filters.htm";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        // Acción del botón "Poner alarma" que lleva a la actividad 3
        btnPonerAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent explícito para ir a la actividad de poner la alarma
                Intent intent = new Intent(LoginCorrecto.this, Alarma.class);
                startActivity(intent);
            }
        });
    }
}