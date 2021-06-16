package com.srilatha.ClientSideExample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.srilatha.ClientSideExample.R;
import com.srilatha.ClientSideExample.adapter.CatalogueAdapter;
import com.srilatha.ClientSideExample.bean.CatalogueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseMainActivity
 *
 * @author 林zero
 * @date 2019/3/16
 */
public abstract class BaseMainActivity extends BaseActivity {
    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter.notifyDataSetChanged();
    }

    RecyclerView recyclerView;
    protected List<CatalogueBean> list = new ArrayList<>();
    protected CatalogueAdapter adapter;

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recycler_view);

        initAdapter();
    }

    private void initAdapter() {
        adapter = new CatalogueAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
