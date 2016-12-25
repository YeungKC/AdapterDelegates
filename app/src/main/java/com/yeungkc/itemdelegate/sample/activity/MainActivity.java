package com.yeungkc.itemdelegate.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yeungkc.itemdelegate.sample.R;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.multi_type).setOnClickListener(mListener);
        findViewById(R.id.multi_type_span).setOnClickListener(mListener);
        findViewById(R.id.single_select).setOnClickListener(mListener);
        findViewById(R.id.multi_select).setOnClickListener(mListener);
        findViewById(R.id.rv_nested).setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.multi_type:
                    MultiTypeActivity.startActivity(MainActivity.this, 1);
                    break;
                case R.id.multi_type_span:
                    MultiTypeActivity.startActivity(MainActivity.this, 2);
                    break;
                case R.id.multi_select:
                    MultiSelectActivity.startActivity(MainActivity.this);
                    break;
                case R.id.single_select:
                    SingleSelectActivity.startActivity(MainActivity.this);
                    break;
                case R.id.rv_nested:
                    RecyclerViewNestedActivity.startActivity(MainActivity.this);
                    break;
            }
        }
    };
}
