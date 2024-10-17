package com.example.adrian_fernandez_rodriguez_pmdm_tarea1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class Alarma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alarma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextNombreAlar = findViewById(R.id.editTextNombreAlar);
        EditText editTextHora = findViewById(R.id.editTextHora);
        EditText editTextMinutos = findViewById(R.id.editTextMin);
        Button btnCrearAlarma = findViewById(R.id.btnCrearAlar);


        btnCrearAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int hora = Integer.parseInt(editTextHora.getText().toString());
                    int minutos = Integer.parseInt(editTextMinutos.getText().toString());

                    if (hora < 0 || hora > 23) {
                        Toast.makeText(Alarma.this, "Hora inválida. Debe estar entre 0 y 23.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (minutos < 0 || minutos > 59) {
                        Toast.makeText(Alarma.this, "Minutos inválidos. Deben estar entre 0 y 59.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent crearAlarma = new Intent(AlarmClock.ACTION_SET_ALARM);
                    crearAlarma.putExtra(AlarmClock.EXTRA_MESSAGE, editTextNombreAlar.getText().toString());
                    crearAlarma.putExtra(AlarmClock.EXTRA_HOUR, hora);
                    crearAlarma.putExtra(AlarmClock.EXTRA_MINUTES, minutos);
                    startActivity(crearAlarma);

                } catch (NumberFormatException e) {
                    Toast.makeText(Alarma.this, "Por favor, ingresa valores numéricos válidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}