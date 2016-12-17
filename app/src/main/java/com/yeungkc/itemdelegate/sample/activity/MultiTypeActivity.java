package com.yeungkc.itemdelegate.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeungkc.itemdelegate.ItemDelegatesManager;
import com.yeungkc.itemdelegate.ListDelegationAdapter;
import com.yeungkc.itemdelegate.sample.bean.Category;
import com.yeungkc.itemdelegate.sample.item_delegate.CategoryItemDelegate;
import com.yeungkc.itemdelegate.sample.bean.Post;
import com.yeungkc.itemdelegate.sample.item_delegate.PostItemDelegate;
import com.yeungkc.itemdelegate.sample.R;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeActivity extends AppCompatActivity {

    public static final String SPAN_COUNT_KEY = "SPAN_COUNT_KEY";

    private int mSpanCount = 1;

    public static void startActivity(Context context, int spanCount) {
        Intent intent = new Intent(context, MultiTypeActivity.class);
        intent.putExtra(SPAN_COUNT_KEY, spanCount);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        mSpanCount = getIntent().getIntExtra(SPAN_COUNT_KEY, mSpanCount);

        ItemDelegatesManager<List<Object>> listItemDelegatesManager = new ItemDelegatesManager<>();
        listItemDelegatesManager.addDelegate(new CategoryItemDelegate());
        listItemDelegatesManager.addDelegate(new PostItemDelegate());

        ListDelegationAdapter<List<Object>> adapter = new ListDelegationAdapter<>(listItemDelegatesManager);
        adapter.setHasStableIds(true);

        GridLayoutManager layout = new GridLayoutManager(this, mSpanCount);
        layout.setSpanSizeLookup(adapter.getSpanSizeLookup(layout.getSpanCount()));

        ArrayList<Object> dataSets = initData();

        adapter.setItems(dataSets);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
    }

    @NonNull
    private ArrayList<Object> initData() {
        ArrayList<Object> dataSets = new ArrayList<>();

        int position = 0;

        dataSets.add(new Category("title" + 0));
        for (int i = 0; i < 10; i++) {
            dataSets.add(new Post(R.mipmap.ic_launcher,position+++""));
        }

        dataSets.add(new Category("title" + 1));
        for (int i = 0; i < 10; i++) {
            dataSets.add(new Post(R.mipmap.ic_launcher,position+++""));
        }
        return dataSets;
    }
}
