package com.brains404.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
      EditText login ;
      EditText password ;
      Button signin ;
      CheckBox toRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences.Editor editor = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
        login = findViewById(R.id.et_login);
        password = findViewById(R.id.et_password);
        signin = findViewById(R.id.btn_sign_in);
        toRemember= findViewById(R.id.checkBox);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toRemember.isChecked()){
                 editor.putString("login",login.getText().toString());
                 editor.putString("password",password.getText().toString());
                 editor.putBoolean("toRemember",toRemember.isChecked());
                 editor.apply();
                }else{
                    editor.clear();
                    editor.apply();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences loginPrefs = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        if(loginPrefs.contains("toRemember")){
            boolean isSaved=loginPrefs.getBoolean("toRemember",false);
            if(isSaved){
                login.setText(loginPrefs.getString("login",""));
                password.setText(loginPrefs.getString("password",""));
                toRemember.setChecked(loginPrefs.getBoolean("toRemember",false));

            }else{
                login.setText("");
                password.setText("");
                toRemember.setChecked(false);
            }
        }
    }
}
