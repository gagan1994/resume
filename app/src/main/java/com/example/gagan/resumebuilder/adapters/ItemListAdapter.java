package com.example.gagan.resumebuilder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.resumebuilder.R;
import com.example.gagan.resumebuilder.interfaces.EditCallBack;
import com.example.gagan.resumebuilder.models.ItemList;
import com.example.gagan.resumebuilder.utils.Utils;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.Holder> {
    public final List<String> mDataset;
    private final View.OnClickListener onClickItem;
    String nullString = "asdjasldjsakldjaljd";

    public ItemListAdapter(List<String> list, View.OnClickListener viewOnClick) {
        mDataset = list;
        this.onClickItem=viewOnClick;


    }

    @Override
    public ItemListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, null
                , false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(ItemListAdapter.Holder holder, int position) {
        if (mDataset.get(position).equalsIgnoreCase(nullString)) {
            holder.displayAdd();
        } else {
            holder.addItem(mDataset.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (mDataset.get(position).equalsIgnoreCase(nullString))
                ? R.layout.add_layout : R.layout.item_list;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btnAdd;

        public Holder(View itemView) {
            super(itemView);
        }

        public void displayAdd() {
            btnAdd = itemView.findViewById(R.id.add_btn);
            btnAdd.setOnClickListener(onClickItem);
        }

        public void addItem(final String s) {
            textView = itemView.findViewById(R.id.id_item_val);
            textView.setText(s);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDataset.remove(s);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
