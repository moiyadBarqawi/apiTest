package edu.birzeit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button makeup;
    private Button dogview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeup=findViewById(R.id.Makeup_btn);
        dogview=findViewById(R.id.Dogs);

        makeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MakeupActivity.class);
                startActivity(intent);

            }
        });
        dogview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), DogAcrivity.class);
                startActivity(intent);

            }
        });
    }
}