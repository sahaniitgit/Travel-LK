package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        LinearLayout layout=findViewById(R.id.linear_layout);
        Button back=findViewById(R.id.button3);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //getting the information from the previous activity
        Bundle extras=getIntent().getExtras();
        String name=extras.getString("namekey");
        String imageurl=extras.getString("imagekey");
        String info=extras.getString("descriptionkey");

        //setting the views
        TextView txt = new TextView (ThirdActivity.this);
        TextView txt2= new TextView(ThirdActivity.this);
        ImageView image= new ImageView(ThirdActivity.this);
        TextView empty=new TextView(ThirdActivity.this);
        txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        empty.setHeight(10);
        empty.setText("  ");
        txt.setTextSize(20);
        txt.setTypeface(null, Typeface.BOLD);
        txt.setTextColor(Color.parseColor("#000000"));
        txt.setWidth(width);
        txt2.setWidth(width);
        txt2.setTextSize(15);
        txt2.setTextColor(Color.parseColor("#000000"));
        txt.setText(name);
        Picasso.get().load(imageurl).resize(width,650).into(image);
        txt2.setText(info);
        layout.addView(txt);
        layout.addView(empty);
        layout.addView(image);
        layout.addView(txt2);
    }
}