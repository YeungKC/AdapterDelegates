package com.yeungkc.itemdelegate.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeungkc.itemdelegate.ListDelegationAdapter;
import com.yeungkc.itemdelegate.ListItemDelegatesManager;
import com.yeungkc.itemdelegate.sample.R;
import com.yeungkc.itemdelegate.sample.bean.Post;
import com.yeungkc.itemdelegate.sample.item_delegate.RvItemDelegate;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewNestedActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RecyclerViewNestedActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        ListItemDelegatesManager<List<Post>> listItemDelegatesManager = new ListItemDelegatesManager<>();
        listItemDelegatesManager.addDelegate(new RvItemDelegate());

        ListDelegationAdapter<List<Post>> adapter = new ListDelegationAdapter<>(listItemDelegatesManager);
        adapter.setHasStableIds(true);

        LinearLayoutManager layout = new LinearLayoutManager(this);

        ArrayList<List<Post>> dataSets = initData();

        adapter.setDataSets(dataSets);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
    }

    private ArrayList<List<Post>> initData() {
        ArrayList<List<Post>> lists = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ArrayList<Post> posts = new ArrayList<>();

            for (int j = 0; j < 9; j++) {
                posts.add(new Post(R.mipmap.ic_launcher, String.valueOf(i) + " " +String.valueOf(j)));
            }

            lists.add(posts);
        }

        return lists;
    }
}
