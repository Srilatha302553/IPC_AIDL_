package com.srilatha.ClientSideExample.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srilatha.ClientSideExample.R;
import com.srilatha.ClientSideExample.bean.CatalogueBean;

import java.util.List;

public class CatalogueAdapter extends RecyclerView.Adapter<CatalogueAdapter.ViewHolder> {
    private Activity activity;
    private List<CatalogueBean> list;

    public CatalogueAdapter(Activity activity, List<CatalogueBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_catalogue, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.itemView.setOnClickListener(view -> {
            CatalogueBean catalogueBean = list.get(viewHolder.getAdapterPosition());
            Intent intent = new Intent(activity, catalogueBean.getClazz());
            if (catalogueBean.getBundle() != null) {
                intent.putExtras(catalogueBean.getBundle());
            }
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
