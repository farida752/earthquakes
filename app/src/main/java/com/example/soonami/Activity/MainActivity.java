package com.example.soonami.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.soonami.Adapter.EventAdapter;
import com.example.soonami.Model.Event;
import com.example.soonami.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
public static final String TAG="MainActivity";

    RecyclerView eventRecyclerView;
    EventAdapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayout;
    SharedPreferences prefs;
    boolean state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        eventRecyclerView=findViewById(R.id.event_recyclerView);
        recyclerLayout=new LinearLayoutManager(this);
        prefs= getSharedPreferences("login", Context.MODE_PRIVATE);


      /* String jsonReqest = "{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1600790394000,\"url\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?minmagnitude=6&format=geojson\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.10.3\",\"count\":18},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":6.9,\"place\":\"central Mid-Atlantic Ridge\",\"time\":1600465438936,\"updated\":1600639252455,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bq10\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bq10&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":3.454,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":732,\"net\":\"us\",\"code\":\"7000bq10\",\"ids\":\",pt20262001,at00qgvj1d,us7000bq10,\",\"sources\":\",pt,at,us,\",\"types\":\",impact-link,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":11.257,\"rms\":0.66,\"gap\":16,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.9 - central Mid-Atlantic Ridge\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-26.8408,0.9167,10]},\"id\":\"us7000bq10\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"160 km NNE of Labasa, Fiji\",\"time\":1600143136407,\"updated\":1600229871864,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bndc\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bndc&format=geojson\",\"felt\":1,\"cdi\":5.2,\"mmi\":6.477,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":554,\"net\":\"us\",\"code\":\"7000bndc\",\"ids\":\",us7000bndc,\",\"sources\":\",us,\",\"types\":\",dyfi,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.028,\"rms\":0.84,\"gap\":35,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 160 km NNE of Labasa, Fiji\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[179.9438,-15.093,10]},\"id\":\"us7000bndc\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.4,\"place\":\"15 km W of Esso, Russia\",\"time\":1600141288012,\"updated\":1600315901155,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bnd1\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bnd1&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":3.197,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":630,\"net\":\"us\",\"code\":\"7000bnd1\",\"ids\":\",us7000bnd1,pt20259000,at00qgokx3,\",\"sources\":\",us,pt,at,\",\"types\":\",ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.858,\"rms\":0.73,\"gap\":29,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.4 - 15 km W of Esso, Russia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[158.4539,55.9268,344]},\"id\":\"us7000bnd1\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.1,\"place\":\"57 km SE of Ōfunato, Japan\",\"time\":1599878650969,\"updated\":1600052462354,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bm9m\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bm9m&format=geojson\",\"felt\":17,\"cdi\":4.3,\"mmi\":4.173,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":580,\"net\":\"us\",\"code\":\"7000bm9m\",\"ids\":\",us7000bm9m,at00qgiy9o,pt20256001,\",\"sources\":\",us,at,pt,\",\"types\":\",dyfi,ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.223,\"rms\":0.95,\"gap\":47,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.1 - 57 km SE of Ōfunato, Japan\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[142.2473,38.7591,32.09]},\"id\":\"us7000bm9m\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.3,\"place\":\"83 km NNE of Tocopilla, Chile\",\"time\":1599809757894,\"updated\":1599984253078,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000blm2\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000blm2&format=geojson\",\"felt\":147,\"cdi\":6.8,\"mmi\":7.213,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":711,\"net\":\"us\",\"code\":\"7000blm2\",\"ids\":\",us7000blm2,at00qghh3w,pt20255000,\",\"sources\":\",us,at,pt,\",\"types\":\",dyfi,ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.072,\"rms\":1.39,\"gap\":41,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.3 - 83 km NNE of Tocopilla, Chile\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-69.8943,-21.3928,51]},\"id\":\"us7000blm2\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"73 km NNE of Port-Vila, Vanuatu\",\"time\":1599459160093,\"updated\":1599633329105,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bj6y\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bj6y&format=geojson\",\"felt\":3,\"cdi\":3.4,\"mmi\":6.226,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":555,\"net\":\"us\",\"code\":\"7000bj6y\",\"ids\":\",at00qg9yl1,pt20251000,us7000bj6y,\",\"sources\":\",at,pt,us,\",\"types\":\",dyfi,ground-failure,impact-link,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.039,\"rms\":1.35,\"gap\":29,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 73 km NNE of Port-Vila, Vanuatu\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[168.476,-17.0893,10]},\"id\":\"us7000bj6y\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.3,\"place\":\"17 km E of Talagutong, Philippines\",\"time\":1599405823148,\"updated\":1599758261108,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000biyd\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000biyd&format=geojson\",\"felt\":37,\"cdi\":7.2,\"mmi\":4.468,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":637,\"net\":\"us\",\"code\":\"7000biyd\",\"ids\":\",at00qg8tfl,pt20250007,us7000biyd,\",\"sources\":\",at,pt,us,\",\"types\":\",dyfi,ground-failure,impact-link,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.832,\"rms\":1.22,\"gap\":29,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.3 - 17 km E of Talagutong, Philippines\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[125.8285,6.2693,120]},\"id\":\"us7000biyd\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.7,\"place\":\"central Mid-Atlantic Ridge\",\"time\":1599375079004,\"updated\":1599489201287,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000biu8\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000biu8&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":0,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":691,\"net\":\"us\",\"code\":\"7000biu8\",\"ids\":\",pt20250004,at00qg85pi,us7000biu8,\",\"sources\":\",pt,at,us,\",\"types\":\",impact-link,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":22.623,\"rms\":0.74,\"gap\":39,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.7 - central Mid-Atlantic Ridge\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-37.2174,7.6929,10]},\"id\":\"us7000biu8\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.2,\"place\":\"101 km NW of Port-Vila, Vanuatu\",\"time\":1599361155559,\"updated\":1599534053041,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000birm\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000birm&format=geojson\",\"felt\":4,\"cdi\":5,\"mmi\":4.195,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":593,\"net\":\"us\",\"code\":\"7000birm\",\"ids\":\",pt20250002,at00qg7uyr,us7000birm,\",\"sources\":\",pt,at,us,\",\"types\":\",dyfi,ground-failure,impact-link,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":1.731,\"rms\":1.19,\"gap\":54,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.2 - 101 km NW of Port-Vila, Vanuatu\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[167.5791,-17.1497,8.24]},\"id\":\"us7000birm\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.3,\"place\":\"45 km NW of Ovalle, Chile\",\"time\":1599355018930,\"updated\":1599679303178,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000biqb\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000biqb&format=geojson\",\"felt\":111,\"cdi\":5.1,\"mmi\":6.286,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":667,\"net\":\"us\",\"code\":\"7000biqb\",\"ids\":\",us7000biqb,at00qg7q8a,pt20250001,\",\"sources\":\",us,at,pt,\",\"types\":\",dyfi,ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.342,\"rms\":1.04,\"gap\":86,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.3 - 45 km NW of Ovalle, Chile\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-71.5642,-30.3355,30.71]},\"id\":\"us7000biqb\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.5,\"place\":\"94 km NW of Vallenar, Chile\",\"time\":1598994557626,\"updated\":1599608987217,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bg4v\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bg4v&format=geojson\",\"felt\":52,\"cdi\":5.3,\"mmi\":6.056,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":678,\"net\":\"us\",\"code\":\"7000bg4v\",\"ids\":\",us7000bg4v,at00qg003e,pt20245003,\",\"sources\":\",us,at,pt,\",\"types\":\",dyfi,ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.39,\"rms\":0.86,\"gap\":49,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.5 - 94 km NW of Vallenar, Chile\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-71.3716,-27.9154,14.52]},\"id\":\"us7000bg4v\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.3,\"place\":\"Near the coast of Atacama, Chile\",\"time\":1598934602198,\"updated\":1600298958414,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bfjx\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bfjx&format=geojson\",\"felt\":4,\"cdi\":4.5,\"mmi\":6.396,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":612,\"net\":\"us\",\"code\":\"7000bfjx\",\"ids\":\",us7000bfjx,pt20245001,\",\"sources\":\",us,pt,\",\"types\":\",dyfi,ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.25,\"rms\":0.97,\"gap\":69,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.3 - Near the coast of Atacama, Chile\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-71.2874,-28.0385,16.53]},\"id\":\"us7000bfjx\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.8,\"place\":\"85 km NW of Vallenar, Chile\",\"time\":1598933368731,\"updated\":1600639289114,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bfjr\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bfjr&format=geojson\",\"felt\":260,\"cdi\":5.4,\"mmi\":6.751,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":852,\"net\":\"us\",\"code\":\"7000bfjr\",\"ids\":\",at00qfyovo,pt20245000,us7000bfjr,\",\"sources\":\",at,pt,us,\",\"types\":\",dyfi,finite-fault,ground-failure,impact-link,impact-text,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.307,\"rms\":0.85,\"gap\":69,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.8 - 85 km NW of Vallenar, Chile\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-71.3031,-27.9728,23]},\"id\":\"us7000bfjr\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.1,\"place\":\"Chagos Archipelago region\",\"time\":1598894644946,\"updated\":1599595993905,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bfbx\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bfbx&format=geojson\",\"felt\":7,\"cdi\":4.3,\"mmi\":0,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":575,\"net\":\"us\",\"code\":\"7000bfbx\",\"ids\":\",us7000bfbx,pt20244000,at00qfxuzz,\",\"sources\":\",us,pt,at,\",\"types\":\",dyfi,ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":4.041,\"rms\":1.28,\"gap\":22,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.1 - Chagos Archipelago region\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[70.1973,-4.0158,10]},\"id\":\"us7000bfbx\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.5,\"place\":\"central Mid-Atlantic Ridge\",\"time\":1598822429757,\"updated\":1599776581234,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bf3k\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bf3k&format=geojson\",\"felt\":2,\"cdi\":4.3,\"mmi\":4.817,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":651,\"net\":\"us\",\"code\":\"7000bf3k\",\"ids\":\",us7000bf3k,at00qfwba7,pt20243000,\",\"sources\":\",us,at,pt,\",\"types\":\",dyfi,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":15.394,\"rms\":0.66,\"gap\":31,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.5 - central Mid-Atlantic Ridge\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-29.8656,0.7821,10]},\"id\":\"us7000bf3k\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.2,\"place\":\"141 km SSW of Kokopo, Papua New Guinea\",\"time\":1598382532908,\"updated\":1600438593910,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bcty\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bcty&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":6.299,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":591,\"net\":\"us\",\"code\":\"7000bcty\",\"ids\":\",us7000bcty,pt20238005,\",\"sources\":\",us,pt,\",\"types\":\",ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":1.25,\"rms\":0.73,\"gap\":18,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.2 - 141 km SSW of Kokopo, Papua New Guinea\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[151.8365,-5.5454,22]},\"id\":\"us7000bcty\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"141 km SSW of Kokopo, Papua New Guinea\",\"time\":1598382178428,\"updated\":1600438421952,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bctq\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bctq&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":5.965,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":554,\"net\":\"us\",\"code\":\"7000bctq\",\"ids\":\",us7000bctq,pt20238004,\",\"sources\":\",us,pt,\",\"types\":\",ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":1.261,\"rms\":0.71,\"gap\":16,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 141 km SSW of Kokopo, Papua New Guinea\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[151.8642,-5.5603,23]},\"id\":\"us7000bctq\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"4 km S of Jacó, Costa Rica\",\"time\":1598305869145,\"updated\":1598827729227,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000bcbv\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000bcbv&format=geojson\",\"felt\":106,\"cdi\":5.7,\"mmi\":5.362,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":614,\"net\":\"us\",\"code\":\"7000bcbv\",\"ids\":\",us7000bcbv,pt20237000,at00qfl8p6,\",\"sources\":\",us,pt,at,\",\"types\":\",dyfi,ground-failure,internal-origin,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.638,\"rms\":1.3,\"gap\":84,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 4 km S of Jacó, Costa Rica\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-84.6308,9.5749,19.43]},\"id\":\"us7000bcbv\"}],\"bbox\":[-84.6308,-30.3355,8.24,179.9438,55.9268,344]}";
*/
      //String usgsRequest = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&&minmagnitude=5";
        String usgsRequest =createString();
     URL url=createUrl ( usgsRequest );
     new EventAsyncTask ().execute ( url );
        //UpdateData(ExtractFeatureFromJson(jsonReqest));
    }

    public String createString(){
        SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
        String requiredMag= prefs.getString(SettingsActivity.KEY_MAGNITUDE,"5");
        state = prefs.getBoolean(SettingsActivity.KEY_STATE,false);
        String usgsRequest="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&&minmagnitude="+requiredMag ;
       // Log.i(TAG,usgsRequest);
        //String usgsRequest = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&&minmagnitude=5";
        return usgsRequest;
    }

  public URL createUrl  (String usgsRequest) {
      URL url = null;
      try {
          url = new URL ( usgsRequest );
      } catch (MalformedURLException e) {
          e.printStackTrace ();
      }
      return url;
  }

