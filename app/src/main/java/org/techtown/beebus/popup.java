package org.techtown.beebus;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class popup extends Dialog {
        private TextView txt_contents;
        private Button shutdownClick;
        private Button button3;


    public popup(@NonNull Context context, String contents) {
            super(context);
            setContentView(R.layout.activity_popup);
            txt_contents = findViewById(R.id.textView12);
            txt_contents.setText(contents);

            button3 = findViewById(R.id.button3);
            shutdownClick = findViewById(R.id.button4);
            shutdownClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    dismiss();
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getApplicationContext(),wait.class);
                  //  Log.i("add", "onClick:"+dex +"||"+dey);
                    context.startActivity(intent);
                    dismiss();
                }
            });
        }
    }
