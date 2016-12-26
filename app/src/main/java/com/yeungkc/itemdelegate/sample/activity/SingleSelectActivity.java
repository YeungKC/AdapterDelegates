package com.yeungkc.itemdelegate.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yeungkc.itemdelegate.ItemDelegatesManager;
import com.yeungkc.itemdelegate.ListDelegationAdapter;
import com.yeungkc.itemdelegate.sample.R;
import com.yeungkc.itemdelegate.sample.bean.Company;
import com.yeungkc.itemdelegate.sample.item_delegate.CompanyItemDelegate;

import java.util.ArrayList;
import java.util.List;

public class SingleSelectActivity extends AppCompatActivity {

    private CompanyItemDelegate mCompanyItemDelegate;
    private ArrayList<Company> mDataSets;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SingleSelectActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_and_fab);

        ItemDelegatesManager<List<Company>> listItemDelegatesManager = new ItemDelegatesManager<>();
        mCompanyItemDelegate = new CompanyItemDelegate();
        listItemDelegatesManager.addDelegate(mCompanyItemDelegate);

        ListDelegationAdapter<List<Company>> adapter = new ListDelegationAdapter<>(listItemDelegatesManager);
        adapter.setHasStableIds(true);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);

        mDataSets = initData();

        adapter.setDataSets(mDataSets);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);

        initFab();
    }

    private ArrayList<Company> initData() {
        ArrayList<Company> companies = new ArrayList<>();

        for (int i = 0; i < 233; i++) {
            companies.add(new Company("Company " + i));
        }

        return companies;
    }

    private void initFab() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        SingleSelectActivity.this,
                        mDataSets.get(mCompanyItemDelegate.getCurrentPosition()).getName()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
