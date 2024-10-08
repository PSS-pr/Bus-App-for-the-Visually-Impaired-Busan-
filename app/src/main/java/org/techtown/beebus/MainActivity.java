package org.techtown.beebus;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    CheckBox cLogin;
    SharedPreferences cpref;
    SharedPreferences.Editor ceditor;
    Boolean cloginChecked;
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    String loginId, loginPwd;
    EditText cID, cPassword;
    String userID;
    String userPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Register = findViewById(R.id.btn_register);
        Button Login = findViewById(R.id.bt_login);
        TextView FindId = findViewById(R.id.findid);
        TextView FindPw = findViewById(R.id.findpw);
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.bt_login);
        btn_register = findViewById(R.id.btn_register);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);

        userID = et_id.getText().toString();
        userPass = et_pass.getText().toString();

        cID = (EditText) findViewById(R.id.et_id);
        cPassword = (EditText) findViewById(R.id.et_pass);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });
        cLogin = (CheckBox) findViewById(R.id.cLoginCheck);
        cLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){  // autologin 체크
                    cpref = getSharedPreferences("cpref",  Activity.MODE_PRIVATE);
                    ceditor = cpref.edit();
                    cloginChecked = true;
                }
                else{ // augologin 취소
                    cpref = getSharedPreferences("cpref",  Activity.MODE_PRIVATE);
                    ceditor = cpref.edit();
                    cloginChecked = false;
                    ceditor.clear();
                    ceditor.commit();
                }
            }
        });

        cpref = getSharedPreferences("cpref", Activity.MODE_PRIVATE);
        if (cpref.getBoolean("autoLogin", false)) {
            cID.setText(cpref.getString("id", ""));
            cPassword.setText(cpref.getString("pw", ""));
            cLogin.setChecked(true);
        }

            btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = cID.getText().toString();
                String pw = cPassword.getText().toString();
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                Response.Listener<String> responseLinstener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                if(cloginChecked) {
                                    cpref = getSharedPreferences("cpref",  Activity.MODE_PRIVATE);
                                    ceditor = cpref.edit();
                                    ceditor.putString("id", userID);
                                    ceditor.putString("pw", userPass);
                                    ceditor.putBoolean("autoLogin", true);
                                    ceditor.commit();

                                } else{}

                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
                                Toast.makeText(getApplicationContext(), "로그인성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, homepage.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPAss", userPass);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("로그인실패").setNegativeButton("확인", null)
                                        .create().show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseLinstener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
/*
     if(loginId != null && loginPwd != null) {
            Response.Listener<String> responseLinstener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject2 = new JSONObject(response);
                        boolean success = jsonObject2.getBoolean("success");
                        if (success) {


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
        }*/
        FindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, findId.class);
                startActivity(intent);
            }
        });
        FindPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, findPassward.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        userID = et_id.getText().toString();
        userPass = et_pass.getText().toString();
        if(!userID.equals(null)&&!userID.equals("")||!userPass.equals(null)&&!userPass.equals("")){
          // btn_login.performClick();
        }

   
    }
}