public String makeHttpRequest(URL url){
        String jsonResponse="";
    HttpURLConnection httpURLConnection=null;
    InputStream in =null;

    try {
        httpURLConnection=(HttpURLConnection)url.openConnection ();
        httpURLConnection.setRequestMethod ( "GET" );
        httpURLConnection.setConnectTimeout (15000 );
        httpURLConnection.setReadTimeout ( 10000 );
        httpURLConnection.connect ();

        if(httpURLConnection.getResponseCode ()==200){
            in=httpURLConnection.getInputStream ();
            jsonResponse=readFromStream ( in );
        }else{
            Log.i(TAG,"conection Error"+httpURLConnection.getResponseCode ());
        }
    } catch (IOException e) {
        e.printStackTrace ();
    }
    finally {
        if(httpURLConnection!=null)
            httpURLConnection.disconnect ();
        if(in!=null) {
            try {
                in.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
return jsonResponse;
}

public String readFromStream (InputStream in){
    BufferedReader reader =new BufferedReader ( new InputStreamReader ( in, Charset.forName ( "utf-8" ) ) );
    StringBuffer stringBuffer= new StringBuffer ();
    String data= null;
    try {
        while ((data=reader.readLine ())!=null){
            stringBuffer.append ( data );
        }
    } catch (IOException e) {
        e.printStackTrace ();
    } finally {
        try {
            reader.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
    return stringBuffer.toString ();
}


public ArrayList<Event> ExtractFeatureFromJson(String jsonRequest){
        ArrayList<Event> events=new ArrayList<>();
    try {
      JSONObject root=new JSONObject (jsonRequest);
      JSONArray features=root.getJSONArray ("features"  );
      for(int i=0;i<features.length();i++) {
          JSONObject currentObject = features.getJSONObject(i);
          JSONObject properties = currentObject.getJSONObject("properties");
          String place = properties.getString("place");
          int tusunami = properties.getInt("tsunami");
          long time = properties.getLong("time");
          double magnitude = properties.getDouble("mag");
          String url=properties.getString("url");
          events.add(new Event(time, place, tusunami, magnitude,url));
           // Log.i(TAG,events.get(i).toString ());
      }

    } catch (JSONException e) {
        e.printStackTrace ();
    }
    return events;
}

public void UpdateData(final ArrayList<Event> events){

    recyclerAdapter=new EventAdapter(events);
    eventRecyclerView.setAdapter(recyclerAdapter);
    eventRecyclerView.setLayoutManager(recyclerLayout);
    recyclerAdapter.setListener(new EventAdapter.onItemClickListener() {
        @Override
        public void onArrowClick(boolean isExpandable, int position) {
            if(isExpandable){
                recyclerAdapter.data.get(position).setIsExpandable(false);
                recyclerAdapter.notifyDataSetChanged();

            }
            else {
                recyclerAdapter.data.get(position).setIsExpandable(true);
                recyclerAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onSeeMoreClick(String url,int position) {
         Intent intent=new Intent(Intent.ACTION_VIEW);
         intent.setData(Uri.parse(url));
         if(intent.resolveActivity(getPackageManager())!=null){
             startActivity(intent);
         }

        }
    }) ;

}

class EventAsyncTask extends AsyncTask<URL,Void,String>{

    @Override
    protected String doInBackground(URL... urls) {
        String jsonReqest=makeHttpRequest ( urls[0] );
        return jsonReqest;
    }

    @Override
    protected void onPostExecute(String jsonReqest) {
        super.onPostExecute ( jsonReqest );
      // ArrayList<Event> events= ExtractFeatureFromJson ( jsonReqest );
      // UpdateData(events);
       UpdateData (ExtractFeatureFromJson ( jsonReqest )  );

    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.settings_item:
                Intent intent=new Intent(this,SettingsActivity.class);
                intent.putExtra(TAG,state);
                //startActivityForResult(intent,1);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String usgsRequest =createString();
        URL url=createUrl ( usgsRequest );
        new EventAsyncTask ().execute ( url );
    }
}