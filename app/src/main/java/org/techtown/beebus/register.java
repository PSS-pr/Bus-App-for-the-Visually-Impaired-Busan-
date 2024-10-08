package org.techtown.beebus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class register extends AppCompatActivity {
    private EditText et_id,et_pass,et_name,et_key1,et_key2,et_key3,et_key4;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_id=findViewById(R.id.et_id);
        et_pass =findViewById(R.id.et_pass);
        et_name=findViewById(R.id.et_name);
        et_key1=findViewById(R.id.et_key);
        et_key2=findViewById(R.id.et_key2);
        et_key3=findViewById(R.id.et_key3);
        et_key4=findViewById(R.id.et_key4);
        btn = findViewById(R.id.regbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                String userKey1 = et_key1.getText().toString();
                String userKey2 = et_key2.getText().toString();
                String userKey3 = et_key3.getText().toString();
                String userKey4 = et_key4.getText().toString();

                String userKey = userKey1+","+userKey2+","+userKey3+","+userKey4;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success)
                            {
                                Toast.makeText(getApplicationContext(),"회원등록 완료",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"회원등록 실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID,userPass,userName,userKey,responseListener);
                RequestQueue queue = Volley.newRequestQueue(register.this);
                queue.add(registerRequest);
            }
        });
    }
}
