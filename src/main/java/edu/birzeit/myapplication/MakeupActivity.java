package edu.birzeit.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MakeupActivity extends AppCompatActivity {

    private EditText brand;
    private EditText product;
    private Switch show;
    private TextView tag;
    private Button search;
    private RequestQueue queue;
    private Button Back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeup);
        show=findViewById(R.id.switch1);
        tag = findViewById(R.id.Brand_help);
        search=findViewById(R.id.Search_makeup_btn);
        brand=findViewById(R.id.Search_brand);
        product=findViewById(R.id.Search_product);
        Back=findViewById(R.id.back_btn);
        queue = Volley.newRequestQueue(this);

        String placeh="alva ,anna sui,annabelle,benefit,boosh,burt's bees,butter london,c'est moi,cargo cosmetics \n"+
                "china glaze,clinique,coastal classic creation,colourpop,covergirl,dalish,deciem,dior,dr. hauschka \n" +
                "e.l.f.,essie,fenty,glossier,green people,iman,l'oreal,lotus cosmetics usa,maia's mineral galaxy,marcelle\n" +
                "marienatie,maybelline,milani,mineral fusion,misa.mistura,moov,nudus,nyx,orly,pacifica.penny lane organics.physicians formula\n" +
                "piggy paint,pure anada, minerals,revlon,sally b's skin yummies,salon perfect,sante\n" +
                "sinful colours,smashbox,stila,suncoat,w3llpeople,wet n wild,zorah,zorah biocosmetiques";
        tag.setText(placeh);



        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!show.getShowText()){

                    tag.setVisibility(View.VISIBLE);
                    tag.setText(placeh);

                }
                else {

                    tag.setVisibility(View.INVISIBLE);
                }

            }

        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(brand.getText().toString().isEmpty()){
                    Toast.makeText(MakeupActivity.this, "you can't leave brand empty", Toast.LENGTH_SHORT).show();

                }else if (!brand.getText().toString().isEmpty()&&product.getText().toString().isEmpty()){
                    String b=brand.getText().toString().trim();
                    String str ="http://makeup-api.herokuapp.com/api/v1/products.json?brand="+b;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, str,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    String result = "";
                                    try {
                                        Product[] treeObj= new Gson().fromJson(response, Product[].class);
                                        String x ="";
                                        for (int i=0;i<treeObj.length;i++){
                                            x+=treeObj[i].toString();
                                        }
                                        tag.setText(x);
                                        tag.setVisibility(View.VISIBLE);
                                        InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    } catch (Exception exception) {
                                        exception.printStackTrace();

                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MakeupActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            tag.setText( error.toString());
                        }
                    });



                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                } else if (!brand.getText().toString().isEmpty()&&!product.getText().toString().isEmpty()) {
                    String b=brand.getText().toString().trim();
                    String p=product.getText().toString().trim();
                    String str ="http://makeup-api.herokuapp.com/api/v1/products.json?brand="+b+"&product_type="+p;


                    StringRequest stringRequest = new StringRequest(Request.Method.GET, str,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //txtResult.setText(response);
                                    String result = "";
                                  //  Toast.makeText(MakeupActivity.this,str, Toast.LENGTH_SHORT).show();

                                    try {


                                        Product[] treeObj= new Gson().fromJson(response, Product[].class);
                                        String x ="";
                                        for (int i=0;i<treeObj.length;i++){
                                            x+=treeObj[i].toString();
                                        }
                                        tag.setText(x);
                                        tag.setVisibility(View.VISIBLE);





                                       // Toast.makeText(MakeupActivity.this,x, Toast.LENGTH_SHORT).show();
//                                        for( int i=0;i<jsonObject.length();i++) {
//                                            JSONObject mainObj = jsonObject.getJSONObject("main");
//                                            String brand = mainObj.optString("brand");
//                                            String name = mainObj.optString("name");
//                                            double price = mainObj.getDouble("price");
//                                            String category = mainObj.optString("category  ");
//                                            JSONArray color = mainObj.getJSONArray("product_colors");
//                                            String   color_name_f = "colors :";
//                                            for( int j=0;j<color.length();j++) {
//                                                JSONObject job = (JSONObject) color.get(j);
//                                                String color_name = job.get("colour_name").toString();
//                                                color_name_f+= " " +color_name+",";
//
//                                            }
//
//                                            String product_type = mainObj.optString("product_type  ");
//
//
//                                            result += "{ brand: " + brand;
//                                            result += "\nname:  " + name;
//                                            result += "\nprice: " + price + "$";
//                                            result += "\ncategory: " + category;
//                                            result += "\n " +  color_name_f;
//                                            result += "\nproduct_type: " + product_type + "}\n";
//                                        }

                                        //close keyboard
                                        InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                                    } catch (Exception exception) {
                                       exception.printStackTrace();

                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MakeupActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            tag.setText( error.toString());
                        }
                    });



                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }

                else{
                    Toast.makeText(MakeupActivity.this, "you can't leave brand empty", Toast.LENGTH_SHORT).show();
                }


            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }


}