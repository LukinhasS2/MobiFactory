package com.pucpr.mobifactory;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, ForgotPasswordbutton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        ForgotPasswordbutton = findViewById(R.id.ForgotPasswordbutton);

        dbHelper = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (Exception e) {
                    Log.e(TAG, "Erro ao tentar fazer login", e);
                    Toast.makeText(LoginActivity.this, "Erro ao tentar fazer login. Verifique os logs para mais detalhes.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ForgotPasswordbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Falta implementar lógica de recuperação de senha
                Toast.makeText(LoginActivity.this, "Recuperação de senha ainda não implementada.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, insira o login e a senha.", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(DatabaseHelper.TABLE_USERS,
                    new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_USER_LEVEL},
                    DatabaseHelper.COLUMN_USERNAME + "=? AND " + DatabaseHelper.COLUMN_PASSWORD + "=?",
                    new String[]{username, password},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int userLevelIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_LEVEL);
                if (userLevelIndex != -1) {
                    String userLevel = cursor.getString(userLevelIndex);
                    Toast.makeText(this, "Login bem-sucedido. Bem-vindo, " + username + "! Nível de usuário: " + userLevel, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Erro ao recuperar o nível de usuário.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nome de usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Erro ao consultar o banco de dados", e);
            Toast.makeText(this, "Erro ao consultar o banco de dados. Verifique os logs para mais detalhes.", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }
}