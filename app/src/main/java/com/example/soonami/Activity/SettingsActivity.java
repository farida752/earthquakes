package com.example.soonami.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.soonami.R;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences prefs;
    EditText magnitude;
    ImageView check;
    boolean isChecked;
    public static final String KEY_MAGNITUDE="com.example.soonami.Activity.KEY_MAGNITUDE";
    public static final String  KEY_STATE="com.example.soonami.Activity.KEY_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        prefs=getSharedPreferences("login", Context.MODE_PRIVATE);
        magnitude=findViewById(R.id.edit_text_mag);
        check=findViewById(R.id.check_image);

        Intent intent=getIntent();


        isChecked=intent.getBooleanExtra(MainActivity.TAG,false);
        if(isChecked){
            check.setImageResource(R.drawable.ic_green_check);
        }
        else {
            check.setImageResource(R.drawable.ic_graycheck);
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=prefs.edit();
                if(isChecked==false){
                    String mag=magnitude.getText().toString();
                    if(mag.equals("")==false){
                    isChecked=true;
                    check.setImageResource(R.drawable.ic_green_check);
                    editor.putString(KEY_MAGNITUDE,mag);
                    editor.putBoolean(KEY_STATE,true);
                    editor.commit();}
                    else{
                        Toast.makeText(SettingsActivity.this, "you didn't enter magnitude", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    isChecked=false;
                    check.setImageResource(R.drawable.ic_graycheck);
                    editor.clear();
                    editor.commit();
                }
            }
        });

    }
}