package id.adidharmawati.diyzone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.adidharmawati.diyzone.database.AppDatabase;
import id.adidharmawati.diyzone.database.entity.Tutorial;

public class AddActivity extends AppCompatActivity {

    private EditText txtTutorial, txtTitle;
    private Button btnSubmit, btnCancel;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        txtTitle    = (EditText) findViewById(R.id.txtTitle);
        txtTutorial = (EditText) findViewById(R.id.txtTutorial);
        btnCancel   = (Button) findViewById(R.id.cancel);
        btnSubmit   = (Button) findViewById(R.id.submit);

        database = AppDatabase.getInstance(getApplicationContext());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = txtTitle.getText().toString();
                String tutorial = txtTutorial.getText().toString();

                if(title.isEmpty() || tutorial == null){
                    Toast.makeText(AddActivity.this,"Please Complete Your Tutorial First!",Toast.LENGTH_SHORT).show();
                }else{
                    new AlertDialog.Builder(AddActivity.this)
                            .setIcon(R.mipmap.ic_logo)
                            .setTitle("Add New Tutorial")
                            .setMessage("Are You Sure Want to Add '"+ title +"'?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    database.tutorialDao().insertAll(title,tutorial);
                                    Intent addIntent = new Intent(getApplicationContext(),IndexActivity.class);
                                    startActivity(addIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(AddActivity.this,"Kembali ke aplikasi untuk melanjutkan pengisian data!",Toast.LENGTH_SHORT).show();
    }
}