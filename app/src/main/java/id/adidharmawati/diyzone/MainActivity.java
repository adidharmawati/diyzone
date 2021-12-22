package id.adidharmawati.diyzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister, btnLogin;

    @Override
    protected void onStart() {
        super.onStart();
        SessionManager session = new SessionManager(this);
        if(session.statusLogin()){
            Intent mainIntent = new Intent(MainActivity.this, IndexActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = (Button)findViewById(R.id.button_register);
        btnLogin    = (Button)findViewById(R.id.button_login);

        Intent halRegister = new Intent(this, RegisterActivity.class);
        Intent halLogin = new Intent(this, LoginActivity.class);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(halRegister,1);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(halLogin,1);
            }
        });
    }
}