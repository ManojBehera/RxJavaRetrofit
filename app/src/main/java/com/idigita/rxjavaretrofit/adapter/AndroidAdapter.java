package com.idigita.rxjavaretrofit.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idigita.rxjavaretrofit.R;
import com.idigita.rxjavaretrofit.network.model.Android;

import java.util.List;

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.ViewHolder> {
    private static final String TAG = AndroidAdapter.class.getName();
    private List<Android> list;
    public AndroidAdapter(List<Android> list){
        this.list = list;
    }
    @NonNull
    @Override
    public AndroidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AndroidAdapter.ViewHolder viewHolder, int position) {
        Android android = list.get(position);
        viewHolder.name.setText(android.getName());
        viewHolder.ver.setText(android.getVer());
        viewHolder.api.setText(android.getApi());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, ver, api;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            ver = (TextView) itemView.findViewById(R.id.ver);
            api = (TextView) itemView.findViewById(R.id.api);
        }
    }
}
