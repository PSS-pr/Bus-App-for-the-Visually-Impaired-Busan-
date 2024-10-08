package org.techtown.beebus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class wait extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    String result;
    double rslt,dex,dey;
    String gety,getx;
    TextView textView,textview;
    String str,id;
    double x,y;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    phpdo task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        Intent intent=getIntent();
        str=intent.getStringExtra("str");
        id=intent.getStringExtra("userID");
        dex=intent.getDoubleExtra("dex",0.0);
        dey=intent.getDoubleExtra("dey",0.0);

        Log.i("TAGa", "onCreate: "+id);
        textView =(TextView) findViewById(R.id.busmeter);

        textview = (TextView)findViewById(R.id.textView131);
        task = new phpdo();
        task.execute(id);
//      realbus();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(wait.this);
    }
//    void realbus(){
//
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                while (true) {
//
//                    try {
//                        Thread.sleep(5000);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }).start();
//    }
    @Override
    protected void onStart() {

        DatabaseReference conditionRef2 = mRootRef.child("BUS"+str+"LAT");
        DatabaseReference conditionRef = mRootRef.child("BUS"+str+"LON");
        DatabaseReference conditionRef3 = mRootRef.child("BUS"+str+"CARD");
        DatabaseReference conditionRef4 = mRootRef.child("BUS"+str+"CARDCHECK");
        mmter();

        super.onStart();

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getx = dataSnapshot.getValue(String.class);
                x=Double.parseDouble(getx);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        conditionRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gety = dataSnapshot.getValue(String.class);
                y=Double.parseDouble(gety);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        conditionRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String cardnum = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        conditionRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String cardnumcheck = dataSnapshot.getValue(String.class);
                if(cardnumcheck.equals("1"))
                {
                    Intent intent = new Intent(wait.this,stopbell.class);
                    intent.putExtra("str",str);
                    conditionRef4.setValue("0");
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng location1 = new LatLng(dey,dex);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location1));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {

            googleMap.setMyLocationEnabled(true);

        } else {
            checkLocationPermissionWithRationale();
        }


    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("위치정보")
                        .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다.위치정보 접근을 허용하여 주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(wait.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);

                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                        googleMap.setMyLocationEnabled(true);

                    }
                } else {

                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }

                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    void mmter(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(3000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    double EARTH_R, Rad, radLat1, radLat2, radDist;
                    double distance, ret;

                    EARTH_R = 6371000.0;
                    Rad = Math.PI/180;
                    radLat1 = Rad * dey;
                    radLat2 = Rad * Double.valueOf(gety);
                    radDist = Rad * (dex - Double.valueOf(getx));

                    distance = Math.sin(radLat1) * Math.sin(radLat2);

                    distance = distance + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radDist);

                    ret = EARTH_R * Math.acos(distance);

                    rslt = Math.round(Math.round(ret) / 1000);
                    result = String.valueOf(rslt);
                    Log.d("addb", "addb: "+gety+getx);
                    result = Math.round(ret) +" m";
                    Log.d("s", "run: "+result);
                    textView.setText(result+"||"+str);



                }
            }
        }).start();
    }
    private class phpdo extends AsyncTask<String,Void,String> {

        protected void onPreExecute(){

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {


                Log.i("TAGa", "onCreate: "+id);

                final String link = "http://kappa0908.ivyro.net/keySelect.php?userID="+id;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }
        @Override
        protected void onPostExecute(String result){
            //txtview.setText("Login Successful");
            textview.setText(result);
            DatabaseReference conditionRef3 = mRootRef.child("BUS"+str+"CARD");
            conditionRef3.setValue(result.toString());
        }
    }

//    void mmtea(){
//        this.googleMap = googleMap;
//
//    }

}
