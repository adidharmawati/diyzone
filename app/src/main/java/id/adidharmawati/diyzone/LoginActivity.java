package id.adidharmawati.diyzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.adidharmawati.diyzone.database.AppDatabase;
import id.adidharmawati.diyzone.database.dao.UserDao;
import id.adidharmawati.diyzone.database.entity.User;

public class LoginActivity extends AppCompatActivity {

    private AppDatabase database;
    private EditText etUsername, etPassword;
    private Button btnCancel, btnLogin;
    List<User> dataLogin = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnCancel  = findViewById(R.id.btn_cancel);
        btnLogin   = findViewById(R.id.btn_login);

        SessionManager session = new SessionManager(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                database = AppDatabase.getInstance(LoginActivity.this);

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please Complete Your Data First!",Toast.LENGTH_SHORT).show();
                }else{
                    dataLogin = database.userDao().findUser(username, password);
                    int loginStat = dataLogin.size();

                    if(loginStat !=0){
                        session.saveSession(dataLogin.get(0).getId_user(), dataLogin.get(0).getUsername(), dataLogin.get(0).getEmail(), dataLogin.get(0).getGender(), dataLogin.get(0).getAge());
                        Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Akun Tidak Terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}