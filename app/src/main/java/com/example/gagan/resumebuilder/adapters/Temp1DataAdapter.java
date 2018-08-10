package com.example.gagan.resumebuilder.adapters;

import android.media.MediaDataSource;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.resumebuilder.R;
import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;
import com.example.gagan.resumebuilder.models.DataInfo;
import com.example.gagan.resumebuilder.models.Header;

import java.util.List;

public class Temp1DataAdapter extends RecyclerView.Adapter<Temp1DataAdapter.VH> {
    private final List<Temp1BaseModel> mDataset;

    public Temp1DataAdapter(List<Temp1BaseModel> modelList) {
        this.mDataset = modelList;
    }

    @Override
    public Temp1DataAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, null, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(Temp1DataAdapter.VH holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position) instanceof Header ?
                        R.layout.header_info : R.layout.data_info;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView header, info, tv_year;
        FloatingActionButton button;
        ImageView image;
        ImageView iv_delete;
        private Temp1BaseModel tempModel;

        public VH(View itemView) {
            super(itemView);
        }

        public void bind(Temp1BaseModel temp1BaseModel) {
            if (temp1BaseModel instanceof Header) {
                header = itemView.findViewById(R.id.tv_header_info);
                image = itemView.findViewById(R.id.image);
                button = itemView.findViewById(R.id.ic_add);
                button.setOnClickListener(this);
                header.setText(((Header) temp1BaseModel).getTitle());
                image.setImageDrawable(itemView.getContext().getResources()
                        .getDrawable(((Header) temp1BaseModel).getDrawable()));
            } else if (temp1BaseModel instanceof DataInfo) {
                DataInfo obj = (DataInfo) temp1BaseModel;
                this.tempModel = temp1BaseModel;
                header = itemView.findViewById(R.id.heading);
                info = itemView.findViewById(R.id.info);
                tv_year = itemView.findViewById(R.id.tv_year);
                iv_delete = itemView.findViewById(R.id.iv_delete);
                header.setOnClickListener(this);
                info.setOnClickListener(this);
                tv_year.setOnClickListener(this);

                header.setText(obj.getHeader());
                info.setText(obj.getDescription());
                tv_year.setText(obj.getYear());
                iv_delete.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            DataInfo obj = (DataInfo) tempModel;
            switch (v.getId()) {
                case R.id.heading:
                    obj.setHeader(header.getText().toString());
                    break;
                case R.id.info:
                    obj.setDescription(info.getText().toString());
                    break;
                case R.id.tv_year:
                    obj.setYear(tv_year.getText().toString());
                    break;
                case R.id.ic_add:
                    mDataset.add(new DataInfo());
                    Temp1DataAdapter.this.notifyDataSetChanged();
                    ;
                    break;
                case R.id.iv_delete:
                    mDataset.remove(getAdapterPosition());
                    Temp1DataAdapter.this.notifyDataSetChanged();
            }
        }
    }
}
