package com.tkelly.splitthebill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button even_split_btn = (Button) findViewById(R.id.even_split_btn);
        even_split_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EvenSplitActivity.class);
                startActivity(intent);
            }
        });

        Button split_btn = (Button) findViewById(R.id.split_btn);
        split_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SplitActivity.class);
                startActivity(intent);
            }
        });
    }

}
