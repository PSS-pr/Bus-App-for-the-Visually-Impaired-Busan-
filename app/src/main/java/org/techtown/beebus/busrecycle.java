package org.techtown.beebus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class busrecycle extends AppCompatActivity {

    final String key = "API KEY ";

    final String TAG = "MainActivity";

    //    public String dataKey = MyConfig.dataKey;
    private String requestUrl;
    ArrayList<Item> list = null;
    Item bus = null;
    RecyclerView recyclerView;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busrecycle);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
            Intent intent=getIntent();
         str=intent.getStringExtra("str");

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }
    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {


            String location = str;
            if(location.contains("-"))
            {
                location = location.replace("-","");
                location ="5200"+location+"00";
            }else if (Integer.parseInt(location) > 1000)
            {
                location = "520"+location+"000";
            }
            else if (Integer.parseInt(location) > 100)
            {
                location ="5200"+location+"000"; }
            else if (Integer.parseInt(location)  < 100)
            {
                location = "52000"+ location + "000";
            }
            String queryUrl="http://apis.data.go.kr/6260000/BusanBIMS/busInfoByRouteId?lineid="+location+"&serviceKey="+key;

            Log.i(TAG, "recycle 버스번호: "+location);
            requestUrl = "http://apis.data.go.kr/6260000/BusanBIMS/busInfoByRouteId?lineid="+location+"&serviceKey="+key;
            try {
                boolean b_bstopnm = false;
                boolean b_carno =false;
                boolean b_image =false;
                boolean b_stdid =false;
                boolean b_busnum =false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<Item>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("item") && bus != null) {
                                list.add(bus);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("item")){
                                bus = new Item();
                            }
                            if (parser.getName().equals("bstopnm")) {b_bstopnm = true; b_image =true; b_busnum=true;}
                            if (parser.getName().equals("carno")){ b_carno = true;}
                            if (parser.getName().equals("arsno")){ b_stdid =true;}
                            break;
                        case XmlPullParser.TEXT:
                            if(b_bstopnm){
                                bus.setBstopnm(parser.getText());
                                bus.setImage(R.drawable.a8888);
                                b_bstopnm = false;
                                b_image =false;

                            } else if(b_carno) {
                                bus.setImage(R.drawable.a8889);
                                bus.setCarno(parser.getText());
                                b_carno = false;
                            }
                            else if(b_stdid) {
                                bus.setBstdid(parser.getText());
                                b_stdid = false;
                            }  else if(b_busnum) {
                                bus.setBusnum(location);
                                b_stdid = false;
                            }
                            break;
                    }
                    eventType = parser.next();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MyAdapter adapter = new MyAdapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);
            //어답터 연결


        }
    }
}
