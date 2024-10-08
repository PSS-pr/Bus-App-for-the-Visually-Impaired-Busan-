package org.techtown.beebus;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class search extends AppCompatActivity  implements OnMapReadyCallback {
  //  private popup customDialog;
  //  LodingDialog customProgressDialog;


    String TAG = "STATION LIST";


    private GoogleMap googleMap;
//    EditText edit, edit2;
    TextView text;
    static TextView textView , textView2;
   // private GpsTracker gpsTracker;

    String  userID;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    String Meassage;
    final String key = "%2B6wk99uklzLUFFo6Ex96lJwk2pWLLQZ0W7hjlxZhwu5zw1WFSoa1kFZgX92Dyncv3dO6OXuUcANOEybG0S8lcA%3D%3D";
    String bsname = "";

    String data2;
    String data3;
    double mLatitude;
    double mLongitude;
    String str,str1,str2;
    String bstid = "";
    String bstline = "";
    String bsa = "";
    double dex;
    double dey;
    String[] num2 = new String[2];
    String[] num = new String[2];
    int count=0;
    int count2=0;

  //  private GpsTracker gpsTracker;
  String bsto;
    String bst1;
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        text = (TextView) findViewById(R.id.text);

//        Button button3 = findViewById(R.id.button3);
//        Button button = findViewById(R.id.button);
        Button button4 = findViewById(R.id.reservation);

//        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView2);
//        textView2 =(TextView) findViewById(R.id.mmtexy);
      //  gpsTracker = new GpsTracker(search.this);
//        str = edit.getText().toString(); //EditText에 작성된 Text 얻기
//        str2 = edit2.getText().toString();
        Intent intent2 = getIntent();
         userID=intent2.getStringExtra("userID");
         bsto = intent2.getStringExtra("bstopnm");
         bstid  = intent2.getStringExtra("bstid");
         location  = intent2.getStringExtra("busnum");

        Log.i(TAG, "This A "+bsto+bst1+location);
    //    customProgressDialog = new LodingDialog(this);
    //    customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


//        new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//
//
//                            data2 = getXmlData2();
//                            data3 = getXmlData3();
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                    text.setText(data3);
//                                    button4.setVisibility(View.VISIBLE);
//
//                                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//                                    mapFragment.getMapAsync(search.this);
//                                    //     customProgressDialog.dismiss();
//
////                                    if(count==2)
////                                    {
////                                        button2.setVisibility(View.VISIBLE);
////                                        button3.setVisibility(View.VISIBLE);
////                                    }else {
////                                        button2.setVisibility(View.INVISIBLE);
////                                        button3.setVisibility(View.INVISIBLE);
////                                    }
//
//                                }
//
//                            });
//
//                        }
//                    }).start();

//        data2 = getXmlData2();
//        data3 = getXmlData3();
//
//        text.setText(data3);

//        StringBuffer buffer = new StringBuffer();
//        String queryUrl="http://apis.data.go.kr/6260000/BusanBIMS/busStopList?bstopnm="+bst+"&arsno="+bst1+"&pageNo=1&numOfRows=10&serviceKey="+key;
//        try {
//            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.
//            InputStream is = url.openStream(); //url 위치로 입력스트림 연결
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xpp = factory.newPullParser();
//            xpp.setInput(new InputStreamReader(is,"UTF-8")); //inputstream 으로부터 xml 입력받기
//            String tag;
//            xpp.next();
//            int eventType=xpp.getEventType();
//            while(eventType != XmlPullParser.END_DOCUMENT){
//                switch(eventType){
//                    case XmlPullParser.START_DOCUMENT:
//                        buffer.append("파싱 시작...\n\n");
//                        break;
//
//                    case XmlPullParser.START_TAG:
//                        tag= xpp.getName();//태그 이름 얻어오기
//                        if(tag.equals("item"));
//                        xpp.next();
//
//                        if (tag.equals("bstopid")) {
//                            bstline = xpp.getText();
//                            bsname = xpp.getText();;
//                            xpp.next();
//                        }
//                        break;
//                    case XmlPullParser.TEXT:
//                        break;
//                    case XmlPullParser.END_TAG:
//                        tag= xpp.getName(); //태그 이름 얻어오기
//                        if(tag.equals("item")) buffer.append("\n");  //첫번째 검색결과 종료..줄바꿈
//                        break;
//                }
//
//                eventType = xpp.next();
//            }
//        }catch (Exception e){
//
//        }
//
//        data =buffer.toString();
//
//
//
//        StringBuffer buffer2 = new StringBuffer();
//        String queryUrl1="http://apis.data.go.kr/6260000/BusanBIMS/busStopArrByBstopidLineid?bstopid="+bstline+"&lineid="+location+"&serviceKey="+key;
//        try {
//            URL url= new URL(queryUrl1); //문자열로 된 요청 url을 URL 객체로 생성.
//            InputStream is = url.openStream(); //url 위치로 입력스트림 연결
//
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xpp = factory.newPullParser();
//            xpp.setInput(new InputStreamReader(is,"UTF-8")); //inputstream 으로부터 xml 입력받기
//
//            String tag;
//
//            xpp.next();
//            int eventType=xpp.getEventType();
//
//            while(eventType != XmlPullParser.END_DOCUMENT){
//                switch(eventType){
//                    case XmlPullParser.START_DOCUMENT:
//                        buffer.append("파싱 시작...\n\n");
//                        break;
//
//                    case XmlPullParser.START_TAG:
//                        tag= xpp.getName();//태그 이름 얻어오기
//                        if(tag.equals("item"));
//                        xpp.next();
//
//                        if (tag.equals("nodenm")) {
//                            bsname = xpp.getText();
//                            buffer2.append("정류소명 :");
//                            buffer2.append(xpp.getText());
//                            buffer2.append("\n\n");
//                            xpp.next();
//                        }
//                        else if (tag.equals("lineno")) {
//                            bsname = xpp.getText();
//                            buffer2.append("버스번호 :");
//                            buffer2.append(xpp.getText());
//                            buffer2.append("\n\n");
//                            xpp.next();
//                        }
//                        else if (tag.equals("min1")) {
//                            bsname = xpp.getText();
//                            buffer2.append("1번쨰 버스 :");
//                            buffer2.append(xpp.getText()+"분");
//                            buffer2.append("\n\n");
//                            xpp.next();
//                        }
//                        else if (tag.equals("station1")) {
//                            bsname = xpp.getText();
//                            buffer2.append("1번쨰 버스 남은 정류장 :");
//                            buffer2.append(xpp.getText()+"정류장");
//                            buffer2.append("\n\n");
//                            xpp.next();
//                        }
//                        else if (tag.equals("lowplate1")) {
//                            bsname = xpp.getText();
//                            buffer2.append("버스타입:");
//                            buffer2.append(xpp.getText());
//                            buffer2.append("\n\n");
//                            xpp.next();
//                        }
////                        else if (tag.equals("min2")) {
////                            bstid = xpp.getText();
////                            xpp.next();
////                        }
//                        else if (tag.equals("gpsx")) {
//                            dex=Double.parseDouble(xpp.getText());
//
//                            xpp.next();
//                        }
//                        else if (tag.equals("gpsy")) {
//                            dey=Double.parseDouble(xpp.getText());
//
//                            xpp.next();
//                        }
////                        else if (tag.equals("station2")) {
////                            bsname = xpp.getText();
////                            xpp.next();
////                        }
////                        else if (tag.equals("lowplate2")) {
////                            bsname = xpp.getText();
////                            xpp.next();
////                        }
//                        break;
//                    case XmlPullParser.TEXT:
//                        break;
//                    case XmlPullParser.END_TAG:
//                        tag= xpp.getName(); //태그 이름 얻어오기
//                        if(tag.equals("item")) buffer.append("\n");  //첫번째 검색결과 종료..줄바꿈
//                        break;
//                }
//                eventType = xpp.next();
//            }
//        }catch (Exception e){
//        }
//        data2=buffer2.toString();
//
//
//        Log.i(TAG, "This A: "+ data2 + data);
//        text.setText(data2);
        Log.i(TAG, "onCreate: "+bstid +"abs"+ bsto);
        mOnClick();

            }




    void realbus(){

        new Thread(new Runnable() {
            Button button4 = findViewById(R.id.reservation);
            @Override
            public void run() {
                data2 = getXmlData2();
                data3 = getXmlData3();
                text.setText(data3);
                button4.setVisibility(View.VISIBLE);

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(search.this);
                while (true) {

                    try {
                        Thread.sleep(5000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }


    public void btnOnclick(View view) {
        switch (view.getId()) {
            case R.id.reservation:
//
                if (location.equals("5200062000"))
                {
                    location="62";
                }else if(location.equals("5200129100"))
                {
                    location="129-1";
                }
                Log.i("asa", "btnOnclick: "+dex+"||"+dey+str+"||"+str2+"lp"+location);

                if(location.equals("62")||location.equals("129-1"))
                {
                    Intent intent = new Intent(search.this,wait.class);
                    intent.putExtra("dex",dex);
                    intent.putExtra("dey",dey);
                    intent.putExtra("str",location);
                    intent.putExtra("userID",userID);
                    Log.i("TAGb", "btnOnclick: "+userID);
                    startActivity(intent);

                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(search.this);
                    builder.setMessage("현재 지원하지않는 버스입니다").setNegativeButton("확인", null)
                            .create().show();

            }
        }
    }

//    String getXmlData(){
//
//        StringBuffer buffer = new StringBuffer();
//        String str = edit.getText().toString(); //EditText에 작성된 Text 얻기
//        String str2 = edit2.getText().toString();
//        String location2 = str2;
//        String location = str;
//
//
//        if(location.contains("-"))
//        {
//            location = location.replace("-","");
//            location ="5200"+location+"00";
//        }else if (Integer.parseInt(location) > 1000)
//        {
//            location = "520"+location+"000";
//        }
//        else if (Integer.parseInt(location) > 100)
//        {
//            location ="5200"+location+"000"; }
//        else if (Integer.parseInt(location)  < 100)
//        {
//            location = "52000"+ location + "000";
//        }
//        String queryUrl="http://apis.data.go.kr/6260000/BusanBIMS/busInfoByRouteId?lineid="+location+"&serviceKey="+key;
//        try {
//            boolean carno = false;
//            boolean bstopnm =false;
//            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.
//            InputStream is = url.openStream(); //url 위치로 입력스트림 연결
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xpp = factory.newPullParser();
//            xpp.setInput(new InputStreamReader(is,"UTF-8")); //inputstream 으로부터 xml 입력받기
//            String tag;
//            xpp.next();
//            int eventType=xpp.getEventType();
//            while(eventType != XmlPullParser.END_DOCUMENT){
//                switch(eventType){
//                    case XmlPullParser.START_DOCUMENT:
//                        buffer.append("파싱 시작...\n\n");
//
//                        break;
//                    case XmlPullParser.START_TAG:
//                        tag= xpp.getName();//태그 이름 얻어오기
//                        if(tag.equals("item"));
//                        xpp.next();
//                        if (tag.equals("bstopnm")) {
//                            bsname = xpp.getText();
//                            buffer.append("정류소 아이디 :");
//                            buffer.append(xpp.getText());
//                            buffer.append("\n");
//                            xpp.next();
//                        }
//                        else if (tag.equals("arsno")) {
//                            if (location2.equals(bsname))
//                            {
//                                num[count] =  xpp.getText();
//                                count++;
//                                bstid = xpp.getText();
//                                buffer.append("정류소번호 :");
//                            }
//                            buffer.append("\n"); //줄바꿈 문자 추가
//                            xpp.next();
//                        }
//                        else if (tag.equals("direction")) {
//                            buffer.append("방향:");
//                            bsa=xpp.getText();
//                            buffer.append(xpp.getText());
//                            if (xpp.getText().equals("1")||xpp.getText().equals("3")||xpp.getText().equals("0")) {
//                                buffer.append("상행선");
//                            } else if (xpp.getText().equals("2")) {
//                                buffer.append("하행선");
//                            }  else {
//                                buffer.append("하행선");
//                            }
//                            buffer.append("\n");
//                            xpp.next();
//                        }
//                        if(xpp.getName().equals("item")){
////                            bus = new DataModel();
//                        }
//
//                    case XmlPullParser.TEXT:
//
//                        break;
//                    case XmlPullParser.END_TAG:
//                        tag= xpp.getName(); //태그 이름 얻어오기
//                        if(tag.equals("item")) {
//                            buffer.append("\n");  //첫번째 검색결과 종료..줄바꿈
//                        }
//
//                        break;
//                }
//                eventType = xpp.next();
//            }
//
//        }catch (Exception e){
//        }
//        return buffer.toString(); //StringBuffer 문자열 객체 반환
//    }




    String getXmlData2(){
//        String str = edit.getText().toString(); //EditText에 작성된 Text 얻기
//        String str2 = edit2.getText().toString();
//        String location2 = str2;
//        String location = str;


//        if(location.contains("-"))
//        {
//            location = location.replace("-","");
//            location ="5200"+location+"00";
//        }else if (Integer.parseInt(location) > 1000)
//        {
//            location = "520"+location+"000";
//        }
//        else if (Integer.parseInt(location) > 100)
//        {
//            location ="5200"+location+"000"; }
//        else if (Integer.parseInt(location)  < 100)
//        {
//            location = "52000"+ location + "000";
//        }
        StringBuffer buffer = new StringBuffer();
        Log.i(TAG, "getXmlData2: "+bsto + "|abs|"+bstid);
        String queryUrl="http://apis.data.go.kr/6260000/BusanBIMS/busStopList?bstopnm="+bsto+"&arsno="+bstid+"&pageNo=1&numOfRows=10&serviceKey="+key;
        try {
            Log.i("loga2", "mOnClick: "+location+bstid+bsto);
            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url 위치로 입력스트림 연결
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is,"UTF-8")); //inputstream 으로부터 xml 입력받기
            String tag;
            xpp.next();
            int eventType=xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기
                        if(tag.equals("item"));
                        xpp.next();

                        if (tag.equals("bstopid")) {
                            bstline = xpp.getText();
                            bsname = xpp.getText();;
                            xpp.next();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기
                        if(tag.equals("item")) buffer.append("\n");  //첫번째 검색결과 종료..줄바꿈
                        break;
                }

                eventType = xpp.next();
            }
        }catch (Exception e){

        }

        return buffer.toString(); //StringBuffer 문자열 객체 반환
    }

    String getXmlData3(){
//        Button button2 = findViewById(R.id.button2);
//        Button button3 = findViewById(R.id.button3);
//        String str = edit.getText().toString(); //EditText에 작성된 Text 얻기
//        String str2 = edit2.getText().toString();
//        button2.setVisibility(View.INVISIBLE);
//        button3.setVisibility(View.INVISIBLE);
//        String location2 = str2;

        Log.i("loga3", "mOnClick: "+location+bsto+bstid);

        StringBuffer buffer = new StringBuffer();
        Log.i("loga", "mOnClick: "+bstline+"abs"+bstid+"abs"+bsto);
        String queryUrl="http://apis.data.go.kr/6260000/BusanBIMS/busStopArrByBstopidLineid?bstopid="+bstline+"&lineid="+location+"&serviceKey="+key;
        try {
            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url 위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is,"UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType=xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기
                        if(tag.equals("item"));
                        xpp.next();

                        if (tag.equals("nodenm")) {
                            bsname = xpp.getText();
                            buffer.append("정류소명 :");
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                            xpp.next();
                        }
                        else if (tag.equals("lineno")) {
                            bsname = xpp.getText();
                            buffer.append("버스번호 :");
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                            xpp.next();
                        }
                        else if (tag.equals("min1")) {
                            bsname = xpp.getText();
                            buffer.append("1번쨰 버스 :");
                            buffer.append(xpp.getText()+"분");
                            if(xpp.getText().equals(null)&&xpp.getText().equals(""))
                            {
                                buffer.append("차고지대기");
                            }
                            buffer.append("\n\n");
                            xpp.next();
                        }
                        else if (tag.equals("station1")) {
                            bsname = xpp.getText();
                            buffer.append("1번쨰 버스 남은 정류장 :");
                            buffer.append(xpp.getText()+"정류장");
                            buffer.append("\n\n");
                            xpp.next();
                        }
                        else if (tag.equals("lowplate1")) {
                            bsname = xpp.getText();
                            buffer.append("버스타입:");
                            buffer.append(xpp.getText());

                            buffer.append("\n\n");
                            xpp.next();
                        }
//                        else if (tag.equals("min2")) {
//                            bstid = xpp.getText();
//                            xpp.next();
//                        }
                        else if (tag.equals("gpsx")) {
                            dex=Double.parseDouble(xpp.getText());

                            xpp.next();
                        }
                        else if (tag.equals("gpsy")) {
                            dey=Double.parseDouble(xpp.getText());

                            xpp.next();
                        }
//                        else if (tag.equals("station2")) {
//                            bsname = xpp.getText();
//                            xpp.next();
//                        }
//                        else if (tag.equals("lowplate2")) {
//                            bsname = xpp.getText();
//                            xpp.next();
//                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기
                        if(tag.equals("item")) buffer.append("\n");  //첫번째 검색결과 종료..줄바꿈
                        break;
                }
                eventType = xpp.next();
            }
        }catch (Exception e){
        }
        Log.i(TAG, "getXmlData3: "+buffer.toString());
        return buffer.toString(); //StringBuffer 문자열 객체 반환
    }
    public void mOnClick() {

        Log.i(TAG, "getXmlData2: "+bsto + "|abs|"+bstid);
        Log.i("loga5", "mOnClick: "+location+"abs"+bstid+"abs"+bsto);


            Button button4 = findViewById(R.id.reservation);
            button4.setVisibility(View.INVISIBLE);
                    //  customProgressDialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {


                            data2 = getXmlData2();
                            Log.i("loga2end", "mOnClick: "+bstline+"abs"+bstid+"abs"+bsto);
                            data3 = getXmlData3();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("logarun", "mOnClick: "+bstline+"abs"+bstid+bsto);
                                    text.setText(data3);
                                    button4.setVisibility(View.VISIBLE);

                                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                                    mapFragment.getMapAsync(search.this);
                                    //     customProgressDialog.dismiss();

//                                    if(count==2)
//                                    {
//                                        button2.setVisibility(View.VISIBLE);
//                                        button3.setVisibility(View.VISIBLE);
//                                    }else {
//                                        button2.setVisibility(View.INVISIBLE);
//                                        button3.setVisibility(View.INVISIBLE);
//                                    }

                                }

                            });

                        }
                    }).start();





    }
