package edu.birzeit.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText password;
    private EditText email;
    private Button loginBtn;
    private Button regButton;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sharedPreferences = this.getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final Boolean isloggedin = sharedPreferences.getBoolean("ISLOGGEDIN",false);

        final String required_email = sharedPreferences.getString("EMAIL","DEFAULT_EMAIL");
        final String required_password = sharedPreferences.getString("PASSWORD","DEFAULT_PASSWORD");


        email = this.findViewById(R.id.log_Email);
        password = this.findViewById(R.id.log_password);
        loginBtn = this.findViewById(R.id.log_button);
        regButton=this.findViewById(R.id.reg_go);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(required_email, required_password);



            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });

    }
    private void validate(String required_email, String required_password) {
        String email_in = email.getText().toString();
        String password_in = password.getText().toString();
        if (!TextUtils.isEmpty(email_in) && !TextUtils.isEmpty(password_in)){// learned TextUtils from github
            if (email_in.contains("@")){
                isEmailLogin(email_in, password_in, required_email, required_password);
            }
        }else {
            Toast.makeText(this,R.string.log_in_pro,Toast.LENGTH_LONG).show();//Email Please check the fields moved into strings learned from github
        }

    }


    private void isEmailLogin(String email, String password, String required_email, String required_password) {
        if(email.equals(required_email) && password.equals(required_password)) {
            sharedPreferences.edit().putBoolean("ISLOGGEDIN",false).apply();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(this,R.string.wrong_emailorpass,Toast.LENGTH_LONG).show();
        }
    }
}

