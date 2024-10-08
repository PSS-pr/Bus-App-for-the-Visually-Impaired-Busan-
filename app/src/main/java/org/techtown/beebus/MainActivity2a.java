package org.techtown.beebus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity2a extends AppCompatActivity {
    final String key = "api key";

    EditText editText;


    final String TAG = "MainActivity";
//    public String dataKey = MyConfig.dataKey;
    private String requestUrl;
    ArrayList<Item> list = null;
    Item bus = null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2a);

        editText = findViewById(R.id.eda);


        Button button =findViewById(R.id.bao);


//        AsyncTask


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = editText.getText().toString();
                if(!str.equals(null)&&!str.equals("")) {

                    Intent intent = new Intent(MainActivity2a.this, busrecycle.class);
                    intent.putExtra("str", str);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2a.this);
                    builder.setMessage("버스번호 를 입력해주세요 ").setNegativeButton("확인", null)
                            .create().show();
                }
                }
        });

    }



}
