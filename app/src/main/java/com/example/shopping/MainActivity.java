package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText IdET,PasswordET;
    Button GoMain,login,join;
    String id,password;
//    SharedPreferences sharedPreferences = getSharedPreferences("userInfo",0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IdET = findViewById(R.id.IdET);
        PasswordET = findViewById(R.id.PasswordET);
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo",0);


        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = IdET.getText().toString();
                password = PasswordET.getText().toString();
                String response = sharedPreferences.getString(id,"");
                try{
                    JSONObject infoJson = new JSONObject(response);
                    String checkpw;
                    checkpw = infoJson.get("password").toString();
                    if(checkpw.equals(password)){
                        Toast.makeText(getApplicationContext(),"환영합니다",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,SoppingActivity.class);
                        intent.putExtra("ID",id);
                        startActivity(intent);//액티비티 이동
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"로그인에 실패하셨습니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }//로그인 실패
                }catch (JSONException e){
                    e.printStackTrace();
                }




            }
        });

        join = (Button) findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,joinActivity.class);
                startActivity(intent);//액티비티 이동
            }
        });
        GoMain = (Button) findViewById(R.id.GoMain);
        GoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = IdET.getText().toString();
                Intent intent = new Intent(MainActivity.this,SoppingActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);//액티비티 이동
            }
        });
    }
}