//
//      public void b1(View v) {
//        switch (v.getId()) {
////            case R.id.button2:
////                bstid = num[0];
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        data = getXmlData();
//                        data2 = getXmlData2();
//                        data3 = getXmlData3();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                text.setText(data3);
//                                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//                                mapFragment.getMapAsync(search.this);
//                            }
//                        });
//                    }
//                }).start();
//                break;
//        }
//    }
//    public void b2(View v) {
//        switch (v.getId()) {
//            case R.id.button3:
//                bstid = num[1];
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        data = getXmlData();
//                        data2 = getXmlData2();
//                        data3 = getXmlData3();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                text.setText(data3);
//                                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//                                mapFragment.getMapAsync(search.this);
//                            }
//                        });
//                    }
//                }).start();
//                break;
//        }
//    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;


        LatLng location = new LatLng(dey,dex);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        MarkerOptions markerOptions = new MarkerOptions().position(location);
        googleMap.addMarker(markerOptions);

//        gpsTracker = new GpsTracker(search.this);
//        double latitude = gpsTracker.getLatitude();
//        double longitude = gpsTracker.getLongitude();
//        String lat = String.valueOf(latitude);
//        String longg = String.valueOf(longitude);
//        double EARTH_R, Rad, radLat1, radLat2, radDist;
//        double distance, ret;
//
//        EARTH_R = 6371000.0;
//        Rad = Math.PI/180;
//        radLat1 = Rad * dey;
//        radLat2 = Rad * latitude;
//        radDist = Rad * (dex - longitude);
//
//        distance = Math.sin(radLat1) * Math.sin(radLat2);
//
//        distance = distance + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radDist);
//
//        ret = EARTH_R * Math.acos(distance);
//
//        double rslt = Math.round(Math.round(ret) / 1000);
//        String result = String.valueOf(rslt);
//
//        result = Math.round(ret) +" m";
//
//
//        textView.setText(result);



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
                                ActivityCompat.requestPermissions(search.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);

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

    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

    }
}
