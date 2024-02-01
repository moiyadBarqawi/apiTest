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

public class RegisterActivity extends Activity {

    private EditText reg_username;
    private EditText reg_email;
    private EditText reg_password;
    private EditText reg_confirm_password;
    private Button logbutton;
    private Button register_btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regesiter);
        reg_username =findViewById(R.id.reg_username);
        reg_email= findViewById(R.id.reg_Email);
        reg_password=findViewById(R.id.ref_password);
        reg_confirm_password=findViewById(R.id.reg_confirm_password);
        logbutton= findViewById(R.id.log_back);
        register_btn=findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

            }
        });
        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });


//i used this to learn hot to make my code cleaner didn't steal styles just learned from it :https://github.com/Inihood1/Login-And-Register-with-sharedpreference-Android/tree/master
    }
    private void validate() {
        String name = reg_username.getText().toString();
        String email_in = reg_email.getText().toString();
        String password_in = reg_password.getText().toString();
        String re_password = reg_confirm_password.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email_in) && !TextUtils.isEmpty(password_in)){
            if (email_in.contains("@")){
                    if (password_in.equals(re_password)){
                        startRegistration(name, email_in, password_in);
                    }else {

                        Toast.makeText(this, getString(R.string.password_mismatch), Toast.LENGTH_LONG).show();//learned how to add to strings
                    }

            }else {
                Toast.makeText(this, getString(R.string.email_not_valid), Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, getString(R.string.check_fields), Toast.LENGTH_LONG).show();
        }
    }

    private void startRegistration(String name, String email_in, String password_in) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME",name);
        editor.putString("EMAIL",email_in);

        editor.putString("PASSWORD",password_in);
        editor.putBoolean("ISLOGGEDIN",true);
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}

