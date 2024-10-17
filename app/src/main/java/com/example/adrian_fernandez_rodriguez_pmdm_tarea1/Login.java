package com.example.adrian_fernandez_rodriguez_pmdm_tarea1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    private String username = "admin";
    private String password = "admin";

    private ActivityResultLauncher<Intent> modificarCredencialesLauncher;

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

        Button btnIniciarSesion = findViewById(R.id.btnLog);
        Button btnModificarCredenciales = findViewById(R.id.btnModify);
        EditText editTextUsername = findViewById(R.id.editTextName);
        EditText editTextPassword = findViewById(R.id.editTextPwd);

        modificarCredencialesLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String nuevoUsername = data.getStringExtra("NEW_USERNAME");
                        String nuevaPassword = data.getStringExtra("NEW_PASSWORD");

                        if (nuevoUsername != null && nuevaPassword != null) {
                            username = nuevoUsername;
                            password = nuevaPassword;
                            Toast.makeText(Login.this, "Credenciales modificadas", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = editTextUsername.getText().toString();
                String inputPwd = editTextPassword.getText().toString();

                if (inputName.equals(username) && inputPwd.equals(password)) {
                    Intent intent = new Intent(Login.this, LoginCorrecto.class);
                    intent.putExtra("USERNAME", inputName);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnModificarCredenciales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ModificarCredenciales.class);
                modificarCredencialesLauncher.launch(intent);
            }
        });
    }
}
