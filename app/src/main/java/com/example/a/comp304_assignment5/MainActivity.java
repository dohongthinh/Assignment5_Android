package com.example.a.comp304_assignment5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView city = (ListView) findViewById(R.id.cityListView);
        city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get city name
                String cityName = ((TextView)view).getText().toString();


                //
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("cityName", cityName);
                startActivity(intent);
            }
        });
    }
}
