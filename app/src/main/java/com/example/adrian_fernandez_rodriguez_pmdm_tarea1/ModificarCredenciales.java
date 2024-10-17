package com.example.adrian_fernandez_rodriguez_pmdm_tarea1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModificarCredenciales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_credenciales);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextNuevoUsuario = findViewById(R.id.editTextNewName);
        EditText editTextNuevaContrasena = findViewById(R.id.editTextNewPwd);
        Button btnGuardarCambios = findViewById(R.id.btnSave);

        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoUsuario = editTextNuevoUsuario.getText().toString();
                String nuevaContrasena = editTextNuevaContrasena.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("NEW_USERNAME", nuevoUsuario);
                resultIntent.putExtra("NEW_PASSWORD", nuevaContrasena);
                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });
    }
}