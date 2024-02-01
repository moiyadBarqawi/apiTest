package edu.birzeit.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DogAcrivity extends Activity {
    private ImageView img;
    private Button btn;
    private Button back;
    private RequestQueue queue;
    String url="https://dog.ceo/api/breeds/image/random";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog);
        img=findViewById(R.id.imageView2);
        btn=findViewById(R.id.dog_btn);
        back=findViewById(R.id.back_dog);
        queue = Volley.newRequestQueue(this);

        //set start img "https://images.dog.ceo/breeds/tervuren/yoda_on_terrace.jpg"
        Picasso.get().load("https://images.dog.ceo/breeds/tervuren/yoda_on_terrace.jpg").into(img);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                try {
                                    Photo treeObj= new Gson().fromJson(response, Photo.class);
                                    String x =treeObj.getMessage();

                                    Picasso.get().load(x).into(img);//source :https://github.com/square/picasso

                                    InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                    //Toast.makeText(DogAcrivity.this, "fdfd", Toast.LENGTH_SHORT).show();

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DogAcrivity.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });



                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });



    }
}
