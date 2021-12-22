package id.adidharmawati.diyzone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.adidharmawati.diyzone.database.AppDatabase;
import id.adidharmawati.diyzone.database.entity.Tutorial;

public class ViewActivity extends AppCompatActivity {

    private AppDatabase database;
    List<Tutorial> dataTutor = new ArrayList<>();
    int idTutorial;
    TextView tvTitle, tvTutorial;
    Button btnBack, btnEdit, btnDelete;
    String title, tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvTitle    = findViewById(R.id.txtTitle);
        tvTutorial = findViewById(R.id.txtTutorial);
        btnBack    = findViewById(R.id.Back);
        btnEdit    = findViewById(R.id.Edit);
        btnDelete  = findViewById(R.id.Delete);

        Intent tutorialIntent = getIntent();
        idTutorial = tutorialIntent.getIntExtra("id", 0);

        showData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(ViewActivity.this, EditActivity.class);
                editIntent.putExtra("id",idTutorial);
                startActivity(editIntent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ViewActivity.this)
                        .setIcon(R.mipmap.ic_logo)
                        .setTitle("Delete Tutorial")
                        .setMessage("Are You Sure Want to Delete '"+title+"'?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ViewActivity.this,"Data Terhapus!", Toast.LENGTH_SHORT).show();
                                database.tutorialDao().delete(idTutorial);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
        Toast.makeText(ViewActivity.this,"Selamat melanjutkan!",Toast.LENGTH_SHORT).show();
    }

    public void showData(){
        database     = AppDatabase.getInstance(ViewActivity.this);
        dataTutor = database.tutorialDao().selectTutor(idTutorial);

        title    = dataTutor.get(0).getJudul();
        tutorial = dataTutor.get(0).getIsi();

        tvTitle.setText(title);
        tvTutorial.setText(tutorial);
    }
}