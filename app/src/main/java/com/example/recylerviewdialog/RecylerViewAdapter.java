package com.example.recylerviewdialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHodler> {

    private Context context;

    public RecylerViewAdapter() {
    }

    public List<Bean> list;

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyler_item_layout,parent,false);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        if (list != null && list.size() >0) {
            holder.contentItem.setText(list.get(position).getContentItem());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private TextView contentItem;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            contentItem = itemView.findViewById(R.id.item_text);
        }
    }
}
