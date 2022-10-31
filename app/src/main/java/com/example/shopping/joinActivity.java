package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class joinActivity extends AppCompatActivity {
    EditText idET, pwET;
    EditText repwEt;
    EditText nameEt;
    EditText phoneEt;
    EditText addressEt;
    CheckBox agreeCB;
    Button joinBtn;
    String id,password,repassword,name,phone,address;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        agreeCB = findViewById(R.id.agreeCB);
        idET = (EditText) findViewById(R.id.idET);
        pwET = (EditText) findViewById(R.id.pwET);
        repwEt = (EditText) findViewById(R.id.repwET);
        nameEt = (EditText) findViewById(R.id.nameET);
        phoneEt = (EditText) findViewById(R.id.phoneET);
        addressEt = (EditText) findViewById(R.id.addressET);
        joinBtn = (Button) findViewById(R.id.joinBtn);
        joinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                id = idET.getText().toString();
                password = pwET.getText().toString();
                repassword = repwEt.getText().toString();
                name = repwEt.getText().toString();
                phone = repwEt.getText().toString();
                address = repwEt.getText().toString();


                if(!agreeCB.isChecked()){
                    Toast.makeText(getApplicationContext(),"약관을 읽고 동의 버튼을 눌러주세요",Toast.LENGTH_SHORT).show();
                    agreeCB.requestFocus();
                }
                else if(name.equals("")){
                    Toast.makeText(getApplicationContext(),"아이디를 입력해 주세요",Toast.LENGTH_SHORT).show();
                    idET.requestFocus();

                }
                else if(pwET.equals("")){
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력해 주세요",Toast.LENGTH_SHORT).show();
                    pwET.requestFocus();

                }
                else if(repwEt.equals("")){
                    Toast.makeText(getApplicationContext(),"아이디를 입력해 주세요",Toast.LENGTH_SHORT).show();
                    repwEt.requestFocus();
                }
                else if(!password.equals(repassword)){
                    Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                    pwET.requestFocus();
                }
                else{
                    signCheck();
                    idET.setText("");
                    pwET.setText("");
                    repwEt.setText("");
                    nameEt.setText("");
                    phoneEt.setText("");
                    addressEt.setText("");

                }

            }

        });

    }
    protected void signCheck(){
        sharedPreferences = this.getSharedPreferences("userInfo",0);
        editor = sharedPreferences.edit();
        JSONObject infoJson = new JSONObject();
        try{
                infoJson.put("id", id);
                infoJson.put("password", password);
                infoJson.put("name", name);
                infoJson.put("phone", phone);
                infoJson.put("address", address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = sharedPreferences.getString(id,"");
        if(response=="") {
            editor.putString(id, infoJson.toString());
            editor.commit();
            Toast.makeText(getApplicationContext(),"환영합니다!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(joinActivity.this,MainActivity.class);
            startActivity(intent);//액티비티 이동
        }
        else{
            Toast.makeText(getApplicationContext(),"아이디가 중복되었습니다.",Toast.LENGTH_SHORT).show();
            return;
        }
    }

}