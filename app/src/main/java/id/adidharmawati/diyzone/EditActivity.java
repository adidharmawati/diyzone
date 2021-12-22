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
import id.adidharmawati.diyzone.database.entity.Tutorial;

public class EditActivity extends AppCompatActivity {

    EditText etTitle, etTutorial;
    Button btnCancel, btnSave;
    int idTutorial;
    AppDatabase database;
    List<Tutorial> dataTutor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle    = findViewById(R.id.txtTitle);
        etTutorial = findViewById(R.id.txtTutorial);
        btnCancel  = findViewById(R.id.cancel);
        btnSave    = findViewById(R.id.save);

        Intent tutorialIntent = getIntent();
        idTutorial = tutorialIntent.getIntExtra("id", 0);

        database     = AppDatabase.getInstance(EditActivity.this);
        dataTutor    = database.tutorialDao().selectTutor(idTutorial);

        String title = dataTutor.get(0).getJudul();
        String tutorial = dataTutor.get(0).getIsi();

        etTitle.setText(title);
        etTutorial.setText(tutorial);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = etTitle.getText().toString().trim();
                String newTutor = etTutorial.getText().toString().trim();

                database.tutorialDao().updateTutor(idTutorial,newTitle,newTutor);
                Toast.makeText(EditActivity.this, "Data Berhasil Diubah!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}