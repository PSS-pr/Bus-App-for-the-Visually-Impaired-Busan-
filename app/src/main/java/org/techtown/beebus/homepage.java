package org.techtown.beebus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class homepage extends AppCompatActivity {
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        Intent intent2=getIntent();
        userID=intent2.getStringExtra("userID");
        Log.i("TAGc", "onCreate: "+userID);
        TextView imageView = findViewById(R.id.sup2);
        TextView imagebutton2 = findViewById(R.id.sup4);
        imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(homepage.this, MainActivity2a.class);
             intent.putExtra("userID",userID);
             startActivity(intent);
         }
     });

        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage.this,stopbell.class);
                startActivity(intent);
            }

        });
    }
    private long time = 0;
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            moveTaskToBack(true);
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            return;
        }
    }
}
