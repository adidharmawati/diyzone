package id.adidharmawati.diyzone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import id.adidharmawati.diyzone.database.AppDatabase;
import id.adidharmawati.diyzone.database.entity.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword;
    private TextView tvAge, tvadUsername, tvadEmail, tvadGender, tvadAge;
    private TextInputLayout tilUsername;
    private Button btnSubmit,btnOk,btnNo, btnCancel;
    private RadioGroup rgGender;
    private RadioButton rbGender;
    private SeekBar sbAge;
    private CheckBox cbTandc;
    private AppDatabase database;
    String gender;
    Dialog daRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername  = (EditText)findViewById(R.id.txtUsername);
        etEmail     = (EditText)findViewById(R.id.txtEmail);
        etPassword  = (EditText)findViewById(R.id.txtPassword);
        btnSubmit   = (Button)findViewById(R.id.button_submit);
        btnCancel   = (Button)findViewById(R.id.button_cancel);
        tilUsername = (TextInputLayout)findViewById(R.id.txtinUsername);
        rgGender    = (RadioGroup)findViewById(R.id.radiobtn_gender);
        sbAge       = (SeekBar)findViewById(R.id.sbAge);
        tvAge       = (TextView)findViewById(R.id.txtAge);
        cbTandc     = (CheckBox)findViewById(R.id.checkBox_tandc);
        daRegister  = new Dialog(RegisterActivity.this);

        btnOk       = daRegister.findViewById(R.id.btn_ok);
        btnNo       = daRegister.findViewById(R.id.btn_no);
        tvadUsername = daRegister.findViewById(R.id.ad_username);
        tvadEmail = daRegister.findViewById(R.id.ad_email);
        tvadGender = daRegister.findViewById(R.id.ad_gender);
        tvadAge = daRegister.findViewById(R.id.ad_age);

        database = AppDatabase.getInstance(getApplicationContext());

        //ini buat ngeset perubahan seekbarnya biar bisa dibaca di text view
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int i = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                i = progress;
                tvAge.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                int checkedId = rgGender.getCheckedRadioButtonId();
                String age = tvAge.getText().toString();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || checkedId == -1 || age=="0") {
                    Toast.makeText(RegisterActivity.this,"Please Complete Your Data First!",Toast.LENGTH_SHORT).show();
                }else{
                    if(username.length() > 15){
                        tilUsername.setError("Username Too Long!");
                    }else{
                        findRadioButton(checkedId);

                        rbGender = findViewById(checkedId);
                        String gender = rbGender.getText().toString();

                        //baca isi checkbox
                        boolean isChecked = cbTandc.isChecked();

                        if(isChecked){

                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setIcon(R.mipmap.ic_logo)
                                    .setTitle("Please Re-Check Your Profile!")
                                    .setMessage("Halo, "+username+", email anda "+email+", dengan gender "+gender+", dan umur "+age+" tahun. Apakah data sudah benar dan ingin dilanjutkan?")
                            .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

//                                    User user = new User();
//                                    user.username = username;
//                                    user.email = email;
//                                    user.password = password;
//                                    user.gender = gender;
//                                    user.age = age;
//                                    database.userDao().insertAll(user);
                                    database.userDao().insertUser(username,email,password,gender,age);

//                                    Bundle extras = new Bundle();
//                                    extras.putString("username",username);
//                                    extras.putString("email",email);
//                                    extras.putString("gender",gender);
//                                    extras.putString("age",age);
                                    Toast.makeText(RegisterActivity.this,"Data Registered! Now please Login.",Toast.LENGTH_LONG).show();
                                    Intent submitIntent = (new Intent(RegisterActivity.this,MainActivity.class));
                                    startActivity(submitIntent);

                                    finish();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();

                        }else{
                            Toast.makeText(RegisterActivity.this,"Please Approve the Terms Condition and Privacy Policy first!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //isiin validasi kalo text unamenya kebanyakan (yt :stevdza-san, liked vid)
    }

    //untuk inisiasiin radio button yang di pilih dalam bentuk
    private void findRadioButton(int checkedId) {
        switch (checkedId){
            case R.id.radioButton_male:
                gender = "male";
                break;
            case R.id.radioButton_female:
                gender = "female";
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(RegisterActivity.this,"Kembali ke aplikasi untuk melanjutkan pengisian data!",Toast.LENGTH_SHORT).show();
    }
}