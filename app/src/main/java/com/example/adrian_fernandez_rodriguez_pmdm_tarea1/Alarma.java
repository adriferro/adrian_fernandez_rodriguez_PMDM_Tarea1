package com.example.adrian_fernandez_rodriguez_pmdm_tarea1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

        EditText editTextNombre = findViewById(R.id.editTextNombreAlar);
        EditText editTextHora = findViewById(R.id.editTextHora);
        EditText editTextMinutos = findViewById(R.id.editTextMin);
        Button btnCrearAlarma = findViewById(R.id.btnCrearAlar);

        // Acción del botón "Crear alarma"
        btnCrearAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreAlarma = editTextNombre.getText().toString();
                String horaStr = editTextHora.getText().toString();
                String minutosStr = editTextMinutos.getText().toString();

                if (nombreAlarma.isEmpty() || horaStr.isEmpty() || minutosStr.isEmpty()) {
                    Toast.makeText(Alarma.this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int hora = Integer.parseInt(horaStr);
                int minutos = Integer.parseInt(minutosStr);

                // Configurar la alarma
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minutos);
                calendar.set(Calendar.SECOND, 0);

                //Intent intent = new Intent(Alarma.this, AlarmReceiver.class);
                //PendingIntent pendingIntent = PendingIntent.getBroadcast(Alarma.this, 0, intent, 0);
                //AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                //if (alarmManager != null) {
                  //  alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    //Toast.makeText(Alarma.this, "Alarma configurada: " + nombreAlarma, Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }
}