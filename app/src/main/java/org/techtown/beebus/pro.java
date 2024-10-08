package org.techtown.beebus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

    public class pro extends AppCompatActivity {
    private TextView textView,textView2,textView3;
    private EditText editText,editText2;
    private Button button;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef2 = mRootRef.child("BUS62LED");
    DatabaseReference conditionRef = mRootRef.child("id");
    public static String text2;
    public static String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pro);
    textView = (TextView) findViewById(R.id.textview);
    textView2 = (TextView) findViewById(R.id.textview2);
    editText = (EditText) findViewById(R.id.edittext);
    editText2 = (EditText) findViewById(R.id.edittext2);
    button = (Button) findViewById(R.id.button);
    textView3 = (TextView) findViewById(R.id.ID);


    }


    @Override
    protected void onStart() {
        super.onStart();
        conditionRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                text = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            text2 = dataSnapshot.getValue(String.class);

            textView.setText("내용 ="+text2);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionRef2.setValue(editText2.getText().toString());
                conditionRef.setValue(editText.getText().toString());
            }
        });

    }

}
