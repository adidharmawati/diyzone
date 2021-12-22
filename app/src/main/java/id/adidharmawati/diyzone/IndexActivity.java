package id.adidharmawati.diyzone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.adidharmawati.diyzone.adapter.TutorialAdapter;
import id.adidharmawati.diyzone.database.AppDatabase;
import id.adidharmawati.diyzone.database.entity.Tutorial;
import id.adidharmawati.diyzone.database.entity.User;

public class IndexActivity extends AppCompatActivity {

    private RecyclerView rvIndex;
    SearchView searchView;
    private Button btnAdd, btnLogout;
    private AppDatabase database;
    private TutorialAdapter tutorialAdapter;
    private List<Tutorial> list = new ArrayList<>();
    private List<User> user = new ArrayList<>();
    private TextView tvUsername, tvEmail, tvGender, tvAge;
    int idUser;

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.tutorialDao().getAll());
        tutorialAdapter.notifyDataSetChanged();
        Toast.makeText(IndexActivity.this,"Daftar diperbarui!",Toast.LENGTH_SHORT).show();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(database.tutorialDao().getAll());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        btnAdd      = findViewById(R.id.btn_add);
        btnLogout   = findViewById(R.id.button_logout);
        rvIndex     = findViewById(R.id.rv_add);
        tvUsername  = findViewById(R.id.tv_uname);
        tvEmail     = findViewById(R.id.tv_email);
        tvGender    = findViewById(R.id.tv_gender);
        tvAge       = findViewById(R.id.tv_age);
        searchView  = findViewById(R.id.search_view);

        SessionManager session = new SessionManager(this);
        idUser = session.getId();

        database     = AppDatabase.getInstance(IndexActivity.this);
        user = database.userDao().selectUser(idUser);

        String username = user.get(0).getUsername();
        String email    = user.get(0).getEmail();
        String gender   = user.get(0).getGender();
        String age      = user.get(0).getAge();

        tvUsername.setText(username);
        tvEmail.setText(email);
        tvGender.setText(gender);
        tvAge.setText(age);

        Toast.makeText(this, "Welcome "+username+" !",Toast.LENGTH_LONG).show();

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.tutorialDao().getAll());
        tutorialAdapter = new TutorialAdapter(getApplicationContext(),list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        rvIndex.setLayoutManager(layoutManager);
        rvIndex.setAdapter(tutorialAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this,AddActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.removeSession();
                Toast toast = Toast.makeText(IndexActivity.this, "You're Logged out!", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(IndexActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(IndexActivity.this,"Sampai Jumpa!",Toast.LENGTH_SHORT).show();
    }

    private void filter (String newText){
        List<Tutorial> filteredList = new ArrayList<>();
        for(Tutorial item: list){
            if(item.getJudul().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        tutorialAdapter.filterList(filteredList);
    }
}