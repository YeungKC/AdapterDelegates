package com.yeungkc.itemdelegate.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yeungkc.itemdelegate.ItemDelegatesManager;
import com.yeungkc.itemdelegate.ListDelegationAdapter;
import com.yeungkc.itemdelegate.sample.R;
import com.yeungkc.itemdelegate.sample.bean.Category;
import com.yeungkc.itemdelegate.sample.bean.Episode;
import com.yeungkc.itemdelegate.sample.item_delegate.CategoryItemDelegate;
import com.yeungkc.itemdelegate.sample.item_delegate.EpisodeItemDelegate;

import java.util.ArrayList;
import java.util.List;

public class MultiSelectActivity extends AppCompatActivity {

    private EpisodeItemDelegate mEpisodeItemDelegate;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MultiSelectActivity.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_and_fab);

        ItemDelegatesManager<List<Object>> listItemDelegatesManager = new ItemDelegatesManager<>();
        mEpisodeItemDelegate = new EpisodeItemDelegate();
        listItemDelegatesManager.addDelegate(new CategoryItemDelegate())
                .addDelegate(mEpisodeItemDelegate);

        ListDelegationAdapter<List<Object>> adapter = new ListDelegationAdapter<>(listItemDelegatesManager);
        adapter.setHasStableIds(true);

        GridLayoutManager layout = new GridLayoutManager(this, 5);
        layout.setSpanSizeLookup(adapter.getSpanSizeLookup(layout.getSpanCount()));

        ArrayList<Object> dataSets = initData();

        adapter.setItems(dataSets);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);

        initFab();
    }

    private ArrayList<Object> initData() {
        ArrayList<Object> dataSets = new ArrayList<>();

        dataSets.add(new Category("第一季"));
        for (int i = 1; i <= 10; i++) {
            dataSets.add(new Episode("S01 " + i));
        }

        dataSets.add(new Category("第二季"));
        for (int i = 1; i <= 300; i++) {
            dataSets.add(new Episode("S02 " + i));
        }

        return dataSets;
    }

    private void initFab() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder content = new StringBuilder();
                for (String name : mEpisodeItemDelegate.getSelectedSet()) {
                    content.append(name).append(" ");
                }

                Toast.makeText(view.getContext(), content, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
