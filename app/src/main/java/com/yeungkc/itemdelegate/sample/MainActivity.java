package com.yeungkc.itemdelegate.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
//    com.yeungkc.itemdelegates.sample.MainAdapter adapter = new com.yeungkc.itemdelegates.sample.MainAdapter(this, getAnimals());
//    rv.setAdapter(adapter);


        findViewById(R.id.reptielsActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, com.yeungkc.itemdelegates.sample.ReptilesActivity.class));
            }
        });
    }
}
