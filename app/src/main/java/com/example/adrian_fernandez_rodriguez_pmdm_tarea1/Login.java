package com.example.adrian_fernandez_rodriguez_pmdm_tarea1;

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

public class Login extends AppCompatActivity {

    private String username = "admin";
    private String password = "admin";
    private static final int REQUEST_CODE_MODIFY_CREDENTIALS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a los botones y campos de texto
        Button btnIniciarSesion = findViewById(R.id.btnLog);
        Button btnModificarCredenciales = findViewById(R.id.btnModify);
        EditText editTextUsername = findViewById(R.id.editTextName);
        EditText editTextPassword = findViewById(R.id.editTextPwd);

        // Acción del botón "Iniciar sesión"
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = editTextUsername.getText().toString();
                String inputPwd = editTextPassword.getText().toString();

                if (inputName.equals(username) && inputPwd.equals(password)) {
                    // Credenciales correctas, pasar a la segunda actividad
                    Intent intent = new Intent(Login.this, LoginCorrecto.class);
                    intent.putExtra("USERNAME", inputName);
                    startActivity(intent);
                } else {
                    // Credenciales incorrectas, mostrar Toast
                    Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Acción del botón "Modificar credenciales"
        btnModificarCredenciales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la cuarta actividad para modificar credenciales
                Intent intent = new Intent(Login.this, ModificarCredenciales.class);
                startActivityForResult(intent, REQUEST_CODE_MODIFY_CREDENTIALS); // Usamos startActivityForResult para recibir nuevos datos
            }
        });
    }

    // Recibir las nuevas credenciales de la actividad ModifyCredentialsActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MODIFY_CREDENTIALS && resultCode == RESULT_OK) {
            // Obtener nuevas credenciales
            String nuevoUsername = data.getStringExtra("NEW_USERNAME");
            String nuevaPassword = data.getStringExtra("NEW_PASSWORD");

            if (nuevoUsername != null && nuevaPassword != null) {
                username = nuevoUsername;
                password = nuevaPassword;
                Toast.makeText(this, "Credenciales modificadas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
