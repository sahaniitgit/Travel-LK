package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //getting the metrics of the displayed screen
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        LinearLayout layout;
        //arraylist which contains location names
        ArrayList<String> list = new ArrayList<String>();

        //arraylist which contains the image urls of the locations
        ArrayList<String> website= new ArrayList<String>();

        //arraylist which contains the description of the locations
        ArrayList<String> description= new ArrayList<String>();

        list.add("Crescat Boulevard- Colombo");
        list.add("Ruwanweli Maha seya");
        list.add("Thuparamaya");
        list.add("Ridi Viharaya");

        website.add("https://cinnamonweb.blob.core.windows.net/cinnamonweb-prd/dining_wellness/Crescat-shopping-exp-1090X610.jpg");
        website.add("https://mapio.net/images-p/25543374.jpg");
        website.add("https://www.attractionsinsrilanka.com/wp-content/uploads/2020/09/Thuparamaya.jpg");
        website.add("https://www.lanka-excursions-holidays.com/uploads/4/0/2/1/40216937/ridigama-1-900_orig.jpg");

        description.add("CrescatCrescatCrescatCrescatCrescatCrescatCrescatCrescat");
        description.add("RuwanweliseyaRuwanweliseyaRuwanweliseyaRuwanweliseyaRuwanweliseya");
        description.add("ThuparamayaThuparamayaThuparamayaThuparamayaThuparamayaThuparamaya");
        description.add("RidiViharayaRidiViharayaRidiViharayaRidiViharayaRidiViharayaRidiViharaya");

        layout= findViewById(R.id.linearlayout);

        for (int i = 0; i < list.size(); i++) {
            LinearLayout horizontal=new LinearLayout(this);
            horizontal.setOrientation(LinearLayout.HORIZONTAL);
            TextView txt = new TextView (SecondActivity.this);
            TextView emptycolumn= new TextView(SecondActivity.this);
            TextView emptyline= new TextView(SecondActivity.this);
            ShapeableImageView imageView= new ShapeableImageView(SecondActivity.this);
            Picasso.get().load(website.get(i)).resize(width/20*10,450).into(imageView);
            imageView.setClickable(true);
            imageView.setId(i);
            imageView.setShapeAppearanceModel(imageView.getShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED,50)
                    .build());
            txt.setMaxWidth(width/20*9);
            txt.setMinWidth(width/20*9);
            txt.setHeight(500);
            txt.setTextSize(20);
            txt.setText(list.get(i));
            txt.setTypeface(null, Typeface.BOLD);
            txt.setId(i);
            txt.setClickable(true);
            txt.setTextColor(Color.parseColor("#000000"));
            emptycolumn.setText("");
            emptycolumn.setWidth(width/20);
            emptycolumn.setHeight(500);
            emptyline.setWidth(width);
            emptyline.setHeight(10);
            emptyline.setText("      ");
            emptycolumn.setId(i);
            emptycolumn.setClickable(true);
            horizontal.addView(imageView);
            horizontal.addView(emptycolumn);
            horizontal.addView(txt);
            layout.addView(horizontal);
            layout.addView(emptyline);

            //setting the clickable areas
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedimage=imageView.getId();
                    Intent activity2Intent = new Intent(getApplicationContext(), ThirdActivity.class);
                    activity2Intent.putExtra("namekey",list.get(clickedimage));
                    activity2Intent.putExtra("imagekey",website.get(clickedimage));
                    activity2Intent.putExtra("descriptionkey",description.get(clickedimage));
                    startActivity(activity2Intent);
                }
            });
            emptycolumn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vw) {
                    int clickedspace=emptycolumn.getId();
                    Intent activity2Intent = new Intent(getApplicationContext(), ThirdActivity.class);
                    activity2Intent.putExtra("namekey",list.get(clickedspace));
                    activity2Intent.putExtra("imagekey",website.get(clickedspace));
                    activity2Intent.putExtra("descriptionkey",description.get(clickedspace));
                    startActivity(activity2Intent);
                }
            });
            txt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int clickedtxt=txt.getId();
                    Intent activity2Intent = new Intent(getApplicationContext(), ThirdActivity.class);
                    activity2Intent.putExtra("namekey",list.get(clickedtxt));
                    activity2Intent.putExtra("imagekey",website.get(clickedtxt));
                    activity2Intent.putExtra("descriptionkey",description.get(clickedtxt));
                    startActivity(activity2Intent);
                }
            });
        }
    }
}